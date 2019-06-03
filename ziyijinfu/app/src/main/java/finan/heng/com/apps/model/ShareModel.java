package finan.heng.com.apps.model;

public class ShareModel {
//"title" : title,
//            "description" : description,
//            "icon" : icon,
//            "url" : url
    private String title;
    private String description;
    private String icon;
    private String url;

    public ShareModel() {
    }

    public ShareModel(String title, String description, String icon, String url) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
