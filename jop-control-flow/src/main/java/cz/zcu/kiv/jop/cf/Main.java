package cz.zcu.kiv.jop.cf;

import cz.zcu.kiv.jop.cf.cfg.ControlFlowGraph;
import soot.*;

public class Main {

    public static int index = 0;

    public static void main(String[] args) {
        long start = System.nanoTime();

        MetodLoader metodLoader = MetodLoader.getInstance();

        String classpath = "cz.zcu.kiv.jop.cf.TestingClass.TestIF";
        String metodName = "metod0";

        SootMethod method = metodLoader.getSootMetod(classpath, metodName);

        ControlFlowGraph cfg = new ControlFlowGraph(method);
        //cfg.saveGraph("graph");

        Analyze.analysing(cfg);

        long end = System.nanoTime();

        long duration = (end - start) / 1000000;
        System.out.println("    " +duration + " ms");
    }

}
