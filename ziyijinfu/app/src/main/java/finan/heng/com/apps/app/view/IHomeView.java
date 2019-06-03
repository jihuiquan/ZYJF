package finan.heng.com.apps.app.view;

import java.util.ArrayList;
import finan.heng.com.apps.manager.entity.model.BannerModel;
import finan.heng.com.apps.manager.entity.model.NotificationModel;
import finan.heng.com.apps.manager.entity.model.ProductModel;
import finan.heng.com.apps.model.HomeInfo;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public interface IHomeView extends IBaseView{
    void showArticles(ArrayList<NotificationModel> articleList);

    void showBanners(ArrayList<BannerModel> bannerList);

    void showProducts(ArrayList<ProductModel> productList);

    void showHotActivity(ProductModel newProductList,boolean isNew);

    void showDesc(String newTitle, String newDescribe, String productTitile, String productDescribe);

    void finishRefresh();

    void isShowEmptyView(boolean empty);

    void showViewByLoginStatus(boolean showUnLoginView, boolean invitShow, boolean registerShow, boolean helpCenterShow, boolean platformDataShow, boolean platformIntroShow);

    void showNormalProductsLabel(boolean b);

    void passPlatformDataUrl(String platformDataUrl);

    void setTabUrl(HomeInfo homeInfo);


}
