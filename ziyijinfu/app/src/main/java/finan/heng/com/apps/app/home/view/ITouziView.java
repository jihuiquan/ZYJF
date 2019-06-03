package finan.heng.com.apps.app.home.view;

import finan.heng.com.apps.app.view.IBaseView;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.manager.entity.model.WelfareResult;
import finan.heng.com.apps.model.GetProductDetailMoneyResponse;
import finan.heng.com.apps.model.RiskCheckResponse;
import finan.heng.com.apps.model.RiskDefaultResponse;
import finan.heng.com.apps.model.SureBuyResponse;
import finan.heng.com.apps.model.SurePayResponse;

/**
 * Created by Administrator on 2017/9/20.
 */

public interface ITouziView extends IBaseView{
    void requestSuccess(SureBuyResponse buyResponse);
    void requestFailed(Throwable throwable);
    void showRedPack(String text);
    void showCoupon(String text);
    void getEstimatedSuccess(GetProductDetailMoneyResponse getProductDetailMoneyResponse);
    void checkInvestMoneySuccess(RiskCheckResponse riskCheckResponse);
    void riskDefaultSuccess(RiskDefaultResponse riskDefaultResponse);

    void investRequestSuccess(SurePayResponse surePayResponse);

    void getAvailableRedAndCouponSuccess(WelfareResult result);
    void getAvailableRed(WelfareResult result);
    void getAvailableCoupon(WelfareResult result);

}
