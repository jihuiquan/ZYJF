package finan.heng.com.apps.manager.entity.respond;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class ProductListRespond extends BaseRespond{

    /**
     * code : 0
     * result : {"pager":{"pageSize":"10","pageNo":"1","start":"0","toUrl":"","totalPage":"2","result":"","totalRows":"13","orderBy":"0","condition":{"repaymentType":"","status":"","plstimeLimit":"","genreId":""},"pageCount":"2"},"repaymentType":"-1","productsList":[{"id":"336","genreId":"17","title":"新手专享","description":"历史年化收益（实际利率+新手奖励利率）","loanCompany":"山东某服装公司","productsScale":"700000.00","investBegin":"100.00","investEnd":"15000.00","profit":"0.03330","profitFloat":"0.08670","plstimeLimitType":"0","plstimeLimitValue":"15","prdtimeLimitType":"1","prdtimeLimitValue":"2","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-11-23 00:00:00.0","endDate":"2017-12-08 00:00:00.0","calInterestType":"1","repaymentType":"0","raiseType":"-1","status":"30","type":"","addUser":"admin","addTime":"2017-09-23 14:16:52.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 14:16:59.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"995","genreTitle":"新手体验区","genreIcon":"/upload/image/20170714113506021.png","investCount":"0","investAmount":"0.00","surplusAmount":"700000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"328","genreId":"19","title":"恒宝006期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"10000.00","investBegin":"100.00","investEnd":"","profit":"0.12000","profitFloat":"","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"30","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"7","genreTitle":"子壹金服计划","genreIcon":"","investCount":"1","investAmount":"2300.00","surplusAmount":"7700.00","investPercent":0.23,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"327","genreId":"19","title":"恒宝005期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"50000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"30","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"6","genreTitle":"子壹金服计划","genreIcon":"","investCount":"1","investAmount":"1100.00","surplusAmount":"48900.00","investPercent":0.02,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"326","genreId":"19","title":"恒宝004期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"500000.00","investBegin":"100.00","investEnd":"","profit":"0.09900","profitFloat":"","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"30","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"5","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"500000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.09900"},{"id":"325","genreId":"19","title":"恒宝003期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"5000000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"30","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"4","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"5000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"322","genreId":"17","title":"新手专享","description":"历史年化收益（实际利率+新手奖励利率）","loanCompany":"上海大禄投资管理有限公司","productsScale":"5000000.00","investBegin":"100.00","investEnd":"20000.00","profit":"0.08560","profitFloat":"0.03440","plstimeLimitType":"0","plstimeLimitValue":"15","prdtimeLimitType":"1","prdtimeLimitValue":"6","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2018-03-23 00:00:00.0","endDate":"2018-04-07 00:00:00.0","calInterestType":"1","repaymentType":"1","raiseType":"1","status":"30","type":"-1","addUser":"admin","addTime":"2017-09-23 00:10:27.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:10:51.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"2","genreTitle":"新手体验区","genreIcon":"/upload/image/20170714113506021.png","investCount":"0","investAmount":"0.00","surplusAmount":"5000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"330","genreId":"19","title":"恒宝008期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"500000000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-10-23 00:00:00.0","beginDate":"2017-10-26 00:00:00.0","endDate":"2017-11-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"20","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"9","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"500000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.07500"},{"id":"329","genreId":"19","title":"恒宝007期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"50000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-10-23 00:00:00.0","beginDate":"2017-10-26 00:00:00.0","endDate":"2017-11-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"20","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"8","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"50000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"324","genreId":"19","title":"恒宝002期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"50000000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"50","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"3","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"50000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"323","genreId":"19","title":"恒宝001期","description":"全场加息2%","loanCompany":"大连某装饰工程有限公司","productsScale":"50000000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"60","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"3","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"50000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"}],"totalRows":"13","status":"-1","pageNo":"1","pageSize":"10","plstimeLimit":"-1","totalPage":"2","genreId":"-1"}
     */

    private ResultEntity result;


    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
    public static class ResultEntity implements Serializable{
        /**
         * pager : {"pageSize":"10","pageNo":"1","start":"0","toUrl":"","totalPage":"2","result":"","totalRows":"13","orderBy":"0","condition":{"repaymentType":"","status":"","plstimeLimit":"","genreId":""},"pageCount":"2"}
         * repaymentType : -1
         * productsList : [{"id":"336","genreId":"17","title":"新手专享","description":"历史年化收益（实际利率+新手奖励利率）","loanCompany":"山东某服装公司","productsScale":"700000.00","investBegin":"100.00","investEnd":"15000.00","profit":"0.03330","profitFloat":"0.08670","plstimeLimitType":"0","plstimeLimitValue":"15","prdtimeLimitType":"1","prdtimeLimitValue":"2","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-11-23 00:00:00.0","endDate":"2017-12-08 00:00:00.0","calInterestType":"1","repaymentType":"0","raiseType":"-1","status":"30","type":"","addUser":"admin","addTime":"2017-09-23 14:16:52.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 14:16:59.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"995","genreTitle":"新手体验区","genreIcon":"/upload/image/20170714113506021.png","investCount":"0","investAmount":"0.00","surplusAmount":"700000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"328","genreId":"19","title":"恒宝006期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"10000.00","investBegin":"100.00","investEnd":"","profit":"0.12000","profitFloat":"","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"30","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"7","genreTitle":"子壹金服计划","genreIcon":"","investCount":"1","investAmount":"2300.00","surplusAmount":"7700.00","investPercent":0.23,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"327","genreId":"19","title":"恒宝005期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"50000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"30","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"6","genreTitle":"子壹金服计划","genreIcon":"","investCount":"1","investAmount":"1100.00","surplusAmount":"48900.00","investPercent":0.02,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"326","genreId":"19","title":"恒宝004期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"500000.00","investBegin":"100.00","investEnd":"","profit":"0.09900","profitFloat":"","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"30","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"5","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"500000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.09900"},{"id":"325","genreId":"19","title":"恒宝003期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"5000000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"30","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"4","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"5000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"322","genreId":"17","title":"新手专享","description":"历史年化收益（实际利率+新手奖励利率）","loanCompany":"上海大禄投资管理有限公司","productsScale":"5000000.00","investBegin":"100.00","investEnd":"20000.00","profit":"0.08560","profitFloat":"0.03440","plstimeLimitType":"0","plstimeLimitValue":"15","prdtimeLimitType":"1","prdtimeLimitValue":"6","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2018-03-23 00:00:00.0","endDate":"2018-04-07 00:00:00.0","calInterestType":"1","repaymentType":"1","raiseType":"1","status":"30","type":"-1","addUser":"admin","addTime":"2017-09-23 00:10:27.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:10:51.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"2","genreTitle":"新手体验区","genreIcon":"/upload/image/20170714113506021.png","investCount":"0","investAmount":"0.00","surplusAmount":"5000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"330","genreId":"19","title":"恒宝008期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"500000000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-10-23 00:00:00.0","beginDate":"2017-10-26 00:00:00.0","endDate":"2017-11-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"20","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"9","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"500000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.07500"},{"id":"329","genreId":"19","title":"恒宝007期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"50000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-10-23 00:00:00.0","beginDate":"2017-10-26 00:00:00.0","endDate":"2017-11-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"20","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"8","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"50000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"324","genreId":"19","title":"恒宝002期","description":"全场加息3%","loanCompany":"大连某装饰工程有限公司","productsScale":"50000000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"50","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"3","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"50000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"},{"id":"323","genreId":"19","title":"恒宝001期","description":"全场加息2%","loanCompany":"大连某装饰工程有限公司","productsScale":"50000000.00","investBegin":"100.00","investEnd":"","profit":"0.07500","profitFloat":"0.04500","plstimeLimitType":"0","plstimeLimitValue":"30","prdtimeLimitType":"0","prdtimeLimitValue":"3","forenoticeDate":"2017-09-22 00:00:00.0","publishDate":"2017-09-23 00:00:00.0","beginDate":"2017-09-26 00:00:00.0","endDate":"2017-10-26 00:00:00.0","calInterestType":"0","repaymentType":"0","raiseType":"1","status":"60","type":"0","addUser":"admin","addTime":"2017-09-23 00:34:47.0","verifyTrialUser":"admin","verifyTrialTime":"2017-09-23 00:35:48.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"3","genreTitle":"子壹金服计划","genreIcon":"","investCount":"0","investAmount":"0.00","surplusAmount":"50000000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":"","minProfit":"0.12000"}]
         * totalRows : 13
         * status : -1
         * pageNo : 1
         * pageSize : 10
         * plstimeLimit : -1
         * totalPage : 2
         * genreId : -1
         */

        private PagerEntity pager;
        private String repaymentType;
        private String totalRows;
        private String status;
        private String pageNo;
        private String pageSize;
        private String plstimeLimit;
        private String totalPage;
        private String genreId;
        private List<ProductsListEntity> productsList;

        public PagerEntity getPager() {
            return pager;
        }

        public void setPager(PagerEntity pager) {
            this.pager = pager;
        }

        public String getRepaymentType() {
            return repaymentType;
        }

        public void setRepaymentType(String repaymentType) {
            this.repaymentType = repaymentType;
        }

        public String getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(String totalRows) {
            this.totalRows = totalRows;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPlstimeLimit() {
            return plstimeLimit;
        }

        public void setPlstimeLimit(String plstimeLimit) {
            this.plstimeLimit = plstimeLimit;
        }

        public String getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(String totalPage) {
            this.totalPage = totalPage;
        }

        public String getGenreId() {
            return genreId;
        }

        public void setGenreId(String genreId) {
            this.genreId = genreId;
        }

        public List<ProductsListEntity> getProductsList() {
            return productsList;
        }

        public void setProductsList(List<ProductsListEntity> productsList) {
            this.productsList = productsList;
        }
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
        public static class PagerEntity implements Serializable{
            /**
             * pageSize : 10
             * pageNo : 1
             * start : 0
             * toUrl :
             * totalPage : 2
             * result :
             * totalRows : 13
             * orderBy : 0
             * condition : {"repaymentType":"","status":"","plstimeLimit":"","genreId":""}
             * pageCount : 2
             */

            private String pageSize;
            private String pageNo;
            private String start;
            private String toUrl;
            private String totalPage;
            private String result;
            private String totalRows;
            private String orderBy;
            private ConditionEntity condition;
            private String pageCount;

            public String getPageSize() {
                return pageSize;
            }

            public void setPageSize(String pageSize) {
                this.pageSize = pageSize;
            }

            public String getPageNo() {
                return pageNo;
            }

            public void setPageNo(String pageNo) {
                this.pageNo = pageNo;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getToUrl() {
                return toUrl;
            }

            public void setToUrl(String toUrl) {
                this.toUrl = toUrl;
            }

            public String getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(String totalPage) {
                this.totalPage = totalPage;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getTotalRows() {
                return totalRows;
            }

            public void setTotalRows(String totalRows) {
                this.totalRows = totalRows;
            }

            public String getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(String orderBy) {
                this.orderBy = orderBy;
            }

            public ConditionEntity getCondition() {
                return condition;
            }

            public void setCondition(ConditionEntity condition) {
                this.condition = condition;
            }

            public String getPageCount() {
                return pageCount;
            }

            public void setPageCount(String pageCount) {
                this.pageCount = pageCount;
            }
            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
            public static class ConditionEntity implements Serializable{
                /**
                 * repaymentType :
                 * status :
                 * plstimeLimit :
                 * genreId :
                 */

                private String repaymentType;
                private String status;
                private String plstimeLimit;
                private String genreId;

                public String getRepaymentType() {
                    return repaymentType;
                }

                public void setRepaymentType(String repaymentType) {
                    this.repaymentType = repaymentType;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getPlstimeLimit() {
                    return plstimeLimit;
                }

                public void setPlstimeLimit(String plstimeLimit) {
                    this.plstimeLimit = plstimeLimit;
                }

                public String getGenreId() {
                    return genreId;
                }

                public void setGenreId(String genreId) {
                    this.genreId = genreId;
                }
            }
        }
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
        public static class ProductsListEntity implements Serializable{
            /**
             * id : 336
             * genreId : 17
             * title : 新手专享
             * description : 历史年化收益（实际利率+新手奖励利率）
             * loanCompany : 山东某服装公司
             * productsScale : 700000.00
             * investBegin : 100.00
             * investEnd : 15000.00
             * profit : 0.03330
             * profitFloat : 0.08670
             * plstimeLimitType : 0
             * plstimeLimitValue : 15
             * prdtimeLimitType : 1
             * prdtimeLimitValue : 2
             * forenoticeDate : 2017-09-22 00:00:00.0
             * publishDate : 2017-09-23 00:00:00.0
             * beginDate : 2017-11-23 00:00:00.0
             * endDate : 2017-12-08 00:00:00.0
             * calInterestType : 1
             * repaymentType : 0
             * raiseType : -1
             * status : 30
             * type :
             * addUser : admin
             * addTime : 2017-09-23 14:16:52.0
             * verifyTrialUser : admin
             * verifyTrialTime : 2017-09-23 14:16:59.0
             * verifyTrialRemark :
             * verifyReviewUser :
             * verifyReviewTime :
             * verifyReviewRemark :
             * sequence : 995
             * genreTitle : 新手体验区
             * genreIcon : /upload/image/20170714113506021.png
             * investCount : 0
             * investAmount : 0.00
             * surplusAmount : 700000.00
             * investPercent : 0
             * content :
             * img :
             * repaymentSource :
             * usageOfLoan :
             * minProfit : 0.12000
             */

            private String id;
            private String genreId;
            private String title;
            private String description;
            private String loanCompany;
            private String productsScale;
            private String investBegin;
            private String investEnd;
            private String profit;
            private String profitFloat;
            private String plstimeLimitType;
            private String plstimeLimitValue;
            private String prdtimeLimitType;
            private String prdtimeLimitValue;
            private String forenoticeDate;
            private String publishDate;
            private String beginDate;
            private String endDate;
            private String calInterestType;
            private String repaymentType;
            private String raiseType;
            private String status;
            private String type;
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
            private int investPercent;
            private String content;
            private String img;
            private String repaymentSource;
            private String usageOfLoan;
            private String minProfit;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGenreId() {
                return genreId;
            }

            public void setGenreId(String genreId) {
                this.genreId = genreId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getLoanCompany() {
                return loanCompany;
            }

            public void setLoanCompany(String loanCompany) {
                this.loanCompany = loanCompany;
            }

            public String getProductsScale() {
                return productsScale;
            }

            public void setProductsScale(String productsScale) {
                this.productsScale = productsScale;
            }

            public String getInvestBegin() {
                return investBegin;
            }

            public void setInvestBegin(String investBegin) {
                this.investBegin = investBegin;
            }

            public String getInvestEnd() {
                return investEnd;
            }

            public void setInvestEnd(String investEnd) {
                this.investEnd = investEnd;
            }

            public String getProfit() {
                return profit;
            }

            public void setProfit(String profit) {
                this.profit = profit;
            }

            public String getProfitFloat() {
                return profitFloat;
            }

            public void setProfitFloat(String profitFloat) {
                this.profitFloat = profitFloat;
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

            public void setPrdtimeLimitType(String prdtimeLimitType) {
                this.prdtimeLimitType = prdtimeLimitType;
            }

            public String getPrdtimeLimitValue() {
                return prdtimeLimitValue;
            }

            public void setPrdtimeLimitValue(String prdtimeLimitValue) {
                this.prdtimeLimitValue = prdtimeLimitValue;
            }

            public String getForenoticeDate() {
                return forenoticeDate;
            }

            public void setForenoticeDate(String forenoticeDate) {
                this.forenoticeDate = forenoticeDate;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public int getInvestPercent() {
                return investPercent;
            }

            public void setInvestPercent(int investPercent) {
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

            public String getMinProfit() {
                return minProfit;
            }

            public void setMinProfit(String minProfit) {
                this.minProfit = minProfit;
            }
        }
    }
}
