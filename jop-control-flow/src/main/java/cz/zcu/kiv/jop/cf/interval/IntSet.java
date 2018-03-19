package cz.zcu.kiv.jop.cf.interval;

import java.util.ArrayList;
import java.util.List;

public class IntSet {
    List<IntInterval> intIntervalList;

    public IntSet(){
        intIntervalList = new ArrayList<IntInterval>();
        IntInterval intInterval = new IntInterval(10,20);
        intIntervalList.add(intInterval);
    }

    public void addPenetrationInerval(IntInterval interval){
        int min = interval.getMin();
        int max = interval.getMax();

        for(int i = 0; i < intIntervalList.size(); i++){

            if(intIntervalList.get(i).getMax() < min || max < intIntervalList.get(i).getMin() ){
                intIntervalList.remove(i);
                i--;
            }else{
                if(intIntervalList.get(i).getMin() < min){
                    intIntervalList.get(i).setMin(min);
                }

                if(intIntervalList.get(i).getMax() > max){
                    intIntervalList.get(i).setMax(max);
                }
            }
        }
    }

    public void rmNumber(int num){

        for(int i = 0; i < intIntervalList.size(); i++){

            int min = intIntervalList.get(i).getMin();
            int max = intIntervalList.get(i).getMax();

            if(max == num && num == min ){
                intIntervalList.remove(i);
                i--;
            }else if(min <= num && max >=  num){

                if(min == num){
                    intIntervalList.get(i).setMin(num+1);
                }else if(max == num){
                    intIntervalList.get(i).setMax(num-1);
                }else{
                    IntInterval in2 = new IntInterval(min, max);

                    intIntervalList.get(i).setMax(num-1);
                    in2.setMin(num+1);

                    intIntervalList.add(in2);
                }
            }
        }
    }

    @Override
    public String toString(){
        String out = "";
        for(int i = 0; i < intIntervalList.size(); i++){
            out = out + "<" +intIntervalList.get(i).getMin() + "," + intIntervalList.get(i).getMax() + ">";
            if(i+1 < intIntervalList.size()){
                out = out + " U ";
            }
        }
        return out;
    }
}
