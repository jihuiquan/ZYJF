package finan.heng.com.apps.manager.entity.model;

import android.graphics.Color;

import java.util.Arrays;
import java.util.List;

import finan.zhimabao.com.apps.R;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class ProductModel extends BaseModel{


    public static final int TYPE_DAY = 0;
    public static final int TYPE_MONTH = 1;

    public static final int STATUS_FINISH = 60; // 已完成
    public static final int STATUS_REPAYMENT = 50; // 还款中
    public static final int STATUS_REVIEW_THROUGH = 41; // 复审通过
    public static final int STATUS_REVIEW_WAITING = 40 ; // 待复审
    public static final int STATUS_REVIEW_PASS = -41; //复审未通过
    public static final int STATUS_FIRST_PASS = -11; // 初审未通过
    public static final int STATUS_FIRST_WAITING = 10; // 待初审
    public static final int STATUS_FIRST_THROUGH = 11;// 初审通过
    public static final int STATUS_NOTICE = 20; //预告中
    public static final int STATUS_PURCHASE = 30; // 立即投资

    public String getStatusDesc(int status){
        switch (status){
            case STATUS_FINISH:
                return "已完成";
            case STATUS_REPAYMENT:
                return "还款中";
            case STATUS_REVIEW_THROUGH:
                return "复审通过";
            case STATUS_REVIEW_WAITING:
                return "待复审";
            case STATUS_REVIEW_PASS:
                return "复审未通过";
            case STATUS_FIRST_PASS:
                return "初审未通过";
            case STATUS_FIRST_WAITING:
                return "待初审";
            case STATUS_FIRST_THROUGH:
                return "初审通过";
            case STATUS_NOTICE:
                return "预售中";
            case STATUS_PURCHASE:
                return "立即投资";
        }
        return "未知状态";
    }
    /**
     * profit : 0.09900
     * surplusAmount : 500000.00
     * title : 恒宝004期
     * investBegin : 100.00
     * productsId : 326
     * status : 30
     * description : 全场加息3%
     * investEnd :
     * plstimeLimitType : 0
     * profitFloat :
     * type : 0
     * plstimeLimitValue : 30
     * investPercent : 0
     */
    private String productTitle;
    private double profit;
    private double surplusAmount;
    private String title;
    private String investBegin;
    private int productsId;
    private int status;
    private String description;
    private String investEnd;
    private int plstimeLimitType;
    private double profitFloat;
    private int type;
    private String plstimeLimitValue;
    private double investPercent;
    private boolean isActivity;
    private String activityName;
    private String[] tags;
    private double profitScale; // 募集总额
    private String leftDesc;
    private String rightDesc;
    private String cashRateShow;//红包返现是否显示(0:否；1:是)
    private String cashRateProfit;//红包返现加息年化率
    /**标的状态角标*/
    private String productListImageUrl;
    private String profitLabel;
    /**投资期限*/
    private String plstimeLimitLabel;
    /**起投金额*/
    private String investBeginLabel;
    /**剩余可投*/
    private String surplusAmountLabel;
    /**还款方式*/
    private String repaymentTypeLabel;
    /**募集期限类型*/
    private String prdtimeLimitTypeLabel;
    /**立即出借*/
    private String buttonLabel;
    /**标的类型角标*/
    private String typeIcon;

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public String getRepaymentTypeLabel() {
        return repaymentTypeLabel;
    }

    public void setRepaymentTypeLabel(String repaymentTypeLabel) {
        this.repaymentTypeLabel = repaymentTypeLabel;
    }

    public String getPrdtimeLimitTypeLabel() {
        return prdtimeLimitTypeLabel;
    }

    public void setPrdtimeLimitTypeLabel(String prdtimeLimitTypeLabel) {
        this.prdtimeLimitTypeLabel = prdtimeLimitTypeLabel;
    }

    public String getSurplusAmountLabel() {
        return surplusAmountLabel;
    }

    public void setSurplusAmountLabel(String surplusAmountLabel) {
        this.surplusAmountLabel = surplusAmountLabel;
    }

    public String getProductListImageUrl() {
        return productListImageUrl;
    }

    public void setProductListImageUrl(String productListImageUrl) {
        this.productListImageUrl = productListImageUrl;
    }

    public String getProfitLabel() {
        return profitLabel;
    }

    public void setProfitLabel(String profitLabel) {
        this.profitLabel = profitLabel;
    }

    public String getPlstimeLimitLabel() {
        return plstimeLimitLabel;
    }

    public void setPlstimeLimitLabel(String plstimeLimitLabel) {
        this.plstimeLimitLabel = plstimeLimitLabel;
    }

    public String getInvestBeginLabel() {
        return investBeginLabel;
    }

    public void setInvestBeginLabel(String investBeginLabel) {
        this.investBeginLabel = investBeginLabel;
    }

    public void setCashRateShow(String cashRateShow) {
        this.cashRateShow = cashRateShow;
    }

    public void setCashRateProfit(String cashRateProfit) {
        this.cashRateProfit = cashRateProfit;
    }

    public Boolean getCashRateShow() {
        if (cashRateShow != null && cashRateShow.equals("1")) {
            return true;
        }
        return false;
    }

    public String getCashRateProfit() {
        return cashRateProfit;
    }
    public String getLeftDesc() {
        return leftDesc;
    }

    public void setLeftDesc(String leftDesc) {
        this.leftDesc = leftDesc;
    }

    public String getRightDesc() {
        return rightDesc;
    }

    public void setRightDesc(String rightDesc) {
        this.rightDesc = rightDesc;
    }

    public double getProfitScale() {
        return profitScale;
    }

    public void setProfitScale(double profitScale) {
        this.profitScale = profitScale;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(double surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInvestBegin() {
        return investBegin;
    }

    public void setInvestBegin(String investBegin) {
        this.investBegin = investBegin;
    }

    public int getProductsId() {
        return productsId;
    }

    public void setProductsId(int productsId) {
        this.productsId = productsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestEnd() {
        return investEnd;
    }

    public void setInvestEnd(String investEnd) {
        this.investEnd = investEnd;
    }

    public int getPlstimeLimitType() {
        return plstimeLimitType;
    }

    public void setPlstimeLimitType(int plstimeLimitType) {
        this.plstimeLimitType = plstimeLimitType;
    }

    public double getProfitFloat() {
        return profitFloat;
    }

    public void setProfitFloat(double profitFloat) {
        this.profitFloat = profitFloat;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPlstimeLimitValue() {
        return plstimeLimitValue;
    }

    public void setPlstimeLimitValue(String plstimeLimitValue) {
        this.plstimeLimitValue = plstimeLimitValue;
    }

    public double getInvestPercent() {
        return investPercent;
    }

    public void setInvestPercent(double investPercent) {
        this.investPercent = investPercent;
    }

    public boolean isActivity() {
        return isActivity;
    }

    public void setActivity(boolean activity) {
        isActivity = activity;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getPeriodDesc(int plstimeLimitType){
        switch (plstimeLimitType){
            case TYPE_DAY:
                return "天";
            case TYPE_MONTH:
                return "个月";
        }
        return "未知期限类型";
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productTitle='" + productTitle + '\'' +
                ", profit=" + profit +
                ", surplusAmount=" + surplusAmount +
                ", title='" + title + '\'' +
                ", investBegin='" + investBegin + '\'' +
                ", productsId=" + productsId +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", investEnd='" + investEnd + '\'' +
                ", plstimeLimitType=" + plstimeLimitType +
                ", profitFloat=" + profitFloat +
                ", type=" + type +
                ", plstimeLimitValue='" + plstimeLimitValue + '\'' +
                ", investPercent=" + investPercent +
                ", isActivity=" + isActivity +
                ", activityName='" + activityName + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", profitScale=" + profitScale +
                ", leftDesc='" + leftDesc + '\'' +
                ", rightDesc='" + rightDesc + '\'' +
                '}';
    }

    public boolean isHasAddRate(){
        return getProfitFloat() > 0;
    }
    public int getResourceByStatus(int status){
        if (status == STATUS_NOTICE){
            return R.drawable.btn_commit_circle_yellow_left;
        }else if (status == 50|| status == 41 || status == 40 || status == 60 || status == 10 || status == 11 || status == -11 || status == -41){
            return R.drawable.btn_commit_circle_gray_left;
        }
        return R.drawable.btn_commit_circle_red_left;
    }
    public  int getColorByStatus(int status){
        if (status == STATUS_NOTICE){
            return Color.parseColor("#fdb43c");
        }else if (status == 50 || status == 41 || status == 40 || status == 60 || status == 10 || status == 11 || status == -11 || status == -41){
            return Color.parseColor("#999999");
        }
        return Color.parseColor("#fc291d");
    }
}
