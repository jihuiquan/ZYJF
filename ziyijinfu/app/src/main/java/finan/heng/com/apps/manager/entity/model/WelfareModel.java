package finan.heng.com.apps.manager.entity.model;

/**
 * Created by Administrator on 2017/11/20.
 */

public class WelfareModel {
    public String id;//福利id
    public String title;//红包
    public String bonuses;//20.000
    public String sources;

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String days;//57天后过期
    public String hbcondition;//单笔投资满5000.00元可用
    public String hbcondition2;//投资30天以上可用
    public String endtime;//有效期至2018/01/16
    public boolean isAvailable;
    public boolean isSelect;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBonuses() {
        return bonuses;
    }

    public void setBonuses(String bonuses) {
        this.bonuses = bonuses;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getHbcondition() {
        return hbcondition;
    }

    public void setHbcondition(String hbcondition) {
        this.hbcondition = hbcondition;
    }

    public String getHbcondition2() {
        return hbcondition2;
    }

    public void setHbcondition2(String hbcondition2) {
        this.hbcondition2 = hbcondition2;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
