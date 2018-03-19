package cz.zcu.kiv.jop.cf.analyze;

import cz.zcu.kiv.jop.cf.data.VariableItem;
import cz.zcu.kiv.jop.cf.data.VariableTable;
import cz.zcu.kiv.jop.cf.interval.Interval;
import cz.zcu.kiv.jop.cf.interval.IntervalSet;
import soot.Value;
import soot.jimple.ConditionExpr;
import soot.jimple.IntConstant;
import soot.jimple.internal.JimpleLocal;

public class GreaterOrEqual implements IRelationalOperators {

    //vypočítá intervaly pro vstupní parametry
    public void analyze(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue ){

        Value leftValue = expr.getOp1();
        Value rightValue = expr.getOp2();

        if(leftValue instanceof JimpleLocal && rightValue instanceof JimpleLocal){
            leftAndRightVariables(expr, intervalSets, variableTable, isBranchesTrue);
        }else if(leftValue instanceof JimpleLocal && !(rightValue instanceof JimpleLocal)){
            leftVariableRrightConst(expr, intervalSets, variableTable, isBranchesTrue);
        }else if(!(leftValue instanceof JimpleLocal) && rightValue instanceof JimpleLocal){

        }
    }

    public void leftAndRightVariables(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue){

        VariableItem variableItemLeft = null;
        VariableItem variableItemRight = null;

        Interval i1 = new Interval();
        Interval i2 = new Interval();

        variableItemLeft = variableTable.getMapItem().get(String.valueOf(expr.getOp1()));
        variableItemRight = variableTable.getMapItem().get(String.valueOf(expr.getOp2()));

        if(variableItemLeft != null && variableItemLeft.getParam() && variableItemRight != null && variableItemRight.getParam()){

            i1.setMax(intervalSets[variableItemLeft.getParameterRef().getIndex()].getMax());
            i1.setMin(intervalSets[variableItemLeft.getParameterRef().getIndex()].getMin());

            i2.setMax(intervalSets[variableItemRight.getParameterRef().getIndex()].getMax());
            i2.setMin(intervalSets[variableItemRight.getParameterRef().getIndex()].getMin());

            if(isBranchesTrue){
                if(i1.getMin() < i2.getMax()){
                    if(i1.getMax() <= i2.getMax()){
                        i2.setMax(i2.getMax() - (Math.abs(i2.getMin()) + Math.abs(i2.getMax()))/2.0);
                    }
                    i1.setMin(i2.getMax());
                }
            }else{
                if(i2.getMin() < i1.getMax()){
                    if(i2.getMax() <= i1.getMax()){
                        i1.setMax(i1.getMax() - (Math.abs(i1.getMin()) + Math.abs(i1.getMax()))/2.0);
                    }
                    i2.setMin(i1.getMax() + 1);
                }
            }

            intervalSets[variableItemLeft.getParameterRef().getIndex()].addPenetrationInerval(i1);
            intervalSets[variableItemRight.getParameterRef().getIndex()].addPenetrationInerval(i2);

        }else if(variableItemLeft != null && !variableItemLeft.getParam() && variableItemRight != null && variableItemRight.getParam()){

            if(variableItemLeft.getSootValue() instanceof IntConstant){

                IntConstant intConstant = (IntConstant)variableItemLeft.getSootValue();

                if(isBranchesTrue){
                    i1.setMax(intConstant.value);
                }else{
                    i1.setMin(intConstant.value + 1);
                }

                intervalSets[variableItemRight.getParameterRef().getIndex()].addPenetrationInerval(i1);
            }
        }else if(variableItemLeft != null && variableItemLeft.getParam() && variableItemRight != null && !variableItemRight.getParam()){

        }
    }

    public void leftVariableRrightConst(ConditionExpr expr, IntervalSet[] intervalSets, VariableTable variableTable, boolean isBranchesTrue){
        Value rightValue = expr.getOp2();
        VariableItem variableItemLeft = null;

        variableItemLeft = variableTable.getMapItem().get(String.valueOf(expr.getOp1()));

        if(variableItemLeft != null && variableItemLeft.getParam()){
            if(rightValue instanceof IntConstant){
                Interval interval = new Interval();

                IntConstant intConstant = (IntConstant)rightValue;

                if(isBranchesTrue){
                    interval.setMin(intConstant.value);
                }else{
                    interval.setMax(intConstant.value - 1);
                }

                intervalSets[variableItemLeft.getParameterRef().getIndex()].addPenetrationInerval(interval);
            }
        }
    }
}
