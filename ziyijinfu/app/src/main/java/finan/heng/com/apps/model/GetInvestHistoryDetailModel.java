package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetInvestHistoryDetailModel implements Serializable {

    private InvestDetailInfoBean investDetailInfo;

    public InvestDetailInfoBean getInvestDetailInfo() {
        return investDetailInfo;
    }
    public void setInvestDetailInfo(InvestDetailInfoBean investDetailInfo) {
        this.investDetailInfo = investDetailInfo;
    }
    public static class InvestDetailInfoBean {
        private String productId;
        private String addTime;
        private String title;
        private String amount;
        private String minProfit;
        private String showStatus;
        private String endTime;
        @Expose
        @SerializedName("plstimeLimit")
        private String tzqx;
        @Expose
        @SerializedName("interestSum")
        private String ygsy;
        @Expose
        @SerializedName("bonusesAmount")
        private String hbsy;
        @Expose
        @SerializedName("bonusesInterest")
        private String jxqsy;
        @Expose
        @SerializedName("minInterest")
        private String basicInterest;//基本收益
        /**出借金额*/
        private String amountLabel;
        /**出借时间*/
        private String addTimeLabel;
        /**历史年化*/
        private String minProfitLabel;
        /**出借期限*/
        private String plstimeLimitLabel;
        /**加息券收益*/
        private String bonusesInterestLabel;
        /**红包抵扣金额*/
        private String bonusesAmountLabel;
        /**基础收益*/
        private String minInterestLabel;
        /**预估总收益*/
        private String interestSumLabel;
        /**预计回款*/
        private String endTimeLabel;
        /**目前状态*/
        private String showStatusLabel;

        public void setBasicInterest(String basicInterest) {
            this.basicInterest = basicInterest;
        }

        public String getAmountLabel() {
            return amountLabel;
        }

        public void setAmountLabel(String amountLabel) {
            this.amountLabel = amountLabel;
        }

        public String getAddTimeLabel() {
            return addTimeLabel;
        }

        public void setAddTimeLabel(String addTimeLabel) {
            this.addTimeLabel = addTimeLabel;
        }

        public String getMinProfitLabel() {
            return minProfitLabel;
        }

        public void setMinProfitLabel(String minProfitLabel) {
            this.minProfitLabel = minProfitLabel;
        }

        public String getPlstimeLimitLabel() {
            return plstimeLimitLabel;
        }

        public void setPlstimeLimitLabel(String plstimeLimitLabel) {
            this.plstimeLimitLabel = plstimeLimitLabel;
        }

        public String getBonusesInterestLabel() {
            return bonusesInterestLabel;
        }

        public void setBonusesInterestLabel(String bonusesInterestLabel) {
            this.bonusesInterestLabel = bonusesInterestLabel;
        }

        public String getBonusesAmountLabel() {
            return bonusesAmountLabel;
        }

        public void setBonusesAmountLabel(String bonusesAmountLabel) {
            this.bonusesAmountLabel = bonusesAmountLabel;
        }

        public String getMinInterestLabel() {
            return minInterestLabel;
        }

        public void setMinInterestLabel(String minInterestLabel) {
            this.minInterestLabel = minInterestLabel;
        }

        public String getInterestSumLabel() {
            return interestSumLabel;
        }

        public void setInterestSumLabel(String interestSumLabel) {
            this.interestSumLabel = interestSumLabel;
        }

        public String getEndTimeLabel() {
            return endTimeLabel;
        }

        public void setEndTimeLabel(String endTimeLabel) {
            this.endTimeLabel = endTimeLabel;
        }

        public String getShowStatusLabel() {
            return showStatusLabel;
        }

        public void setShowStatusLabel(String showStatusLabel) {
            this.showStatusLabel = showStatusLabel;
        }

        public String getBasicInterest() {
            return basicInterest;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMinProfit() {
            return minProfit;
        }

        public void setMinProfit(String minProfit) {
            this.minProfit = minProfit;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTzqx() {
            return tzqx;
        }

        public void setTzqx(String tzqx) {
            this.tzqx = tzqx;
        }

        public String getYgsy() {
            return ygsy;
        }

        public void setYgsy(String ygsy) {
            this.ygsy = ygsy;
        }

        public String getHbsy() {
            return hbsy;
        }

        public void setHbsy(String hbsy) {
            this.hbsy = hbsy;
        }

        public String getJxqsy() {
            return jxqsy;
        }

        public void setJxqsy(String jxqsy) {
            this.jxqsy = jxqsy;
        }
    }
}
