package cz.zcu.kiv.jop.cf.data;

import soot.Unit;
import soot.jimple.ConditionExpr;

public class BranchTableItem {

    private Unit unit;
    private boolean isActualTrueBranch;
    private ConditionExpr expr;

    private VariableTable variableTable;

    public BranchTableItem(Unit unit){
        isActualTrueBranch = false;
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public boolean isActualTrueBranch() {
        return isActualTrueBranch;
    }

    public void setActualTrueBranch(boolean actualTrueBranch) {
        isActualTrueBranch = actualTrueBranch;
    }

    public ConditionExpr getExpr() {
        return expr;
    }

    public void setExpr(ConditionExpr expr) {
        this.expr = expr;
    }

    public VariableTable getVariableTable() {
        return variableTable;
    }

    public void setVariableTable(VariableTable variableTable) {
        this.variableTable = variableTable;
    }
}
