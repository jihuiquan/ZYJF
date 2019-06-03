package finan.heng.com.apps.app.presenter;

import com.dynamic.foundations.common.utils.StringUtils;
import com.orhanobut.logger.Logger;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.view.IHomeView;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.manager.entity.model.HomeInfoModel;
import finan.heng.com.apps.model.HomeInfo;
import finan.heng.com.apps.model.HomeResponse;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.model.ProductInfo;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import rx.functions.Action1;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class HomePresenter extends BasePresenter {
    IHomeView view;

    public HomePresenter(IHomeView view) {
        this.view = view;
    }

    public void getHomeInfo() {
        MyWalletResponse cacheData = DataCache.instance.getCacheData("heng", "MyWalletResponse");

        String token = DataCache.instance.getCacheData("heng", "token");
        Logger.i("header" + "token=" + token);
        Logger.i("url" + URLHelper.getInstance().URL);

        new HttpHelper().getHomeScroll(token).subscribe(new Action1<HomeResponse>() {
            @Override
            public void call(HomeResponse homeResponse) {

                view.finishRefresh();
                if (homeResponse != null && homeResponse.isSuccess()) {
                    HomeInfo result = homeResponse.result;
                    Logger.i("homeinfo" + result.toString());
                    HomeInfoModel info = result.getHomeInfo();
                    view.showArticles(info.getNotifications());
                    view.showBanners(info.getBannsers());
                    view.showViewByLoginStatus(result.safeShow, result.inviteShow, result.registerShow
                            , result.helpCenterShow, result.guideShow, result.platformIntroShow);
                    view.showDesc(StringUtils.trimToEmpty(info.getNewTitle())
                            , StringUtils.trimToEmpty(info.getNewDescribe())
                            , StringUtils.trimToEmpty(info.getProductTitle())
                            , StringUtils.trimToEmpty(info.getProductDescribe()));
                    if (info.getNewerProducts() != null) {
                        view.showHotActivity(info.getNewerProducts(),true);
                    } else if (info.getStarProducts() != null){
                        view.showHotActivity(info.getStarProducts(),false);
                    } else {
                        view.showHotActivity(null,false);
                    }
                    view.setTabUrl(result);
                    view.isShowEmptyView(false);
                    view.showNormalProductsLabel(info.getNormalProducts() != null && info.getNormalProducts().size() > 0);
                    view.showProducts(info.getNormalProducts());
                    view.passPlatformDataUrl(result.platformDataUrl);
                }
            }
        }, new Action1<Throwable>()

        {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    view.toast(requestErrorThrowable.getMessage());
                    if (requestErrorThrowable.getErrorCode().equals("402")){
                        view.isShowEmptyView(false);
                    } else {
                        view.isShowEmptyView(true);
                    }
                    view.finishRefresh();
                }
            }
        });
    }
}
