package finan.heng.com.apps.model;

import java.io.Serializable;
import java.util.List;

public class InvestRecordList implements Serializable {
    private List<InvestRecord> cdkeyTypeList;

    public List<InvestRecord> getCdkeyTypeList() {
        return cdkeyTypeList;
    }

    public void setCdkeyTypeList(List<InvestRecord> cdkeyTypeList) {
        this.cdkeyTypeList = cdkeyTypeList;
    }
}
