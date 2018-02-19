package cz.zcu.kiv.jop.cf;

import cz.zcu.kiv.jop.cf.cfg.ControlFlowGraph;
import soot.*;
import soot.jimple.*;
import soot.util.dot.DotGraph;

import javax.management.StringValueExp;
import java.util.*;

public class Analyze {

    private ControlFlowGraph controlFlowGraph;

    public static void analysing(ControlFlowGraph controlFlowGraph){

        Iterator i = controlFlowGraph.getUnitGraph().iterator();
        Map<Unit, Integer> mapUnit = new HashMap<Unit,Integer>();

        Unit u = (Unit)i.next();
        DotGraph dot = new DotGraph("callgraph");

        List<ParameterDate> parameterDates = new ArrayList<ParameterDate>();

        for(int j = 0; j < controlFlowGraph.getSootMethod().getParameterCount(); j++){
            parameterDates.add(new ParameterDate(controlFlowGraph.getSootMethod().getParameterType(j)));
        }

        while (u != null) {

            if (u instanceof DefinitionStmt){
                DefinitionStmt assign = (DefinitionStmt) u;
                Value rightOp = assign.getRightOp();
                if (rightOp instanceof IntConstant) {
                    System.out.println("IntConstant " + rightOp);
                } else if (rightOp instanceof ParameterRef){

                    parameterDates.get(((ParameterRef) rightOp).getIndex()).setName(String.valueOf(assign.getLeftOp()));

                    System.out.println("ParameterRef " + rightOp);
                } else if (rightOp instanceof Local) {
                    System.out.println("Local " + rightOp);
                } else {
                    System.out.println("???"+u.toString());
                }
            }

            if (u instanceof IfStmt){
                ConditionExpr expr = (ConditionExpr) ((IfStmt) u).getCondition();

                String symbol = expr.getSymbol().trim();

                if(symbol.equals("==")){

                }else if(symbol.equals("<=")){

                }else if(symbol.equals(">=")){

                }else if(symbol.equals("<")){

                }else if(symbol.equals(">")){

                }else if(symbol.equals("!=")){

                }else {
                    throw new IllegalArgumentException("bat symbol");
                }


            }


            if(i.hasNext()){
                u = (Unit)i.next();
            }else{
                u = null;
            }
        }

    }

    public static void parameterType(ControlFlowGraph controlFlowGraph){
        List<Type> parameterTypeList = controlFlowGraph.getSootMethod().getParameterTypes();
        List<String> parameterTypeStringList = new ArrayList<String>();
        for (Type aParameterType : parameterTypeList) {
            parameterTypeStringList.add(aParameterType.toString());
            System.out.println(aParameterType.toString());
        }
    }

}
