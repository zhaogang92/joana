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

import com.ibm.wala.ipa.callgraph.pruned.DoNotPrune;

import edu.kit.joana.api.sdg.SDGConfig;
import edu.kit.joana.ifc.sdg.graph.SDG;
import edu.kit.joana.wala.core.SDGBuilder.FieldPropagation;
import edu.kit.joana.wala.core.SDGBuilder.PointsToPrecision;
import edu.kit.joana.wala.eval.util.EvalPaths;

/**
 * @author Juergen Graf <juergen.graf@gmail.com>
 */
public class TestObjGraphPerformanceJC extends TestObjGraphPerformance {

	public static final int NUMBER_OF_RUNS = 3;
	
	@Override
	protected void postCreateConfigHook(final SDGConfig config) {
		config.setPruningPolicy(DoNotPrune.INSTANCE);
		config.setExclusions("");
		//config.setComputeSummaryEdges(false);
	}
	
	@Test
	public void test_JC_Purse_PtsType_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsType_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsType_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_CorporateCard_PtsType_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test_JC_Purse_PtsInst_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsInst_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsInst_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test_JC_CorporateCard_PtsInst_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Purse_PtsObj_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsObj_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsObj_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_CorporateCard_PtsObj_Graph_StdNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_FIXP_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test_JC_Purse_PtsObj_Graph_FastNoEscape() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_NO_OPT_NO_ESCAPE,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsObj_Graph_FastNoEscape() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_NO_OPT_NO_ESCAPE,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsObj_Graph_FastNoEscape() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_NO_OPT_NO_ESCAPE,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test_JC_CorporateCard_PtsObj_Graph_FastNoEscape() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_NO_OPT_NO_ESCAPE,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Purse_PtsObj_Graph_FastNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsObj_Graph_FastNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsObj_Graph_FastNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test_JC_CorporateCard_PtsObj_Graph_FastNoOpt() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_NO_OPT,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Purse_PtsType_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsType_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsType_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test_JC_CorporateCard_PtsType_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Purse_PtsInst_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsInst_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsInst_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test_JC_CorporateCard_PtsInst_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Purse_PtsObj_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsObj_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsObj_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test_JC_CorporateCard_PtsObj_Graph_Std() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Purse_PtsObj_Graph_Fast() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_PROPAGATION,
					EvalPaths.JC_STUBS, EvalPaths.JC_PURSE, "javacard.framework.JCMainPurse");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Safe_PtsObj_Graph_Fast() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_PROPAGATION,
					EvalPaths.JC_STUBS, EvalPaths.JC_SAFE, "javacard.framework.JCMainSafeApplet");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void test_JC_Wallet_PtsObj_Graph_Fast() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_PROPAGATION,
					EvalPaths.JC_STUBS, EvalPaths.JC_WALLET, "javacard.framework.JCMain");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void test_JC_CorporateCard_PtsObj_Graph_Fast() {
		try {
			final String currentTestcase = currentMethodName();
			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_PROPAGATION,
					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
			assertFalse(sdg.vertexSet().isEmpty());
			outputStatistics(sdg, cfg, currentTestcase);
		} catch (ApiTestException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}



//	@Test
//	public void test_JC_CorporateCard_PtsType_Graph_NoEscape() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH_NO_ESCAPE,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsType_Graph_Fast() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH_SIMPLE_PROPAGATION,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsType_Graph_Fixpoint() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH_FIXPOINT_PROPAGATION,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsType_Graph_NoMergeAtAll() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_GRAPH_NO_MERGE_AT_ALL,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsObj_Graph_Fast() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_SIMPLE_PROPAGATION,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsObj_Graph_Standard() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsObj_Graph_Fixpoint() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_FIXPOINT_PROPAGATION,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsObj_Graph_NoMerge() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_GRAPH_NO_MERGE_AT_ALL,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsObj_Tree() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.OBJECT_SENSITIVE, FieldPropagation.OBJ_TREE,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsInstance_Tree() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.INSTANCE_BASED, FieldPropagation.OBJ_TREE,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test_JC_CorporateCard_PtsType_Tree() {
//		try {
//			final String currentTestcase = currentMethodName();
//			final SDGConfig cfg = createConfig(currentTestcase, PointsToPrecision.TYPE_BASED, FieldPropagation.OBJ_TREE,
//					EvalPaths.JC_STUBS, EvalPaths.JC_CORPORATECARD, "javacard.framework.JCMainCorporateCard");
//			final SDG sdg = buildSDG(cfg, NUMBER_OF_RUNS);
//			assertFalse(sdg.vertexSet().isEmpty());
//			outputStatistics(sdg, cfg, currentTestcase);
//		} catch (ApiTestException e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}
	
}
