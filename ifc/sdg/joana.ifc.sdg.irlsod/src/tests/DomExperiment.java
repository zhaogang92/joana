package tests;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.VertexNameProvider;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import edu.kit.joana.graph.dominators.AbstractCFG;
import edu.kit.joana.graph.dominators.CustomCFG;
import edu.kit.joana.graph.dominators.InterprocDominators2;
import edu.kit.joana.ifc.sdg.graph.SDGEdge;
import edu.kit.joana.ifc.sdg.graph.SDGNode;
import edu.kit.joana.ifc.sdg.graph.slicer.graph.VirtualNode;
import edu.kit.joana.ifc.sdg.graph.slicer.graph.threads.ThreadRegion;
import edu.kit.joana.ifc.sdg.graph.slicer.graph.threads.ThreadsInformation.ThreadInstance;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;

public class DomExperiment {
	public static <V, E> void export(final Graph<V, E> graph, final DOTExporter<V, E> exporter, final String filename)
			throws FileNotFoundException {
		final PrintWriter pw = new PrintWriter(filename);
		exporter.export(pw, graph);
	}

	public static <V, E> DOTExporter<V, E> genericExporter() {
		return new DOTExporter<V, E>(new VertexNameProvider<V>() {
			private final TObjectIntHashMap<V> id = new TObjectIntHashMap<V>();
			private int maxID = 0;

			private int getID(final V vn) {
				int ret;
				if (!id.containsKey(vn)) {
					id.put(vn, maxID);
					ret = maxID;
					maxID++;
				} else {
					ret = id.get(vn);
				}
				return ret;
			}

			@Override
			public String getVertexName(final V ti) {
				return Integer.toString(getID(ti));
			}

		}, new VertexNameProvider<V>() {

			@Override
			public String getVertexName(final V ti) {
				return ti.toString();
			}

		}, new EdgeNameProvider<E>() {

			@Override
			public String getEdgeName(final E e) {
				return "";
			}

		});
	}

	public static DOTExporter<ThreadInstance, DefaultEdge> tctExporter() {
		return new DOTExporter<ThreadInstance, DefaultEdge>(new VertexNameProvider<ThreadInstance>() {

			@Override
			public String getVertexName(final ThreadInstance ti) {
				return Integer.toString(ti.getId());
			}

		}, new VertexNameProvider<ThreadInstance>() {

			@Override
			public String getVertexName(final ThreadInstance ti) {
				return Integer.toString(ti.getId());
			}

		}, new EdgeNameProvider<DefaultEdge>() {

			@Override
			public String getEdgeName(final DefaultEdge e) {
				return "";
			}

		});
	}

	public static DOTExporter<VirtualNode, SDGEdge> threadGraphExporter() {
		return new DOTExporter<VirtualNode, SDGEdge>(new VertexNameProvider<VirtualNode>() {

			private final TObjectIntHashMap<VirtualNode> id = new TObjectIntHashMap<VirtualNode>();
			private int maxID = 0;

			private int getID(final VirtualNode vn) {
				int ret;
				if (!id.containsKey(vn)) {
					id.put(vn, maxID);
					ret = maxID;
					maxID++;
				} else {
					ret = id.get(vn);
				}
				return ret;
			}

			@Override
			public String getVertexName(final VirtualNode vn) {
				return Integer.toString(getID(vn));
			}

		}, new VertexNameProvider<VirtualNode>() {

			@Override
			public String getVertexName(final VirtualNode ti) {
				return String.format("(%d, %d)", ti.getNode().getId(), ti.getNumber());
			}

		}, new EdgeNameProvider<SDGEdge>() {

			@Override
			public String getEdgeName(final SDGEdge e) {
				return e.getKind().toString();
			}

		});
	}

	public static DOTExporter<Integer, DefaultEdge> standardExporter() {
		return new DOTExporter<Integer, DefaultEdge>(new VertexNameProvider<Integer>() {

			@Override
			public String getVertexName(final Integer tr) {
				return Integer.toString(tr);
			}

		}, new VertexNameProvider<Integer>() {

			@Override
			public String getVertexName(final Integer tr) {
				return Integer.toString(tr);
			}

		}, new EdgeNameProvider<DefaultEdge>() {

			@Override
			public String getEdgeName(final DefaultEdge e) {
				return "";
			}

		});
	}

	public static DOTExporter<SDGNode, SDGEdge> joanaGraphExporter() {
		return new DOTExporter<SDGNode, SDGEdge>(new VertexNameProvider<SDGNode>() {

			@Override
			public String getVertexName(final SDGNode tr) {
				return Integer.toString(tr.getId());
			}

		}, new VertexNameProvider<SDGNode>() {

			@Override
			public String getVertexName(final SDGNode tr) {
				return Integer.toString(tr.getId()) + " " + tr.getKind();
			}

		}, new EdgeNameProvider<SDGEdge>() {

			@Override
			public String getEdgeName(final SDGEdge e) {
				return "";
			}

		});
	}

