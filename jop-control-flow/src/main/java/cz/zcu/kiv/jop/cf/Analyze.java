package cz.zcu.kiv.jop.cf;

import cz.zcu.kiv.jop.cf.analyze.GreaterOrEqual;
import cz.zcu.kiv.jop.cf.analyze.LessOrEqual;
import cz.zcu.kiv.jop.cf.cfg.ControlFlowGraph;
import cz.zcu.kiv.jop.cf.data.*;
import cz.zcu.kiv.jop.cf.interval.Interval;
import cz.zcu.kiv.jop.cf.interval.IntervalSet;
import soot.*;
import soot.JastAddJ.SwitchStmt;
import soot.jimple.*;
import soot.jimple.internal.JGotoStmt;
import soot.jimple.internal.JimpleLocal;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.util.Switch;
import soot.util.dot.DotGraph;

import java.util.*;

public class Analyze {

    private ControlFlowGraph controlFlowGraph;

    private static BranchTable branchTable;

    private static VariableTable variableTable;

    private static DateMethodParameters dmp;

    public static void init(ControlFlowGraph controlFlowGraph){

        int countParameters = controlFlowGraph.getSootMethod().getParameterCount();
        dmp = new DateMethodParameters(countParameters);

        for(int j = 0; j < countParameters; j++){
            System.out.println(controlFlowGraph.getSootMethod().getBytecodeParms());
            dmp.getParameterInfos()[j] = new ParameterInfo(controlFlowGraph.getSootMethod().getParameterType(j));
        }
    }

    public static void analysing(ControlFlowGraph controlFlowGraph){

        init(controlFlowGraph);

        Iterator i = controlFlowGraph.getUnitGraph().iterator();
        Unit u = (Unit)i.next();

        variableTable = new VariableTable();
        branchTable = new BranchTable();

        goTrueBranche(u, controlFlowGraph);

        generateOutput();

        while(branchTable.getBranchTableItems().size() != 0){

            BranchTableItem bti = branchTable.getBranchTableItems().get(branchTable.getBranchTableItems().size()-1);

            while(bti.isActualTrueBranch()){
                branchTable.getBranchTableItems().remove(branchTable.getBranchTableItems().size()-1);
                if(branchTable.getBranchTableItems().size() > 0){
                    bti = branchTable.getBranchTableItems().get(branchTable.getBranchTableItems().size()-1);
                }else {
                    bti = null;
                    break;
                }
            }

            if(bti != null){
                bti.setActualTrueBranch(true);

                u = bti.getUnit();
                u = controlFlowGraph.getUnitGraph().getSuccsOf(u).get(1);

                goTrueBranche(u, controlFlowGraph);
                generateOutput();
            }
        }
    }

    public static void generateOutput(){

        IntervalSet[] outputPar = new IntervalSet[dmp.getParameterInfos().length];
        for(int i = 0; i < dmp.getParameterInfos().length; i++){
            outputPar[i] = new IntervalSet();
        }

        GreaterOrEqual greaterOrEqual = new GreaterOrEqual();
        LessOrEqual lessOrEqual = new LessOrEqual();

        for(int i = 0; i < branchTable.getBranchTableItems().size(); i++){

            Unit u = branchTable.getBranchTableItems().get(i).getUnit();
            ConditionExpr expr = (ConditionExpr) ((IfStmt) u).getCondition();
            String symbol = expr.getSymbol().trim();

            VariableTable vt = branchTable.getBranchTableItems().get(i).getVariableTable();
            boolean isBranchesTrue = branchTable.getBranchTableItems().get(i).isActualTrueBranch();

            if(symbol.equals("==")){

            }else if(symbol.equals("<=")){
                lessOrEqual.analyze(expr, outputPar, vt, isBranchesTrue);
            }else if(symbol.equals(">=")){
                greaterOrEqual.analyze(expr, outputPar, vt, isBranchesTrue);
            }else if(symbol.equals("<")){

            }else if(symbol.equals(">")){

            }else if(symbol.equals("!=")){

            }else {
                throw new IllegalArgumentException("bat symbol");
            }
        }

        for(int j = 0; j < outputPar.length; j++){
            System.out.println("PAR-"+j+": " + outputPar[j]);
        }
    }

