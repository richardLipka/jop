package cz.zcu.kiv.jop.cf.interval;

import java.util.ArrayList;
import java.util.List;

public class IntervalSet {
    List<Interval> intervalList;

    public IntervalSet(){
        intervalList = new ArrayList<Interval>();
        Interval interval = new Interval();
        intervalList.add(interval);
    }

    public void addPenetrationInerval(Interval interval){
        double min = interval.getMin();
        double max = interval.getMax();

        for(int i = 0; i < intervalList.size(); i++){

            if(intervalList.get(i).getMax() < min || max < intervalList.get(i).getMin() ){
                intervalList.remove(i);
                i--;
            }else{
                if(intervalList.get(i).getMin() < min){
                    intervalList.get(i).setMin(min);
                }

                if(intervalList.get(i).getMax() > max){
                    intervalList.get(i).setMax(max);
                }
            }
        }
    }

    public void rmNumber(double num){

        for(int i = 0; i < intervalList.size(); i++){

            double min = intervalList.get(i).getMin();
            double max = intervalList.get(i).getMax();

            if(max == num && num == min ){
                intervalList.remove(i);
                i--;
            }else if(min <= num && max >=  num){

                if(min == num){
                    intervalList.get(i).setMin(num+1);
                }else if(max == num){
                    intervalList.get(i).setMax(num-1);
                }else{
                    Interval in2 = new Interval(min, max);

                    intervalList.get(i).setMax(num-1);
                    in2.setMin(num+1);

                    intervalList.add(in2);
                }
            }
        }
    }

    public double getMax(){
        double max = Long.MIN_VALUE;

        for(int i = 0; i < intervalList.size(); i++){
            if(intervalList.get(i).getMax() > max){
                max = intervalList.get(i).getMax();
            }
        }
        return max;
    }

    public double getMin(){
        double min = Long.MAX_VALUE;

        for(int i = 0; i < intervalList.size(); i++){
            if(intervalList.get(i).getMin() < min){
                min = intervalList.get(i).getMin();
            }
        }
        return min;
    }

    @Override
    public String toString(){
        String out = "";
        for(int i = 0; i < intervalList.size(); i++){
            out = out + "<" +intervalList.get(i).getMin() + "," + intervalList.get(i).getMax() + ">";
            if(i+1 < intervalList.size()){
                out = out + " U ";
            }
        }
        return out;
    }
}
