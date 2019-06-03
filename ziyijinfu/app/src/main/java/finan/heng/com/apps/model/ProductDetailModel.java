package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/11 20:42
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailModel implements Serializable {

    /**
     * products : {"id":"177","genreId":"5","title":"测试A","loanCompany":"南洋科技","productsScale":"100000.00","investBegin":"100.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-09 00:00:00.0","publishDate":"2017-05-10 00:00:00.0","beginDate":"2017-05-10 00:00:00.0","endDate":"2017-05-11 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"0","repaymentType":"1","raiseType":"0","status":"60","addUser":"admin","addTime":"2017-05-10 15:08:39.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-08 17:34:21.0","verifyTrialRemark":"","verifyReviewUser":"admin","verifyReviewTime":"2017-05-10 15:08:39.0","verifyReviewRemark":"","sequence":"0","genreTitle":"抵押标","genreIcon":"","investCount":"12","investAmount":"100000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""}
     */

    private ProductsBean products;

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }
    private String investDateLabel;//投资日
    private String beginDateLabel  ;//计息日
    private String endDateLabel;//回款日
    private String calInterestTypeLabel;//起息方式
    private String repaymentTypeLabel;//还款方式
    private String newRuleLabel;//新手规则
    public String cashRateLabel;//投资红包

    /**合约年化收益率*/
    public String profitLabel;
    /**起投金额*/
    public String investBeginLabel;
    /**投资期限*/
    public String plstimeLimitLabel;
    /**投资限额*/
    public String investEndLabel;
    public String surplusAmountLabel;
    public String productsScaleLabel;
    /**起息方式*/
    public String calInterestTypeLabel2;
    /**还款方式*/
    public String repaymentTypeLabel2;
    /**剩余可投*/
    public String newRuleLabel2;
    /**起投金额*/
    public String cashRateLabel2;
    /**今日投资*/
    public String investDateLabel2;
    /**开始计息*/
    public String beginDateLabel2;
    /**开始回款*/
    public String endDateLabel2;
    /**投资记录*/
    public String orderListLabel;
    /**具体日期*/
    public String dateTips;
    /**安全保障*/
    public String productsReportLabel;
    /**项目详情*/
    public String productsDetailsLabel;
    /**立即出借*/
    public String buttonLabel;
    /**计算器是否显示*/
    public boolean calculatorStatus;
    /**计算器标题*/
    public String calculatorTitle;
    /**计算器年化收益*/
    public String calculatorProfitLabel;
    /**计算器投资期限*/
    public String calculatorPlstimeLimitLabel;
    /**出借金额提示*/
    public String calculatorAmountTips;
    /**计算按钮*/
    public String calculatorButtonLabel;
    /**计算结果*/
    public String calculatorResultLabel;

    public String getCalculatorTitle() {
        return calculatorTitle;
    }

    public void setCalculatorTitle(String calculatorTitle) {
        this.calculatorTitle = calculatorTitle;
    }

    public String getCalculatorProfitLabel() {
        return calculatorProfitLabel;
    }

    public void setCalculatorProfitLabel(String calculatorProfitLabel) {
        this.calculatorProfitLabel = calculatorProfitLabel;
    }

    public String getCalculatorPlstimeLimitLabel() {
        return calculatorPlstimeLimitLabel;
    }

    public void setCalculatorPlstimeLimitLabel(String calculatorPlstimeLimitLabel) {
        this.calculatorPlstimeLimitLabel = calculatorPlstimeLimitLabel;
    }

    public String getCalculatorAmountTips() {
        return calculatorAmountTips;
    }

    public void setCalculatorAmountTips(String calculatorAmountTips) {
        this.calculatorAmountTips = calculatorAmountTips;
    }

    public String getCalculatorButtonLabel() {
        return calculatorButtonLabel;
    }

    public void setCalculatorButtonLabel(String calculatorButtonLabel) {
        this.calculatorButtonLabel = calculatorButtonLabel;
    }

    public String getCalculatorResultLabel() {
        return calculatorResultLabel;
    }

    public void setCalculatorResultLabel(String calculatorResultLabel) {
        this.calculatorResultLabel = calculatorResultLabel;
    }

    public boolean isCalculatorStatus() {
        return calculatorStatus;
    }

    public void setCalculatorStatus(boolean calculatorStatus) {
        this.calculatorStatus = calculatorStatus;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public String getOrderListLabel() {
        return orderListLabel;
    }

    public void setOrderListLabel(String orderListLabel) {
        this.orderListLabel = orderListLabel;
    }

    public String getDateTips() {
        return dateTips;
    }

    public void setDateTips(String dateTips) {
        this.dateTips = dateTips;
    }

    public String getProductsReportLabel() {
        return productsReportLabel;
    }

    public void setProductsReportLabel(String productsReportLabel) {
        this.productsReportLabel = productsReportLabel;
    }

    public String getProductsDetailsLabel() {
        return productsDetailsLabel;
    }

    public void setProductsDetailsLabel(String productsDetailsLabel) {
        this.productsDetailsLabel = productsDetailsLabel;
    }

    public String getInvestDateLabel2() {
        return investDateLabel2;
    }

    public void setInvestDateLabel2(String investDateLabel2) {
        this.investDateLabel2 = investDateLabel2;
    }

    public String getBeginDateLabel2() {
        return beginDateLabel2;
    }

    public void setBeginDateLabel2(String beginDateLabel2) {
        this.beginDateLabel2 = beginDateLabel2;
    }

    public String getEndDateLabel2() {
        return endDateLabel2;
    }

    public void setEndDateLabel2(String endDateLabel2) {
        this.endDateLabel2 = endDateLabel2;
    }

    public String getCalInterestTypeLabel2() {
        return calInterestTypeLabel2;
    }

    public void setCalInterestTypeLabel2(String calInterestTypeLabel2) {
        this.calInterestTypeLabel2 = calInterestTypeLabel2;
    }

    public String getRepaymentTypeLabel2() {
        return repaymentTypeLabel2;
    }

    public void setRepaymentTypeLabel2(String repaymentTypeLabel2) {
        this.repaymentTypeLabel2 = repaymentTypeLabel2;
    }

    public String getNewRuleLabel2() {
        return newRuleLabel2;
    }

    public void setNewRuleLabel2(String newRuleLabel2) {
        this.newRuleLabel2 = newRuleLabel2;
    }

    public String getCashRateLabel2() {
        return cashRateLabel2;
    }

    public void setCashRateLabel2(String cashRateLabel2) {
        this.cashRateLabel2 = cashRateLabel2;
    }

    public String getSurplusAmountLabel() {
        return surplusAmountLabel;
    }

    public void setSurplusAmountLabel(String surplusAmountLabel) {
        this.surplusAmountLabel = surplusAmountLabel;
    }

    public String getProductsScaleLabel() {
        return productsScaleLabel;
    }

    public void setProductsScaleLabel(String productsScaleLabel) {
        this.productsScaleLabel = productsScaleLabel;
    }

    public String getProfitLabel() {
        return profitLabel;
    }

    public void setProfitLabel(String profitLabel) {
        this.profitLabel = profitLabel;
    }

    public String getInvestBeginLabel() {
        return investBeginLabel;
    }

    public void setInvestBeginLabel(String investBeginLabel) {
        this.investBeginLabel = investBeginLabel;
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

    public String getInvestDateLabel() {

        return investDateLabel == null ?"":investDateLabel;
    }

    public void setInvestDateLabel(String investDateLabel) {
        this.investDateLabel = investDateLabel;
    }

    public String getBeginDateLabel() {
        return beginDateLabel== null ?"":beginDateLabel;
    }

    public void setBeginDateLabel(String beginDateLabel) {
        this.beginDateLabel = beginDateLabel;
    }

    public String getEndDateLabel() {
        return endDateLabel == null ?"":endDateLabel;
    }

    public void setEndDateLabel(String endDateLabel) {
        this.endDateLabel = endDateLabel;
    }

    public String getCalInterestTypeLabel() {
        return calInterestTypeLabel == null ?"":calInterestTypeLabel;
    }

    public void setCalInterestTypeLabel(String calInterestTypeLabel) {
        this.calInterestTypeLabel = calInterestTypeLabel;
    }

    public String getRepaymentTypeLabel() {
        return repaymentTypeLabel == null ?"":repaymentTypeLabel;
    }

    public void setRepaymentTypeLabel(String repaymentTypeLabel) {
        this.repaymentTypeLabel = repaymentTypeLabel;
    }

    public String getNewRuleLabel() {
        return newRuleLabel == null ?"":newRuleLabel;
    }

    public void setNewRuleLabel(String newRuleLabel) {
        this.newRuleLabel = newRuleLabel;
    }
    public static class ProductsBean {
        /**
         * id : 177
         * genreId : 5
         * title : 测试A
         * loanCompany : 南洋科技
         * productsScale : 100000.00
         * investBegin : 100.00
         * minProfit : 0.08000
         * plstimeLimitType : 0
         * plstimeLimitValue : 1
         * prdtimeLimitType : 0
         * prdtimeLimitValue : 1
         * forenoticeDate : 2017-05-09 00:00:00.0
         * publishDate : 2017-05-10 00:00:00.0
         * beginDate : 2017-05-10 00:00:00.0
         * endDate : 2017-05-11 00:00:00.0
         * repaymentBeginDate :
         * repaymentEndDate :
         * repaymentCapital : 0.00
         * repaymentInterest : 0.00
         * overdueInterest : 0.00
         * repaymentDate :
         * calInterestType : 0
         * repaymentType : 1
         * raiseType : 0
         * status : 60
         * addUser : admin
         * addTime : 2017-05-10 15:08:39.0
         * verifyTrialUser : admin
         * verifyTrialTime : 2017-05-08 17:34:21.0
         * verifyTrialRemark :
         * verifyReviewUser : admin
         * verifyReviewTime : 2017-05-10 15:08:39.0
         * verifyReviewRemark :
         * sequence : 0
         * genreTitle : 抵押标
         * genreIcon :
         * investCount : 12
         * investAmount : 100000.00
         * surplusAmount : 0.00
         * investPercent : 1.0
         * content :
         * img :
         * repaymentSource :
         * usageOfLoan :
         */

        private int id;
        private String genreId;
        private String title;
        private String loanCompany;
        private String productsScale;
        private String investBegin;
        private String minProfit;
        private String description;
        private String plstimeLimitType;
        private String plstimeLimitValue;
        private String prdtimeLimitType;
        private String prdtimeLimitValue;
        private String forenoticeDate;
        private String publishDate;
        private String beginDate;
        private String endDate;
        private String repaymentBeginDate;
        private String repaymentEndDate;
        private String repaymentCapital;
        private String repaymentInterest;
        private String overdueInterest;
        private String repaymentDate;
        private String calInterestType;
        private String repaymentType;
        private String raiseType;
        private String status;
        private String addUser;
        private String addTime;
        private String verifyTrialUser;
        private String verifyTrialTime;
        private String verifyTrialRemark;
        private String verifyReviewUser;
        private String verifyReviewTime;
        private String verifyReviewRemark;
        private String sequence;
        private String genreTitle;
        private String genreIcon;
        private String investCount;
        private String investAmount;
        private String surplusAmount;
        private double investPercent;
        private String content;
        private String img;
        private String repaymentSource;
        private String usageOfLoan;
        private String profitFloat;
        private String profit;
        private String tags;

        private String cashRateShow;//红包返现是否显示(0:否；1:是)
        private String cashRateProfit;//红包返现加息年化率

        public Boolean getCashRateShow() {
            if (cashRateShow != null && cashRateShow.equals("1")) {
                return true;
            }
            return false;
        }

        public String getCashRateProfit() {
            return cashRateProfit;
        }

        public String getTags() {
            return tags == null ?"":tags;
        }

        public String getProfitFloat() {
            return profitFloat;
        }
        public String getProfit() {
            return profit;
        }
        public void setProfit(String profit) {
            this.profit = profit;
        }

        private String investEnd;//新手的最大限额
        private String type;//-1表示新手

        public String getInvestEnd() {
            return investEnd;
        }



        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGenreId() {
            return genreId;
        }



        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLoanCompany() {
            return loanCompany;
        }



        public String getProductsScale() {
            return productsScale;
        }



        public String getInvestBegin() {
            return investBegin;
        }



        public String getMinProfit() {
            return minProfit;
        }



        public String getPlstimeLimitType() {
            return plstimeLimitType;
        }

        public void setPlstimeLimitType(String plstimeLimitType) {
            this.plstimeLimitType = plstimeLimitType;
        }

        public String getPlstimeLimitValue() {
            return plstimeLimitValue;
        }

        public void setPlstimeLimitValue(String plstimeLimitValue) {
            this.plstimeLimitValue = plstimeLimitValue;
        }

        public String getPrdtimeLimitType() {
            return prdtimeLimitType;
        }



        public String getPrdtimeLimitValue() {
            return prdtimeLimitValue;
        }



        public String getForenoticeDate() {
            return forenoticeDate;
        }



        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getRepaymentBeginDate() {
            return repaymentBeginDate;
        }

        public void setRepaymentBeginDate(String repaymentBeginDate) {
            this.repaymentBeginDate = repaymentBeginDate;
        }

        public String getRepaymentEndDate() {
            return repaymentEndDate;
        }

        public void setRepaymentEndDate(String repaymentEndDate) {
            this.repaymentEndDate = repaymentEndDate;
        }

        public String getRepaymentCapital() {
            return repaymentCapital;
        }

        public void setRepaymentCapital(String repaymentCapital) {
            this.repaymentCapital = repaymentCapital;
        }

        public String getRepaymentInterest() {
            return repaymentInterest;
        }

        public void setRepaymentInterest(String repaymentInterest) {
            this.repaymentInterest = repaymentInterest;
        }

        public String getOverdueInterest() {
            return overdueInterest;
        }

        public void setOverdueInterest(String overdueInterest) {
            this.overdueInterest = overdueInterest;
        }

        public String getRepaymentDate() {
            return repaymentDate;
        }

        public void setRepaymentDate(String repaymentDate) {
            this.repaymentDate = repaymentDate;
        }

        public String getCalInterestType() {
            return calInterestType;
        }

        public void setCalInterestType(String calInterestType) {
            this.calInterestType = calInterestType;
        }

        public String getRepaymentType() {
            return repaymentType;
        }

        public void setRepaymentType(String repaymentType) {
            this.repaymentType = repaymentType;
        }

        public String getRaiseType() {
            return raiseType;
        }

        public void setRaiseType(String raiseType) {
            this.raiseType = raiseType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getVerifyTrialUser() {
            return verifyTrialUser;
        }

        public void setVerifyTrialUser(String verifyTrialUser) {
            this.verifyTrialUser = verifyTrialUser;
        }

        public String getVerifyTrialTime() {
            return verifyTrialTime;
        }

        public void setVerifyTrialTime(String verifyTrialTime) {
            this.verifyTrialTime = verifyTrialTime;
        }

        public String getVerifyTrialRemark() {
            return verifyTrialRemark;
        }

        public void setVerifyTrialRemark(String verifyTrialRemark) {
            this.verifyTrialRemark = verifyTrialRemark;
        }

        public String getVerifyReviewUser() {
            return verifyReviewUser;
        }

        public void setVerifyReviewUser(String verifyReviewUser) {
            this.verifyReviewUser = verifyReviewUser;
        }

        public String getVerifyReviewTime() {
            return verifyReviewTime;
        }

        public void setVerifyReviewTime(String verifyReviewTime) {
            this.verifyReviewTime = verifyReviewTime;
        }

        public String getVerifyReviewRemark() {
            return verifyReviewRemark;
        }

        public void setVerifyReviewRemark(String verifyReviewRemark) {
            this.verifyReviewRemark = verifyReviewRemark;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
        }

        public String getGenreTitle() {
            return genreTitle;
        }

        public void setGenreTitle(String genreTitle) {
            this.genreTitle = genreTitle;
        }

        public String getGenreIcon() {
            return genreIcon;
        }

        public void setGenreIcon(String genreIcon) {
            this.genreIcon = genreIcon;
        }

        public String getInvestCount() {
            return investCount;
        }

        public void setInvestCount(String investCount) {
            this.investCount = investCount;
        }

        public String getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(String investAmount) {
            this.investAmount = investAmount;
        }

        public String getSurplusAmount() {
            return surplusAmount;
        }

        public void setSurplusAmount(String surplusAmount) {
            this.surplusAmount = surplusAmount;
        }

        public double getInvestPercent() {
            return investPercent;
        }

        public void setInvestPercent(double investPercent) {
            this.investPercent = investPercent;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRepaymentSource() {
            return repaymentSource;
        }

        public void setRepaymentSource(String repaymentSource) {
            this.repaymentSource = repaymentSource;
        }

        public String getUsageOfLoan() {
            return usageOfLoan;
        }

        public void setUsageOfLoan(String usageOfLoan) {
            this.usageOfLoan = usageOfLoan;
        }
    }
}
