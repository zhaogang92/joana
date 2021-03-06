package edu.kit.joana.ifc.sdg.irlsod;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import edu.kit.joana.ifc.sdg.core.IFC;
import edu.kit.joana.ifc.sdg.core.SecurityNode;
import edu.kit.joana.ifc.sdg.core.violations.IUnaryViolation;
import edu.kit.joana.ifc.sdg.core.violations.IViolation;
import edu.kit.joana.ifc.sdg.core.violations.UnaryViolation;
import edu.kit.joana.ifc.sdg.graph.SDG;
import edu.kit.joana.ifc.sdg.graph.SDGEdge;
import edu.kit.joana.ifc.sdg.graph.SDGNode;
import edu.kit.joana.ifc.sdg.graph.slicer.conc.I2PBackward;
import edu.kit.joana.ifc.sdg.lattice.IStaticLattice;
import edu.kit.joana.ifc.sdg.lattice.LatticeUtil;
import edu.kit.joana.ifc.sdg.lattice.NotInLatticeException;

public class ORLSODChecker<L> extends IFC<L> {

	/** the lattice which provides the security levels we annotate nodes with */
	protected IStaticLattice<L> secLattice;

	/** the SDG we want to check */
	protected SDG sdg;

	/** user-provided annotations */
	protected Map<SDGNode, L> userAnn;

	/** maps each node to its so-called <i>probabilistic influencers</i> */
	protected ProbInfComputer probInf;

	/** classification which is computed in a fixed-point iteration */
	protected Map<SDGNode, L> cl;

	/**
	 * When propagation security levels along the SDG (step "2.a"), use the whole slice, or just the sdg predecessors
	 **/
	protected final PredecessorMethod predecessorMethod;

	public ORLSODChecker(final SDG sdg, final IStaticLattice<L> secLattice,
			final ProbInfComputer probInf, final PredecessorMethod predecessorMethod) {
		this(sdg, secLattice, null, probInf, predecessorMethod);
	}
	
	public ORLSODChecker(final SDG sdg, final IStaticLattice<L> secLattice, final Map<SDGNode, L> userAnn,
			final ProbInfComputer probInf, final PredecessorMethod predecessorMethod) {
		super(sdg, secLattice);
		this.sdg = sdg;
		this.secLattice = secLattice;
		this.userAnn = userAnn;
		this.probInf = probInf;
		this.predecessorMethod = predecessorMethod;
	}

	protected Map<SDGNode, L> initCL(final boolean incorporateUserAnns) {
		final Map<SDGNode, L> ret = new HashMap<SDGNode, L>();
		for (final SDGNode n : sdg.vertexSet()) {
			if (incorporateUserAnns && userAnn.containsKey(n)) {
				ret.put(n, userAnn.get(n));
			} else {
				ret.put(n, secLattice.getBottom());
			}
		}
		return ret;
	}
	/**
	 * like initCL but assigns nothing and not bottom to non-user-annotated nodes
	 * @return user annotations as map
	 */
	protected Map<SDGNode, L> initCLPartial(final boolean incorporateUserAnns) {
		final Map<SDGNode, L> ret = new HashMap<SDGNode, L>();
		for (final SDGNode n : sdg.vertexSet()) {
			if (incorporateUserAnns && userAnn.containsKey(n)) {
				ret.put(n, userAnn.get(n));
			}
		}
		return ret;
	}

	protected final Collection<? extends IViolation<SecurityNode>> checkCompliance() {
		final LinkedList<IUnaryViolation<SecurityNode, L>> ret = new LinkedList<>();
		for (final Map.Entry<SDGNode, L> userAnnEntry : userAnn.entrySet()) {
			final SDGNode s = userAnnEntry.getKey();
			final L userLvl = userAnnEntry.getValue();
			if (!LatticeUtil.isLeq(secLattice, cl.get(s), userLvl)) {
				ret.add(new UnaryViolation<SecurityNode, L>(new SecurityNode(s), userLvl, cl.get(s)));
				System.out.println("Violation at node " + s + ": user-annotated level is " + userLvl
						+ ", computed level is " + cl.get(s));
			}
		}
		return ret;
	}

