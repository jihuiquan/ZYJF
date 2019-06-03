package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class BaseResponse<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    @Expose
    @SerializedName("code")
    public String errorCode;

    @Expose
    @SerializedName("message")
    public String errorMsg;

    public T result;

    public void clearData(){

    }
    private static String SUCCESS = "0";
    public boolean isSuccess(){
        return SUCCESS.equals(errorCode);
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
        return "BaseResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
