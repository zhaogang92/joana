package tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.graph.GraphIntegrity.UnsoundGraphException;

import edu.kit.joana.api.lattice.BuiltinLattices;
import edu.kit.joana.ifc.sdg.graph.SDG;
import edu.kit.joana.ifc.sdg.graph.SDGEdge;
import edu.kit.joana.ifc.sdg.graph.SDGNode;
import edu.kit.joana.ifc.sdg.graph.SDGSerializer;
import edu.kit.joana.ifc.sdg.graph.slicer.graph.CFG;
import edu.kit.joana.ifc.sdg.graph.slicer.graph.threads.PreciseMHPAnalysis;
import edu.kit.joana.ifc.sdg.irlsod.ORLSODChecker;
import edu.kit.joana.ifc.sdg.irlsod.PathBasedORLSODChecker;
import edu.kit.joana.ifc.sdg.irlsod.PredecessorMethod;
import edu.kit.joana.ifc.sdg.irlsod.ProbInfComputer;
import edu.kit.joana.ifc.sdg.irlsod.ThreadModularCDomOracle;
import edu.kit.joana.ifc.sdg.mhpoptimization.CSDGPreprocessor;
import edu.kit.joana.ifc.sdg.mhpoptimization.PruneInterferences;
import edu.kit.joana.ifc.sdg.util.BytecodeLocation;
import edu.kit.joana.ifc.sdg.util.sdg.GraphModifier;
import edu.kit.joana.ifc.sdg.util.sdg.ReducedCFGBuilder;

public class ORLSODExperiment {

