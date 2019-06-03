package finan.heng.com.apps.model;

import java.io.Serializable;

public class TradeTypeModel implements Serializable {
    private String title;
    private String type;

    public TradeTypeModel() {
    }

    public TradeTypeModel(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
