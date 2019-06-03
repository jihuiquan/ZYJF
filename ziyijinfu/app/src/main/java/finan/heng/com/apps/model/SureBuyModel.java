package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import java.io.Serializable;
import java.util.List;

public class SureBuyModel implements Serializable {
    private UserAccountBean userAccount;
    private ProductsBean products;
    private List<GetProductRedPackModel.BonusesByTypeBean> redList;
    private List<GetProductRedPackModel.BonusesByTypeBean> couponList;
    private String surplusAmount2;
    private String token;
    /**标题*/
    private String title;
    /**合约年化收益率*/
    private String profitLabel;
    /**投资期限*/
    private String plstimeLimitLabel;
    /**投资限额*/
    private String investEndLabel;
    /**剩余可投*/
    private String surplusAmountLabel;
    /**账户余额*/
    private String balanceLabel;
    /**投资金额*/
    private String amountLabel;
    /**余额充值*/
    private String rechargeButtonLabel;
    /**剩余全投*/
    private String amountButtonLabel;
    /**红包*/
    private String redPacketLabel;
    /**加息券*/
    private String couponsLabel;
    /**基本收益*/
    private String basicProfitLabel;
    /**预估总收益*/
    private String totalProfitLabel;
    /**确认出借*/
    private String buttonLabel;
    /**子壹金服投资协议*/
    private String investmentAgreementName;
    /**风险提示函*/
    private String riskReminderName;
    /**投资金额提示*/
    private String amountTips;
    /**红包启用状态*/
    private boolean redPacketStatus;
    /**加息券启用状态*/
    private boolean couponsStatus;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRedPacketStatus() {
        return redPacketStatus;
    }

    public void setRedPacketStatus(boolean redPacketStatus) {
        this.redPacketStatus = redPacketStatus;
    }

    public boolean isCouponsStatus() {
        return couponsStatus;
    }

    public void setCouponsStatus(boolean couponsStatus) {
        this.couponsStatus = couponsStatus;
    }

    public String getAmountTips() {
        return amountTips;
    }

