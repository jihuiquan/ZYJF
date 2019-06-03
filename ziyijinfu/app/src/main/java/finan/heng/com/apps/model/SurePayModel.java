package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class SurePayModel implements Serializable {


    /**
     * paidTime : 2017-05-20 20:53:32
     * orderId : 201705202053321852
     */

    private String paidTime;
    private String orderId;
    // 投资金额
    private String amount;
    // 投资期限
    private String plstimeLimitValue;
    // 投资期限类型
    private String prdtimeLimitType;
    // 预估总收益
    private String profit;
    /**出借成功*/
    private String paymentSuccessTitle;
    /**恭喜你，出借成功*/
    private String paymentSuccessTips;
    /**出借金额*/
    private String paymentSuccessAmountLabel;
    /**出借期限*/
    private String paymentSuccessPlstimeLimitLabel;
    /**预估总收益*/
    private String paymentSuccessProfitLabel;
    /**出借奖励*/
    private String paymentSuccessRewardLabel;
    /**奖励在1-3个工作日发放，请到我的账户中查看。*/
    private String paymentSuccessrRwardTips;

    public String getPaymentSuccessTitle() {
        return paymentSuccessTitle;
    }

    public void setPaymentSuccessTitle(String paymentSuccessTitle) {
        this.paymentSuccessTitle = paymentSuccessTitle;
    }

    public String getPaymentSuccessTips() {
        return paymentSuccessTips;
    }

    public void setPaymentSuccessTips(String paymentSuccessTips) {
        this.paymentSuccessTips = paymentSuccessTips;
    }

    public String getPaymentSuccessAmountLabel() {
        return paymentSuccessAmountLabel;
    }

    public void setPaymentSuccessAmountLabel(String paymentSuccessAmountLabel) {
        this.paymentSuccessAmountLabel = paymentSuccessAmountLabel;
    }

    public String getPaymentSuccessPlstimeLimitLabel() {
        return paymentSuccessPlstimeLimitLabel;
    }

    public void setPaymentSuccessPlstimeLimitLabel(String paymentSuccessPlstimeLimitLabel) {
        this.paymentSuccessPlstimeLimitLabel = paymentSuccessPlstimeLimitLabel;
    }

    public String getPaymentSuccessProfitLabel() {
        return paymentSuccessProfitLabel;
    }

    public void setPaymentSuccessProfitLabel(String paymentSuccessProfitLabel) {
        this.paymentSuccessProfitLabel = paymentSuccessProfitLabel;
    }

    public String getPaymentSuccessRewardLabel() {
        return paymentSuccessRewardLabel;
    }

    public void setPaymentSuccessRewardLabel(String paymentSuccessRewardLabel) {
        this.paymentSuccessRewardLabel = paymentSuccessRewardLabel;
    }

    public String getPaymentSuccessrRwardTips() {
        return paymentSuccessrRwardTips;
    }

    public void setPaymentSuccessrRwardTips(String paymentSuccessrRwardTips) {
        this.paymentSuccessrRwardTips = paymentSuccessrRwardTips;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private ArrayList<SurePayBonusModel> userBonusesList;

    public ArrayList<SurePayBonusModel> getUserBonusesList() {
        return userBonusesList;
    }


    public void setUserBonusesList(ArrayList<SurePayBonusModel> userBonusesList) {
        this.userBonusesList = userBonusesList;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPlstimeLimitValue() {
        return plstimeLimitValue;
    }

    public void setPlstimeLimitValue(String plstimeLimitValue) {
        this.plstimeLimitValue = plstimeLimitValue;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getPrdtimeLimitType() {
        return prdtimeLimitType;
    }

    public void setPrdtimeLimitType(String prdtimeLimitType) {
        this.prdtimeLimitType = prdtimeLimitType;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "SurePayModel{" +
                "paidTime='" + paidTime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount='" + amount + '\'' +
                ", plstimeLimitValue='" + plstimeLimitValue + '\'' +
                ", prdtimeLimitType='" + prdtimeLimitType + '\'' +
                ", profit='" + profit + '\'' +
                ", token='" + token + '\'' +
                ", userBonusesList=" + userBonusesList +
                '}';
    }
}
