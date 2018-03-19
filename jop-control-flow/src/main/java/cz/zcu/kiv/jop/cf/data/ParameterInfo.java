package cz.zcu.kiv.jop.cf.data;

import soot.Type;
import soot.Value;

import java.util.ArrayList;
import java.util.List;

public class ParameterInfo {

    Type type;
    Value sootValue;
    String name;
    boolean isModified;

    public ParameterInfo(Type type){
        this.type = type;
    }
    public Value getSootValue() {
        return sootValue;
    }

    public void setSootValue(Value sootValue) {
        this.sootValue = sootValue;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
