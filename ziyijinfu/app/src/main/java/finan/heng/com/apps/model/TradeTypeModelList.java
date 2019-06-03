package finan.heng.com.apps.model;

import java.io.Serializable;
import java.util.List;

public class TradeTypeModelList implements Serializable{
    private List<TradeTypeModel> cdkeyTypeList;

    public List<TradeTypeModel> getCdkeyTypeList() {
        return cdkeyTypeList;
    }

    public void setCdkeyTypeList(List<TradeTypeModel> cdkeyTypeList) {
        this.cdkeyTypeList = cdkeyTypeList;
    }
}
