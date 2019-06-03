package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/15.
 */
public class BankListInfo implements Serializable {

     @Expose
    @SerializedName("bankList")
    public ArrayList<BankInfo> bankList;//银行ID，返回
    private String token;
    /**支付开通说明*/
    private String serviceAgreementLabel;
    /**手机*/
    private String mobile;

    public String getServiceAgreementLabel() {
        return serviceAgreementLabel;
    }

    public void setServiceAgreementLabel(String serviceAgreementLabel) {
        this.serviceAgreementLabel = serviceAgreementLabel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }
    @Override
    public String toString() {
        return "BankListInfo{" +
                "bankList=" + bankList +
                '}';
    }
}
