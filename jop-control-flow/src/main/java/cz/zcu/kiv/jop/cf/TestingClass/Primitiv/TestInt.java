package cz.zcu.kiv.jop.cf.TestingClass.Primitiv;
/**
 * Created by Jan on 06.02.2018.
 */
public class TestInt {


    public int metod0(int a){
        if(a > 123){
            a = -1;
        }
        else{
            a = 66;
        }
        return a;
    }

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

        for(int i = 42; i < a; i++){
            out += 2;
        }

        return out;
    }

    public int metod3(int a){
        int out = 0;

       if(a < 10){
           if(a > 5){
               out++;
           }else{
               out--;
           }

       }
        return out;
    }

    public int metod4(int a, int b){
        int out = 0;

        for(int i = 0; i < a; i++){
            if(a < b){
                out += 2;
            }else{
                out *= 2;
            }

        }
        return out;
    }

    public void metod5(int a){
        while(a < 10){
            a--;
        }
    }

    public void metod6(int a){
        int out = 0;
        switch (a){
            case 1: out = 11; break;
            case 2: out = 22; break;
            case 3: out = 33; break;
            default: out = 44;
        }

    }
}
