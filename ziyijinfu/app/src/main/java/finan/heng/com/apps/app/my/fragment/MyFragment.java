package finan.heng.com.apps.app.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcxiaoke.bus.annotation.BusReceiver;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.LoginActivity;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.app.home.activity.EvaluationSuccessActivity;
import finan.heng.com.apps.app.home.activity.RiskEvaluationActivity;
import finan.heng.com.apps.app.my.activity.CardActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiListActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiOrTiXianActivity;
import finan.heng.com.apps.app.my.activity.InviteFriendActivity;
import finan.heng.com.apps.app.my.activity.JiaoYiJiLuActivity;
import finan.heng.com.apps.app.my.activity.MessageCenterActivity;
import finan.heng.com.apps.app.my.activity.SettingActivity;
import finan.heng.com.apps.app.my.activity.TouZiJiLuActivity;
import finan.heng.com.apps.app.my.activity.YouHuiQuanActivity;
import finan.heng.com.apps.app.ui.activity.CheckPhoneActivity;
import finan.heng.com.apps.app.ui.activity.ShowStepViewActivity;
import finan.heng.com.apps.app.ui.activity.VerifyInfoActivity;
import finan.heng.com.apps.app.ui.widget.MenuItem;
import finan.heng.com.apps.base.LazyFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.model.EvaluationSubmitResult;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.model.MessageUnreadNumResult;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.MyWalletModel;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.model.event.AccountRefreshEvent;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.heng.com.apps.utils.DialogUtil;
import finan.heng.com.apps.utils.SharedpreferenceUtil;
import finan.zhimabao.com.apps.BuildConfig;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/*

 * Created by Administrator on 2017/4/23.
 */
public class MyFragment extends LazyFragment implements View.OnClickListener {

    //    private int[] icon = {R.mipmap.record, R.mipmap.deal, R.mipmap.news, R.mipmap.invite, R.mipmap.discountcoupon, R.mipmap.help, R.mipmap.contact, R.mipmap.about};
    private TextView mBtnChongZhi, mBtnTiXian;
    private ImageView isShowNum;
    private RelativeLayout rlLogin;
    private TextView keYong, ShouYi, zongZiChan;
    private SmartRefreshLayout mPullToRefreshView;
    private TextView mUnreadMessageNum;
    private MenuItem menu_redpag, invite_friend, coupon, card_layout;
    private LinearLayout trade_record;
    //    private TextView userAccount;
    private ImageView userPhoto;
    //    private RelativeLayout unLogin;
    private Button mLoginButton;

