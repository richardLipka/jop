package cz.zcu.kiv.jop.cf.data;

import soot.Type;
import soot.Value;
import soot.jimple.ParameterRef;

public class VariableItem {
    Type type;
    Value sootValue;
    String name;
    Boolean param = false;
    ParameterRef parameterRef = null;

    public Boolean getParam() {
        return param;
    }

    public void setParam(Boolean param) {
        this.param = param;
    }

    public ParameterRef getParameterRef() {
        return parameterRef;
    }

    public void setParameterRef(ParameterRef parameterRef) {
        this.parameterRef = parameterRef;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Value getSootValue() {
        return sootValue;
    }

    public void setSootValue(Value sootValue) {
        this.sootValue = sootValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