    public static void goTrueBranche(Unit u, ControlFlowGraph controlFlowGraph){
        while (u != null) {

            System.out.println(u);

            if (u instanceof DefinitionStmt){
                definitionStmt(u);
            }else if (u instanceof IfStmt){
                ifStmt(u);
            }else if(u instanceof SwitchStmt){
                switchStmt(u);
            }else if (u instanceof InvokeStmt) {
                invokeStmt(u);
            }

            if(controlFlowGraph.getUnitGraph().getSuccsOf(u) != null && controlFlowGraph.getUnitGraph().getSuccsOf(u).size() > 0){
                if(!(u instanceof JGotoStmt)){
                    u = controlFlowGraph.getUnitGraph().getSuccsOf(u).get(0);
                }else {
                    boolean found = false;
                    for(int i = 0; i < branchTable.getBranchTableItems().size(); i++){
                        if(branchTable.getBranchTableItems().get(i).getUnit().equals(controlFlowGraph.getUnitGraph().getSuccsOf(u).get(0))){
                            Unit un = branchTable.getBranchTableItems().get(i).getUnit();
                            u = controlFlowGraph.getUnitGraph().getSuccsOf(un).get(1);
                            found = true;
                            break;
                        }
                    }

                    if(!found){
                        u = controlFlowGraph.getUnitGraph().getSuccsOf(u).get(0);
                    }

                }

            }else{
                u = null;
            }
        }
    }

    public static void switchStmt(Unit u){
        SwitchStmt switchStmt = (SwitchStmt) u;
    }

    public static void invokeStmt(Unit u){
        InvokeStmt stmt = (InvokeStmt)u;
        InvokeExpr expr = stmt.getInvokeExpr();
        SootMethod targetMethod = expr.getMethod();
        Body body = targetMethod.retrieveActiveBody();
        UnitGraph g = new ExceptionalUnitGraph(body);

        System.out.println(targetMethod.getActiveBody());
    }

    public static void definitionStmt(Unit u){
        if (u instanceof DefinitionStmt){
            DefinitionStmt assign = (DefinitionStmt) u;
            Value rightOp = assign.getRightOp();

            String name = String.valueOf(assign.getLeftOp());

            if (rightOp instanceof IntConstant) {
                System.out.println("IntConstant " + rightOp);

                VariableItem vi = new VariableItem();

                vi.setName(name);
                vi.setSootValue(assign.getRightOp());
                vi.setType(assign.getLeftOp().getType());

                variableTable.getMapItem().put(name, vi);

            } else if (rightOp instanceof ParameterRef){

                int index = ((ParameterRef) rightOp).getIndex();

                dmp.getParameterInfos()[index].setName(name);
                dmp.getParameterInfos()[index].setSootValue(assign.getLeftOp());

                VariableItem vi = new VariableItem();

                vi.setName(name);
                vi.setSootValue(assign.getRightOp());
                vi.setType(assign.getLeftOp().getType());
                vi.setParam(true);
                vi.setParameterRef((ParameterRef) rightOp);

                variableTable.getMapItem().put(name, vi);

                System.out.println("ParameterRef " + rightOp);

            } else if (rightOp instanceof Local) {
                System.out.println("Local " + rightOp);
            } else if(rightOp instanceof VirtualInvokeExpr){
                VirtualInvokeExpr vie =(VirtualInvokeExpr) rightOp;

                for(ParameterInfo p: dmp.getParameterInfos()){
                    if(p.getSootValue().equals( vie.getBase())){
                        System.out.println("ssss");
                    }
                }

                SootMethod targetMethod = vie.getMethod();
                Body body = targetMethod.retrieveActiveBody();
                UnitGraph g = new ExceptionalUnitGraph(body);

                System.out.println(targetMethod.getActiveBody());

            }else {
                System.out.println("???"+u.toString());
            }
        }
    }

    public static void ifStmt(Unit u){
        if(u instanceof IfStmt){
            ConditionExpr expr = (ConditionExpr) ((IfStmt) u).getCondition();

            BranchTableItem bti = new BranchTableItem(u);
            bti.setVariableTable(new VariableTable(variableTable));

            branchTable.getBranchTableItems().add(bti);
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