	public static <E> DOTExporter<Set<ThreadRegion>, E> regionClusterGraphExporter() {
		return new DOTExporter<Set<ThreadRegion>, E>(new VertexNameProvider<Set<ThreadRegion>>() {
			private final TObjectIntMap<Set<ThreadRegion>> id = new TObjectIntHashMap<Set<ThreadRegion>>();
			private int maxID = 0;

			int getID(final Set<ThreadRegion> x) {
				if (!id.containsKey(x)) {
					id.put(x, maxID);
					final int ret = maxID;
					maxID++;
					return ret;
				} else {
					return id.get(x);
				}
			}

			@Override
			public String getVertexName(final Set<ThreadRegion> tr) {
				return Integer.toString(getID(tr));
			}
		}, new VertexNameProvider<Set<ThreadRegion>>() {

			@Override
			public String getVertexName(final Set<ThreadRegion> tr) {
				return tr.toString();
			}

		}, new EdgeNameProvider<E>() {

			@Override
			public String getEdgeName(final E e) {
				return "";
			}

		});
	}

	public static <E> DOTExporter<ThreadRegion, E> regionGraphExporter() {
		return new DOTExporter<ThreadRegion, E>(new VertexNameProvider<ThreadRegion>() {
			@Override
			public String getVertexName(final ThreadRegion tr) {
				return Integer.toString(tr.getID());
			}
		}, new VertexNameProvider<ThreadRegion>() {

			@Override
			public String getVertexName(final ThreadRegion tr) {
				return tr.toString();
			}

		}, new EdgeNameProvider<E>() {

			@Override
			public String getEdgeName(final E e) {
				return "";
			}

		});
	}

	private static <V, E> DirectedGraph<V, DefaultEdge> makeDomGraph(final AbstractCFG<V, E> icfg) {
		final InterprocDominators2<V, E> dom = new InterprocDominators2<V, E>(icfg);
		dom.runWorklist();
		final DirectedGraph<V, DefaultEdge> domGraph = new DefaultDirectedGraph<V, DefaultEdge>(DefaultEdge.class);
		for (final V v : icfg.vertexSet()) {
			domGraph.addVertex(v);
		}
		for (final V v : icfg.vertexSet()) {
			for (final V vDom : dom.idoms(v)) {
				domGraph.addEdge(vDom, v);
			}
		}
		return domGraph;
	}

	private static CustomCFG<Integer, DefaultEdge> example1() {
		final CustomCFG<Integer, DefaultEdge> icfg = new CustomCFG<Integer, DefaultEdge>(DefaultEdge.class);
		icfg.addNormalEdge(1, 2);
		icfg.addNormalEdge(1, 10);
		icfg.addNormalEdge(8, 9);
		icfg.addNormalEdge(12, 9);
		icfg.addNormalEdge(3, 4);
		icfg.addNormalEdge(6, 7);
		icfg.addCall(2, 3, 4, 5);
		icfg.addCall(11, 3, 4, 12);
		icfg.addCall(5, 6, 7, 8);
		icfg.addCall(10, 6, 7, 11);
		icfg.setRoot(1);
		return icfg;
	}

	private static CustomCFG<Integer, DefaultEdge> example2() {
		final CustomCFG<Integer, DefaultEdge> icfg = new CustomCFG<Integer, DefaultEdge>(DefaultEdge.class);
		icfg.addNormalEdge(1, 2);
		icfg.addNormalEdge(2, 3);
		icfg.addNormalEdge(3, 4);
		icfg.addNormalEdge(3, 5);
		icfg.addNormalEdge(4, 2);
		icfg.addNormalEdge(4, 10);
		icfg.addNormalEdge(5, 6);
		icfg.addCall(6, 8, 9, 7);
		icfg.addNormalEdge(7, 11);
		icfg.addNormalEdge(8, 9);
		icfg.addNormalEdge(10, 12);
		icfg.addNormalEdge(11, 12);
		icfg.setRoot(1);
		return icfg;
	}

	private static CustomCFG<Integer, DefaultEdge> example3() {
		final CustomCFG<Integer, DefaultEdge> icfg = new CustomCFG<Integer, DefaultEdge>(DefaultEdge.class);
		icfg.addNormalEdge(1, 2);
		icfg.addCall(2, 3, 12, 9);
		icfg.addNormalEdge(3, 4);
		icfg.addNormalEdge(4, 5);
		icfg.addNormalEdge(4, 12);
		icfg.addNormalEdge(5, 6);
		icfg.addNormalEdge(6, 7);
		icfg.addNormalEdge(7, 8);
		icfg.addNormalEdge(8, 11);
		icfg.addNormalEdge(9, 10);
		icfg.addNormalEdge(10, 11);
		icfg.addNormalEdge(11, 13);
		icfg.setRoot(1);
		return icfg;
	}

	private static <V, E> void runExample(final AbstractCFG<V, E> example, final DOTExporter<V, E> exporterCFG,
			final String cfFileName, final DOTExporter<V, DefaultEdge> exporterDom, final String dgFileName)
					throws FileNotFoundException {
		final DirectedGraph<V, DefaultEdge> domGraph = makeDomGraph(example);
		export(example.getUnderlyingGraph(), exporterCFG, cfFileName);
		export(domGraph, exporterDom, dgFileName);
	}

	public static void main(final String[] args) throws FileNotFoundException {
		runExample(example1(), standardExporter(), "controlFlow1.dot", standardExporter(), "domGraph1.dot");
		runExample(example2(), standardExporter(), "controlFlow2.dot", standardExporter(), "domGraph2.dot");
		runExample(example3(), standardExporter(), "controlFlow3.dot", standardExporter(), "domGraph3.dot");
	}
}
