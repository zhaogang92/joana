/**
 * This file is part of the Joana IFC project. It is developed at the
 * Programming Paradigms Group of the Karlsruhe Institute of Technology.
 *
 * For further details on licensing please read the information at
 * http://joana.ipd.kit.edu or contact the authors.
 */
package edu.kit.joana.wala.eval;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.kit.joana.api.sdg.SDGConfig;
import edu.kit.joana.ifc.sdg.graph.SDG;
import edu.kit.joana.wala.core.SDGBuilder.FieldPropagation;
import edu.kit.joana.wala.core.SDGBuilder.PointsToPrecision;
import edu.kit.joana.wala.eval.TestObjGraphPerformance.ApiTestException;
import edu.kit.joana.wala.eval.util.EvalPaths;

/**
 * @author Juergen Graf <juergen.graf@gmail.com>
 */
public class TestObjGraphPerformanceJavaGrande extends TestObjGraphPerformance {

	@Test
	public void test_JRE14_JavaGrandeBarrier_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFBarrierBench");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeCrypt_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFCryptBenchSizeA");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeForkJoin_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFForkJoinBench");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeLUFact_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFLUFactBenchSizeA");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeMolDyn_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFMolDynBenchSizeA");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeMonteCarlo_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFMonteCarloBenchSizeA");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeRayTracer_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFRayTracerBenchSizeA");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeSeries_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFSeriesBenchSizeA");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeSOR_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFSORBenchSizeA");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeSparseMatmult_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFSparseMatmultBenchSizeA");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JRE14_JavaGrandeSync_PtsType_Graph() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JAVAGRANDE_STUBS, EvalPaths.JAVAGRANDE_CP, "def.JGFSyncBench");
			cfg.setComputeInterferences(true);
			final SDG sdg = buildSDG(cfg);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
