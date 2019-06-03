package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;

public class MyWalletModel implements Serializable {

    /**
     "availableAmount": "0.00",
     "bindingAction": "ToRealName",
     "CDKeyLabel": "我的礼品卡*",
     "totalAssetsLabel": "总资产（元）*",
     "bindingImageUrl": "https://download.ziyijinfu.com/other/binding.png",
     "totalInterest": "0.00",
     "tradeDetailLabel": "交易流水*",
     "cashBackSum": "1069.00",
     "redPacketLabel": "投资红包*",
     "investDetailLabel": "投资记录*",
     "shareLabel": "邀请好友*",
     "rechargeButtonLabel": "充值*",
     "title": "我的账户*",
     "balanceLabel": "账户余额（元）*",
     "allAssets": "0.00",
     "couponsLabel": "我的加息券*",
     "totalProfitLabel": "累计收益*",
     "bindingShow": true,
     "withdrawButtonLabel": "提现*",
     "bindingUrl": ""
     */
    /**
     * availableAmount : 20000000.00
     * allAssets : 20000000.00
     * totalInterest : 0.00
     */

    private String availableAmount;//可用
    private String allAssets;//所有资产
    private String totalInterest;//总利息

    private String cashBackSum;//红包余额
    private String bindingAction;//
    /**我的礼品卡*/
    private String CDKeyLabel;//
    /**总资产（元）*/
    private String totalAssetsLabel;//
    private String bindingImageUrl;//
    /**交易流水*/
    private String tradeDetailLabel;//
    /**投资红包*/
    private String redPacketLabel;//
    /**投资记录*/
    private String investDetailLabel;//
    /**邀请好友*/
    private String shareLabel;//
    /**充值*/
    private String rechargeButtonLabel;//
    /**我的账户*/
    private String title;//
    /**账户余额（元）*/
    private String balanceLabel;//
    /**我的加息券*/
    private String couponsLabel;//
    /**累计收益*/
    private String totalProfitLabel;//
    private String bindingShow;//
    /**提现*/
    private String withdrawButtonLabel;//
    private String bindingUrl;//

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getAllAssets() {
        return allAssets;
    }

    public void setAllAssets(String allAssets) {
        this.allAssets = allAssets;
    }

    public String getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(String totalInterest) {
        this.totalInterest = totalInterest;
    }

    public String getCashBackSum() {
        return cashBackSum;
    }

    public void setCashBackSum(String cashBackSum) {
        this.cashBackSum = cashBackSum;
    }

    public String getBindingAction() {
        return bindingAction;
    }

    public void setBindingAction(String bindingAction) {
        this.bindingAction = bindingAction;
    }

    public String getCDKeyLabel() {
        return CDKeyLabel;
    }

    public void setCDKeyLabel(String CDKeyLabel) {
        this.CDKeyLabel = CDKeyLabel;
    }

    public String getTotalAssetsLabel() {
        return totalAssetsLabel;
    }

    public void setTotalAssetsLabel(String totalAssetsLabel) {
        this.totalAssetsLabel = totalAssetsLabel;
    }

    public String getBindingImageUrl() {
        return bindingImageUrl;
    }

    public void setBindingImageUrl(String bindingImageUrl) {
        this.bindingImageUrl = bindingImageUrl;
    }

    public String getTradeDetailLabel() {
        return tradeDetailLabel;
    }

    public void setTradeDetailLabel(String tradeDetailLabel) {
        this.tradeDetailLabel = tradeDetailLabel;
    }

    public String getRedPacketLabel() {
        return redPacketLabel;
    }

    public void setRedPacketLabel(String redPacketLabel) {
        this.redPacketLabel = redPacketLabel;
    }

    public String getInvestDetailLabel() {
        return investDetailLabel;
    }

    public void setInvestDetailLabel(String investDetailLabel) {
        this.investDetailLabel = investDetailLabel;
    }

    public String getShareLabel() {
        return shareLabel;
    }

    public void setShareLabel(String shareLabel) {
        this.shareLabel = shareLabel;
    }

    public String getRechargeButtonLabel() {
        return rechargeButtonLabel;
    }

    public void setRechargeButtonLabel(String rechargeButtonLabel) {
        this.rechargeButtonLabel = rechargeButtonLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBalanceLabel() {
        return balanceLabel;
    }

    public void setBalanceLabel(String balanceLabel) {
        this.balanceLabel = balanceLabel;
    }

    public String getCouponsLabel() {
        return couponsLabel;
    }

    public void setCouponsLabel(String couponsLabel) {
        this.couponsLabel = couponsLabel;
    }

    public String getTotalProfitLabel() {
        return totalProfitLabel;
    }

    public void setTotalProfitLabel(String totalProfitLabel) {
        this.totalProfitLabel = totalProfitLabel;
    }

    public String getBindingShow() {
        return bindingShow;
    }

    public void setBindingShow(String bindingShow) {
        this.bindingShow = bindingShow;
    }

    public String getWithdrawButtonLabel() {
        return withdrawButtonLabel;
    }

    public void setWithdrawButtonLabel(String withdrawButtonLabel) {
        this.withdrawButtonLabel = withdrawButtonLabel;
    }

    public String getBindingUrl() {
        return bindingUrl;
    }

    public void setBindingUrl(String bindingUrl) {
        this.bindingUrl = bindingUrl;
    }
}
