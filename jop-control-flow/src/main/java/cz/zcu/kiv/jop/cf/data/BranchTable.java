package cz.zcu.kiv.jop.cf.data;

import java.util.ArrayList;
import java.util.List;

public class BranchTable {

    List<BranchTableItem> branchTableItems;

    public BranchTable(){
        this.branchTableItems = new ArrayList<BranchTableItem>();
    }

    public List<BranchTableItem> getBranchTableItems() {
        return branchTableItems;
    }

    public void setBranchTableItems(List<BranchTableItem> branchTableItems) {
        this.branchTableItems = branchTableItems;
    }
}
