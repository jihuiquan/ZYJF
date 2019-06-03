package finan.heng.com.apps.app.view;

import finan.heng.com.apps.model.CompanyInfoResponseDeta;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public interface IMoreView extends IBaseView{

    void showNoticeNumber(int artNoRead);

    void showCompanyImg(String phoneImgUrl);

    void showCompanyMobile(String phone);

    void showCompanyWechat(String wechat);

    void setHelperCenterUrl(String helperCenterUrl);

    void success(CompanyInfoResponseDeta response );
}
