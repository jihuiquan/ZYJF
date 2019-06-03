package finan.heng.com.apps.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * Created by lfu on 2017/2/22.
 */

public class ResponseData implements Serializable{

    private static final long serialVersionUID = 1L;

    @Expose
    @SerializedName("code")
    public String errorCode;

    @Expose
    @SerializedName("message")
    public String errorMsg;

    public void clearData(){

    }
    private static String SUCCESS = "0";
    public boolean isSuccess(){
        return SUCCESS.equals(errorCode);
    }
    public boolean needReSetPwd(){

        return "100".equals(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
