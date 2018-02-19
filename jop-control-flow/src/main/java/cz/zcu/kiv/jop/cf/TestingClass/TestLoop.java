package cz.zcu.kiv.jop.cf.TestingClass;
/**
 * Created by Jan on 06.02.2018.
 */
public class TestLoop {


    public int metod1(int a, int b){
        int out = b + 10;

        while (a < out)
            a++;

        return a;
    }

    public void metod2(int a){

        do {
            a = a + 20;
        } while (a < 100);

    }

    public int metod3(int a){

        for(int i=1; i < 11; i++){
            a++;
            if(i > 6){
                System.out.println("AA");
            }
        }

        return a;
    }

    public void metod4( int[] numbers){

        for (int item : numbers) {
            System.out.println("Count is: " + item);
        }
    }

    public void metod5( int[] arrayOfInts){
        int i;
        for (i = 0; i < arrayOfInts.length; i++) {
            if (arrayOfInts[i] == 1) {
                break;
            }
        }
    }

}
