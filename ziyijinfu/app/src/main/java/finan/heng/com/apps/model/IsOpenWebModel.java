package finan.heng.com.apps.model;

import java.io.Serializable;

public class IsOpenWebModel implements Serializable {
    /**
     description = "";
     link = "https://itunes.apple.com/cn/app/id1412606155?mt=8";
     type = 0;
     version = "1.0.0";
     */
    private String description;
    private String link;
    private String type;
    private String version;

    public IsOpenWebModel() {
    }

    public IsOpenWebModel(String description, String link, String type, String version) {
        this.description = description;
        this.link = link;
        this.type = type;
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
