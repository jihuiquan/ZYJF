package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class ProductInfo implements Serializable{
    public static final String TYPE_DAY = "0";
    public static final String TYPE_MONTH = "1";

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

    public static final String TYPE_NEWER = "-1"; // 新手
    public String getStatusDesc(String status){
        try {
            int sta = Integer.parseInt(status);
            switch (sta){
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
                    return "预告中";
                case STATUS_PURCHASE:
                    return "立即投资";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "未知状态";
        }
        return "未知状态";
    }

    @Expose
    @SerializedName("surplusAmount")
    public String surplusAmount;
    @Expose
    @SerializedName("productsId")
    public String productsId;
    @Expose
    @SerializedName("investBegin")
    public String investBegin;
    @Expose
    @SerializedName("status")
    public String status;
    @Expose
    @SerializedName("investPercent")
    public String investPercent;
    @Expose
    @SerializedName("title")
    public String title;
    @Expose
    @SerializedName("description")
    public String description;
    @Expose
    @SerializedName("plstimeLimitType")
    public String plstimeLimitType;
    @Expose
    @SerializedName("plstimeLimitValue")
    public String plstimeLimitValue;
    @Expose
    @SerializedName("type")
    public String type; // 标的类型 -1新手标
    @Expose
    @SerializedName("profit")
    public String profit; // 基本利率
    @Expose
    @SerializedName("profitFloat")
    public String profitFloat;// 浮动利率
    @Expose
    @SerializedName("investEnd")
    public String investEnd; // 投资限额
    @Expose
    @SerializedName("tags")
    public String tags;
    private String cashRateShow;//红包返现是否显示(0:否；1:是)
    private String cashRateProfit;//红包返现加息年化率
    private String profitLabel;
    /**投资期限*/
    private String plstimeLimitLabel;
    /**起投金额*/
    private String investBeginLabel;
    /**立即出借*/
    private String buttonLabel;
    /**标的类型角标*/
    private String typeIcon;
    private String surplusAmountLabel;
    /**还款方式*/
    private String repaymentTypeLabel;
    /**募集期限类型*/
    private String prdtimeLimitTypeLabel;

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

    public String getCashRateShow() {
        return cashRateShow;
    }

    public String getCashRateProfit() {
        return cashRateProfit;
    }

    public String getPeriodDesc(String plstimeLimitType){
        if (plstimeLimitType.equals(TYPE_DAY)){
            return "天";
        } else if (plstimeLimitType.equals(TYPE_MONTH)){
            return "个月";
        } else {
            return "未知期限类型";
        }
    }

    public boolean isNewer(){
        return type.equals(TYPE_NEWER);
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "surplusAmount='" + surplusAmount + '\'' +
                ", productsId='" + productsId + '\'' +
                ", investBegin='" + investBegin + '\'' +
                ", status='" + status + '\'' +
                ", investPercent='" + investPercent + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", plstimeLimitType='" + plstimeLimitType + '\'' +
                ", plstimeLimitValue='" + plstimeLimitValue + '\'' +
                ", type='" + type + '\'' +
                ", profit='" + profit + '\'' +
                ", profitFloat='" + profitFloat + '\'' +
                ", investEnd='" + investEnd + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
