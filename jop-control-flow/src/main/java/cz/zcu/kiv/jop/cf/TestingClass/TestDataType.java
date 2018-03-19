package cz.zcu.kiv.jop.cf.TestingClass;
/**
 * Created by Jan on 06.02.2018.
 */
public class TestDataType {


    public int metod0(int[] a){
        int out = 5;

        if(a[0] == 123)
            out = -55;

        return out;
    }
    
    public int metoda1(String[] a){
        return a.length;
    }


}