package cz.zcu.kiv.jop.cf.TestingClass;

public class TestPerson {


    public boolean isAdults(Person p){

        if(18 < p.getAge()){
            return true;
        }else{
            return false;
        }
    }
}
