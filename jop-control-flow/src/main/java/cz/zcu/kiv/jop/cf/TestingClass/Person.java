package cz.zcu.kiv.jop.cf.TestingClass;

public class Person {

    public Calor haircolor;

    public int age;


    public Person(int age, Calor haircolor){
        this.age = age;
        this.haircolor = haircolor;
    }

    public Calor getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(Calor haircolor) {
        this.haircolor = haircolor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
