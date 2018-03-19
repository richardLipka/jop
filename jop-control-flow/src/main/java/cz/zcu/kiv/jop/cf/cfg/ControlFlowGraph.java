package cz.zcu.kiv.jop.cf.cfg;

import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import soot.*;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.util.dot.DotGraph;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ControlFlowGraph {

    private UnitGraph unitGraph = null;
    private SootMethod sootMethod = null;


    public ControlFlowGraph(SootMethod metod){

        Body b = metod.retrieveActiveBody();

        System.out.println(b);

        this.sootMethod = metod;
        this.unitGraph = new ExceptionalUnitGraph(b);

    }

    public UnitGraph getUnitGraph() {
        return unitGraph;
    }

    public void setUnitGraph(UnitGraph unitGraph) {
        this.unitGraph = unitGraph;
    }

    public SootMethod getSootMethod() {
        return sootMethod;
    }

    public void setSootMethod(SootMethod sootMethod) {
        this.sootMethod = sootMethod;
    }


    public void saveGraph(String name){
        Iterator i = unitGraph.iterator();
        Map<Unit, Integer> mapUnit = new HashMap<Unit,Integer>();

        Unit u = (Unit)i.next();
        DotGraph dot = new DotGraph("callgraph");

        int j = 1;
        while (u != null) {

            UnitPrinter up = new NormalUnitPrinter(sootMethod.getActiveBody());
            up.setIndent("");
            u.toString(up);

            for(Unit p : unitGraph.getSuccsOf(u)){
                UnitPrinter up2 = new NormalUnitPrinter(sootMethod.getActiveBody());
                up2.setIndent("");
                p.toString(up2);

                addToMap(mapUnit, p);

                dot.drawEdge(mapUnit.get(u)+": "+String.valueOf(up.output()), mapUnit.get(p)+": "+String.valueOf(up2.output()));
            }

            System.out.println(j + ":" +up.output());
            j++;

            if(i.hasNext()){
                u = (Unit)i.next();
            }else{
                u = null;
            }
        }

        System.out.println(dot);

        //saveGraphGraphviz(dot.toString(), "pom");

        dot.plot(name+".dot");
    }

    public static void saveGraphGraphviz(String dot, String filename){
        try{
            MutableGraph g = Parser.read(dot);
            Graphviz output = Graphviz.fromGraph(g);
            output.renderToFile(new File(filename));

        }catch (Exception e){
            System.out.println(e);
        }
    }


    public static String printMetod(DotGraph dot, UnitGraph graph, Body b, String entry){
        Map<Unit, Integer> mapUnit = new HashMap<Unit,Integer>();
        System.out.println("Call Graph size : "+ graph.size());
        Iterator i = graph.iterator();
        int j = 0;
        int idnext = 1;

        String out = "";
        String in = "";

        Unit u = (Unit)i.next();

        while (u != null) {

            UnitPrinter up = new NormalUnitPrinter(b);
            up.setIndent("");
            u.toString(up);
            System.out.println(j + ":" +up.output());

            out = mapUnit.get(u)+": "+String.valueOf(up.output());
            addToMap(mapUnit,u);

            if(j == 0 && entry != null){
                dot.drawEdge(entry, mapUnit.get(u)+": "+String.valueOf(up.output()));
            }

            if(in.length() > 2){
                dot.drawEdge(in, mapUnit.get(u)+": "+String.valueOf(up.output()));
                in = "";
            }

            /*if(end != null && !i.hasNext()){
                dot.drawEdge(mapUnit.get(u)+": "+String.valueOf(up.output()), end);
            }*/

            for(Unit p : graph.getSuccsOf(u)){
                UnitPrinter up2 = new NormalUnitPrinter(b);
                up2.setIndent("");
                p.toString(up2);

                addToMap(mapUnit, p);


                if (u instanceof InvokeStmt && false) {
                    System.out.println("AAAAAAAAAAAAAAAAAA");
                    InvokeStmt stmt = (InvokeStmt)u;
                    InvokeExpr expr = stmt.getInvokeExpr();
                    SootMethod targetMethod = expr.getMethod();
                    Body body = targetMethod.retrieveActiveBody();
                    UnitGraph g = new ExceptionalUnitGraph(body);

                    in = printMetod(dot,g,body,mapUnit.get(u)+": "+String.valueOf(up.output()));

                    //System.out.println(targetMethod.getActiveBody());

                }else{
                    dot.drawEdge(mapUnit.get(u)+": "+String.valueOf(up.output()), mapUnit.get(p)+": "+String.valueOf(up2.output()));
                }


                //System.out.print(up2.output() + ",");
            }

            j++;


            if(i.hasNext()){
                u = (Unit)i.next();
            }else{
                u = null;
            }

        }

        return out;

    }

    public static void printGrapt(UnitGraph graph, Body b, String name){

        DotGraph dot = new DotGraph("callgraph");

        printMetod(dot, graph,b, null);

        //saveGraph(dot.toString(), "aa.png");
        dot.plot(name+".dot");

    }


    /*public static void saveGraph(String dot, String filename){
        try{
            MutableGraph g = Parser.read(dot);
            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File(filename));
        }catch (Exception e){

        }
     }*/

    public static void addToMap(Map<Unit, Integer> mapUnit, Unit u){
        boolean content = false;

        for (Map.Entry<Unit, Integer> entry : mapUnit.entrySet())
        {
            if(entry.getKey().equals(u)){
                content = true;
                continue;
            }
            //System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        if(!content){
            mapUnit.put(u, mapUnit.size());
        }
    }



}
