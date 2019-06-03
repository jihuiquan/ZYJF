package finan.heng.com.apps.model;

import com.dynamic.foundations.common.utils.ArithUtils;
import com.dynamic.foundations.common.utils.StringUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.manager.entity.model.BannerModel;
import finan.heng.com.apps.manager.entity.model.HomeInfoModel;
import finan.heng.com.apps.manager.entity.model.NotificationModel;
import finan.heng.com.apps.manager.entity.model.ProductModel;
import finan.heng.com.apps.save.DataCache;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 10:50
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomeInfo implements Serializable {
    @Expose
    @SerializedName("platformIntroUrl")
    public String platformIntroUrl;

    @Expose
    @SerializedName("helpCenterUrl")
    public String helpCenterUrl;

    @Expose
    @SerializedName("guideUrl")
    public String guideUrl;

    @Expose
    @SerializedName("safeUrl")
    public String safeUrl;

    @Expose
    @SerializedName("bannerList")
    public ArrayList<HomeBannner> bannerList;
    @Expose
    @SerializedName("articleList")
    public ArrayList<HomeNoticeList> articleList;
    @Expose
    @SerializedName("productList")
    public ArrayList<ProductInfo> productList;
    @Expose
    @SerializedName("starProduct")
    public ProductInfo starProduct;
    @Expose
    @SerializedName("newProduct")
    public ProductInfo newProduct;
    @Expose
    @SerializedName("newTitle")
    public String newTitle;
    @Expose
    @SerializedName("newDescribe")
    public String newDescribe;
    @Expose
    @SerializedName("productTitle")
    public String productTitile;
    @Expose
    @SerializedName("productDescribe")
    public String productDescribe;
    @Expose
    @SerializedName("isShow")
    public String isShow;
    @Expose
    @SerializedName("safeShow")
    public boolean safeShow; // 安全保障
    @Expose
    @SerializedName("inviteShow")
    public boolean inviteShow; // 邀请好友

    @Expose
    @SerializedName("guideShow")
    public boolean guideShow; // 新手指引


    @Expose
    @SerializedName("registerShow")
    public boolean registerShow; // 注册

    @Expose
    @SerializedName("riskUrl")
    public String platformDataUrl;

    @Expose
    @SerializedName("riskShow")
    public boolean platformDataShow; // 平台数据
    public boolean helpCenterShow; // 帮助中心
    public boolean platformIntroShow; // 平台简介
    public boolean safe2Show;
    public String token;
    @Expose
    @SerializedName("starTitle")
    public String starTitle;
    @Expose
    @SerializedName("starDescribe")
    public String starDescribe;

    private String platformIntroTitle;
    private String platformIntroAction;
    private String platformIntroIcon;

    private String safeTitle;
    private String safeAction;
    private String safeIcon;

    private String inviteTitle;
    private String inviteAction;
    private String inviteIcon;

    private String helpCenterTitle;
    private String helpCenterAction;
    private String helpCenterIcon;

    private String guideTitle;
    private String guideAction;
    private String guideIcon;

    private String inviteUrl;

    /**首页标题*/
    private String title;
    private String registerUrl;
    private String registerAction;
    private String registerImageUrl;
    /**底部提示语*/
    private String homeSlogan;
    /**无数据时背景图*/
    private String homeBackgroundImageUrl;
    /**标的状态角标*/
    private String productListImageUrl;
//    private List<NewProductModel> newProduct;

    public final static String STATUS_SHOWLOGIN = "0";// 登录
    public final static String STATUS_SHOWUNLOGIN = "1"; // 未登录

    public String getProductListImageUrl() {
        return productListImageUrl;
    }

    public void setProductListImageUrl(String productListImageUrl) {
        this.productListImageUrl = productListImageUrl;
    }

    public String getHomeBackgroundImageUrl() {
        return homeBackgroundImageUrl;
    }

    public void setHomeBackgroundImageUrl(String homeBackgroundImageUrl) {
        this.homeBackgroundImageUrl = homeBackgroundImageUrl;
    }

    public String getHomeSlogan() {
        return homeSlogan;
    }

    public void setHomeSlogan(String homeSlogan) {
        this.homeSlogan = homeSlogan;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public String getRegisterAction() {
        return registerAction;
    }

    public void setRegisterAction(String registerAction) {
        this.registerAction = registerAction;
    }

    public String getRegisterImageUrl() {
        return registerImageUrl;
    }

    public void setRegisterImageUrl(String registerImageUrl) {
        this.registerImageUrl = registerImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatformIntroIcon() {
        return platformIntroIcon;
    }

    public void setPlatformIntroIcon(String platformIntroIcon) {
        this.platformIntroIcon = platformIntroIcon;
    }

    public String getSafeIcon() {
        return safeIcon;
    }

    public void setSafeIcon(String safeIcon) {
        this.safeIcon = safeIcon;
    }

    public String getInviteIcon() {
        return inviteIcon;
    }

    public void setInviteIcon(String inviteIcon) {
        this.inviteIcon = inviteIcon;
    }

    public String getHelpCenterIcon() {
        return helpCenterIcon;
    }

    public void setHelpCenterIcon(String helpCenterIcon) {
        this.helpCenterIcon = helpCenterIcon;
    }

    public String getGuideIcon() {
        return guideIcon;
    }

    public void setGuideIcon(String guideIcon) {
        this.guideIcon = guideIcon;
    }

    public String getGuideTitle() {
        return guideTitle;
    }

    public void setGuideTitle(String guideTitle) {
        this.guideTitle = guideTitle;
    }

    public String getGuideAction() {
        return guideAction;
    }

    public void setGuideAction(String guideAction) {
        this.guideAction = guideAction;
    }

    public String getInviteUrl() {
        return inviteUrl;
    }

    public void setInviteUrl(String inviteUrl) {
        this.inviteUrl = inviteUrl;
    }

    public String getPlatformIntroTitle() {
        return platformIntroTitle;
    }

    public void setPlatformIntroTitle(String platformIntroTitle) {
        this.platformIntroTitle = platformIntroTitle;
    }

    public String getPlatformIntroAction() {
        return platformIntroAction;
    }

    public void setPlatformIntroAction(String platformIntroAction) {
        this.platformIntroAction = platformIntroAction;
    }

    public String getSafeTitle() {
        return safeTitle;
    }

    public void setSafeTitle(String safeTitle) {
        this.safeTitle = safeTitle;
    }

    public String getSafeAction() {
        return safeAction;
    }

    public void setSafeAction(String safeAction) {
        this.safeAction = safeAction;
    }

    public String getInviteTitle() {
        return inviteTitle;
    }

    public void setInviteTitle(String inviteTitle) {
        this.inviteTitle = inviteTitle;
    }

    public String getInviteAction() {
        return inviteAction;
    }

    public void setInviteAction(String inviteAction) {
        this.inviteAction = inviteAction;
    }

    public String getHelpCenterTitle() {
        return helpCenterTitle;
    }

    public void setHelpCenterTitle(String helpCenterTitle) {
        this.helpCenterTitle = helpCenterTitle;
    }

    public String getHelpCenterAction() {
        return helpCenterAction;
    }

    public void setHelpCenterAction(String helpCenterAction) {
        this.helpCenterAction = helpCenterAction;
    }

    public boolean isEmpty() {
        boolean isHasProduct = productList != null && productList.size() > 0;
        return newProduct == null && starProduct == null && !isHasProduct;
    }

    public HomeInfoModel getHomeInfo() {
        HomeInfoModel infoModel = new HomeInfoModel();
        infoModel.setProductTitle(StringUtils.trimToEmpty(productTitile));
        infoModel.setProductDescribe(StringUtils.trimToEmpty(productDescribe));
        infoModel.setNewDescribe(StringUtils.trimToEmpty(newDescribe));
        infoModel.setNewTitle(StringUtils.trimToEmpty(newTitle));
        infoModel.setSafeShow(safe2Show);
        infoModel.setInvitShow(inviteShow);
        infoModel.setRegisterShow(registerShow);
        infoModel.setPlatformDataShow(platformDataShow);
        infoModel.setPlatformIntroShow(platformIntroShow);
        infoModel.setHelpCenterShow(helpCenterShow);
        /**
         * 测试数据
         */
        infoModel.setShowUnLoginView(StringUtils.equals(StringUtils.trimToEmpty(isShow), STATUS_SHOWUNLOGIN)); // 显示未登录页面
        ArrayList<BannerModel> banners = new ArrayList<>();
        if (bannerList != null && bannerList.size() > 0) {
            for (HomeBannner entity : bannerList) {
                BannerModel bannerModel = new BannerModel();
                bannerModel.setBannerImgae(StringUtils.trimToEmpty(entity.bannerImgae));
                bannerModel.setImgaeLinkUrl(StringUtils.trimToEmpty(entity.imgaeLinkUrl));
                bannerModel.setImgTitle(StringUtils.trimToEmpty(entity.imgTitle));
                banners.add(bannerModel);
            }
        }
        infoModel.setBannsers(banners);

        ArrayList<NotificationModel> notifications = new ArrayList<>();
        if (articleList != null && articleList.size() > 0) {
            for (HomeNoticeList entity : articleList) {
                NotificationModel notificationModel = new NotificationModel();
                notificationModel.setHotTitle(StringUtils.trimToEmpty(entity.hotTitle));
                notificationModel.setTime(StringUtils.trimToEmpty(entity.time));
                notificationModel.setUrl(StringUtils.trimToEmpty(entity.url));
                notifications.add(notificationModel);
            }
        }
        infoModel.setNotifications(notifications);

        ArrayList<ProductModel> normalProducts = new ArrayList<>();
        if (productList != null && productList.size() > 0) {
            for (ProductInfo entity : productList) {
                ProductModel productModel = new ProductModel();
                /**
                 * 测试数据用
                 */
//            entity.tags="";// 空
//            entity.tags = null; //null验证
//            entity.tags = "ffff";//单一值验证
//            entity.tags = ",fff";
//            entity.tags = "fff,ffff,";

                productModel.setCashRateShow(entity.getCashRateShow());
                productModel.setCashRateProfit(entity.getCashRateProfit());

                try {
                    productModel.setActivity(StringUtils.isNotEmpty(entity.tags));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setTags(entity.tags.split(","));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setProfitScale(Double.parseDouble(entity.profit));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setDescription(entity.description);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setInvestBegin(String.valueOf(entity.investBegin));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setInvestEnd(String.valueOf(entity.investEnd));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setType(Integer.parseInt(entity.type));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setTitle(entity.title);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setInvestPercent(Double.parseDouble(entity.investPercent));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setPlstimeLimitType(Integer.parseInt(entity.plstimeLimitType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setPlstimeLimitValue(String.valueOf(entity.plstimeLimitValue));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setProductsId(Integer.parseInt(entity.productsId));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setProfit(Double.parseDouble(entity.profit));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setProfitFloat(Double.parseDouble(entity.profitFloat));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setStatus(Integer.parseInt(entity.status));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    productModel.setSurplusAmount(Double.parseDouble(entity.surplusAmount));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                productModel.setPlstimeLimitLabel(entity.getPlstimeLimitLabel());
                productModel.setSurplusAmountLabel(entity.getSurplusAmountLabel());
                productModel.setRepaymentTypeLabel(entity.getRepaymentTypeLabel());
                productModel.setPrdtimeLimitTypeLabel(entity.getPrdtimeLimitTypeLabel());
                productModel.setProfitLabel(entity.getProfitLabel());
                productModel.setButtonLabel(entity.getButtonLabel());
                // 临时数据
                normalProducts.add(productModel);
            }
        }
        infoModel.setNormalProducts(normalProducts);

        ProductModel productModel = new ProductModel();

        if (newProduct != null) {
            productModel.setProductTitle(StringUtils.trimToEmpty(newTitle));

            //测试用
            try {
                productModel.setActivity(StringUtils.isNotEmpty(newProduct.tags));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setTags(newProduct.tags.split(","));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setDescription(newProduct.description);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setInvestBegin(String.valueOf(newProduct.investBegin));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setInvestEnd(String.valueOf(newProduct.investEnd));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setType(Integer.parseInt(newProduct.type));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setTitle(newProduct.title);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setInvestPercent(Double.parseDouble(newProduct.investPercent));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setPlstimeLimitType(Integer.parseInt(newProduct.plstimeLimitType));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setPlstimeLimitValue(String.valueOf(newProduct.plstimeLimitValue));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setProductsId(Integer.parseInt(newProduct.productsId));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setProfit(Double.parseDouble(newProduct.profit));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setProfitFloat(Double.parseDouble(newProduct.profitFloat));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setStatus(Integer.parseInt(newProduct.status));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setSurplusAmount(Double.parseDouble(newProduct.surplusAmount));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setRightDesc("" + productModel.getPlstimeLimitValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                productModel.setLeftDesc("" + (int) Double.parseDouble(productModel.getInvestBegin()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            productModel.setPlstimeLimitLabel(productModel.getPlstimeLimitLabel());
            productModel.setInvestBeginLabel(productModel.getInvestBeginLabel());
            productModel.setProductListImageUrl(productModel.getProductListImageUrl());
            productModel.setProfitLabel(productModel.getProfitLabel());
            infoModel.setNewerProducts(productModel);
        }
        Logger.i("newmodel" + infoModel.toString());
        ProductModel starModel = new ProductModel();

        if (starProduct != null) {

            starModel.setProductTitle(StringUtils.trimToEmpty(starProduct.title));
            //测试用
            try {
                starModel.setActivity(StringUtils.isNotEmpty(starProduct.tags));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                starModel.setTags(starProduct.tags.split(","));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                starModel.setDescription(starProduct.description);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setInvestBegin(String.valueOf(starProduct.investBegin));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setInvestEnd(String.valueOf(starProduct.investEnd));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setType(Integer.parseInt(starProduct.type));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            try {
                starModel.setTitle(starProduct.title);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setInvestPercent(Double.parseDouble(starProduct.investPercent));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setPlstimeLimitType(Integer.parseInt(starProduct.plstimeLimitType));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setPlstimeLimitValue(String.valueOf(starProduct.plstimeLimitValue));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setProductsId(Integer.parseInt(starProduct.productsId));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setProfit(Double.parseDouble(starProduct.profit));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (starProduct.getCashRateShow().equals("1")) {
                    starModel.setProfitFloat(Double.parseDouble(starProduct.getCashRateProfit()));
                } else {
                    starModel.setProfitFloat(Double.parseDouble(starProduct.profitFloat));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setStatus(Integer.parseInt(starProduct.status));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            try {
                starModel.setSurplusAmount(Double.parseDouble(starProduct.surplusAmount));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                starModel.setRightDesc("" + starModel.getPlstimeLimitValue() );
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (starModel.getSurplusAmount() >= 10000) {
                    Double surplus = ArithUtils.div2point(starModel.getSurplusAmount(), 10000, 2);
                    starModel.setLeftDesc("" + IConstants.Formatter.rateFormat.format(surplus));
                } else {
                    starModel.setLeftDesc("" + (int) starModel.getSurplusAmount() );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            starModel.setPlstimeLimitLabel(starProduct.getPlstimeLimitLabel());
            starModel.setInvestBeginLabel(starProduct.getInvestBeginLabel());
            starModel.setProfitLabel(starProduct.getProfitLabel());
            starModel.setButtonLabel(starProduct.getButtonLabel());
            starModel.setTypeIcon(starProduct.getTypeIcon());
            infoModel.setStarProducts(starModel);
        }
        return infoModel;
    }

    @Override
    public String toString() {
        return "HomeInfo{" +
                "bannerList=" + bannerList +
                ", articleList=" + articleList +
                ", productList=" + productList +
                ", newProductList=" + newProduct +
                ", newTitle='" + newTitle + '\'' +
                ", newDescribe='" + newDescribe + '\'' +
                ", productTitile='" + productTitile + '\'' +
                ", productDescribe='" + productDescribe + '\'' +
                ", isShow=" + isShow +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        Logger.i("body" + "response----" + "token=" + com.dynamic.foundations.common.utils.StringUtils.trimToEmpty(token));
        if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(token)) {
            DataCache.instance.saveCacheData("heng", "token", token);
        }
    }
}
