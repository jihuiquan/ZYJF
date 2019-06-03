package finan.heng.com.apps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/18 23:16
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class BaoFooResult implements Serializable{

    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public Boolean success;
    public String amount;
    public String resultUrl;
    public String title;
    private String token;
    public String phone;
    public boolean islink;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "BaoFooResult{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", resultUrl='" + resultUrl + '\'' +
                ", title='" + title + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
