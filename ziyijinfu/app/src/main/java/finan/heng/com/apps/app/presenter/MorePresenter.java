package finan.heng.com.apps.app.presenter;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.view.IMoreView;
import finan.heng.com.apps.model.CompanyInfoResponse;
import finan.heng.com.apps.model.CompanyInfoResponseDeta;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class MorePresenter extends BasePresenter{
    IMoreView view;

    public MorePresenter(IMoreView view) {
        this.view = view;
    }
    public void getCompanyInfo(){
        new HttpHelper().getCompanyInfoMore().subscribe(new Action1<CompanyInfoResponse>() {
            @Override
            public void call(CompanyInfoResponse companyInfoResponse) {
                CompanyInfoResponseDeta responseDeta = companyInfoResponse.result;
                if (responseDeta != null){
                    view.showCompanyImg(responseDeta.getPhoneImgUrl());//电话图片
                    view.showCompanyMobile(responseDeta.getPhone());//电话赋值
                    view.showCompanyWechat(responseDeta.getWechat());//微信公众号赋值
                    view.showNoticeNumber(responseDeta.getArtNoRead());
                    view.setHelperCenterUrl(responseDeta.getHelpCenterUrl());//帮助中心跳转界面
                    view.success(responseDeta);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
//                    view.toast(requestErrorThrowable.getMessage());
                }
            }
        });
    }
}
