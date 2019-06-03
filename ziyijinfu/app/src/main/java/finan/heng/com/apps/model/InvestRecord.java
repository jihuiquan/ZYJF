package finan.heng.com.apps.model;

import java.io.Serializable;

public class InvestRecord implements Serializable {
    private String title;
    private String status;

    public InvestRecord() {
    }

    public InvestRecord(String title, String status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