	@Test
	public void doORLSOD1() throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		doConfig(new StandardTestConfig("example/bin", "Lorlsod/ORLSOD1", "orlsod1", 1, 2, 2));
	}

	@Test
	public void doORLSOD2() throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		doConfig(new StandardTestConfig("example/bin", "Lorlsod/ORLSOD2", "orlsod2", 1, 2, 0));
	}

	@Test
	public void doORLSOD3() throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		doConfig(new StandardTestConfig("example/bin", "Lorlsod/ORLSOD3", "orlsod3", 1, 2, 0));
	}

	@Test
	public void doNoSecret() throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		doConfig(new StandardTestConfig("example/bin", "Lorlsod/NoSecret", "noSecret", 0, 2, 0));
	}

	@Test
	public void doLateSecretAccess()
			throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		doConfig(new StandardTestConfig("example/bin", "Lorlsod/LateSecretAccess", "lateSecAccess", 1, 2, 0));
	}

	private static void doConfig(final TestConfig cfg)
			throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		final SDG sdg = JoanaRunner.buildSDG(cfg.progDesc.classPath, cfg.progDesc.mainClass);
		CSDGPreprocessor.preprocessSDG(sdg);
		final CFG redCFG = ReducedCFGBuilder.extractReducedCFG(sdg);
		GraphModifier.removeCallCallRetEdges(redCFG);
		DomExperiment.export(redCFG, DomExperiment.joanaGraphExporter(), cfg.outputFiles.dotFile);
		final PreciseMHPAnalysis mhp = PreciseMHPAnalysis.analyze(sdg);
		PruneInterferences.pruneInterferences(sdg, mhp);
		final PrintWriter pw = new PrintWriter(cfg.outputFiles.pdgFile);
		SDGSerializer.toPDGFormat(sdg, pw);
		pw.close();
		final Map<SDGNode, String> userAnn = new HashMap<SDGNode, String>();
		int noHighThings = 0;
		for (final SDGNode src : cfg.srcSelector.select(sdg)) {
			userAnn.put(src, BuiltinLattices.STD_SECLEVEL_HIGH);
			System.out.println(String.format("userAnn(%s) = %s", src, BuiltinLattices.STD_SECLEVEL_HIGH));
			noHighThings++;
		}
		Assert.assertEquals(cfg.expectedNoHighThings, noHighThings);
		int noLowThings = 0;
		for (final SDGNode snk : cfg.snkSelector.select(sdg)) {
			userAnn.put(snk, BuiltinLattices.STD_SECLEVEL_LOW);
			System.out.println(String.format("userAnn(%s) = %s", snk, BuiltinLattices.STD_SECLEVEL_LOW));
			noLowThings++;
		}
		Assert.assertEquals(cfg.expectedNoLowThings, noLowThings);
		final ThreadModularCDomOracle tmdo = new ThreadModularCDomOracle(sdg);
		final ProbInfComputer probInf = new ProbInfComputer(sdg, tmdo);
		final ORLSODChecker<String> checkerPath = new PathBasedORLSODChecker<String>(sdg,
				BuiltinLattices.getBinaryLattice(), userAnn, probInf);
		final int noViosPath = checkerPath.checkIFlow().size();
		Assert.assertEquals(cfg.expectedNoViolations, noViosPath);

		// The optimization finds exactly the same number of violations.
		// Also, because the classification Map cl is context-insensitive
		// anayway, it is indeed sufficient to propagate along
		// sdg edges, instead of propagating all levels in the i2p-slice of a
		// node.
		final ORLSODChecker<String> checkerSlice = new ORLSODChecker<String>(sdg, BuiltinLattices.getBinaryLattice(),
				userAnn, probInf, PredecessorMethod.SLICE);
		Assert.assertEquals(noViosPath, checkerSlice.checkIFlow().size());
		final ORLSODChecker<String> checkerEdge = new ORLSODChecker<String>(sdg, BuiltinLattices.getBinaryLattice(),
				userAnn, probInf, PredecessorMethod.EDGE);
		Assert.assertEquals(noViosPath, checkerEdge.checkIFlow().size());

	}

	@Test
	public void testORLSOD5a() throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		doConfig(new StandardTestConfig("example/bin", "Lorlsod/ORLSOD5a", "orlsod5a", 1, 2, 2));
	}

	@Test
	public void testPost_Fig2_3() throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		doConfig(new StandardTestConfig("example/bin", "Lpost16/Fig2_3", "post_fig2_3", 1, 2, 1));
	}

	@Test
	public void testORLSOD_imprecise()
			throws ClassHierarchyException, IOException, UnsoundGraphException, CancelException {
		/**
		 * NOTE: The program is actually secure but ORLSOD by design fails to detect this. RLSOD and LSOD deem this
		 * program secure (no "normal" flows and o low-observable conflict). TODO: add test code which proves this silly
		 * claim!
		 */
		doConfig(new StandardTestConfig("example/bin", "Lorlsod/ORLSODImprecise", "orlsod_imprecise", 1, 1, 1));
	}

	static class TestConfig {
		ProgDesc progDesc;
		OutputFiles outputFiles;
		NodeSelector srcSelector;
		NodeSelector snkSelector;
		int expectedNoHighThings;
		int expectedNoLowThings;
		int expectedNoViolations;
	}

	static class StandardTestConfig extends TestConfig {
		StandardTestConfig(final String classPath, final String mainClass, final String shortName,
				final int expectedNoSources, final int expectedNoSinks, final int expectedNoViolations) {
			this.progDesc = new ProgDesc(classPath, mainClass);
			this.outputFiles = new OutputFiles(String.format("%s.dot", shortName), String.format("%s.pdg", shortName));
			this.srcSelector = new CriterionBasedNodeSelector(FieldAccess.staticRead(mainClass + ".HIGH"));
			this.snkSelector = new CriterionBasedNodeSelector(FieldAccess.staticWrite(mainClass + ".LOW"));
			this.expectedNoHighThings = expectedNoSources;
			this.expectedNoLowThings = expectedNoSinks;
			this.expectedNoViolations = expectedNoViolations;
		}
	}

	static class ProgDesc {
		String classPath;
		String mainClass;

		ProgDesc(final String classPath, final String mainClass) {
			this.classPath = classPath;
			this.mainClass = mainClass;
		}
	}

	static class OutputFiles {
		String dotFile;
		String pdgFile;

		OutputFiles(final String dotFile, final String pdgFile) {
			this.dotFile = dotFile;
			this.pdgFile = pdgFile;
		}
	}

	static interface NodeSelector {
		Collection<? extends SDGNode> select(SDG sdg);
	}

	@FunctionalInterface
	private static interface NodeCriterion {
		boolean accept(SDGNode n, SDG sdg);
	}

	private static class FixedNodeSelector implements NodeSelector {

		private final int[] ids;

		FixedNodeSelector(final int... ids) {
			this.ids = ids;
		}

		@Override
		public Collection<? extends SDGNode> select(final SDG sdg) {
			final List<SDGNode> ret = new LinkedList<SDGNode>();
			for (final int id : ids) {
				ret.add(sdg.getNode(id));
			}
			return ret;
		}
	}

	private static class CriterionBasedNodeSelector implements NodeSelector {
		private final NodeCriterion crit;

		public CriterionBasedNodeSelector(final NodeCriterion crit) {
			this.crit = crit;
		}

		@Override
		public Collection<? extends SDGNode> select(final SDG sdg) {
			final List<SDGNode> ret = new LinkedList<SDGNode>();
			for (final SDGNode n : sdg.vertexSet()) {
				if (crit.accept(n, sdg)) {
					ret.add(n);
				}
			}
			return ret;
		}
	}

	private static class FieldAccess implements NodeCriterion {
		enum AccessType {
			READ, WRITE;
			SDGNode.Operation toOperation() {
				switch (this) {
				case READ:
					return SDGNode.Operation.REFERENCE;
				case WRITE:
					return SDGNode.Operation.MODIFY;
				default:
					throw new UnsupportedOperationException("unhandled case: " + this);
				}
			}
		}

		private final String fieldName;
		private final int staticOrObject;
		private final SDGNode.Operation operation;

		FieldAccess(final String fieldName, final boolean isStatic, final AccessType accType) {
			this.fieldName = fieldName;
			this.staticOrObject = isStatic ? BytecodeLocation.STATIC_FIELD : BytecodeLocation.OBJECT_FIELD;
			this.operation = accType.toOperation();
		}

		@Override
		public boolean accept(final SDGNode n, final SDG sdg) {
			if (n.getOperation() == operation) {
				final SDGNode field = findRelevantFieldInCEClosure(n, sdg);
				return field != null;
			} else {
				return false;
			}
		}

		private SDGNode findRelevantFieldInCEClosure(final SDGNode fieldNode, final SDG sdg) {
			for (final SDGNode n : ceClosure(fieldNode, sdg)) {
				if (n.getBytecodeName().equals(fieldName) && (n.getBytecodeIndex() == this.staticOrObject)) {
					return n;
				}
			}
			return null;
		}

		private Set<SDGNode> ceClosure(final SDGNode start, final SDG sdg) {
			final LinkedList<SDGNode> worklist = new LinkedList<SDGNode>();
			final Set<SDGNode> done = new HashSet<SDGNode>();
			worklist.add(start);
			while (!worklist.isEmpty()) {
				final SDGNode next = worklist.poll();
				if (done.contains(next)) {
					continue;
				}
				for (final SDGEdge eOut : sdg.getOutgoingEdgesOfKind(next, SDGEdge.Kind.CONTROL_DEP_EXPR)) {
					worklist.add(eOut.getTarget());
				}
				for (final SDGEdge eIn : sdg.getIncomingEdgesOfKind(next, SDGEdge.Kind.CONTROL_DEP_EXPR)) {
					worklist.add(eIn.getSource());
				}
				done.add(next);
			}
			return done;
		}

		public static FieldAccess staticRead(final String fieldName) {
			return new FieldAccess(fieldName, true, AccessType.READ);
		}

		public static FieldAccess staticWrite(final String fieldName) {
			return new FieldAccess(fieldName, true, AccessType.WRITE);
		}

		public static FieldAccess objectRead(final String fieldName) {
			return new FieldAccess(fieldName, false, AccessType.READ);
		}

		public static FieldAccess objectWrite(final String fieldName) {
			return new FieldAccess(fieldName, false, AccessType.WRITE);
		}
	}

	private static class MethodCall {

	}
}
