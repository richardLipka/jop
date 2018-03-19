package cz.zcu.kiv.jop.cf;

import cz.zcu.kiv.jop.cf.cfg.ControlFlowGraph;
import cz.zcu.kiv.jop.cf.interval.Interval;
import cz.zcu.kiv.jop.cf.interval.IntervalSet;
import soot.*;

public class Main {

    public static void main(String[] args) {
        long start = System.nanoTime();


        /*Interval pom = new Interval(-5,50);
        IntervalSet intervalSet = new IntervalSet();
        intervalSet.addPenetrationInerval(pom);

        intervalSet.rmNumber(10);

        System.out.println(intervalSet);*/


        MetodLoader metodLoader = MetodLoader.getInstance();

        String classpath = "cz.zcu.kiv.jop.cf.TestingClass.Primitiv.TestInt";
        String metodName = "metod6";

        SootMethod method = metodLoader.getSootMetod(classpath, metodName);

        ControlFlowGraph cfg = new ControlFlowGraph(method);
        cfg.saveGraph("graph");

        Analyze.analysing(cfg);

        long end = System.nanoTime();

        long duration = (end - start) / 1000000;
        System.out.println("    " +duration + " ms");
    }

}
