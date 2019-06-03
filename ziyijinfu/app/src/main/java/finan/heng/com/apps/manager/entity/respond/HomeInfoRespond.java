package finan.heng.com.apps.manager.entity.respond;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.manager.entity.model.BannerModel;
import finan.heng.com.apps.manager.entity.model.HomeInfoModel;
import finan.heng.com.apps.manager.entity.model.NotificationModel;
import finan.heng.com.apps.manager.entity.model.ProductModel;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HomeInfoRespond extends BaseRespond {

    /**
     * result : {"bannerList":[{"imgaeLinkUrl":"https://api.jincaiwa.com/hotspot/banner1?login","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151123746.jpg","imgTitle":"银行存管"},{"imgaeLinkUrl":"https://api.jincaiwa.com/hotspot/banner2","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151134444.jpg","imgTitle":"网站上线"},{"imgaeLinkUrl":"https://testapi.fadada.com:8443/api/viewdocs.action?app_id=400617&send_app_id=null&v=2.0&timestamp=20170921172326&transaction_id=TEST10&msg_digest=QzY1QzlEMUQyQ0RBMUVBOUIwRTQ4ODlDMDNEMDVFNUU0M0IzNzhBQw==","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151145537.png","imgTitle":"好友推荐"},{"imgaeLinkUrl":"https://api.jincaiwa.com/hotspot/banner4","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151156982.jpg","imgTitle":"兑付公告"},{"imgaeLinkUrl":"https://api.jincaiwa.com/hotspot/banner5","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151214674.png","imgTitle":"注册红包"}],"productList":[],"productTitle":"为你推荐","newProductList":[{"profit":"0.08560","surplusAmount":"5000000.00","title":"新手专享","investBegin":"100.00","productsId":"322","status":"30","description":"历史年化收益（实际利率+新手奖励利率）","investEnd":"20000.00","plstimeLimitType":"0","profitFloat":"0.03440","type":"-1","plstimeLimitValue":"15","investPercent":0},{"profit":"0.12000","surplusAmount":"9800.00","title":"恒宝006期","investBegin":"100.00","productsId":"328","status":"30","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"","type":"0","plstimeLimitValue":"30","investPercent":0.02},{"profit":"0.07500","surplusAmount":"48900.00","title":"恒宝005期","investBegin":"100.00","productsId":"327","status":"30","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"0.04500","type":"0","plstimeLimitValue":"30","investPercent":0.02},{"profit":"0.09900","surplusAmount":"500000.00","title":"恒宝004期","investBegin":"100.00","productsId":"326","status":"30","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"","type":"0","plstimeLimitValue":"30","investPercent":0},{"profit":"0.07500","surplusAmount":"5000000.00","title":"恒宝003期","investBegin":"100.00","productsId":"325","status":"30","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"0.04500","type":"0","plstimeLimitValue":"30","investPercent":0},{"profit":"0.07500","surplusAmount":"500000000.00","title":"恒宝008期","investBegin":"100.00","productsId":"330","status":"20","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"","type":"0","plstimeLimitValue":"30","investPercent":0},{"profit":"0.07500","surplusAmount":"50000.00","title":"恒宝007期","investBegin":"100.00","productsId":"329","status":"20","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"0.04500","type":"0","plstimeLimitValue":"30","investPercent":0}],"articleList":[{"hotTitle":"注册红包规则变更通知","time":"2017-07-28 19:26","url":""},{"hotTitle":"推荐奖励规则变更","time":"2017-07-28 19:27","url":""},{"hotTitle":"恒利来网站上线啦！","time":"2017-07-21 15:10","url":""}],"productDescribe":"个性推荐  专属订制","newTitle":"新手专享","newDescribe":"享受新手奖励利率"}
     */

    private ResultEntity result;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class ResultEntity implements Serializable{
        /**
         * bannerList : [{"imgaeLinkUrl":"https://api.jincaiwa.com/hotspot/banner1?login","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151123746.jpg","imgTitle":"银行存管"},{"imgaeLinkUrl":"https://api.jincaiwa.com/hotspot/banner2","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151134444.jpg","imgTitle":"网站上线"},{"imgaeLinkUrl":"https://testapi.fadada.com:8443/api/viewdocs.action?app_id=400617&send_app_id=null&v=2.0&timestamp=20170921172326&transaction_id=TEST10&msg_digest=QzY1QzlEMUQyQ0RBMUVBOUIwRTQ4ODlDMDNEMDVFNUU0M0IzNzhBQw==","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151145537.png","imgTitle":"好友推荐"},{"imgaeLinkUrl":"https://api.jincaiwa.com/hotspot/banner4","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151156982.jpg","imgTitle":"兑付公告"},{"imgaeLinkUrl":"https://api.jincaiwa.com/hotspot/banner5","bannerImgae":"https://admin.jincaiwa.com/upload/image/20170916151214674.png","imgTitle":"注册红包"}]
         * productList : []
         * productTitle : 为你推荐
         * newProductList : [{"profit":"0.08560","surplusAmount":"5000000.00","title":"新手专享","investBegin":"100.00","productsId":"322","status":"30","description":"历史年化收益（实际利率+新手奖励利率）","investEnd":"20000.00","plstimeLimitType":"0","profitFloat":"0.03440","type":"-1","plstimeLimitValue":"15","investPercent":0},{"profit":"0.12000","surplusAmount":"9800.00","title":"恒宝006期","investBegin":"100.00","productsId":"328","status":"30","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"","type":"0","plstimeLimitValue":"30","investPercent":0.02},{"profit":"0.07500","surplusAmount":"48900.00","title":"恒宝005期","investBegin":"100.00","productsId":"327","status":"30","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"0.04500","type":"0","plstimeLimitValue":"30","investPercent":0.02},{"profit":"0.09900","surplusAmount":"500000.00","title":"恒宝004期","investBegin":"100.00","productsId":"326","status":"30","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"","type":"0","plstimeLimitValue":"30","investPercent":0},{"profit":"0.07500","surplusAmount":"5000000.00","title":"恒宝003期","investBegin":"100.00","productsId":"325","status":"30","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"0.04500","type":"0","plstimeLimitValue":"30","investPercent":0},{"profit":"0.07500","surplusAmount":"500000000.00","title":"恒宝008期","investBegin":"100.00","productsId":"330","status":"20","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"","type":"0","plstimeLimitValue":"30","investPercent":0},{"profit":"0.07500","surplusAmount":"50000.00","title":"恒宝007期","investBegin":"100.00","productsId":"329","status":"20","description":"全场加息3%","investEnd":"","plstimeLimitType":"0","profitFloat":"0.04500","type":"0","plstimeLimitValue":"30","investPercent":0}]
         * articleList : [{"hotTitle":"注册红包规则变更通知","time":"2017-07-28 19:26","url":""},{"hotTitle":"推荐奖励规则变更","time":"2017-07-28 19:27","url":""},{"hotTitle":"恒利来网站上线啦！","time":"2017-07-21 15:10","url":""}]
         * productDescribe : 个性推荐  专属订制
         * newTitle : 新手专享
         * newDescribe : 享受新手奖励利率
         */

        private String productTitle;
        private String productDescribe;
        private String newTitle;
        private String newDescribe;
        private List<BannerListEntity> bannerList;
        private List<?> productList;
        private List<NewProductListEntity> newProductList;
        private List<ArticleListEntity> articleList;

        public String getProductTitle() {
            return productTitle;
        }

        public void setProductTitle(String productTitle) {
            this.productTitle = productTitle;
        }

        public String getProductDescribe() {
            return productDescribe;
        }

        public void setProductDescribe(String productDescribe) {
            this.productDescribe = productDescribe;
        }

        public String getNewTitle() {
            return newTitle;
        }

        public void setNewTitle(String newTitle) {
            this.newTitle = newTitle;
        }

        public String getNewDescribe() {
            return newDescribe;
        }

        public void setNewDescribe(String newDescribe) {
            this.newDescribe = newDescribe;
        }

        public List<BannerListEntity> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerListEntity> bannerList) {
            this.bannerList = bannerList;
        }

        public List<?> getProductList() {
            return productList;
        }

        public void setProductList(List<?> productList) {
            this.productList = productList;
        }

        public List<NewProductListEntity> getNewProductList() {
            return newProductList;
        }

        public void setNewProductList(List<NewProductListEntity> newProductList) {
            this.newProductList = newProductList;
        }

        public List<ArticleListEntity> getArticleList() {
            return articleList;
        }

        public void setArticleList(List<ArticleListEntity> articleList) {
            this.articleList = articleList;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
        public static class BannerListEntity implements Serializable {
            /**
             * imgaeLinkUrl : https://api.jincaiwa.com/hotspot/banner1?login
             * bannerImgae : https://admin.jincaiwa.com/upload/image/20170916151123746.jpg
             * imgTitle : 银行存管
             */

            private String imgaeLinkUrl;
            private String bannerImgae;
            private String imgTitle;

            public String getImgaeLinkUrl() {
                return imgaeLinkUrl;
            }

            public void setImgaeLinkUrl(String imgaeLinkUrl) {
                this.imgaeLinkUrl = imgaeLinkUrl;
            }

            public String getBannerImgae() {
                return bannerImgae;
            }

            public void setBannerImgae(String bannerImgae) {
                this.bannerImgae = bannerImgae;
            }

            public String getImgTitle() {
                return imgTitle;
            }

            public void setImgTitle(String imgTitle) {
                this.imgTitle = imgTitle;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
        public static class NewProductListEntity implements Serializable {
            public static final int TYPE_NEWER = -1;
            /**
             * profit : 0.08560
             * surplusAmount : 5000000.00
             * title : 新手专享
             * investBegin : 100.00
             * productsId : 322
             * status : 30
             * description : 历史年化收益（实际利率+新手奖励利率）
             * investEnd : 20000.00
             * plstimeLimitType : 0
             * profitFloat : 0.03440
             * type : -1
             * plstimeLimitValue : 15
             * investPercent : 0
             */

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
            private String buttonLabel;

            public String getButtonLabel() {
                return buttonLabel;
            }

            public void setButtonLabel(String buttonLabel) {
                this.buttonLabel = buttonLabel;
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

            public boolean isNewer() {
                return TYPE_NEWER == type;
            }
        }
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
        public static class ArticleListEntity implements Serializable {
            /**
             * hotTitle : 注册红包规则变更通知
             * time : 2017-07-28 19:26
             * url :
             */

            private String hotTitle;
            private String time;
            private String url;

            public String getHotTitle() {
                return hotTitle;
            }

            public void setHotTitle(String hotTitle) {
                this.hotTitle = hotTitle;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public HomeInfoModel getHomeInfo() {
        HomeInfoModel infoModel = new HomeInfoModel();
        infoModel.setProductTitle(getResult().getProductTitle());
        infoModel.setProductDescribe(getResult().getProductDescribe());
        infoModel.setNewDescribe(getResult().getNewDescribe());
        infoModel.setNewTitle(getResult().getNewTitle());

        ArrayList<BannerModel> banners = new ArrayList<>();
        for (ResultEntity.BannerListEntity entity : getResult().getBannerList()) {
            BannerModel bannerModel = new BannerModel();
            bannerModel.setBannerImgae(entity.getBannerImgae());
            bannerModel.setImgaeLinkUrl(entity.getImgaeLinkUrl());
            bannerModel.setImgTitle(entity.getImgTitle());
            banners.add(bannerModel);
        }
        infoModel.setBannsers(banners);

        ArrayList<NotificationModel> notifications = new ArrayList<>();
        for (ResultEntity.ArticleListEntity entity : getResult().getArticleList()) {
            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setHotTitle(entity.getHotTitle());
            notificationModel.setTime(entity.getTime());
            notificationModel.setUrl(entity.getUrl());
            notifications.add(notificationModel);
        }
        infoModel.setNotifications(notifications);

        ArrayList<ProductModel> normalProducts = new ArrayList<>();
        ArrayList<ProductModel> newerProducts = new ArrayList<>();
        for (ResultEntity.NewProductListEntity entity : getResult().getNewProductList()) {
            ProductModel productModel = new ProductModel();
            productModel.setDescription(entity.getDescription());
            productModel.setInvestBegin(entity.getInvestBegin());
            productModel.setInvestEnd(entity.getInvestEnd());
            productModel.setType(entity.getType());
            productModel.setTitle(entity.getTitle());
            productModel.setInvestPercent(entity.getInvestPercent());
            productModel.setPlstimeLimitType(entity.getPlstimeLimitType());
            productModel.setPlstimeLimitValue(entity.getPlstimeLimitValue());
            productModel.setProductsId(entity.getProductsId());
            productModel.setProfit(entity.getProfit());
            productModel.setProfitFloat(entity.getProfitFloat());
            productModel.setStatus(entity.getStatus());
            productModel.setSurplusAmount(entity.getSurplusAmount());
            productModel.setButtonLabel(entity.getButtonLabel());
            if (entity.isNewer()) {
                newerProducts.add(productModel);
            } else {
                normalProducts.add(productModel);
            }
        }
//        infoModel.setNewerProducts(newerProducts);
        infoModel.setNormalProducts(normalProducts);
        return infoModel;
    }
}