    private RelativeLayout mAccountLayout, mAllPropertyLayout;
    private LinearLayout mShouyiLayout;
    /**
     * 是否实名认证
     */
    private ImageView img_noRealName;
    private TextView tv_keyongyue, tv_totalProfitLabel, tv_balanceLabel, tv_title;
    private LinearLayout invest_record;
    private static final int LOGIN = 0x101;
    private TextView mInvest_record_text;
    private TextView mTrade_record_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        setToolbar(view);
        EventBus.getDefault().register(this);
        initView(view);
        init();
        if (isLogin()) {
            getWallet();
            getUnreadMessage();
        }
        return view;
    }

    private void setToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView titleText = (TextView) view.findViewById(R.id.title_text);
        View actionView = toolbar.getRootView();
        toolbar.getBackground().setAlpha(255);
        View contentview = view.findViewById(R.id.top_bar);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentview.getLayoutParams();
        params.height = DeviceUtils.getStatusBarHeight(getActivity());
        contentview.setLayoutParams(params);
        if (BuildConfig.isDebug) {
            titleText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DialogUtil.showDialog(getActivity());
                    return false;
                }
            });
        }
    }

    private void loginStatus(boolean isLogin) {
        mShouyiLayout.setVisibility(isLogin ? View.VISIBLE : View.GONE);
        mAllPropertyLayout.setVisibility(isLogin ? View.VISIBLE : View.GONE);
        mAccountLayout.setVisibility(isLogin ? View.VISIBLE : View.GONE);
        mLoginButton.setVisibility(isLogin ? View.GONE : View.VISIBLE);
    }

    private void initUnLogin() {
        loginStatus(false);
        keYong.setText("0.00");
        ShouYi.setText("0.00");
        zongZiChan.setText("0.00");
        isShowNum.setImageResource(R.drawable.my_eye_open);
        mUnreadMessageNum.setVisibility(View.GONE);
    }

    public void onEvent(AccountRefreshEvent event) {
        getWallet();
        getUnreadMessage();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshAccountInfo();
        try {
            if (isLogin()) {
                loginStatus(true);
            } else {
                initUnLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshAccountInfo() {
        try {
            MyWalletResponse cacheData = DataCache.instance.getCacheData("heng", "MyWalletResponse");
            if (cacheData != null) {
                if (SharedpreferenceUtil.getBoolean("showNum", true)) {
                    keYong.setText(cacheData.result.getAvailableAmount());
                    ShouYi.setText(cacheData.result.getTotalInterest());
                    zongZiChan.setText(cacheData.result.getAllAssets());
                    isShowNum.setImageResource(R.drawable.my_eye_open);
                } else {
                    keYong.setText("****");
                    ShouYi.setText("****");
                    zongZiChan.setText("****");
                    isShowNum.setImageResource(R.drawable.my_eye_close);
                }
                try {
                    if (Float.parseFloat(cacheData.result.getCashBackSum()) > 0) {
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView(View view) {
        tv_keyongyue = view.findViewById(R.id.tv_keyongyue);//总资产
        tv_title = view.findViewById(R.id.tv_title);//标题
        tv_totalProfitLabel = view.findViewById(R.id.tv_totalProfitLabel);//累计收益
        tv_balanceLabel = view.findViewById(R.id.tv_balanceLabel);//账户余额
        tv_keyongyue = view.findViewById(R.id.tv_keyongyue);
        tv_keyongyue = view.findViewById(R.id.tv_keyongyue);
        tv_keyongyue = view.findViewById(R.id.tv_keyongyue);
        tv_keyongyue = view.findViewById(R.id.tv_keyongyue);
        tv_keyongyue = view.findViewById(R.id.tv_keyongyue);
        tv_keyongyue = view.findViewById(R.id.tv_keyongyue);

        mLoginButton = view.findViewById(R.id.my_login);
        rlLogin = view.findViewById(R.id.rl_top);
        mBtnChongZhi = view.findViewById(R.id.fragment_my_chongzhi);//充值
        mBtnTiXian = view.findViewById(R.id.fragment_my_tixian);//提现

        keYong = view.findViewById(R.id.tv_jine);
        ShouYi = view.findViewById(R.id.tv_shouyi);
        zongZiChan = view.findViewById(R.id.tv_zongzichan);
        isShowNum = view.findViewById(R.id.show_num);
        mUnreadMessageNum = view.findViewById(R.id.my_inform_items);
        mPullToRefreshView = view.findViewById(R.id.pullRefresh);
        userPhoto = view.findViewById(R.id.user_photo);

        mAccountLayout = view.findViewById(R.id.account_layout);
        mAllPropertyLayout = view.findViewById(R.id.all_property_layout);
        mShouyiLayout = view.findViewById(R.id.shouyi_layout);
        mLoginButton.setOnClickListener(this);
        menu_redpag = view.findViewById(R.id.menu_redpag);//投资红包
        menu_redpag.setOnClickListener(this);

        invest_record = view.findViewById(R.id.invest_record);//投资记录
        mInvest_record_text = (TextView) view.findViewById(R.id.invest_record_text);
        invest_record.setOnClickListener(this);
        trade_record = view.findViewById(R.id.trade_record);//交易流水
        mTrade_record_text = (TextView) view.findViewById(R.id.trade_record_text);
        trade_record.setOnClickListener(this);
        view.findViewById(R.id.message_center).setOnClickListener(this);
        invite_friend = view.findViewById(R.id.invite_friend);//邀请好友
        invite_friend.setOnClickListener(this);
        coupon = view.findViewById(R.id.coupon);//优惠券
        coupon.setOnClickListener(this);
        view.findViewById(R.id.iv_setting).setOnClickListener(this);
        card_layout = view.findViewById(R.id.card_layout);//礼品卡
        card_layout.setOnClickListener(this);
        userPhoto.setOnClickListener(this);
        img_noRealName = view.findViewById(R.id.img_noRealName);
        img_noRealName.setOnClickListener(this);
    }

    private void init() {
        mBtnChongZhi.setOnClickListener(this);
        mBtnTiXian.setOnClickListener(this);
        isShowNum.setOnClickListener(this);
        mPullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getWallet();
                getUnreadMessage();
            }
        });
    }

    private void getUnreadMessage() {
        new HttpHelper().getUnreadMessageNums().subscribe(new Action1<CommonHttpModel<MessageUnreadNumResult>>() {
            @Override
            public void call(CommonHttpModel<MessageUnreadNumResult> messageUnreadNumResultCommonHttpModel) {
                try {
                    if (messageUnreadNumResultCommonHttpModel.code.equals("0")) {
                        if (messageUnreadNumResultCommonHttpModel.result.smgNoRead.equals("") || messageUnreadNumResultCommonHttpModel.result.smgNoRead.equals("0")) {
                            mUnreadMessageNum.setVisibility(View.GONE);
                        } else {
                            mUnreadMessageNum.setVisibility(View.VISIBLE);
                            if (messageUnreadNumResultCommonHttpModel.result.smgNoRead.length() >= 2) {
                                mUnreadMessageNum.setText("·");
                            } else {
                                mUnreadMessageNum.setText("·");
                            }
                        }

                    } else {
                        ViewHelper.showToast(getActivity(), messageUnreadNumResultCommonHttpModel.message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();

                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private MyWalletResponse walletResponse;

    public void getWallet() {
        new HttpHelper().getMyWallet().subscribe(new Action1<MyWalletResponse>() {
            @Override
            public void call(MyWalletResponse myWalletResponse) {
                dismissLoadingDialog();
                MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                if ("0".equals(cacheData.result.realnameStatus)) {
                    img_noRealName.setVisibility(View.VISIBLE);
                    Glide.with(getActivity()).load(myWalletResponse.result.getBindingImageUrl()).into(img_noRealName);
                } else {
                    img_noRealName.setVisibility(View.GONE);
                }
                if (myWalletResponse != null) {
                    walletResponse = myWalletResponse;
                    updateUi(myWalletResponse);
                }
                try {
                    DataCache.instance.saveCacheData("heng", "MyWalletResponse", myWalletResponse);
                    loginState();
                    mPullToRefreshView.finishRefresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    dismissLoadingDialog();
                    if (throwable instanceof RequestErrorThrowable) {
                        RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                        ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                    }
                    mPullToRefreshView.finishRefresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 根据linkUrl跳转制定的页面
     *
     * @param linkUrl
     */
    private void goLinkUrl(String linkUrl) {
        if (TextUtils.isEmpty(linkUrl)) {
            return;
        }
        if (linkUrl.equals("ToShare")) {
            startActivity(new Intent(getActivity(), InviteFriendActivity.class));
        } else if (linkUrl.equals("ToLogin")) {
            startActivityForResult(new Intent(getActivity(), CheckPhoneActivity.class), LOGIN);
        } else if (linkUrl.equals("ToList")) {
            startActivity(new Intent(getActivity(), MainActivity.class).putExtra("fromWeb", "fromWeb"));
        } else if (linkUrl.equals("ToRecharge")) {
            startActivity(new Intent(getActivity(), ChongZhiListActivity.class).putExtra(IConstants.Extra.RECHARGE_TYPE, "1"));
        } else if (linkUrl.equals("ToAccount")) {
            EventBus.getDefault().post(new AccountRefreshEvent());
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("fromWeb", "gotoaccount");
            startActivity(intent);
        } else if (linkUrl.equals("ToRealName")) {
            startActivity(new Intent(getActivity(), VerifyInfoActivity.class));
        } else if (linkUrl.equals("ToRiskAssessment")) {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(getActivity(), CheckPhoneActivity.class), LOGIN);
            } else {
                if (TextUtils.isEmpty(cacheData.result.investStyle)) {//未测评
                    startActivity(new Intent(getActivity(), RiskEvaluationActivity.class));
                } else {
                    getData(cacheData.result.investStyle);
                }
            }
        }
    }

    private void getData(final String investStyle) {
        showLoadingDialog();
        new HttpHelper().getInvestTypeInfo(investStyle).subscribe(new Action1<CommonHttpModel<EvaluationSubmitResult>>() {
            @Override
            public void call(CommonHttpModel<EvaluationSubmitResult> commonResult) {

                try {
                    dismissLoadingDialog();
                    if (commonResult.code.equals("0")) {
                        startActivity(new Intent(getActivity(), EvaluationSuccessActivity.class)
                                .putExtra(EvaluationSuccessActivity.INVEST_TYPE, investStyle)
                                .putExtra(EvaluationSuccessActivity.DESCRIPTION, commonResult.result.tipsOne)
                                .putExtra(EvaluationSuccessActivity.TYPE_DETAIL, commonResult.result.tipsTwo));
                    } else {
                        ViewHelper.showToast(getActivity(), commonResult.message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    dismissLoadingDialog();
                    ViewHelper.showToast(getActivity(), throwable.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private MyWalletModel myWalletModel;

    /**
     * 更新界面
     *
     * @param myWalletResponse
     */
    private void updateUi(MyWalletResponse myWalletResponse) {
        MyWalletModel result = myWalletResponse.result;
        this.myWalletModel = result;
//        tv_title.setText(result.getTitle());//标题
        tv_keyongyue.setText(result.getTotalAssetsLabel());//总资产
        tv_totalProfitLabel.setText(result.getTotalProfitLabel());//累计收益
        tv_balanceLabel.setText(result.getBalanceLabel());//账户余额

        mBtnTiXian.setText(result.getWithdrawButtonLabel());//提现
        mBtnChongZhi.setText(result.getRechargeButtonLabel());//充值
        mInvest_record_text.setText(result.getInvestDetailLabel());//投资记录
        mTrade_record_text.setText(result.getTradeDetailLabel());//交易流水
        invite_friend.setCenterText(result.getShareLabel());//邀请好友
        menu_redpag.setCenterText(result.getRedPacketLabel());//投资红包
        coupon.setCenterText(result.getCouponsLabel());//优惠劵
        card_layout.setCenterText(result.getCDKeyLabel());//礼品卡
    }

    @BusReceiver
    public void StringEvent(String event) {
        if (event.equals("getMyWalletFinish")) {
            loginState();
        } else if (event.equals("MyFragmentUpdate")) {//刷新消息条数
            getUnreadMessage();
        }
    }

    private void loginState() {
        //这里对ui进行操作   展示登陆过的界面
        rlLogin.setVisibility(View.VISIBLE);
        MyWalletResponse cacheData = DataCache.instance.getCacheData("heng", "MyWalletResponse");
        if (cacheData != null) {
            if (SharedpreferenceUtil.getBoolean("showNum", true)) {
                keYong.setText(cacheData.result.getAvailableAmount());
                ShouYi.setText(cacheData.result.getTotalInterest());
                zongZiChan.setText(cacheData.result.getAllAssets());
                isShowNum.setImageResource(R.drawable.my_eye_open);
            } else {
                keYong.setText("****");
                ShouYi.setText("****");
                zongZiChan.setText("****");
                isShowNum.setImageResource(R.drawable.my_eye_close);
            }
            try {
                if (Float.parseFloat(cacheData.result.getCashBackSum()) > 0) {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        try {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            LoginResponse cacheData1 = DataCache.instance.getCacheData("heng", "LoginResponse");
            switch (v.getId()) {
                case R.id.fragment_my_chongzhi:
                    if ("0".equals(cacheData.result.realnameStatus) || TextUtils.isEmpty(cacheData.result.userPaypassword)) {
                        startActivity(new Intent(getActivity(), ShowStepViewActivity.class)
                                .putExtra(IConstants.Extra.EXTRA_SHOWSTEP_LOGO, R.mipmap.ic_showstep_logoother)
                        );
                    } else {
                        startActivity(new Intent(getActivity(), ChongZhiOrTiXianActivity.class)
                                .putExtra(IConstants.Extra.RECHARGE_TYPE, 1)
                        );
                    }
                    break;

                case R.id.fragment_my_tixian://判断是否绑卡
                    if ("0".equals(cacheData.result.realnameStatus) || TextUtils.isEmpty(cacheData.result.userPaypassword)) {
                        startActivity(new Intent(getActivity(), ShowStepViewActivity.class)
                                .putExtra(IConstants.Extra.EXTRA_SHOWSTEP_LOGO, R.mipmap.ic_showstep_logoother));
                    } else {
                        startActivity(new Intent(getActivity(), ChongZhiOrTiXianActivity.class).putExtra("type", 2));
                    }
                    break;
                case R.id.iv_setting:
                    startActivity(new Intent(getActivity(), SettingActivity.class));
                    break;
                case R.id.invest_record:
                    if (cacheData1 == null) {
                        startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    } else {
                        TouZiJiLuActivity.show(myWalletModel.getInvestDetailLabel(), getActivity());
                    }
                    break;
                case R.id.trade_record:
                    if (cacheData1 == null) {
                        startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    } else {
                        if (myWalletModel != null) {
                            JiaoYiJiLuActivity.show(myWalletModel.getTradeDetailLabel(), getActivity());
                        } else {
                            JiaoYiJiLuActivity.show("", getActivity());
                        }
                    }
                    break;
                case R.id.message_center:
                    if (cacheData1 == null) {
                        startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), MessageCenterActivity.class));
                    }
                    break;
                case R.id.invite_friend:
                    if (cacheData1 == null) {
                        startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    } else {
                        InviteFriendActivity.show(myWalletModel.getShareLabel(), getActivity());
                    }
                    break;
                case R.id.coupon:
                    if (cacheData1 == null) {
                        startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    } else {
                        YouHuiQuanActivity.show(myWalletModel.getCouponsLabel(), "coupon", getActivity());
                    }
                    break;
                case R.id.menu_redpag:
                    if (cacheData1 == null) {
                        startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    } else {
                        YouHuiQuanActivity.show(myWalletModel.getRedPacketLabel(), "menu_redpag", getActivity());
                    }
                    break;
                case R.id.show_num:
                    Boolean isShow = SharedpreferenceUtil.getBoolean("showNum", true);
                    if (isShow) {
                        SharedpreferenceUtil.put("showNum", false);
                        isShowNum.setImageResource(R.drawable.my_eye_close);
                        keYong.setText("****");
                        ShouYi.setText("****");
                        zongZiChan.setText("****");
                    } else {
                        SharedpreferenceUtil.put("showNum", true);
                        isShowNum.setImageResource(R.drawable.my_eye_open);
                        MyWalletResponse walletResponse = DataCache.instance.getCacheData("heng", "MyWalletResponse");
                        if (cacheData != null) {
                            keYong.setText(walletResponse.result.getAvailableAmount());
                            ShouYi.setText(walletResponse.result.getTotalInterest());
                            zongZiChan.setText(walletResponse.result.getAllAssets());
                        }
                    }
                    break;
                case R.id.card_layout:
                    if (cacheData1 == null) {
                        startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    } else {
                        CardActivity.show(myWalletModel.getCDKeyLabel(), getActivity());
                    }
                    break;

                case R.id.my_login:
                    startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    break;
                case R.id.user_photo:
                    if (cacheData1 == null) {
                        startActivity(new Intent(getActivity(), CheckPhoneActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), SettingActivity.class));
                    }
                    break;
                case R.id.img_noRealName:
                    goLinkUrl(walletResponse.result.getBindingAction());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadData() {
        getWallet();
        getUnreadMessage();
    }
}
