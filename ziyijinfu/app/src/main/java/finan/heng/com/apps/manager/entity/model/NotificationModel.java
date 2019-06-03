package finan.heng.com.apps.manager.entity.model;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class NotificationModel extends BaseModel{

    /**
     * hotTitle : 注册红包规则变更通知
     * time : 2017-07-28 19:26
     * url :
     */

    private String hotTitle;
    private String time;
    private String url;

    public String getHotTitle() {
        return hotTitle;
    }

    public void setHotTitle(String hotTitle) {
        this.hotTitle = hotTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NotificationModel{" +
                "hotTitle='" + hotTitle + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
