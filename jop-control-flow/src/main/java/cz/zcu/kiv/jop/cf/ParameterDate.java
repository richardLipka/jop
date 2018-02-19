package cz.zcu.kiv.jop.cf;

import soot.Type;

import java.util.List;

public class ParameterDate {

    Type type;

    List<?> value = null;

    String name;

    boolean isModified;

    public ParameterDate(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<?> getValue() {
        return value;
    }

    public void setValue(List<?> value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

}
