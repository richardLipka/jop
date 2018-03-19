package cz.zcu.kiv.jop.cf.data;

public class ParametersValue {

    Object[] values;

    public ParametersValue(int count){
        values = new Object[count];
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
