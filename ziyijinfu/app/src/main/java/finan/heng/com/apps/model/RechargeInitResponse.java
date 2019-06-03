package finan.heng.com.apps.model;

import finan.heng.com.apps.http.ResponseData;
import finan.heng.com.apps.manager.entity.respond.BaseRespond;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class RechargeInitResponse extends ResponseData{
    public RechargeInitResultResponse result;

    @Override
    public String toString() {
        return "RechargeInitResponse{" +
                "result=" + result +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