	/**
	 * Like checkCompliance, but the other way round: checkCompliance looks for nodes
	 * whose security levels are too high (i.e. low nodes which may be influenced
	 * by high nodes) - this method looks for nodes whose security levels are too low
	 * (i.e. high nodes which may influence low nodes). This method is to be used when doing
	 * backwards analysis, i.e. if the propagation is started from the sinks
	 * @return nodes whose levels do not comply
	 */
	protected final Collection<? extends IViolation<SecurityNode>> checkComplianceDual() {
		final LinkedList<IUnaryViolation<SecurityNode, L>> ret = new LinkedList<>();
		for (final Map.Entry<SDGNode, L> userAnnEntry : userAnn.entrySet()) {
			final SDGNode s = userAnnEntry.getKey();
			final L userLvl = userAnnEntry.getValue();
			if (!LatticeUtil.isLeq(secLattice, userLvl, cl.get(s))) {
				ret.add(new UnaryViolation<SecurityNode, L>(new SecurityNode(s), userLvl, cl.get(s)));
				System.out.println("Violation at node " + s + ": user-annotated level is " + userLvl
						+ ", computed level is " + cl.get(s));
			}
		}
		return ret;
	}

	protected void inferUserAnnotationsOnDemand() {
		if (userAnn != null) return;
		
		userAnn = new HashMap<>();
		for (final SDGNode n : sdg.vertexSet()) {
			if (n instanceof SecurityNode) {
				final SecurityNode sn = (SecurityNode) n;
				final String req = sn.getRequired();
				if (req != null && !req.equals(SecurityNode.UNDEFINED)) {
					for (final L elem : secLattice.getElements()) {
						if (req.equals(elem.toString())) {
							userAnn.put(n, elem);
						}
					}
				}
				final String prov = sn.getProvided();
				if (prov != null && !prov.equals(SecurityNode.UNDEFINED)) {
					for (final L elem : secLattice.getElements()) {
						if (prov.equals(elem.toString())) {
							userAnn.put(n, elem);
						}
					}
				}
			}
		}
	}
	
	@Override
	public Collection<? extends IViolation<SecurityNode>> checkIFlow() throws NotInLatticeException {
		inferUserAnnotationsOnDemand();
		final I2PBackward backw = new I2PBackward(sdg);
		// 1.) initialize classification: we go from the bottom up, so every
		// node is classified as low initially
		// except for the user annotated nodes: They are classified with the
		// level given by the user
		cl = initCL(true);
		// 2.) fixed-point iteration
		int numIters = 0;
		boolean change;
		do {
			change = false;
			for (final SDGNode n : sdg.vertexSet()) {
				final L oldLevel = cl.get(n);
				// nothing changes if current level is top already
				if (secLattice.getTop().equals(oldLevel)) {
					continue;
				}
				L newLevel = oldLevel;

				// 2a.) propagate from sdg predecessors
				final Collection<SDGNode> predecessors;
				switch (predecessorMethod) {
				case EDGE:
					// @formatter:off
					predecessors = sdg.incomingEdgesOf(n).stream()
					                                     .filter((e) -> e.getKind().isSDGEdge())
					                                     .map(SDGEdge::getSource)
					                                     .collect(Collectors.toSet());
					// @formatter:on
					System.out.println(String.format("BS(%s) = %s", n, predecessors));
					break;
				case SLICE:
					predecessors = backw.slice(n);
					System.out.println(String.format("PRED(%s) = %s", n, predecessors));
					break;
				default:
					throw new IllegalArgumentException(predecessorMethod.toString());
				}
				for (final SDGNode m : predecessors) {
					newLevel = secLattice.leastUpperBound(newLevel, cl.get(m));
					if (secLattice.getTop().equals(newLevel)) {
						break; // we can abort the loop here - level cannot get
						// any higher
					}
				}
				// 2b.) propagate security levels from probabilistic influencers
				final Collection<? extends SDGNode> pi = probInf.getProbabilisticInfluencers(n);
				System.out.println(String.format("ProbInf(%s) = %s", n, pi));
				for (final SDGNode cp : pi) {
					newLevel = secLattice.leastUpperBound(newLevel, cl.get(cp));
					if (secLattice.getTop().equals(newLevel)) {
						break; // we can abort the loop here - level cannot get
						// any higher
					}
				}
				if (!newLevel.equals(oldLevel)) {
					cl.put(n, newLevel);
					change = true;
				}
			}
			numIters++;
		} while (change);
		System.out.println(String.format("needed %d iteration(s).", numIters));
		// 3.) check that sink levels comply
		return checkCompliance();
	}
}
