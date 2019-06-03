package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class GetProductDetailListModel implements Serializable {


    /**
     * pager : {"pageSize":"10","pageNo":"1","start":"0","toUrl":"","totalPage":"1","result":"","totalRows":"2","orderBy":"0","condition":{"productsId":"197"},"pageCount":"1"}
     * totalRows : 2
     * userOrderList : [{"id":"1854","userId":"1603","productsId":"197","amount":"1000.00","effectiveAmount":"1000.00","bonusesAmount":"0.00","paidAmount":"0.00","paidInterest":"0.00","collAmount":"","collInterest":"","addTime":"2017-05-21 12:21:20.0","tenderAddip":"223.167.127.115, 103.44.145.245","tenderSource":"1","status":"0","userAccount":"15000921406","userRealname":"胡胡","title":"","showStatus":"","minProfit":"","endDate":"","plstimeLimitType":"","plstimeLimitValue":""},{"id":"1853","userId":"1603","productsId":"197","amount":"1000.00","effectiveAmount":"1000.00","bonusesAmount":"0.00","paidAmount":"0.00","paidInterest":"0.00","collAmount":"","collInterest":"","addTime":"2017-05-21 12:19:49.0","tenderAddip":"223.167.127.115, 103.44.145.245","tenderSource":"1","status":"0","userAccount":"15000921406","userRealname":"胡胡","title":"","showStatus":"","minProfit":"","endDate":"","plstimeLimitType":"","plstimeLimitValue":""}]
     * pageNo : 1
     * pageSize : 10
     * totalPage : 1
     */

    private PagerBean pager;
    private String totalRows;
    private String pageNo;
    private String pageSize;
    private String totalPage;
    private ArrayList<UserOrderListBean> userOrderList;

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }

    public String getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
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

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public ArrayList<UserOrderListBean> getUserOrderList() {
        return userOrderList;
    }

    public void setUserOrderList(ArrayList<UserOrderListBean> userOrderList) {
        this.userOrderList = userOrderList;
    }

    public static class PagerBean {
        /**
         * pageSize : 10
         * pageNo : 1
         * start : 0
         * toUrl :
         * totalPage : 1
         * result :
         * totalRows : 2
         * orderBy : 0
         * condition : {"productsId":"197"}
         * pageCount : 1
         */

        private String pageSize;
        private String pageNo;
        private String start;
        private String toUrl;
        private String totalPage;
        private String result;
        private String totalRows;
        private String orderBy;
        private ConditionBean condition;
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

        public ConditionBean getCondition() {
            return condition;
        }

        public void setCondition(ConditionBean condition) {
            this.condition = condition;
        }

        public String getPageCount() {
            return pageCount;
        }

        public void setPageCount(String pageCount) {
            this.pageCount = pageCount;
        }

        public static class ConditionBean {
            /**
             * productsId : 197
             */

            private String productsId;

            public String getProductsId() {
                return productsId;
            }

            public void setProductsId(String productsId) {
                this.productsId = productsId;
            }
        }
    }

    public static class UserOrderListBean {
        /**
         * id : 1854
         * userId : 1603
         * productsId : 197
         * amount : 1000.00
         * effectiveAmount : 1000.00
         * bonusesAmount : 0.00
         * paidAmount : 0.00
         * paidInterest : 0.00
         * collAmount :
         * collInterest :
         * addTime : 2017-05-21 12:21:20.0
         * tenderAddip : 223.167.127.115, 103.44.145.245
         * tenderSource : 1
         * status : 0
         * userAccount : 15000921406
         * userRealname : 胡胡
         * title :
         * showStatus :
         * minProfit :
         * endDate :
         * plstimeLimitType :
         * plstimeLimitValue :
         */

        private String id;
        private String userId;
        private String productsId;
        private String amount;
        private String effectiveAmount;
        private String bonusesAmount;
        private String paidAmount;
        private String paidInterest;
        private String collAmount;
        private String collInterest;
        private String addTime;
        private String tenderAddip;
        private String tenderSource;
        private String status;
        private String userAccount;
        private String userRealname;
        private String title;
        private String showStatus;
        private String minProfit;
        private String endDate;
        private String plstimeLimitType;
        private String plstimeLimitValue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getProductsId() {
            return productsId;
        }

        public void setProductsId(String productsId) {
            this.productsId = productsId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getEffectiveAmount() {
            return effectiveAmount;
        }

        public void setEffectiveAmount(String effectiveAmount) {
            this.effectiveAmount = effectiveAmount;
        }

        public String getBonusesAmount() {
            return bonusesAmount;
        }

        public void setBonusesAmount(String bonusesAmount) {
            this.bonusesAmount = bonusesAmount;
        }

        public String getPaidAmount() {
            return paidAmount;
        }

        public void setPaidAmount(String paidAmount) {
            this.paidAmount = paidAmount;
        }

        public String getPaidInterest() {
            return paidInterest;
        }

        public void setPaidInterest(String paidInterest) {
            this.paidInterest = paidInterest;
        }

        public String getCollAmount() {
            return collAmount;
        }

        public void setCollAmount(String collAmount) {
            this.collAmount = collAmount;
        }

        public String getCollInterest() {
            return collInterest;
        }

        public void setCollInterest(String collInterest) {
            this.collInterest = collInterest;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getTenderAddip() {
            return tenderAddip;
        }

        public void setTenderAddip(String tenderAddip) {
            this.tenderAddip = tenderAddip;
        }

        public String getTenderSource() {
            return tenderSource;
        }

        public void setTenderSource(String tenderSource) {
            this.tenderSource = tenderSource;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUserRealname() {
            return userRealname;
        }

        public void setUserRealname(String userRealname) {
            this.userRealname = userRealname;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public String getMinProfit() {
            return minProfit;
        }

        public void setMinProfit(String minProfit) {
            this.minProfit = minProfit;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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
    }
}
