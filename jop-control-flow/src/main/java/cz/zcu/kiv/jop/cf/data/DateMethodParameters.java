package cz.zcu.kiv.jop.cf.data;

import java.util.ArrayList;
import java.util.List;

public class DateMethodParameters {

    ParameterInfo[] parameterInfos;
    List<ParametersValue> parametersValueList;

    public DateMethodParameters(int count){
        parameterInfos = new ParameterInfo[count];
        parametersValueList = new ArrayList<ParametersValue>();
    }

    public ParameterInfo[] getParameterInfos() {
        return parameterInfos;
    }

    public void setParameterInfos(ParameterInfo[] parameterInfos) {
        this.parameterInfos = parameterInfos;
    }

    public List<ParametersValue> getParametersValueList() {
        return parametersValueList;
    }

    public void setParametersValueList(List<ParametersValue> parametersValueList) {
        this.parametersValueList = parametersValueList;
    }
}
