package finan.heng.com.apps.manager.entity.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class HomeInfoModel extends BaseModel{

    /**
     * productTitle : 为你推荐
     * productDescribe : 个性推荐  专属订制
     * newTitle : 新手专享
     * newDescribe : 享受新手奖励利率
     */

    private String productTitle;
    private String productDescribe;
    private String newTitle;
    private String newDescribe;
    private ArrayList<BannerModel> bannsers;
    private ArrayList<NotificationModel> notifications;
    private ArrayList<ProductModel> normalProducts;
    private ProductModel newerProducts;
    private ProductModel starProducts;
    private boolean isShowUnLoginView; // 是否显示登录状态
    private boolean safeShow;
    private boolean invitShow;
    private boolean platformDataShow;
    private boolean helpCenterShow;
    private boolean platformIntroShow;
    private boolean registerShow;

    public boolean isSafeShow() {
        return safeShow;
    }

    public void setSafeShow(boolean safeShow) {
        this.safeShow = safeShow;
    }

    public boolean isInvitShow() {
        return invitShow;
    }

    public void setInvitShow(boolean invitShow) {
        this.invitShow = invitShow;
    }

    public boolean isRegisterShow() {
        return registerShow;
    }

    public void setRegisterShow(boolean registerShow) {
        this.registerShow = registerShow;
    }

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

    public ArrayList<BannerModel> getBannsers() {
        return bannsers;
    }

    public void setBannsers(ArrayList<BannerModel> bannsers) {
        this.bannsers = bannsers;
    }

    public ArrayList<NotificationModel> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<NotificationModel> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<ProductModel> getNormalProducts() {
        return normalProducts;
    }

    public void setNormalProducts(ArrayList<ProductModel> normalProducts) {
        this.normalProducts = normalProducts;
    }



    public boolean isEmpty() {
        return newerProducts == null;
    }

    public boolean isShowUnLoginView() {
        return isShowUnLoginView;
    }

    public void setShowUnLoginView(boolean showUnLoginView) {
        isShowUnLoginView = showUnLoginView;
    }

    public ProductModel getNewerProducts() {
        return newerProducts;
    }

    public void setNewerProducts(ProductModel newerProducts) {
        this.newerProducts = newerProducts;
    }

    public ProductModel getStarProducts() {
        return starProducts;
    }

    public void setStarProducts(ProductModel starProducts) {
        this.starProducts = starProducts;
    }

    public boolean isPlatformDataShow() {
        return platformDataShow;
    }

    public void setPlatformDataShow(boolean platformDataShow) {
        this.platformDataShow = platformDataShow;
    }

    public boolean isHelpCenterShow() {
        return helpCenterShow;
    }

    public void setHelpCenterShow(boolean helpCenterShow) {
        this.helpCenterShow = helpCenterShow;
    }

    public boolean isPlatformIntroShow() {
        return platformIntroShow;
    }

    public void setPlatformIntroShow(boolean platformIntroShow) {
        this.platformIntroShow = platformIntroShow;
    }

    @Override
    public String toString() {
        return "HomeInfoModel{" +
                "productTitle='" + productTitle + '\'' +
                ", productDescribe='" + productDescribe + '\'' +
                ", newTitle='" + newTitle + '\'' +
                ", newDescribe='" + newDescribe + '\'' +
                ", bannsers=" + bannsers +
                ", notifications=" + notifications +
                ", normalProducts=" + normalProducts +
                ", newerProducts=" + newerProducts +
                ", starProducts=" + starProducts +
                ", isShowUnLoginView=" + isShowUnLoginView +
                ", safeShow=" + safeShow +
                ", invitShow=" + invitShow +
                ", registerShow=" + registerShow +
                '}';
    }
}
