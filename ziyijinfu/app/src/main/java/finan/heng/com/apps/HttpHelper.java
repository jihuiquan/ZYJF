package finan.heng.com.apps;


import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import finan.heng.com.apps.base.BaseApplication;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.http.HttpRequestHelper;
import finan.heng.com.apps.http.HttpRequestHelper2;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.manager.entity.model.WelfareResult;
import finan.heng.com.apps.model.AdInfoResponse;
import finan.heng.com.apps.model.BankCardResponse;
import finan.heng.com.apps.model.BankListResponse;
import finan.heng.com.apps.model.BaoFooResult;
import finan.heng.com.apps.model.BaseResponse;
import finan.heng.com.apps.model.CardResponse;
import finan.heng.com.apps.model.CardTypeResponse;
import finan.heng.com.apps.model.ChangeJiaoYiPasswordResponse;
import finan.heng.com.apps.model.ChangeLoginPasswordResponse;
import finan.heng.com.apps.model.CheckForgetBuyPasswordMsgCodeResponse;
import finan.heng.com.apps.model.CheckMobileResponse;
import finan.heng.com.apps.model.ChongZhiResponse;
import finan.heng.com.apps.model.CompanyInfoResponse;
import finan.heng.com.apps.model.EvaluationResponse;
import finan.heng.com.apps.model.EvaluationSubmitResponse;
import finan.heng.com.apps.model.EvaluationSubmitResult;
import finan.heng.com.apps.model.FinanceTitleResponse;
import finan.heng.com.apps.model.ForgetBuyPasswordMsgCodeResponse;
import finan.heng.com.apps.model.GetCompanyInfoResponse;
import finan.heng.com.apps.model.GetGenreListResponse;
import finan.heng.com.apps.model.GetGongGaoResponse;
import finan.heng.com.apps.model.GetInvestHistoryDetailResponse;
import finan.heng.com.apps.model.GetInvestHistoryListResponse;
import finan.heng.com.apps.model.GetInviteHistoryResponse;
import finan.heng.com.apps.model.GetInviteInvestListResponse;
import finan.heng.com.apps.model.GetJiaoYiListResponse;
import finan.heng.com.apps.model.GetMessageCodeResponse;
import finan.heng.com.apps.model.GetPayStyleResponse;
import finan.heng.com.apps.model.GetProductDetailListResponse;
import finan.heng.com.apps.model.GetProductDetailMoneyResponse;
import finan.heng.com.apps.model.GetProductListResponse;
import finan.heng.com.apps.model.GetProductRedPackResponse;
import finan.heng.com.apps.model.GetRedPackListResponse;
import finan.heng.com.apps.model.GetRenZhengMessageResponse;
import finan.heng.com.apps.model.GetTradeListResponse;
import finan.heng.com.apps.model.GetWithdrawInitResponse;
import finan.heng.com.apps.model.HomeResponse;
import finan.heng.com.apps.model.InvestRecordResponse;
import finan.heng.com.apps.model.InvestTwoResponse;
import finan.heng.com.apps.model.InviteFriendBean;
import finan.heng.com.apps.model.IsOpenWebModelResponse;
import finan.heng.com.apps.model.LogOutResponse;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.model.MessageUnreadNumResult;
import finan.heng.com.apps.model.MoreModelResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.model.ProductDetailResponse;
import finan.heng.com.apps.model.ReBackResponse;
import finan.heng.com.apps.model.RechargeInitResponse;
import finan.heng.com.apps.model.RegisterResponse;
import finan.heng.com.apps.model.RiskCheckResponse;
import finan.heng.com.apps.model.RiskDefaultResponse;
import finan.heng.com.apps.model.RuleNameModel;
import finan.heng.com.apps.model.RuleNameResponse;
import finan.heng.com.apps.model.ServerInfo;
import finan.heng.com.apps.model.SetBuyPasswordResponse;
import finan.heng.com.apps.model.SureBuyResponse;
import finan.heng.com.apps.model.SurePayResponse;
import finan.heng.com.apps.model.SureWithdrawResponse;
import finan.heng.com.apps.model.SystemResult;
import finan.heng.com.apps.model.TradeTypeModelResponse;
import finan.heng.com.apps.model.UpdateInfoRespond;
import finan.heng.com.apps.model.WithDrawActureAmtResponse;
import finan.heng.com.apps.model.getPassWordResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.rx.RxHttpHelper;
import finan.heng.com.apps.save.DataCache;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 */
public class HttpHelper {
    //宝付验证码
    public Observable<CommonHttpModel<BaoFooResult>> paymentConfirm(Map<String,String> params) {
        return HttpRequestHelper.getHttpInterface().paymentSubmit(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //宝付验证码
    public Observable<CommonHttpModel<BaoFooResult>> baofooPayment(String code) {
        return HttpRequestHelper.getHttpInterface().baofooPayment(code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资协议2请求
    public Observable<InvestTwoResponse> getInvestTwo(String id) {
        return HttpRequestHelper.getHttpInterface().getInvestTwo(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //卡片类型
    public Observable<CardTypeResponse> getCardTypeList() {
        return HttpRequestHelper.getHttpInterface().getCardTypeList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //卡片列表
    public Observable<CardResponse> getCardList(Map<String, String> map) {
        return HttpRequestHelper.getHttpInterface().getCardList(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    //获取系统参数
    public Observable<CommonHttpModel<SystemResult>> getSystemParam() {
        return HttpRequestHelper.getHttpInterface().getSystemParam().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<CommonHttpModel<InviteFriendBean>> initInviteFriends(){
        return HttpRequestHelper.getHttpInterface().initInviteFriends().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //投资检查
    public Observable<RiskCheckResponse> investCheck(String value) {
        return HttpRequestHelper.getHttpInterface().investCheck(value).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CommonHttpModel<EvaluationSubmitResult>> getInvestTypeInfo(String investType) {
        return HttpRequestHelper.getHttpInterface().getInvestTypeInfo(investType).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //一键默认
    public Observable<RiskDefaultResponse> riskDefault() {
        return HttpRequestHelper.getHttpInterface().riskDefault().onErrorResumeNext(new Func1<Throwable, Observable<? extends RiskDefaultResponse>>() {
            @Override
            public Observable<? extends RiskDefaultResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<RiskDefaultResponse, Observable<? extends RiskDefaultResponse>>() {
            @Override
            public Observable<? extends RiskDefaultResponse> call(RiskDefaultResponse riskDefaultResponse) {
                if (!riskDefaultResponse.code.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", riskDefaultResponse.message));
                } else {
                    try {
                        MyUserInfo userInfo = DataCache.instance.getCacheData("heng", "MyUserInfo");
                        userInfo.result.investStyle = riskDefaultResponse.result.max_type;
                        DataCache.instance.saveCacheData("heng", "MyUserInfo", userInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    return Observable.just(riskDefaultResponse);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<RiskDefaultResponse>() {
            @Override
            public void call(RiskDefaultResponse riskDefaultResponse) {
                ViewHelper.showToast(BaseApplication.getApplication().getApplicationContext(), "测评成功");
            }
        });
    }

    //获取测评列表
    public Observable<EvaluationResponse> getEvaluationList() {
        return HttpRequestHelper.getHttpInterface().getEvauationList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //提交答案
    public Observable<EvaluationSubmitResponse> sendAnswer(HashMap<String, String> map) {
        return HttpRequestHelper.getHttpInterface().sendAnwser(map).onErrorResumeNext(new Func1<Throwable, Observable<? extends EvaluationSubmitResponse>>() {
            @Override
            public Observable<? extends EvaluationSubmitResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<EvaluationSubmitResponse, Observable<? extends EvaluationSubmitResponse>>() {
            @Override
            public Observable<? extends EvaluationSubmitResponse> call(EvaluationSubmitResponse submitResponse) {
                if (!submitResponse.code.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", submitResponse.message));
                } else {
                    try {
                        MyUserInfo userInfo = DataCache.instance.getCacheData("heng", "MyUserInfo");
                        userInfo.result.investStyle = submitResponse.result.levelType;
                        DataCache.instance.saveCacheData("heng", "MyUserInfo", userInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Observable.just(submitResponse);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    //理财页签设置
    public Observable<FinanceTitleResponse> getFinanceTitle() {
        return HttpRequestHelper.getHttpInterface().getFinanceTitle().onErrorResumeNext(new Func1<Throwable, Observable<? extends FinanceTitleResponse>>() {
            @Override
            public Observable<? extends FinanceTitleResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<FinanceTitleResponse, Observable<? extends FinanceTitleResponse>>() {
            @Override
            public Observable<? extends FinanceTitleResponse> call(FinanceTitleResponse financeTitleResponse) {
                if (!financeTitleResponse.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", financeTitleResponse.errorMsg));
                } else {
                    return Observable.just(financeTitleResponse);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //产品详情
    public Observable<ProductDetailResponse> getProduct(int id) {
        return HttpRequestHelper.getHttpInterface().getProduct(id).onErrorResumeNext(new Func1<Throwable, Observable<? extends ProductDetailResponse>>() {
            @Override
            public Observable<? extends ProductDetailResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ProductDetailResponse, Observable<? extends ProductDetailResponse>>() {
            @Override
            public Observable<? extends ProductDetailResponse> call(ProductDetailResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //首页信息
    public Observable<HomeResponse> getHomeScroll(String token) {
        return HttpRequestHelper.getHttpInterface().getHomeScroll(token).onErrorResumeNext(new Func1<Throwable, Observable<? extends HomeResponse>>() {
            @Override
            public Observable<? extends HomeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<HomeResponse, Observable<? extends HomeResponse>>() {
            @Override
            public Observable<? extends HomeResponse> call(HomeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取验证码
    public Observable<GetMessageCodeResponse> getMessageCode(String phone, String picCode) {
        return HttpRequestHelper.getHttpInterface().getMessageCode(phone, picCode).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetMessageCodeResponse>>() {
            @Override
            public Observable<? extends GetMessageCodeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetMessageCodeResponse, Observable<? extends GetMessageCodeResponse>>() {
            @Override
            public Observable<? extends GetMessageCodeResponse> call(GetMessageCodeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //注册
    public Observable<RegisterResponse> register(HashMap params) {
        return HttpRequestHelper.getHttpInterface().register(params).onErrorResumeNext(new Func1<Throwable, Observable<? extends RegisterResponse>>() {
            @Override
            public Observable<? extends RegisterResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<RegisterResponse, Observable<? extends RegisterResponse>>() {
            @Override
            public Observable<? extends RegisterResponse> call(RegisterResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //登陆
    public Observable<LoginResponse> login(String phone, String clientId, String password) {
        return HttpRequestHelper.getHttpInterface().login(phone, clientId, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends LoginResponse>>() {
            @Override
            public Observable<? extends LoginResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<LoginResponse, Observable<? extends LoginResponse>>() {
            @Override
            public Observable<? extends LoginResponse> call(LoginResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //用户信息
    public Observable<MyUserInfo> getUserInfo() {
        return HttpRequestHelper.getHttpInterface().getUserInfo().onErrorResumeNext(new Func1<Throwable, Observable<? extends MyUserInfo>>() {
            @Override
            public Observable<? extends MyUserInfo> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<MyUserInfo, Observable<? extends MyUserInfo>>() {
            @Override
            public Observable<? extends MyUserInfo> call(MyUserInfo response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //银行列表信息
    public Observable<BankListResponse> getBankInfo() {
        return HttpRequestHelper.getHttpInterface().getBankInfo().onErrorResumeNext(new Func1<Throwable, Observable<? extends BankListResponse>>() {
            @Override
            public Observable<? extends BankListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<BankListResponse, Observable<? extends BankListResponse>>() {
            @Override
            public Observable<? extends BankListResponse> call(BankListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //召回登陆密码
    public Observable<getPassWordResponse> findPassWord(String phone, String msgCode, String password) {
        return HttpRequestHelper.getHttpInterface().findPassWord(phone, msgCode, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends getPassWordResponse>>() {
            @Override
            public Observable<? extends getPassWordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<getPassWordResponse, Observable<? extends getPassWordResponse>>() {
            @Override
            public Observable<? extends getPassWordResponse> call(getPassWordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取短信验证码(找回密码)
    public Observable<GetMessageCodeResponse> findPasswordMessageCode(String phone, String code) {
        return HttpRequestHelper.getHttpInterface().findPasswordMessageCode(phone, code).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetMessageCodeResponse>>() {
            @Override
            public Observable<? extends GetMessageCodeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetMessageCodeResponse, Observable<? extends GetMessageCodeResponse>>() {
            @Override
            public Observable<? extends GetMessageCodeResponse> call(GetMessageCodeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取短信验证码(实名认证)
    public Observable<GetRenZhengMessageResponse> renzhengMessageCode(String yuliuphone) {
        return HttpRequestHelper.getHttpInterface().renzhengMessageCode(yuliuphone).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetRenZhengMessageResponse>>() {
            @Override
            public Observable<? extends GetRenZhengMessageResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetRenZhengMessageResponse, Observable<? extends GetRenZhengMessageResponse>>() {
            @Override
            public Observable<? extends GetRenZhengMessageResponse> call(GetRenZhengMessageResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //实名认证
    public Observable<RuleNameResponse> ruleName(String name, String bankId, String idCard, String cardCode, String phone, String captcha) {
        return HttpRequestHelper.getHttpInterface().ruleName(name, bankId, idCard, cardCode, phone, captcha).onErrorResumeNext(new Func1<Throwable, Observable<? extends RuleNameResponse>>() {
            @Override
            public Observable<? extends RuleNameResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<RuleNameResponse, Observable<? extends RuleNameResponse>>() {
            @Override
            public Observable<? extends RuleNameResponse> call(RuleNameResponse response) {
//                if (!response.errorCode.equals("0")) {
//                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
//                } else {
                    return Observable.just(response);
//                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //我的银行卡
    public Observable<BankCardResponse> myBankCard() {
        return HttpRequestHelper.getHttpInterface().myBankCard().onErrorResumeNext(new Func1<Throwable, Observable<? extends BankCardResponse>>() {
            @Override
            public Observable<? extends BankCardResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<BankCardResponse, Observable<? extends BankCardResponse>>() {
            @Override
            public Observable<? extends BankCardResponse> call(BankCardResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //绑卡发送验证码
    public Observable<CommonHttpModel<RuleNameModel>> bindCardSendSmgCode(String msgCode) {
        return HttpRequestHelper.getHttpInterface().bindCardSendSmgCode(msgCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    //修改登录密码
    public Observable<ChangeLoginPasswordResponse> changeLoginPassword(String oldWord, String newWord) {
        return HttpRequestHelper.getHttpInterface().changeLoginPassword(oldWord, newWord).onErrorResumeNext(new Func1<Throwable, Observable<? extends ChangeLoginPasswordResponse>>() {
            @Override
            public Observable<? extends ChangeLoginPasswordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ChangeLoginPasswordResponse, Observable<? extends ChangeLoginPasswordResponse>>() {
            @Override
            public Observable<? extends ChangeLoginPasswordResponse> call(ChangeLoginPasswordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //修改交易密码
    public Observable<ChangeJiaoYiPasswordResponse> changeJiaoyiPassword(String oldWord, String newWord) {
        return HttpRequestHelper.getHttpInterface().changeJiaoyiPassword(oldWord, newWord).onErrorResumeNext(new Func1<Throwable, Observable<? extends ChangeJiaoYiPasswordResponse>>() {
            @Override
            public Observable<? extends ChangeJiaoYiPasswordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ChangeJiaoYiPasswordResponse, Observable<? extends ChangeJiaoYiPasswordResponse>>() {
            @Override
            public Observable<? extends ChangeJiaoYiPasswordResponse> call(ChangeJiaoYiPasswordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    //重置交易密码
    public Observable<SetBuyPasswordResponse> resetBuyPassword(String word) {
        return HttpRequestHelper.getHttpInterface().resetBuyPassword(word).onErrorResumeNext(new Func1<Throwable, Observable<? extends SetBuyPasswordResponse>>() {
            @Override
            public Observable<? extends SetBuyPasswordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SetBuyPasswordResponse, Observable<? extends SetBuyPasswordResponse>>() {
            @Override
            public Observable<? extends SetBuyPasswordResponse> call(SetBuyPasswordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    //设置交易密码
    public Observable<SetBuyPasswordResponse> setBuyPassword(String word) {
        return HttpRequestHelper.getHttpInterface().setBuyPassword(word).onErrorResumeNext(new Func1<Throwable, Observable<? extends SetBuyPasswordResponse>>() {
            @Override
            public Observable<? extends SetBuyPasswordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SetBuyPasswordResponse, Observable<? extends SetBuyPasswordResponse>>() {
            @Override
            public Observable<? extends SetBuyPasswordResponse> call(SetBuyPasswordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //忘记交易密码的短信验证码
    public Observable<ForgetBuyPasswordMsgCodeResponse> forgetBuyPasswordMsgCode(String idCard) {
        return HttpRequestHelper.getHttpInterface().forgetBuyPasswordMsgCode(idCard).onErrorResumeNext(new Func1<Throwable, Observable<? extends ForgetBuyPasswordMsgCodeResponse>>() {
            @Override
            public Observable<? extends ForgetBuyPasswordMsgCodeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ForgetBuyPasswordMsgCodeResponse, Observable<? extends ForgetBuyPasswordMsgCodeResponse>>() {
            @Override
            public Observable<? extends ForgetBuyPasswordMsgCodeResponse> call(ForgetBuyPasswordMsgCodeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //验证忘记交易密码的短信验证码
    public Observable<CheckForgetBuyPasswordMsgCodeResponse> checkForgetBuyPasswordMsgCode(String msgCode) {
        return HttpRequestHelper.getHttpInterface().checkForgetBuyPasswordMsgCode(msgCode).onErrorResumeNext(new Func1<Throwable, Observable<? extends CheckForgetBuyPasswordMsgCodeResponse>>() {
            @Override
            public Observable<? extends CheckForgetBuyPasswordMsgCodeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<CheckForgetBuyPasswordMsgCodeResponse, Observable<? extends CheckForgetBuyPasswordMsgCodeResponse>>() {
            @Override
            public Observable<? extends CheckForgetBuyPasswordMsgCodeResponse> call(CheckForgetBuyPasswordMsgCodeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取产品列表
    public Observable<GetProductListResponse> getProductList(String geneId, String pageNo) {
        return HttpRequestHelper.getHttpInterface().getProductList(geneId, pageNo).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductListResponse>>() {
            @Override
            public Observable<? extends GetProductListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductListResponse, Observable<? extends GetProductListResponse>>() {
            @Override
            public Observable<? extends GetProductListResponse> call(GetProductListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取类型
    public Observable<GetGenreListResponse> getGenreList() {
        return HttpRequestHelper.getHttpInterface().getGenreList().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetGenreListResponse>>() {
            @Override
            public Observable<? extends GetGenreListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetGenreListResponse, Observable<? extends GetGenreListResponse>>() {
            @Override
            public Observable<? extends GetGenreListResponse> call(GetGenreListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取投资类型
    public Observable<InvestRecordResponse> getInvestTypeList() {
        return HttpRequestHelper.getHttpInterface().getInvestTypeList().onErrorResumeNext(new Func1<Throwable, Observable<? extends InvestRecordResponse>>() {
            @Override
            public Observable<? extends InvestRecordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<InvestRecordResponse, Observable<? extends InvestRecordResponse>>() {
            @Override
            public Observable<? extends InvestRecordResponse> call(InvestRecordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取交易流水类型类型
    public Observable<TradeTypeModelResponse> getTradeTypeList() {
        return HttpRequestHelper.getHttpInterface().getTradeTypeList().onErrorResumeNext(new Func1<Throwable, Observable<? extends TradeTypeModelResponse>>() {
            @Override
            public Observable<? extends TradeTypeModelResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<TradeTypeModelResponse, Observable<? extends TradeTypeModelResponse>>() {
            @Override
            public Observable<? extends TradeTypeModelResponse> call(TradeTypeModelResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取是否打开网页
    public Observable<IsOpenWebModelResponse> getIsOpenWeb() {
        return HttpRequestHelper.getHttpInterface().getIsOpenWeb().onErrorResumeNext(new Func1<Throwable, Observable<? extends IsOpenWebModelResponse>>() {
            @Override
            public Observable<? extends IsOpenWebModelResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<IsOpenWebModelResponse, Observable<? extends IsOpenWebModelResponse>>() {
            @Override
            public Observable<? extends IsOpenWebModelResponse> call(IsOpenWebModelResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //收益预估
    public Observable<GetProductDetailMoneyResponse> getProductDetailMoney(int geneId, int amount) {
        return HttpRequestHelper.getHttpInterface().getProductDetailMoney(geneId, amount).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductDetailMoneyResponse>>() {
            @Override
            public Observable<? extends GetProductDetailMoneyResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductDetailMoneyResponse, Observable<? extends GetProductDetailMoneyResponse>>() {
            @Override
            public Observable<? extends GetProductDetailMoneyResponse> call(GetProductDetailMoneyResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取可用红包和加息券
    public Observable<CommonHttpModel<WelfareResult>> getRedpackAndCoupon(HashMap<String, String> map) {
        return HttpRequestHelper.getHttpInterface().getRedpackAndCoupon(map).onErrorResumeNext(new Func1<Throwable, Observable<? extends CommonHttpModel<WelfareResult>>>() {
            @Override
            public Observable<? extends CommonHttpModel<WelfareResult>> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<CommonHttpModel<WelfareResult>, Observable<? extends CommonHttpModel<WelfareResult>>>() {
            @Override
            public Observable<? extends CommonHttpModel<WelfareResult>> call(CommonHttpModel<WelfareResult> response) {
                if (!response.code.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.message));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //收益预估
    public Observable<GetProductDetailMoneyResponse> getProductDetailMoney(HashMap<String, String> map) {
        return HttpRequestHelper.getHttpInterface().getProductDetailMoney(map).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductDetailMoneyResponse>>() {
            @Override
            public Observable<? extends GetProductDetailMoneyResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductDetailMoneyResponse, Observable<? extends GetProductDetailMoneyResponse>>() {
            @Override
            public Observable<? extends GetProductDetailMoneyResponse> call(GetProductDetailMoneyResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资详情
    public Observable<GetProductDetailListResponse> getProductDetailList(int geneId, String pageNo,String pageSize) {
        return HttpRequestHelper.getHttpInterface().getProductDetailList(geneId, pageNo,pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductDetailListResponse>>() {
            @Override
            public Observable<? extends GetProductDetailListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductDetailListResponse, Observable<? extends GetProductDetailListResponse>>() {
            @Override
            public Observable<? extends GetProductDetailListResponse> call(GetProductDetailListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资详情
    public Observable<LogOutResponse> logout() {
        return HttpRequestHelper.getHttpInterface().logout().onErrorResumeNext(new Func1<Throwable, Observable<? extends LogOutResponse>>() {
            @Override
            public Observable<? extends LogOutResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<LogOutResponse, Observable<? extends LogOutResponse>>() {
            @Override
            public Observable<? extends LogOutResponse> call(LogOutResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认购买
    public Observable<SureBuyResponse> sureBuy(int geneId) {
        return HttpRequestHelper.getHttpInterface().sureBuy(geneId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认支付
    public Observable<SurePayResponse> surePay(int geneId, int couId, int redId, String amount, String actualAmount, String password) {
        return HttpRequestHelper.getHttpInterface().surePay(geneId, couId, redId, amount, actualAmount, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SurePayResponse> surePay(HashMap<String, String> map) {
        return HttpRequestHelper.getHttpInterface().surePay(map).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {

                return Observable.just(response);

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认支付
    public Observable<SurePayResponse> surePayNoCouple(int geneId, int redId, String amount, String actualAmount, String password) {
        return HttpRequestHelper.getHttpInterface().surePayNoCoupon(geneId, redId, amount, actualAmount, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认支付
    public Observable<SurePayResponse> surePayNoRed(int geneId, int couId, String amount, String actualAmount, String password) {
        return HttpRequestHelper.getHttpInterface().surePayNoRed(geneId, couId, amount, actualAmount, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认支付
    public Observable<SurePayResponse> surePayAllNo(int geneId, String amount, String actualAmount, String password) {
        return HttpRequestHelper.getHttpInterface().surePayAllNo(geneId, amount, actualAmount, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //我的钱包
    public Observable<MyWalletResponse> getMyWallet() {
        return HttpRequestHelper.getHttpInterface().getMyWallet().onErrorResumeNext(new Func1<Throwable, Observable<? extends MyWalletResponse>>() {
            @Override
            public Observable<? extends MyWalletResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<MyWalletResponse, Observable<? extends MyWalletResponse>>() {
            @Override
            public Observable<? extends MyWalletResponse> call(MyWalletResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //交易流水
    public Observable<GetTradeListResponse> getTradeList(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getTradeList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //充值流水
    public Observable<GetTradeListResponse> getRechargeList(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getRechargeList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //提现流水
    public Observable<GetTradeListResponse> getWithDrawList(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getWithDrawList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资流水
    public Observable<GetTradeListResponse> getinvestListList(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getinvestListList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //回款流水
    public Observable<GetTradeListResponse> getReceiveList(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getReceiveList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //奖励流水
    public Observable<GetTradeListResponse> getRewardList(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getRewardList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资记录
    public Observable<GetInvestHistoryListResponse> getInvestHistoryList(String pageNo, String pageSize, int orderStatus) {
        return HttpRequestHelper.getHttpInterface().getInvestHistoryList(pageNo, pageSize, orderStatus).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetInvestHistoryListResponse>>() {
            @Override
            public Observable<? extends GetInvestHistoryListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetInvestHistoryListResponse, Observable<? extends GetInvestHistoryListResponse>>() {
            @Override
            public Observable<? extends GetInvestHistoryListResponse> call(GetInvestHistoryListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资详情
    public Observable<GetInvestHistoryDetailResponse> getInvestHistoryDetail(int id) {
        return HttpRequestHelper.getHttpInterface().getInvestHistoryDetail(id).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetInvestHistoryDetailResponse>>() {
            @Override
            public Observable<? extends GetInvestHistoryDetailResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetInvestHistoryDetailResponse, Observable<? extends GetInvestHistoryDetailResponse>>() {
            @Override
            public Observable<? extends GetInvestHistoryDetailResponse> call(GetInvestHistoryDetailResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //邀请记录
    public Observable<GetInviteHistoryResponse> getInviteHistory(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getInviteHistory(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetInviteHistoryResponse>>() {
            @Override
            public Observable<? extends GetInviteHistoryResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetInviteHistoryResponse, Observable<? extends GetInviteHistoryResponse>>() {
            @Override
            public Observable<? extends GetInviteHistoryResponse> call(GetInviteHistoryResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //邀请记录
    public Observable<GetInviteInvestListResponse> getInviteInvestList(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getInviteInvestList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetInviteInvestListResponse>>() {
            @Override
            public Observable<? extends GetInviteInvestListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetInviteInvestListResponse, Observable<? extends GetInviteInvestListResponse>>() {
            @Override
            public Observable<? extends GetInviteInvestListResponse> call(GetInviteInvestListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //红包或加息券
    public Observable<GetRedPackListResponse> getRedPackList(String pageNo, String pageSize, String type, String flag) {
        return HttpRequestHelper.getHttpInterface().getRedPackList(pageNo, pageSize, type, flag).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetRedPackListResponse>>() {
            @Override
            public Observable<? extends GetRedPackListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetRedPackListResponse, Observable<? extends GetRedPackListResponse>>() {
            @Override
            public Observable<? extends GetRedPackListResponse> call(GetRedPackListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //提现界面
    public Observable<GetWithdrawInitResponse> getWithdrawInit() {
        return HttpRequestHelper.getHttpInterface().getWithdrawInit().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetWithdrawInitResponse>>() {
            @Override
            public Observable<? extends GetWithdrawInitResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetWithdrawInitResponse, Observable<? extends GetWithdrawInitResponse>>() {
            @Override
            public Observable<? extends GetWithdrawInitResponse> call(GetWithdrawInitResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<RechargeInitResponse> getRechargeInit() {
        return HttpRequestHelper.getHttpInterface().getRechargeInit().onErrorResumeNext(new Func1<Throwable, Observable<? extends RechargeInitResponse>>() {
            @Override
            public Observable<? extends RechargeInitResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<RechargeInitResponse, Observable<? extends RechargeInitResponse>>() {
            @Override
            public Observable<? extends RechargeInitResponse> call(RechargeInitResponse rechargeInitResponse) {
                if (rechargeInitResponse.isSuccess()) {
                    return Observable.just(rechargeInitResponse);
                } else {
                    return Observable.error(new RequestErrorThrowable("-1", rechargeInitResponse.errorMsg));
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认提现
    public Observable<SureWithdrawResponse> sureWithdraw(String money, String pwd) {
        return HttpRequestHelper.getHttpInterface().sureWithdraw(money, pwd).onErrorResumeNext(new Func1<Throwable, Observable<? extends SureWithdrawResponse>>() {
            @Override
            public Observable<? extends SureWithdrawResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SureWithdrawResponse, Observable<? extends SureWithdrawResponse>>() {
            @Override
            public Observable<? extends SureWithdrawResponse> call(SureWithdrawResponse response) {
//                if (!response.errorCode.equals("0")) {
//                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
//                } else {
                return Observable.just(response);
//                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //我的消息 交易
    public Observable<GetJiaoYiListResponse> getJiaoYiList(String pageNo, String pageSize) {
        return HttpRequestHelper.getHttpInterface().getJiaoYiList(pageNo,pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetJiaoYiListResponse>>() {
            @Override
            public Observable<? extends GetJiaoYiListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetJiaoYiListResponse, Observable<? extends GetJiaoYiListResponse>>() {
            @Override
            public Observable<? extends GetJiaoYiListResponse> call(GetJiaoYiListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //我的消息 公告
    public Observable<GetGongGaoResponse> getGongGao(int pageNo) {
        return HttpRequestHelper.getHttpInterface().getGongGao(pageNo).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetGongGaoResponse>>() {
            @Override
            public Observable<? extends GetGongGaoResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetGongGaoResponse, Observable<? extends GetGongGaoResponse>>() {
            @Override
            public Observable<? extends GetGongGaoResponse> call(GetGongGaoResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //支付方式取得
    public Observable<GetPayStyleResponse> getPayStyle() {
        return HttpRequestHelper.getHttpInterface().getPayStyle().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetPayStyleResponse>>() {
            @Override
            public Observable<? extends GetPayStyleResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetPayStyleResponse, Observable<? extends GetPayStyleResponse>>() {
            @Override
            public Observable<? extends GetPayStyleResponse> call(GetPayStyleResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

//    //支付
//    public Observable<ChongZhiResponse> chongZhi(String amount, String paymentCode) {
//        return HttpRequestHelper.getHttpInterface().chongZhi(amount, paymentCode).onErrorResumeNext(new Func1<Throwable, Observable<? extends ChongZhiResponse>>() {
//            @Override
//            public Observable<? extends ChongZhiResponse> call(Throwable throwable) {
//                return Observable.error(RxHttpHelper.convertIOEError(throwable));
//            }
//        }).flatMap(new Func1<ChongZhiResponse, Observable<? extends ChongZhiResponse>>() {
//            @Override
//            public Observable<? extends ChongZhiResponse> call(ChongZhiResponse response) {
//                if (!response.errorCode.equals("0")) {
//                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
//                } else {
//                    return Observable.just(response);
//                }
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
    //支付
    public Observable<ChongZhiResponse> chongZhi(String amount, String paymentCode) {
        return HttpRequestHelper.getHttpInterface().chongZhi(amount).onErrorResumeNext(new Func1<Throwable, Observable<? extends ChongZhiResponse>>() {
            @Override
            public Observable<? extends ChongZhiResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ChongZhiResponse, Observable<? extends ChongZhiResponse>>() {
            @Override
            public Observable<? extends ChongZhiResponse> call(ChongZhiResponse response) {
//                if (!response.errorCode.equals("0")) {
//                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
//                } else {
                    return Observable.just(response);
//                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //反馈
    public Observable<ReBackResponse> reBack(String content) {
        return HttpRequestHelper.getHttpInterface().reBack(content, 0).onErrorResumeNext(new Func1<Throwable, Observable<? extends ReBackResponse>>() {
            @Override
            public Observable<? extends ReBackResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ReBackResponse, Observable<? extends ReBackResponse>>() {
            @Override
            public Observable<? extends ReBackResponse> call(ReBackResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //反馈
    public Observable<GetProductRedPackResponse> getProductRedPack(int type) {
        return HttpRequestHelper.getHttpInterface().getProductRedPack(type).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductRedPackResponse>>() {
            @Override
            public Observable<? extends GetProductRedPackResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductRedPackResponse, Observable<? extends GetProductRedPackResponse>>() {
            @Override
            public Observable<? extends GetProductRedPackResponse> call(GetProductRedPackResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //公司信息
    public Observable<GetCompanyInfoResponse> getCompanyInfo() {
        return HttpRequestHelper.getHttpInterface().getCompanyInfo().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetCompanyInfoResponse>>() {
            @Override
            public Observable<? extends GetCompanyInfoResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetCompanyInfoResponse, Observable<? extends GetCompanyInfoResponse>>() {
            @Override
            public Observable<? extends GetCompanyInfoResponse> call(GetCompanyInfoResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UpdateInfoRespond> getUpdateInfo(String channel) {
        return HttpRequestHelper.getHttpInterface().getUpdateInfo(channel).onErrorResumeNext(new Func1<Throwable, Observable<? extends UpdateInfoRespond>>() {
            @Override
            public Observable<? extends UpdateInfoRespond> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<UpdateInfoRespond, Observable<? extends UpdateInfoRespond>>() {
            @Override
            public Observable<? extends UpdateInfoRespond> call(UpdateInfoRespond updateInfoRespond) {
                if (!updateInfoRespond.isSuccess()) {
                    return Observable.error(new RequestErrorThrowable("-1", updateInfoRespond.errorMsg));
                } else {
                    return Observable.just(updateInfoRespond);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<CheckMobileResponse> checkMobile(String channel) {
        return HttpRequestHelper.getHttpInterface().checkMobile(channel).onErrorResumeNext(new Func1<Throwable, Observable<? extends CheckMobileResponse>>() {
            @Override
            public Observable<? extends CheckMobileResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<CheckMobileResponse, Observable<? extends CheckMobileResponse>>() {
            @Override
            public Observable<? extends CheckMobileResponse> call(CheckMobileResponse checkMobileResponse) {
                if (checkMobileResponse.isSuccess() || checkMobileResponse.isUnregister() || checkMobileResponse.needReSetPwd()) {
                    return Observable.just(checkMobileResponse);
                } else {
                    return Observable.error(new RequestErrorThrowable("-1", checkMobileResponse.errorMsg));

                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<CommonHttpModel<MessageUnreadNumResult>> getUnreadMessageNums() {

        return HttpRequestHelper.getHttpInterface().getUnreadMessageNums().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CompanyInfoResponse> getCompanyInfoMore() {
        return HttpRequestHelper.getHttpInterface().getCompanyInfoMore().onErrorResumeNext(new Func1<Throwable, Observable<? extends CompanyInfoResponse>>() {
            @Override
            public Observable<? extends CompanyInfoResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<CompanyInfoResponse, Observable<? extends CompanyInfoResponse>>() {
            @Override
            public Observable<? extends CompanyInfoResponse> call(CompanyInfoResponse companyInfoResponse) {
                Logger.i("result" + companyInfoResponse.toString());
                if (!companyInfoResponse.isSuccess()) {
                    return Observable.error(new RequestErrorThrowable("-1", companyInfoResponse.errorMsg));
                } else {
                    return Observable.just(companyInfoResponse);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse<AdInfoResponse>> getAdInfo() {
        return HttpRequestHelper.getHttpInterface().getAdInfo().onErrorResumeNext(new Func1<Throwable, Observable<? extends BaseResponse<AdInfoResponse>>>() {
            @Override
            public Observable<? extends BaseResponse<AdInfoResponse>> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<BaseResponse<AdInfoResponse>, Observable<? extends BaseResponse<AdInfoResponse>>>() {
            @Override
            public Observable<? extends BaseResponse<AdInfoResponse>> call(BaseResponse<AdInfoResponse> adInfoResponseResponseData) {
                Logger.i("result" + adInfoResponseResponseData.toString());
                if (adInfoResponseResponseData.isSuccess()) {
                    return Observable.just(adInfoResponseResponseData);
                } else {
                    return Observable.error(new RequestErrorThrowable("-1", adInfoResponseResponseData.errorMsg));
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WithDrawActureAmtResponse> getWithDrawActurlAmt(String amount) {
        return HttpRequestHelper.getHttpInterface().getWithDrawActurlAmt(amount).onErrorResumeNext(new Func1<Throwable, Observable<? extends WithDrawActureAmtResponse>>() {
            @Override
            public Observable<? extends WithDrawActureAmtResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<WithDrawActureAmtResponse, Observable<? extends WithDrawActureAmtResponse>>() {
            @Override
            public Observable<? extends WithDrawActureAmtResponse> call(WithDrawActureAmtResponse withDrawActureAmtResponse) {
                Logger.i("result" + withDrawActureAmtResponse.toString());
                if (withDrawActureAmtResponse.isSuccess()) {
                    return Observable.just(withDrawActureAmtResponse);
                } else {
                    return Observable.error(new RequestErrorThrowable("-1", withDrawActureAmtResponse.errorMsg));
                }            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<ServerInfo> getServerInfo(){
        return HttpRequestHelper2.getHttpInterface().getServerInfo().onErrorResumeNext(new Func1<Throwable, Observable<? extends ServerInfo>>() {
            @Override
            public Observable<? extends ServerInfo> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ServerInfo, Observable<? extends ServerInfo>>() {
            @Override
            public Observable<? extends ServerInfo> call(ServerInfo withDrawActureAmtResponse) {
                if (withDrawActureAmtResponse.isSuccess()) {
                    return Observable.just(withDrawActureAmtResponse);
                } else {
                    return Observable.error(new RequestErrorThrowable("-1", withDrawActureAmtResponse.errorMsg));
                }            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    };
}
