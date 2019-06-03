package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import finan.heng.com.apps.http.ResponseData;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class CompanyInfoResponse extends ResponseData implements Serializable{
    @Expose
    @SerializedName("result")
    public CompanyInfoResponseDeta result;

    public CompanyInfoResponseDeta getResult() {
        return result;
    }

    public void setResult(CompanyInfoResponseDeta result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CompanyInfoResponse{" +
                "result=" + result +
                '}';
    }
}
