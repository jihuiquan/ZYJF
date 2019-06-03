package finan.heng.com.apps.app.presenter;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.view.IWelcomeView;
import finan.heng.com.apps.http.ResponseData;
import finan.heng.com.apps.model.AdInfoResponse;
import finan.heng.com.apps.model.BaseResponse;
import rx.functions.Action1;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class WelcomePresenter extends BasePresenter{
    IWelcomeView view;

    public WelcomePresenter(IWelcomeView view) {
        this.view = view;
    }

    public void getAdActivity() {
       new HttpHelper().getAdInfo().subscribe(new Action1<BaseResponse<AdInfoResponse>>() {
           @Override
           public void call(BaseResponse<AdInfoResponse> adInfoResponseResponseData) {
                AdInfoResponse response = adInfoResponseResponseData.result;
               if (response != null){
                   view.showAdInfo(response);
               }
           }
       }, new Action1<Throwable>() {
           @Override
           public void call(Throwable throwable) {
               view.showAdInfo(null);
           }
       });
    }
}
