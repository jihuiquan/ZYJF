package finan.heng.com.apps.model;

import finan.heng.com.apps.http.ResponseData;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class ServerInfo extends ResponseData{
    private ServerInfoDetail result;

    public ServerInfoDetail getResult() {
        return result;
    }

    public void setResult(ServerInfoDetail result) {
        this.result = result;
    }
}
