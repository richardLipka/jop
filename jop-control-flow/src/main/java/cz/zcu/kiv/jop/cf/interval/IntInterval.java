package cz.zcu.kiv.jop.cf.interval;

import java.security.InvalidParameterException;

public class IntInterval {
    int min = Integer.MIN_VALUE;
    int max = Integer.MAX_VALUE;

    public IntInterval(){
    }

    public IntInterval(int min,int max){
        setMin(min);
        setMax(max);
    }

    public void setMin(int min){
        if(min <= max){
            this.min = min;
        }else{
            throw new InvalidParameterException("Max ("+max+") must be greater than the min ("+min+")");
        }
    }

    public void setMax(int max){
        if(min <= max){
            this.max = max;
        }else{
            throw new InvalidParameterException("Max ("+max+") must be greater than the min ("+min+")");
        }
    }

    public int getMin(){
        return min;
    }

    public int getMax(){
        return max;
    }

}