    public void setAmountTips(String amountTips) {
        this.amountTips = amountTips;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public String getInvestmentAgreementName() {
        return investmentAgreementName;
    }

    public void setInvestmentAgreementName(String investmentAgreementName) {
        this.investmentAgreementName = investmentAgreementName;
    }

    public String getRiskReminderName() {
        return riskReminderName;
    }

    public void setRiskReminderName(String riskReminderName) {
        this.riskReminderName = riskReminderName;
    }

    public String getRechargeButtonLabel() {
        return rechargeButtonLabel;
    }

    public void setRechargeButtonLabel(String rechargeButtonLabel) {
        this.rechargeButtonLabel = rechargeButtonLabel;
    }

    public String getAmountButtonLabel() {
        return amountButtonLabel;
    }

    public void setAmountButtonLabel(String amountButtonLabel) {
        this.amountButtonLabel = amountButtonLabel;
    }

    public String getRedPacketLabel() {
        return redPacketLabel;
    }

    public void setRedPacketLabel(String redPacketLabel) {
        this.redPacketLabel = redPacketLabel;
    }

    public String getCouponsLabel() {
        return couponsLabel;
    }

    public void setCouponsLabel(String couponsLabel) {
        this.couponsLabel = couponsLabel;
    }

    public String getBasicProfitLabel() {
        return basicProfitLabel;
    }

    public void setBasicProfitLabel(String basicProfitLabel) {
        this.basicProfitLabel = basicProfitLabel;
    }

    public String getTotalProfitLabel() {
        return totalProfitLabel;
    }

    public void setTotalProfitLabel(String totalProfitLabel) {
        this.totalProfitLabel = totalProfitLabel;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getInvestEndLabel() {
        return investEndLabel;
    }

    public void setInvestEndLabel(String investEndLabel) {
        this.investEndLabel = investEndLabel;
    }

    public String getSurplusAmountLabel() {
        return surplusAmountLabel;
    }

    public void setSurplusAmountLabel(String surplusAmountLabel) {
        this.surplusAmountLabel = surplusAmountLabel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSurplusAmount2() {
        return surplusAmount2;
    }

    public UserAccountBean getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountBean userAccount) {
        this.userAccount = userAccount;
    }

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public List<GetProductRedPackModel.BonusesByTypeBean> getRedList() {
        return redList;
    }

    public void setRedList(List<GetProductRedPackModel.BonusesByTypeBean> redList) {
        this.redList = redList;
    }

    public List<GetProductRedPackModel.BonusesByTypeBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<GetProductRedPackModel.BonusesByTypeBean> couponList) {
        this.couponList = couponList;
    }

    public static class UserAccountBean {
        private String userId;
        private String collectionCapital;
        private String availableMoney;
        private String id;
        private String fygoldAccount;
        private String totalRewardMoney;
        private String collectionInterest;
        private String allMoney;
        private String unavailableMoney;
        private String totalInterest;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCollectionCapital() {
            return collectionCapital;
        }

        public void setCollectionCapital(String collectionCapital) {
            this.collectionCapital = collectionCapital;
        }

        public String getAvailableMoney() {
            return availableMoney;
        }

        public void setAvailableMoney(String availableMoney) {
            this.availableMoney = availableMoney;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFygoldAccount() {
            return fygoldAccount;
        }

        public void setFygoldAccount(String fygoldAccount) {
            this.fygoldAccount = fygoldAccount;
        }

        public String getTotalRewardMoney() {
            return totalRewardMoney;
        }

        public void setTotalRewardMoney(String totalRewardMoney) {
            this.totalRewardMoney = totalRewardMoney;
        }

        public String getCollectionInterest() {
            return collectionInterest;
        }

        public void setCollectionInterest(String collectionInterest) {
            this.collectionInterest = collectionInterest;
        }

        public String getAllMoney() {
            return allMoney;
        }

        public void setAllMoney(String allMoney) {
            this.allMoney = allMoney;
        }

        public String getUnavailableMoney() {
            return unavailableMoney;
        }

        public void setUnavailableMoney(String unavailableMoney) {
            this.unavailableMoney = unavailableMoney;
        }

        public String getTotalInterest() {
            return totalInterest;
        }

        public void setTotalInterest(String totalInterest) {
            this.totalInterest = totalInterest;
        }
    }

    public static class ProductsBean {
        private String prdtimeLimitType;
        private String genreIcon;
        private String calInterestType;
        private String sequence;
        private String id;
        private String usageOfLoan;
        private String verifyTrialTime;
        private String publishDate;
        private String repaymentType;
        private String investAmount;
        private String description;
        private String surplusAmount;
        private String forenoticeDate;
        private String investBegin;
        private String addTime;
        private String overdueInterest;
        private String productsScale;
        private String raiseType;
        private String prdtimeLimitValue;
        private String verifyTrialRemark;
        private String loanCompany;
        private String addUser;
        private String repaymentEndDate;
        private String minProfit;
        private String verifyReviewUser;
        private String status;
        private double investPercent;
        private String repaymentSource;
        private String repaymentInterest;
        private String repaymentDate;
        private String investCount;
        private String verifyReviewRemark;
        private String genreId;
        private String content;
        private String genreTitle;
        private String plstimeLimitType;
        private String verifyTrialUser;
        private String img;
        private String verifyReviewTime;
        private String repaymentBeginDate;
        private String title;
        private String plstimeLimitValue;
        private String beginDate;
        private String repaymentCapital;
        private String endDate;
        private String type;
        private String investEnd;
        private String newSurplusAmount;

        public String getNewSurplusAmount() {
            return newSurplusAmount;
        }


        public String getInvestEnd() {
            return investEnd;
        }

        public void setInvestEnd(String investEnd) {
            this.investEnd = investEnd;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrdtimeLimitType() {
            return prdtimeLimitType;
        }

        public void setPrdtimeLimitType(String prdtimeLimitType) {
            this.prdtimeLimitType = prdtimeLimitType;
        }

        public String getGenreIcon() {
            return genreIcon;
        }

        public void setGenreIcon(String genreIcon) {
            this.genreIcon = genreIcon;
        }

        public String getCalInterestType() {
            return calInterestType;
        }

        public void setCalInterestType(String calInterestType) {
            this.calInterestType = calInterestType;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsageOfLoan() {
            return usageOfLoan;
        }

        public void setUsageOfLoan(String usageOfLoan) {
            this.usageOfLoan = usageOfLoan;
        }

        public String getVerifyTrialTime() {
            return verifyTrialTime;
        }

        public void setVerifyTrialTime(String verifyTrialTime) {
            this.verifyTrialTime = verifyTrialTime;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getRepaymentType() {
            return repaymentType;
        }

        public void setRepaymentType(String repaymentType) {
            this.repaymentType = repaymentType;
        }

        public String getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(String investAmount) {
            this.investAmount = investAmount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSurplusAmount() {
            return surplusAmount;
        }

        public void setSurplusAmount(String surplusAmount) {
            this.surplusAmount = surplusAmount;
        }

        public String getForenoticeDate() {
            return forenoticeDate;
        }

        public void setForenoticeDate(String forenoticeDate) {
            this.forenoticeDate = forenoticeDate;
        }

        public String getInvestBegin() {
            return investBegin;
        }

        public void setInvestBegin(String investBegin) {
            this.investBegin = investBegin;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getOverdueInterest() {
            return overdueInterest;
        }

        public void setOverdueInterest(String overdueInterest) {
            this.overdueInterest = overdueInterest;
        }

        public String getProductsScale() {
            return productsScale;
        }

        public void setProductsScale(String productsScale) {
            this.productsScale = productsScale;
        }

        public String getRaiseType() {
            return raiseType;
        }

        public void setRaiseType(String raiseType) {
            this.raiseType = raiseType;
        }

        public String getPrdtimeLimitValue() {
            return prdtimeLimitValue;
        }

        public void setPrdtimeLimitValue(String prdtimeLimitValue) {
            this.prdtimeLimitValue = prdtimeLimitValue;
        }

        public String getVerifyTrialRemark() {
            return verifyTrialRemark;
        }

        public void setVerifyTrialRemark(String verifyTrialRemark) {
            this.verifyTrialRemark = verifyTrialRemark;
        }

        public String getLoanCompany() {
            return loanCompany;
        }

        public void setLoanCompany(String loanCompany) {
            this.loanCompany = loanCompany;
        }

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public String getRepaymentEndDate() {
            return repaymentEndDate;
        }

        public void setRepaymentEndDate(String repaymentEndDate) {
            this.repaymentEndDate = repaymentEndDate;
        }

        public String getMinProfit() {
            return minProfit;
        }

        public void setMinProfit(String minProfit) {
            this.minProfit = minProfit;
        }

        public String getVerifyReviewUser() {
            return verifyReviewUser;
        }

        public void setVerifyReviewUser(String verifyReviewUser) {
            this.verifyReviewUser = verifyReviewUser;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getInvestPercent() {
            return investPercent;
        }

        public void setInvestPercent(double investPercent) {
            this.investPercent = investPercent;
        }

        public String getRepaymentSource() {
            return repaymentSource;
        }

        public void setRepaymentSource(String repaymentSource) {
            this.repaymentSource = repaymentSource;
        }

        public String getRepaymentInterest() {
            return repaymentInterest;
        }

        public void setRepaymentInterest(String repaymentInterest) {
            this.repaymentInterest = repaymentInterest;
        }

        public String getRepaymentDate() {
            return repaymentDate;
        }

        public void setRepaymentDate(String repaymentDate) {
            this.repaymentDate = repaymentDate;
        }

        public String getInvestCount() {
            return investCount;
        }

        public void setInvestCount(String investCount) {
            this.investCount = investCount;
        }

        public String getVerifyReviewRemark() {
            return verifyReviewRemark;
        }

        public void setVerifyReviewRemark(String verifyReviewRemark) {
            this.verifyReviewRemark = verifyReviewRemark;
        }

        public String getGenreId() {
            return genreId;
        }

        public void setGenreId(String genreId) {
            this.genreId = genreId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGenreTitle() {
            return genreTitle;
        }

        public void setGenreTitle(String genreTitle) {
            this.genreTitle = genreTitle;
        }

        public String getPlstimeLimitType() {
            return plstimeLimitType;
        }

        public void setPlstimeLimitType(String plstimeLimitType) {
            this.plstimeLimitType = plstimeLimitType;
        }

        public String getVerifyTrialUser() {
            return verifyTrialUser;
        }

        public void setVerifyTrialUser(String verifyTrialUser) {
            this.verifyTrialUser = verifyTrialUser;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getVerifyReviewTime() {
            return verifyReviewTime;
        }

        public void setVerifyReviewTime(String verifyReviewTime) {
            this.verifyReviewTime = verifyReviewTime;
        }

        public String getRepaymentBeginDate() {
            return repaymentBeginDate;
        }

        public void setRepaymentBeginDate(String repaymentBeginDate) {
            this.repaymentBeginDate = repaymentBeginDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPlstimeLimitValue() {
            return plstimeLimitValue;
        }

        public void setPlstimeLimitValue(String plstimeLimitValue) {
            this.plstimeLimitValue = plstimeLimitValue;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getRepaymentCapital() {
            return repaymentCapital;
        }

        public void setRepaymentCapital(String repaymentCapital) {
            this.repaymentCapital = repaymentCapital;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}
