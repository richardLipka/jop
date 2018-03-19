package cz.zcu.kiv.jop.cf.analyze;

import cz.zcu.kiv.jop.cf.data.VariableTable;
import cz.zcu.kiv.jop.cf.interval.IntervalSet;
import soot.jimple.ConditionExpr;

public interface IRelationalOperators {

    public void analyze(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue );

    public void leftAndRightVariables(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue);

    public void leftVariableRrightConst(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue);
}
