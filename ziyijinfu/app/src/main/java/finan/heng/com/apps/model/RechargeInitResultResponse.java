package finan.heng.com.apps.model;

import com.dynamic.foundations.common.utils.StringUtils;

import finan.heng.com.apps.http.ResponseData;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class RechargeInitResultResponse extends ResponseData {

    /**
     * availableMoney : 0.00
     * tips : 单笔限额50000元，每日限额500000元，每月限额2000000元。
     * bankName : 招商银行
     * bankCode : 8622
     * bankImageUrl : https://admin.jincaiwa.com/upload/image/20170929203905612.png
     */

    private String availableMoney;
    private String tips;
    private String bankName;
    private String bankCode;
    private String bankImageUrl;
    private String bankTips;
    private String bankStatus;
    private String bankSingleLimit;//单笔限额
    private String token;
    public String checkModel;//sms：验证码短信、pass：支付密码
    /**标题*/
    private String title;
    /**充值银行卡*/
    private String bankLabel;
    /**账户余额*/
    private String balanceLabel;
    /**充值金额（元）*/
    private String amountLabel;
    /**最少充值100元*/
    private String amountTips;
    /**支付方式*/
    private String paymentTypeLabel;
    /**确认充值*/
    private String buttonLabel;
    /**子壹金服快捷支付服务协议*/
    private String serviceAgreementLabel;

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

    public String getAmountTips() {
        return amountTips;
    }

    public void setAmountTips(String amountTips) {
        this.amountTips = amountTips;
    }

    public String getPaymentTypeLabel() {
        return paymentTypeLabel;
    }

    public void setPaymentTypeLabel(String paymentTypeLabel) {
        this.paymentTypeLabel = paymentTypeLabel;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public String getServiceAgreementLabel() {
        return serviceAgreementLabel;
    }

    public void setServiceAgreementLabel(String serviceAgreementLabel) {
        this.serviceAgreementLabel = serviceAgreementLabel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBankSingleLimit() {
        return bankSingleLimit;
    }

    public void setBankSingleLimit(String bankSingleLimit) {
        this.bankSingleLimit = bankSingleLimit;
    }
    public RechargeInitResultPayment getPayment() {
        return payment;
    }

    public void setPayment(RechargeInitResultPayment payment) {
        this.payment = payment;
    }

    private RechargeInitResultPayment payment;

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

    public String getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(String availableMoney) {
        this.availableMoney = availableMoney;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankImageUrl() {
        return bankImageUrl;
    }

    public void setBankImageUrl(String bankImageUrl) {
        this.bankImageUrl = bankImageUrl;
    }

    public boolean isNotInService(){
        return StringUtils.equals(bankStatus, "1");
    }

    @Override
    public String toString() {
        return "RechargeInitResultResponse{" +
                "availableMoney='" + availableMoney + '\'' +
                ", tips='" + tips + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", bankImageUrl='" + bankImageUrl + '\'' +
                ", bankTips='" + bankTips + '\'' +
                ", bankStatus='" + bankStatus + '\'' +
                ", bankSingleLimit='" + bankSingleLimit + '\'' +
                ", token='" + token + '\'' +
                ", payment=" + payment +
                '}';
    }
}
