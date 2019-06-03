package finan.heng.com.apps.http;

/*
 * Created by lfu on 2017/2/21.
 */


import com.squareup.okhttp.ResponseBody;

import java.util.Map;

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
import finan.heng.com.apps.model.HomeNoticeResponse;
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
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface InterfaceService {

    public static final String FINANCE_TITLE = "products/genreList";//理财列表的标题
    public static final String PRODUCT_DETAIL = "products/details";//获取理财产品详情
    public static final String HOME_SCROLL_TEXT_DETAIL = "mallHotspot";//获取首页滚动消息详情
    public static final String HOME_SCROLL_TEXT = "home/home";//获取首页消息
    public static final String MESSAGE_CODE = "getMobileCode";//获取验证码
    public static final String IMAGE_CODE = "generateImage";//获取图片验证码
    public static final String REGISTER = "sign";//注册
    public static final String LOGIN = "loginCheck";//登陆
    public static final String USER_INFO = "userInfo";//用户信息
    public static final String FIND_PASSWORD_MSG_CODE = "getReSetPwdCode";//找回登陆密码的获取验证码
    public static final String FIND_PASSWORD = "reSetPwd";//找回登陆密码
    public static final String SHIMING_MSG_CODE = "account/sendMobileSms";//实名认证的短信验证码
//    public static final String BANK_LIST = "account/bankList";//银行卡列表

    public static final String RULE_NAME = "account/realNameAuthentication";//实名认证
    public static final String MY_BANK = "account/myBank";//我的银行卡
    public static final String CHANGE_LOGIN_PASSWORD = "account/updateLoginPassword";//修改登录密码
    public static final String CHANGE_JIAOYI_PASSWORD = "account/updateTradePassword";//修改交易密码
    public static final String SET_BUY_PASSWORD = "account/setTradePassword";//设置交易密码
    public static final String RESET_BUY_PASSWORD = "account/resetPayPassword";//重置交易密码

    public static final String FORGET_BUY_PASSWORD_MSG_CODE = "account/sendResetPaySms";//忘记交易密码的短信验证码
    public static final String CHECK_FORGET_BUY_PASSWORD_MSG_CODE = "account/verifyResetPayCode";//验证忘记交易密码的短信验证码
    public static final String PRODUCT_LIST = "products/productsList";//产品列表
    public static final String PRODUCT_MONEY_ANALYSE = "products/calculationProfit";//收益预估
    public static final String PRODUCT_DETAI_LIST = "products/orderList";//获取投资详情
    public static final String SURE_BUY = "products/state";//确认投资
    public static final String SURE_PAY = "products/payment";//确认支付

    //投资结束调用
    public static final String SHOW_WALLET = "myWallet/showWallet";//我的钱包
    public static final String TRADE_LIST = "myWallet/tradedetailList";//交易流水全部
    public static final String RECHARGE_LIST = "myWallet/rechargeList";//充值流水
    public static final String WITHDRAW_LIST = "myWallet/withdrawList";//提现流水
    public static final String INVEST_LIST = "myWallet/investList";//投资流水
    public static final String RECEIVE_LIST = "myWallet/receivedList";//回款流水
    public static final String REWARD_LIST = "myWallet/rewardList";//奖励流水
    public static final String INVEST_HISTORY_LIST = "myWallet/investDetailList";//投资记录查询
    public static final String INVEST_HISTORY_DETAIL_LIST = "myWallet/investDetailInfo";//投资记录详情查询
    public static final String COMPANY_INFO = "commonParam";//公司信息


    public static final String INVITE_HISTORY = "account/invateRegList";//邀请记录（已注册）
    public static final String INVITE_INVEST_HISTORY = "account/invateProfitList";//邀请记录（已投资）
    public static final String RED_PACK_LIST = "myWallet/myredPackList";//优惠券查询

    public static final String WHTIDRAW_INIT = "withdraw/init";//提现页面
    public static final String SURE_WHTIDRAW_INIT = "withdraw/submit";//确认提现页面
    public static final String NEWS_JIAOYI = "account/sysAlertMessage";//我的消息 交易
    public static final String NEWS_GONGGAO = "information/articleList";//我的消息 公告//原接口hotspot/hotspot

    public static final String PAY_STYLE = "payment/findPayMentList";//支付方式取得
    public static final String RECHAGE_INIT = "payment/init"; // 充值初始化

    public static final String PAYMENT_SUBMIT = "payment/submit"; // 充值初始化
    //    public static final String PAY = "payment/recharge";//充值接口
    public static final String PAY = "payment/readyPay";//充值接口
    public static final String REBACK = "saveFeedBack";//反馈
    public static final String LOGOUT = "logout";//退出
    public static final String QUEREN_HONGBAO_JIAXIQUAN = "bonuses/findBonusesByType";//获取奖励信息（类型：0.红包；1.加息券；2.抵扣金；）
    public static final String ADDRESS_UPDATEINFO = "home/version"; // 获取版本升级信息
    public static final String ADDRESS_COMPANYINFO = "more/more"; // 获取公司信息


    public static final String GET_REDPACK_COUPON = "products/bonuses";
    public static final String ADDRESS_CHECKMOBILE = "checkMobile"; // 验证手机号是否注册
    public static final String ADDRESS_SERVER = "api/getServerURL"; // 验证服务器地址


    public static final String ADDRESS_BIND_CARD_READY = "account/bindBankCard/readyBind"; // 绑卡接口
    public static final String ADDRESS_BIND_CARD_READY_SEND_CODE = "account/bindBankCard/confirmBind";//绑卡发送验证码

    public static final String BANK_LIST = "account/bindBankCard";//银行卡列表

    @FormUrlEncoded
    @POST(ADDRESS_CHECKMOBILE)
    Observable<CheckMobileResponse> checkMobile(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST(ADDRESS_UPDATEINFO)
    Observable<UpdateInfoRespond> getUpdateInfo(@Field("channel") String channel); // 获取版本升级信息

    @POST(ADDRESS_COMPANYINFO)
    Observable<CompanyInfoResponse> getCompanyInfoMore();

    public static final String GET_INVEST_TYPE_INFO = "estimateQuestion/estimateLevel";//根据投资的type获取投资详细信息
    public static final String RISK_CHECK_DEFAULT = "userEstimate/updateUserDefaultLevel";//一键默认
    public static final String INVEST_CHECK = "userEstimate/findUserMaxInvest";//投资检查
    public static final String EVALUATION_LIST = "estimateQuestion/estimateQuestionList";//测评列表
    public static final String SEND_ANSWER = "estimateQuestion/estimateUserLevel";//退出
    public static final String SYSTEM_PARAM = "home/system";//系统参数
    public static final String CARD_TYPE = "myWallet/cdkeyTypeList";//卡片类型

    public static final String CARD_LIST = "myWallet/myCDKeyList";//卡片类型
    public static final String INVEST_TWO = "protocol/investment2";//卡片类型

    public static final String GET_MESSAGE_NUMS = "account/sysAlertNoRead";//获取未读的交易记录条数
    public static final String ADDRESS_ADINFO = "home/advert"; // 获取广告信息

    public static final String WITHDRAW_ACTURLAMT = "withdraw/withdrawActualAmt";
    public static final String ADDRESS_INITINVITE = "inviteParam"; // 邀请好友 初始化
    //    public static final String BAOFOO_PAYMENT = "payment/baofooPaymentSubmit"; // 宝付支付验证码
    public static final String BAOFOO_PAYMENT = "payment/confirmPay"; // 宝付支付验证码

    public static final String PRODUCT_GENRELIST = "products/genreList";//产品类型
    public static final String PRODUCT_TYPELIST = "myWallet/investTypeList";//交易记录类型
    public static final String PRODUCT_TRADETYPELIST = "myWallet/tradeTypeList";//交易流水类型
    public static final String HOME_VERSION = "home/version200";//校验是否打开网页

    @FormUrlEncoded
    @POST(BAOFOO_PAYMENT)
    Observable<CommonHttpModel<BaoFooResult>> baofooPayment(@Field("smsCode") String smsCode);//发送宝付支付的验证码

    @FormUrlEncoded
    @POST(WITHDRAW_ACTURLAMT)
    Observable<WithDrawActureAmtResponse> getWithDrawActurlAmt(@Field("amount") String amount);

    @POST(ADDRESS_ADINFO)
    Observable<BaseResponse<AdInfoResponse>> getAdInfo(); // 获取广告

    @POST(GET_MESSAGE_NUMS)
    Observable<CommonHttpModel<MessageUnreadNumResult>> getUnreadMessageNums();//获取卡片类型

    @FormUrlEncoded
    @POST(GET_INVEST_TYPE_INFO)
//由于和提交答案的返回有重复，这里就重复使用实体类
    Observable<CommonHttpModel<EvaluationSubmitResult>> getInvestTypeInfo(@Field("levelType") String levelType);//获取投资协议

    @FormUrlEncoded
    @POST(INVEST_TWO)
    Observable<InvestTwoResponse> getInvestTwo(@Field("id") String id);//获取投资协议

    @FormUrlEncoded
    @POST(CARD_LIST)
    Observable<CardResponse> getCardList(@FieldMap Map<String, String> map);//获取卡片列表

    @POST(CARD_TYPE)
    Observable<CardTypeResponse> getCardTypeList();//获取卡片类型

    @POST(SYSTEM_PARAM)
    Observable<CommonHttpModel<SystemResult>> getSystemParam();//获取系统参数

    @POST(ADDRESS_INITINVITE)
    Observable<CommonHttpModel<InviteFriendBean>> initInviteFriends(); // 邀请好友初始化

    @POST(RISK_CHECK_DEFAULT)
    Observable<RiskDefaultResponse> riskDefault();//一键默认风险等级

    @FormUrlEncoded
    @POST(INVEST_CHECK)
    Observable<RiskCheckResponse> investCheck(@Field("invest") String invest);//投资风险等级检测

    @POST(EVALUATION_LIST)
    Observable<EvaluationResponse> getEvauationList();//风险测评列表

    @FormUrlEncoded
    @POST(SEND_ANSWER)
    Observable<EvaluationSubmitResponse> sendAnwser(@FieldMap Map<String, String> params);//发送测聘答案


    @POST(FINANCE_TITLE)
    Observable<FinanceTitleResponse> getFinanceTitle();//理财列表标题

    @POST(LOGOUT)
    Observable<LogOutResponse> logout();//退出

    @POST(PAY_STYLE)
    Observable<GetPayStyleResponse> getPayStyle();//支付方式取得

    @FormUrlEncoded
    @POST(NEWS_GONGGAO)
    Observable<GetGongGaoResponse> getGongGao(@Field("pageNo") int pageNo);//我的消息 公告

    @FormUrlEncoded
    @POST(NEWS_JIAOYI)
    Observable<GetJiaoYiListResponse> getJiaoYiList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//我的消息 交易

    @POST(COMPANY_INFO)
    Observable<GetCompanyInfoResponse> getCompanyInfo();//公司信息

    @POST(SHOW_WALLET)
    Observable<MyWalletResponse> getMyWallet();//我的钱包

    @FormUrlEncoded
    @POST(PRODUCT_DETAIL)
    Observable<ProductDetailResponse> getProduct(@Field("id") int id);//获取理财产品详情

    @FormUrlEncoded
    @POST(QUEREN_HONGBAO_JIAXIQUAN)
    Observable<GetProductRedPackResponse> getProductRedPack(@Field("type") int type);//获取优惠信息

    //    @FormUrlEncoded
//    @POST(PAY)
//    Observable<ChongZhiResponse> chongZhi(@Field("amount") String amount, @Field("paymentCode") String paymentCode);//充值接口
    @FormUrlEncoded
    @POST(PAY)
    Observable<ChongZhiResponse> chongZhi(@Field("amount") String amount);//充值接口

    @FormUrlEncoded
    @POST(REBACK)
    Observable<ReBackResponse> reBack(@Field("content") String content, @Field("deviceType") int deviceType);//反馈

    @POST(HOME_SCROLL_TEXT_DETAIL)
    Observable<HomeNoticeResponse> getHomeScrollTextDetail();//获取首页滚动消息详情


    @FormUrlEncoded
    @POST(HOME_SCROLL_TEXT)
    Observable<HomeResponse> getHomeScroll(@Field("token") String token);//获取首页消息

    @POST(USER_INFO)
    Observable<MyUserInfo> getUserInfo();//获取首页消息

    @POST(WHTIDRAW_INIT)
    Observable<GetWithdrawInitResponse> getWithdrawInit();//提现页面

    @POST(RECHAGE_INIT)
    Observable<RechargeInitResponse> getRechargeInit(); // 充值初始化


    @POST(MY_BANK)
    Observable<BankCardResponse> myBankCard();//获取银行卡信息

    @FormUrlEncoded
    @POST(MESSAGE_CODE)
    Observable<GetMessageCodeResponse> getMessageCode(@Field("mobile") String mobile, @Field("captcha") String captcha);//获取图形验证码

    @FormUrlEncoded
    @POST(TRADE_LIST)
    Observable<GetTradeListResponse> getTradeList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//交易流水全部

    @FormUrlEncoded
    @POST(SURE_WHTIDRAW_INIT)
    Observable<SureWithdrawResponse> sureWithdraw(@Field("amount") String amount, @Field("payPwd") String payPwd);//确认提现

    @FormUrlEncoded
    @POST(INVITE_HISTORY)
    Observable<GetInviteInvestListResponse> getInviteInvestList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//邀请记录（已投资）

    @FormUrlEncoded
    @POST(RECEIVE_LIST)
    Observable<GetTradeListResponse> getReceiveList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//回款全部

    @FormUrlEncoded
    @POST(INVITE_INVEST_HISTORY)
    Observable<GetInviteHistoryResponse> getInviteHistory(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//邀请记录

    @FormUrlEncoded
    @POST(INVEST_HISTORY_LIST)
    Observable<GetInvestHistoryListResponse> getInvestHistoryList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize, @Field("orderStatus") int orderStatus);//投资记录

    @FormUrlEncoded
    @POST(REWARD_LIST)
    Observable<GetTradeListResponse> getRewardList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//奖励全部

    @FormUrlEncoded
    @POST(INVEST_LIST)
    Observable<GetTradeListResponse> getinvestListList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//投资流水

    @FormUrlEncoded
    @POST(WITHDRAW_LIST)
    Observable<GetTradeListResponse> getWithDrawList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//交易流水全部

    @FormUrlEncoded
    @POST(RECHARGE_LIST)
    Observable<GetTradeListResponse> getRechargeList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//充值流水全部

    @FormUrlEncoded
    @POST(PRODUCT_LIST)
    Observable<GetProductListResponse> getProductList(@Field("genreId") String genreId, @Field("pageNo") String pageNo);//获取产品列表

    @POST(PRODUCT_GENRELIST)
    Observable<GetGenreListResponse> getGenreList();//获取产品类型

    @POST(PRODUCT_TYPELIST)
    Observable<InvestRecordResponse> getInvestTypeList();//获取投资记录类型

    @POST(PRODUCT_TRADETYPELIST)
    Observable<TradeTypeModelResponse> getTradeTypeList();//获取交易流水类型

    @POST(HOME_VERSION)
    Observable<IsOpenWebModelResponse> getIsOpenWeb();//获取是否打开网页

    @FormUrlEncoded
    @POST(PRODUCT_DETAI_LIST)
    Observable<GetProductDetailListResponse> getProductDetailList(@Field("id") int genreId, @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//获取投资详情

    @FormUrlEncoded
    @POST(SET_BUY_PASSWORD)
    Observable<SetBuyPasswordResponse> setBuyPassword(@Field("tradePassword") String tradePassword);//设置交易密码

    @FormUrlEncoded

    @POST(RESET_BUY_PASSWORD)
    Observable<SetBuyPasswordResponse> resetBuyPassword(@Field("newPassword") String tradePassword);//重置交易密码

    @FormUrlEncoded
    @POST(PRODUCT_MONEY_ANALYSE)
    Observable<GetProductDetailMoneyResponse> getProductDetailMoney(@Field("id") int genreId, @Field("amount") int amount);//收益预估

    @FormUrlEncoded
    @POST(GET_REDPACK_COUPON)
    Observable<CommonHttpModel<WelfareResult>> getRedpackAndCoupon(@FieldMap Map<String, String> map);//获取可用红包和加息券


    @FormUrlEncoded
    @POST(PRODUCT_MONEY_ANALYSE)
    Observable<GetProductDetailMoneyResponse> getProductDetailMoney(@Field("id") int genreId, @Field("amount") int amount, @Field("couponId") int couponId);//收益预估


    @FormUrlEncoded
    @POST(PRODUCT_MONEY_ANALYSE)
    Observable<GetProductDetailMoneyResponse> getProductDetailMoney(@FieldMap Map<String, String> map);//收益预估

    @FormUrlEncoded
    @POST(SURE_BUY)
    Observable<SureBuyResponse> sureBuy(@Field("id") int genreId);//收益预估

    @FormUrlEncoded
    @POST(INVEST_HISTORY_DETAIL_LIST)
    Observable<GetInvestHistoryDetailResponse> getInvestHistoryDetail(@Field("orderId") int orderId);//投资记录详情

    @FormUrlEncoded
    @POST(CHECK_FORGET_BUY_PASSWORD_MSG_CODE)
    Observable<CheckForgetBuyPasswordMsgCodeResponse> checkForgetBuyPasswordMsgCode(@Field("code") String msgCode);//验证忘记交易密码的短信验证码

    @FormUrlEncoded
    @POST(FORGET_BUY_PASSWORD_MSG_CODE)
    Observable<ForgetBuyPasswordMsgCodeResponse> forgetBuyPasswordMsgCode(@Field("idCard") String idCard);//忘记交易密码的短信验证码

    @FormUrlEncoded
    @POST(CHANGE_LOGIN_PASSWORD)
    Observable<ChangeLoginPasswordResponse> changeLoginPassword(@Field("oldPassword") String newPassword, @Field("newPassword") String oldPassword);//修改登录密码

    @FormUrlEncoded
    @POST(CHANGE_JIAOYI_PASSWORD)
    Observable<ChangeJiaoYiPasswordResponse> changeJiaoyiPassword(@Field("oldTradePassword") String oldPassword, @Field("newTradePassword") String newPassword);//修改交易密码

    @FormUrlEncoded
    @POST(FIND_PASSWORD_MSG_CODE)
    Observable<GetMessageCodeResponse> findPasswordMessageCode(@Field("mobile") String mobile, @Field("captcha") String captcha);//获取短信验证码

    @FormUrlEncoded
    @POST(SHIMING_MSG_CODE)
    Observable<GetRenZhengMessageResponse> renzhengMessageCode(@Field("mobile") String mobile);//获取实名认证短信验证码

    @FormUrlEncoded
    @POST(FIND_PASSWORD)
    Observable<getPassWordResponse> findPassWord(@Field("mobile") String mobile, @Field("mobileCode") String mobileCode, @Field("password") String password);//召回登陆密码

    @FormUrlEncoded
    @POST(LOGIN)
    Observable<LoginResponse> login(@Field("userAccount") String userAccount, @Field("clientId") String clientId, @Field("passwd") String passwd);//登陆

    @FormUrlEncoded
    @POST(REGISTER)
    Observable<RegisterResponse> register(@FieldMap Map<String, String> params);//注册

    @FormUrlEncoded
    @POST(RED_PACK_LIST)
    Observable<GetRedPackListResponse> getRedPackList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize, @Field("type") String type, @Field("flag") String flag);//红包或加息券

    @FormUrlEncoded
    @POST(ADDRESS_BIND_CARD_READY)
    Observable<RuleNameResponse> ruleName(@Field("userName") String name, @Field("bankId") String bankId, @Field("idCard") String idCard, @Field("bankCode") String bankCode, @Field("mobile") String phone, @Field("captcha") String captcha);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePay(@Field("productsId") int productsId, @Field("couponId") int couponId, @Field("redId") int redId, @Field("amount") String amount, @Field("actualAmount") String actualAmount, @Field("paypassword") String paypassword);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePayNoRed(@Field("productsId") int productsId, @Field("couponId") int couponId, @Field("amount") String amount, @Field("actualAmount") String actualAmount, @Field("paypassword") String paypassword);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePayNoCoupon(@Field("productsId") int productsId, @Field("redId") int redId, @Field("amount") String amount, @Field("actualAmount") String actualAmount, @Field("paypassword") String paypassword);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePayAllNo(@Field("productsId") int productsId, @Field("amount") String amount, @Field("actualAmount") String actualAmount, @Field("paypassword") String paypassword);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePay(@FieldMap Map<String, String> param);//投资

    @POST(ADDRESS_SERVER)
    Observable<ServerInfo> getServerInfo();// 获取服务器地址

    @FormUrlEncoded
    @POST(ADDRESS_BIND_CARD_READY)
    Observable<RegisterResponse> bindCardReady(@FieldMap Map<String, String> params);//准备绑卡

    @FormUrlEncoded
    @POST(ADDRESS_BIND_CARD_READY_SEND_CODE)
    Observable<CommonHttpModel<RuleNameModel>> bindCardSendSmgCode(@Field("smsCode") String smsCode);//绑卡发送验证码

    @POST(BANK_LIST)
    Observable<BankListResponse> getBankInfo();//获取银行列表

    @FormUrlEncoded
    @POST(PAYMENT_SUBMIT)
    Observable<CommonHttpModel<BaoFooResult>> paymentSubmit(@FieldMap Map<String, String> params);//直接支付
}
