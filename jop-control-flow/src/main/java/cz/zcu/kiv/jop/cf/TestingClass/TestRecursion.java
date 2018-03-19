package cz.zcu.kiv.jop.cf.TestingClass;

/**
 * Created by Jan on 12.02.2018.
 */
public class TestRecursion {

    public int metod1(int factorial){

        if (factorial == 0 || factorial == 1) return 1;

        return factorial * metod1(factorial - 1);
    }



}
