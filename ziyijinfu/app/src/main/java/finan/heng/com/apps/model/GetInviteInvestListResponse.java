package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import finan.heng.com.apps.http.ResponseData;

public class GetInviteInvestListResponse extends ResponseData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose
    @SerializedName("result")
    public GetInviteInvestListModel result;

    @Override
    public String toString() {
        return "GetInviteInvestListResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
