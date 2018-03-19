package cz.zcu.kiv.jop.cf;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.options.Options;

public class MetodLoader {

    private static MetodLoader instance = null;

    private MetodLoader() {
        setSoot();
    }

    public SootMethod getSootMetod(String path, String metod){
        SootClass sootClass = getSootClass(path);

        return sootClass.getMethodByName(metod);
    }

    private SootClass getSootClass(String name){
        Scene scene = Scene.v();
        SootClass c = Scene.v().loadClassAndSupport(name);
        c.setApplicationClass();

        scene.loadNecessaryClasses();

        return c;
    }

    public static MetodLoader getInstance() {
        if(instance == null) {
            instance = new MetodLoader();
        }
        return instance;
    }

    private static void setSoot(){
        String dir = System.getProperty("user.dir");

        dir += "\\src\\main\\java\\";
        System.out.println("current dir =   " + dir);

        Options.v().set_whole_program(true);
        //Options.v().set_verbose(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_output_format(Options.output_format_jimple);
        //Options.v().set_soot_classpath(Scene.v().defaultClassPath() + dir);
        Scene.v().setSootClassPath(Scene.v().getSootClassPath() + ";" + dir);

        System.out.println("Soot class path " + Scene.v().getSootClassPath());
    }
}
