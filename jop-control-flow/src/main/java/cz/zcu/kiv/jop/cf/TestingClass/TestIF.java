package cz.zcu.kiv.jop.cf.TestingClass;
/**
 * Created by Jan on 06.02.2018.
 */
public class TestIF {

    public int metod1(int a, int b){
        int out;
        if(a < b)
            out = a;
        else
            out = b;


        return out;
    }

    public int metod2(int a){
        int out = 0;
        if(a < 10){
            out = a;
        }else if(a < 100){
            out = a*2;
        }else {
            out = (a-2)*2;
        }
        return out;
    }

    public int metod3(int a){
        int out = 0;
        if(a < 10){
            out = a;
        }else if(a < 100){
            out = a*2;
        }else if(a < 1000){
            out = a*3;
        }else {
            out = (a-2)*2;
        }
        return out;
    }

    public int metod4(int day){

        int out = 10;
        switch (day) {

            case 1: out = 1; break;
            case 2: out = 2; break;
            case 3: out = 3; break;
            default: out = 4; break;
        }

        return out;
    }

    public int metod5(int day){

        int out = 10;
        switch (day) {

            case 1: out = 1;
            case 2: out = 2;
            case 3: out = 3; break;
            default: break;
        }
        return out;
    }

    public int metod6(int day){

        int out = 10;
        switch (day) {

            case 1: case 2: case 3: out = 3; break;
            default: System.out.println("NO"); break;
        }

        return out;
    }

    public int metod7(int day){
        return day = day < 5 ? 6 : 7;
    }
}
