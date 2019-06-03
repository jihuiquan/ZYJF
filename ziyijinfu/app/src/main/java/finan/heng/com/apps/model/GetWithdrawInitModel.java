package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import com.dynamic.foundations.common.utils.StringUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetWithdrawInitModel implements Serializable {


    /**
     * txyhImg : http://192.168.1.204:8080/landscape-admin/upload/image/20161024135636718.png
     * txyhkName : 中国工商银行
     * txye : 4907000.00
     * txyhkCode : 7890
     */
    @Expose
    @SerializedName("bankImageUrl")
    private String txyhImg;
    @Expose
    @SerializedName("bankName")
    private String txyhkName;
    @Expose
    @SerializedName("availableMoney")
    private String txye;
    @Expose
    @SerializedName("tips")
    private String txwxts;
    @Expose
    @SerializedName("bankCode")
    private String txyhkCode;
    private String czwxts;
    private String bankTips;
    private String bankStatus;
    private String withdrawTips;
    private String amountTips;
    private String bankSingleLimit;//单笔限额
    private String token;
    /**提现标题*/
    private String title;
    /**提现至银行卡*/
    private String bankLabel;
    /**账户余额*/
    private String balanceLabel;
    /**提现金额（元）*/
    private String amountLabel;
    /**实际到账金额*/
    private String amount2Label;
    /**确认提现*/
    private String buttonLabel;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBankLabel() {
        return bankLabel;
    }

    public void setBankLabel(String bankLabel) {
        this.bankLabel = bankLabel;
    }

    public String getBalanceLabel() {
        return balanceLabel;
    }

    public void setBalanceLabel(String balanceLabel) {
        this.balanceLabel = balanceLabel;
    }

    public String getAmountLabel() {
        return amountLabel;
    }

    public void setAmountLabel(String amountLabel) {
        this.amountLabel = amountLabel;
    }

    public String getAmount2Label() {
        return amount2Label;
    }

    public void setAmount2Label(String amount2Label) {
        this.amount2Label = amount2Label;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public String getAmountTips() {
        return amountTips;
    }

    public void setAmountTips(String amountTips) {
        this.amountTips = amountTips;
    }

    public String getWithdrawTips() {
        return withdrawTips;
    }

    public void setWithdrawTips(String withdrawTips) {
        this.withdrawTips = withdrawTips;
    }

    public String getBankSingleLimit() {
        return bankSingleLimit;
    }

    public void setBankSingleLimit(String bankSingleLimit) {
        this.bankSingleLimit = bankSingleLimit;
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

    public String getCzwxts() {
        return czwxts;
    }

    public void setCzwxts(String czwxts) {
        this.czwxts = czwxts;
    }

    public String getTxwxts() {
        return txwxts;
    }

    public void setTxwxts(String txwxts) {
        this.txwxts = txwxts;
    }

    public String getTxyhImg() {
        return txyhImg;
    }

    public void setTxyhImg(String txyhImg) {
        this.txyhImg = txyhImg;
    }

    public String getTxyhkName() {
        return txyhkName;
    }

    public void setTxyhkName(String txyhkName) {
        this.txyhkName = txyhkName;
    }

    public String getTxye() {
        return txye;
    }

    public void setTxye(String txye) {
        this.txye = txye;
    }

    public String getTxyhkCode() {
        return txyhkCode;
    }

    public void setTxyhkCode(String txyhkCode) {
        this.txyhkCode = txyhkCode;
    }

    public boolean isNotInService(){
        return StringUtils.equals(bankStatus, "1");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "GetWithdrawInitModel{" +
                "txyhImg='" + txyhImg + '\'' +
                ", txyhkName='" + txyhkName + '\'' +
                ", txye='" + txye + '\'' +
                ", txwxts='" + txwxts + '\'' +
                ", txyhkCode='" + txyhkCode + '\'' +
                ", czwxts='" + czwxts + '\'' +
                ", bankTips='" + bankTips + '\'' +
                ", bankStatus='" + bankStatus + '\'' +
                ", withdrawTips='" + withdrawTips + '\'' +
                ", amountTips='" + amountTips + '\'' +
                ", bankSingleLimit='" + bankSingleLimit + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
