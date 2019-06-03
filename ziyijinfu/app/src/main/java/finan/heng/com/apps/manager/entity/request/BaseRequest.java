package finan.heng.com.apps.manager.entity.request;


import java.io.Serializable;

import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.save.DataCache;

/**
 * Created by huwenbao on 15/10/4.
 */
public class BaseRequest implements Serializable {
    private String data = "data";
    private String jsessionid = getSessionId();

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    private String getSessionId() {
        try {
            LoginResponse response = DataCache.instance.getCacheData("heng", "LoginResponse");
            return response.result.sessionId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
