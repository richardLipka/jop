package cz.zcu.kiv.jop.cf.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableTable {
    private Map<String,VariableItem> mapItem;

    public VariableTable(){
        mapItem = new HashMap<String, VariableItem>();
    }

    public VariableTable(VariableTable vt){
        mapItem = new HashMap<String, VariableItem>();

        if(vt != null && vt.getMapItem() != null){
            Map<String,VariableItem> map = vt.getMapItem();
            for (Map.Entry<String, VariableItem> entry : map.entrySet()){
                mapItem.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map<String, VariableItem> getMapItem() {
        return mapItem;
    }

    public void setMapItem(Map<String, VariableItem> mapItem) {
        this.mapItem = mapItem;
    }
}
