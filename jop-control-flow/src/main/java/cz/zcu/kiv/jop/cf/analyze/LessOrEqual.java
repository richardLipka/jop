package cz.zcu.kiv.jop.cf.analyze;

import cz.zcu.kiv.jop.cf.data.VariableItem;
import cz.zcu.kiv.jop.cf.data.VariableTable;
import cz.zcu.kiv.jop.cf.interval.Interval;
import cz.zcu.kiv.jop.cf.interval.IntervalSet;
import soot.Value;
import soot.jimple.ConditionExpr;
import soot.jimple.IntConstant;
import soot.jimple.internal.JimpleLocal;

public class LessOrEqual implements IRelationalOperators {

    public void analyze(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue) {

        Value leftValue = expr.getOp1();
        Value rightValue = expr.getOp2();

        if(leftValue instanceof JimpleLocal && rightValue instanceof JimpleLocal){
            leftAndRightVariables(expr, intervalSets, variableTable, isBranchesTrue);
        }else if(leftValue instanceof JimpleLocal && !(rightValue instanceof JimpleLocal)){
            leftVariableRrightConst(expr, intervalSets, variableTable, isBranchesTrue);
        }else if(!(leftValue instanceof JimpleLocal) && rightValue instanceof JimpleLocal){

        }
    }

    public void leftAndRightVariables(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue) {
        VariableItem variableItemLeft = null;
        VariableItem variableItemRight = null;

        variableItemLeft = variableTable.getMapItem().get(String.valueOf(expr.getOp1()));
        variableItemRight = variableTable.getMapItem().get(String.valueOf(expr.getOp2()));

        if(variableItemLeft != null && variableItemLeft.getParam() && variableItemRight != null && variableItemRight.getParam()){

        }else if(variableItemLeft != null && !variableItemLeft.getParam() && variableItemRight != null && variableItemRight.getParam()){

        }else if(variableItemLeft != null && variableItemLeft.getParam() && variableItemRight != null && !variableItemRight.getParam()){

        }
    }

    public void leftVariableRrightConst(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue) {

        Value rightValue = expr.getOp2();
        VariableItem variableItemLeft = null;

        variableItemLeft = variableTable.getMapItem().get(String.valueOf(expr.getOp1()));

        if(variableItemLeft != null && variableItemLeft.getParam()){
            if(rightValue instanceof IntConstant){
                Interval interval = new Interval();

                IntConstant intConstant = (IntConstant)rightValue;

                if(isBranchesTrue){
                    interval.setMax(intConstant.value);
                }else{
                    interval.setMin(intConstant.value + 1);
                }

                intervalSets[variableItemLeft.getParameterRef().getIndex()].addPenetrationInerval(interval);
            }
        }
    }
}
