package finan.heng.com.apps.app.home.presenter;

import android.util.Log;


import com.dynamic.foundations.common.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import finan.heng.com.apps.HttpHelper;

import finan.heng.com.apps.app.home.view.ITouziView;
import finan.heng.com.apps.app.presenter.BasePresenter;

import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.manager.entity.model.WelfareResult;
import finan.heng.com.apps.model.GetProductDetailMoneyResponse;
import finan.heng.com.apps.model.GetProductRedPackModel;
import finan.heng.com.apps.model.RiskCheckResponse;
import finan.heng.com.apps.model.RiskDefaultResponse;
import finan.heng.com.apps.model.SureBuyResponse;
import finan.heng.com.apps.model.SurePayResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/20.
 */

public class TouziPresenter extends BasePresenter {

    ITouziView touziView;
    Subscription subscription;


    public TouziPresenter(ITouziView iTouziView) {
        this.touziView = iTouziView;
    }

    public void destroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        touziView = null;
    }

    //获取数据
    public void getData(int id) {
        new HttpHelper().sureBuy(id).subscribe(new Action1<SureBuyResponse>() {
            @Override
            public void call(SureBuyResponse sureBuyResponse) {
                try {
                    String token = sureBuyResponse.result.getToken();
                    if (StringUtils.isNotEmpty(token)) {
                        DataCache.instance.saveCacheData("heng", "token", token);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    touziView.requestSuccess(sureBuyResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    touziView.requestFailed(throwable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getAvailableRedAndCoupon(HashMap<String, String> map) {
        new HttpHelper().getRedpackAndCoupon(map).subscribe(new Action1<CommonHttpModel<WelfareResult>>() {
            @Override
            public void call(CommonHttpModel<WelfareResult> commonHttpModel) {
                try {
                    touziView.getAvailableRedAndCouponSuccess(commonHttpModel.result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    Log.i("TouziPresenter", throwable.getMessage() + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //获取预估收益
    public void getEstimatedEarnings(HashMap<String, String> map) {
        new HttpHelper().getProductDetailMoney(map).subscribe(new Action1<GetProductDetailMoneyResponse>() {
            @Override
            public void call(GetProductDetailMoneyResponse getProductDetailMoneyResponse) {
                try {
                    touziView.getEstimatedSuccess(getProductDetailMoneyResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.i("TouziPresenter", throwable.getMessage() + "");
            }
        });
    }

    //检查风险
    public void checkInvestMoney(String value) {
        new HttpHelper().investCheck(value).subscribe(new Action1<RiskCheckResponse>() {
            @Override
            public void call(RiskCheckResponse riskCheckResponse) {
                try {
                    touziView.checkInvestMoneySuccess(riskCheckResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.i("TouziPresenter", throwable.getMessage() + "");
            }
        });
    }

    //一键默认等级
    public void riskDefaultLevel() {
        new HttpHelper().riskDefault().subscribe(new Action1<RiskDefaultResponse>() {
            @Override
            public void call(RiskDefaultResponse riskDefaultResponse) {
                try {
                    touziView.riskDefaultSuccess(riskDefaultResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    touziView.toast(requestErrorThrowable.getMessage());
                }
            }
        });
    }

    public void investRequest(HashMap<String, String> map) {
        new HttpHelper().surePay(map).subscribe(new Action1<SurePayResponse>() {
            @Override
            public void call(SurePayResponse surePayResponse) {
                com.orhanobut.logger.Logger.i("result" + surePayResponse.result.toString());
                try {
                    String token = surePayResponse.result.getToken();
                    if (StringUtils.isNotEmpty(token)) {
                        DataCache.instance.saveCacheData("heng", "token", token);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    touziView.investRequestSuccess(surePayResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    touziView.toast(requestErrorThrowable.getMessage());
                }
            }
        });
    }
}
