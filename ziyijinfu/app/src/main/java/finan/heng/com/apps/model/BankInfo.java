package finan.heng.com.apps.model;

import com.dynamic.foundations.common.utils.StringUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20.
 */
public class BankInfo implements Serializable {

    @Expose
    @SerializedName("bankLimitTips")
    public String bankLimit;

    @Expose
    @SerializedName("bankName")
    public String bankName;//银行名字
    @Expose
    @SerializedName("bankImage")
    public String bankImage;//银行图标
    @Expose
    @SerializedName("bankId")
    public String bankId;//银行ID，返回
    @Expose
    @SerializedName("bankStatus")
    public String bankStatus;
    @Expose
    @SerializedName("bankTips")
    public String bankTips;
    @Expose
    @SerializedName("recommendBankShow")
    public String recommendBankShow;

    public String getBankLimit() {
        return bankLimit;
    }

    public void setBankLimit(String bankLimit) {
        this.bankLimit = bankLimit;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankImage() {
        return bankImage;
    }

    public void setBankImage(String bankImage) {
        this.bankImage = bankImage;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(String bankStatus) {
        this.bankStatus = bankStatus;
    }

    public String getBankTips() {
        return bankTips;
    }

    public void setBankTips(String bankTips) {
        this.bankTips = bankTips;
    }

    public String getRecommendBankShow() {
        return recommendBankShow;
    }

    public void setRecommendBankShow(String recommendBankShow) {
        this.recommendBankShow = recommendBankShow;
    }

    public boolean isNotInService(){
       return StringUtils.equals(bankStatus, "1");
    }

    @Override
    public String toString() {
        return "BankInfo{" +
                "bankLimit='" + bankLimit + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankImage='" + bankImage + '\'' +
                ", bankId='" + bankId + '\'' +
                ", bankStatus='" + bankStatus + '\'' +
                ", bankTips='" + bankTips + '\'' +
                ", recommendBankShow='" + recommendBankShow + '\'' +
                '}';
    }

    public boolean isShow(){
        return StringUtils.equals(recommendBankShow, "1");
    }
}
