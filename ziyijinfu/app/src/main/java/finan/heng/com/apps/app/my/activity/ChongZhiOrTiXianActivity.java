package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/4/27.
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dynamic.foundations.common.utils.StringUtils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.orhanobut.logger.Logger;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.ui.activity.WithDrawResultActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.model.BaoFooResult;
import finan.heng.com.apps.model.ChongZhiResponse;
import finan.heng.com.apps.model.GetWithdrawInitModel;
import finan.heng.com.apps.model.GetWithdrawInitResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.RechargeInitResponse;
import finan.heng.com.apps.model.RechargeInitResultResponse;
import finan.heng.com.apps.model.SureWithdrawResponse;
import finan.heng.com.apps.model.WithDrawActureAmtResponse;
import finan.heng.com.apps.model.event.AccountRefreshEvent;
import finan.heng.com.apps.model.event.PurchaseEvent;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.TrueButton;
import finan.heng.com.apps.utils.WithdrawlDialog;
import finan.heng.com.apps.widgets.NoticeView;
import finan.zhimabao.com.apps.R;
import rx.Subscriber;
import rx.functions.Action1;

public class ChongZhiOrTiXianActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private ImageView ivBankIcon, tvChoose, mPayType;
    private TextView tvBankName, tvBankNum, tvTiShi, activity_chongzhi_warning, tv_cash;
    private View line2;
    private RelativeLayout ll_paytype;
    private EditText etMoney;
    private Button btn;
    private String mTxyhkCode;
    boolean isSelect = true;
    private int type;
    private String mTxyhImg;
    private String payType = "";
    private LinearLayout ll_award;
    private TextView tv_award;
    private NoticeView notice_bank;
    private double bankSingleLimit;
    private String checkModel = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        type = getIntent().getIntExtra("type", -1);
        try {
            payType = getIntent().getStringExtra(IConstants.Extra.PAY_PURCHASE);
        } catch (Exception e) {
            e.printStackTrace();
            payType = "";
        }

        notice_bank = findViewById(R.id.notice_bank);

        Logger.i("rechargetype: " + type);
        initView();
        init();
        if (type == 1) {
            activity_chongzhi_warning.setVisibility(View.VISIBLE);
            tvTiShi.setVisibility(View.GONE);
            line2.setVisibility(View.VISIBLE);
            ll_paytype.setVisibility(View.VISIBLE);
            ll_award.setVisibility(View.GONE);
            getRechargeInit();
        } else if (type == 2) {
            activity_chongzhi_warning.setVisibility(View.VISIBLE);
            tvTiShi.setVisibility(View.VISIBLE);
            line2.setVisibility(View.GONE);
            ll_paytype.setVisibility(View.GONE);
            ll_award.setVisibility(View.VISIBLE);
            getWithDrawInit();
            KeyboardVisibilityEvent.setEventListener(
                    ChongZhiOrTiXianActivity.this,
                    new KeyboardVisibilityEventListener() {
                        @Override
                        public void onVisibilityChanged(boolean isOpen) {
                            // some code depending on keyboard visiblity status
                            if (!isOpen) {
                                String s = etMoney.getText().toString().trim();
                                if (StringUtils.isNotEmpty(s)) {

                                    showLoadingDialog();

                                    new HttpHelper().getWithDrawActurlAmt(s.toString()).subscribe(new Action1<WithDrawActureAmtResponse>() {
                                        @Override
                                        public void call(WithDrawActureAmtResponse withDrawActureAmtResponse) {
                                            dismissLoadingDialog();
                                            try {
                                                tv_award.setText(withDrawActureAmtResponse.result.withdrawAmount);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Action1<Throwable>() {
                                        @Override
                                        public void call(Throwable throwable) {
                                            dismissLoadingDialog();
                                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                            ViewHelper.showToast(ChongZhiOrTiXianActivity.this, requestErrorThrowable.getMessage());
                                        }
                                    });
                                }
                            }
                        }
                    });
        }
        EventBus.getDefault().register(this);
    }

    public void onEvent(PurchaseEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 提现初始化
     */
    private void getWithDrawInit() {
        showLoadingDialog();
        new HttpHelper().getWithdrawInit().subscribe(new Action1<GetWithdrawInitResponse>() {
            @Override
            public void call(GetWithdrawInitResponse getWithdrawInitResponse) {
                dismissLoadingDialog();
                try {
                    String token = getWithdrawInitResponse.result.getToken();
                    if (StringUtils.isNotEmpty(token)) {
                        DataCache.instance.saveCacheData("heng", "token", token);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Logger.i("result" + getWithdrawInitResponse.toString());
                mTxyhImg = getWithdrawInitResponse.result.getTxyhImg();
                Glide.with(ChongZhiOrTiXianActivity.this).load(mTxyhImg).into(ivBankIcon);
                tvBankName.setText(getWithdrawInitResponse.result.getTxyhkName());
                mTxyhkCode = getWithdrawInitResponse.result.getTxyhkCode();
                tvBankNum.setText("**** **** **** " + mTxyhkCode);
                tv_cash.setText(getWithdrawInitResponse.result.getTxye());
                activity_chongzhi_warning.setText(StringUtils.trimToEmpty(getWithdrawInitResponse.result.getWithdrawTips()));
                tvTiShi.setText(getWithdrawInitResponse.result.getTxwxts());
                etMoney.setHint("最多可提" + getWithdrawInitResponse.result.getTxye() + "元");
                etMoney.setHint(StringUtils.trimToEmpty(getWithdrawInitResponse.result.getAmountTips()));
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(StringUtils.trimToEmpty(getWithdrawInitResponse.result.getBankTips()));
                notice_bank.start(arrayList);
                notice_bank.setVisibility(StringUtils.isEmpty(getWithdrawInitResponse.result.getBankTips()) ? View.GONE : View.VISIBLE);
                canWithDraw = !getWithdrawInitResponse.result.isNotInService();
                updateWithdrawUi(getWithdrawInitResponse.result);
                try {
                    bankSingleLimit = Double.parseDouble(getWithdrawInitResponse.result.getBankSingleLimit());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ChongZhiOrTiXianActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    /**
     * 提现ui更新
     *
     * @param withdrawResponse
     */
    private void updateWithdrawUi(GetWithdrawInitModel withdrawResponse) {
        if (withdrawResponse == null) {
            return;
        }
        setBarTitle(withdrawResponse.getTitle());
        btn.setText(withdrawResponse.getButtonLabel());
        tv_withdraw.setText(withdrawResponse.getBankLabel());
        tv_amountLabel.setText(withdrawResponse.getAmountLabel());
        tv_cash_label.setText(withdrawResponse.getBalanceLabel());
        tv_award_label.setText(withdrawResponse.getAmount2Label());
    }

    /**
     * 充值初始化
     */
    private void getRechargeInit() {
        showLoadingDialog();
        new HttpHelper().getRechargeInit().subscribe(new Action1<RechargeInitResponse>() {
            @Override
            public void call(RechargeInitResponse rechargeInitResponse) {
                Logger.i("result" + rechargeInitResponse.toString());
                dismissLoadingDialog();
                try {
                    String token = rechargeInitResponse.result.getToken();
                    if (StringUtils.isNotEmpty(token)) {
                        DataCache.instance.saveCacheData("heng", "token", token);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                activity_chongzhi_warning.setText(StringUtils.trimToEmpty(rechargeInitResponse.result.getTips()));
                mTxyhImg = rechargeInitResponse.result.getBankImageUrl();
                checkModel = rechargeInitResponse.result.checkModel;
                Glide.with(ChongZhiOrTiXianActivity.this).load(mTxyhImg).into(ivBankIcon);
                if (rechargeInitResponse.result.getPayment() != null) {
                    Glide.with(ChongZhiOrTiXianActivity.this).load(rechargeInitResponse.result.getPayment().paymentLogo).into(mPayType);
                }
                tvBankName.setText(rechargeInitResponse.result.getBankName());
                mTxyhkCode = rechargeInitResponse.result.getBankCode();
                tvBankNum.setText("**** **** **** " + mTxyhkCode);
                etMoney.setHint("最少充值100元");
                tv_cash.setText(rechargeInitResponse.result.getAvailableMoney());
                tvTiShi.setText(StringUtils.trimToEmpty(rechargeInitResponse.result.getTips()));
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(StringUtils.trimToEmpty(rechargeInitResponse.result.getBankTips()));
                notice_bank.start(arrayList);
                notice_bank.setVisibility(StringUtils.isEmpty(rechargeInitResponse.result.getBankTips()) ? View.GONE : View.VISIBLE);
                canRecharge = !rechargeInitResponse.result.isNotInService();
                updateRechargeUi(rechargeInitResponse.result);
                try {
                    bankSingleLimit = Double.parseDouble(rechargeInitResponse.result.getBankSingleLimit());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ChongZhiOrTiXianActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private RechargeInitResultResponse rechargeResult;
    /**
     * 更新充值界面
     *
     * @param rechargeResponse
     */
    private void updateRechargeUi(RechargeInitResultResponse rechargeResponse) {
        if (rechargeResponse == null) {
            return;
        }
        this.rechargeResult = rechargeResponse;
        setBarTitle(rechargeResponse.getTitle());
        btn.setText(rechargeResponse.getButtonLabel());
        tv_withdraw.setText(rechargeResponse.getBankLabel());
        tv_amountLabel.setText(rechargeResponse.getAmountLabel());
        tv_cash_label.setText(rechargeResponse.getBalanceLabel());
//        tv_award_label.setText(rechargeResponse.getAmount2Label());
        tv_serviceAgreementLabel.setText("《"+rechargeResponse.getServiceAgreementLabel()+"》");
        tv_paymentTypeLabel.setText(rechargeResponse.getPaymentTypeLabel());
    }

    private boolean canRecharge = true;
    private boolean canWithDraw = true;
    private TextView tv_withdraw, tv_amountLabel, tv_cash_label, tv_award_label,tv_serviceAgreementLabel,tv_paymentTypeLabel;

    private void init() {
        tvChoose.setOnClickListener(this);
        etMoney.addTextChangedListener(this);
        btn.setOnClickListener(this);
    }

    private void initView() {
        ivBankIcon = (ImageView) findViewById(R.id.tixian_bank_icon);
        tvBankName = (TextView) findViewById(R.id.tixian_bank_name);
        tv_award = findViewById(R.id.tv_award);
        activity_chongzhi_warning = (TextView) findViewById(R.id.activity_chongzhi_warning);
        ll_paytype = (RelativeLayout) findViewById(R.id.ll_paytype);
        ll_award = findViewById(R.id.ll_award);
        line2 = findViewById(R.id.line2);
        tv_cash = (TextView) findViewById(R.id.tv_cash);
        mPayType = findViewById(R.id.tv_paytype);
        findViewById(R.id.activity_chongzhi_xieyi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ChongZhiOrTiXianActivity.this, WebActivity.class);
                if(rechargeResult!=null){
                    intent2.putExtra("title", rechargeResult.getServiceAgreementLabel());
                }else {
                    intent2.putExtra("title", "子壹金服快捷支付服务协议");
                }
                intent2.putExtra("url", URLHelper.getInstance().URL + "/protocol/payment");
                startActivity(intent2);
            }
        });
        tvBankNum = (TextView) findViewById(R.id.tixian_bank_num);
        tvChoose = (ImageView) findViewById(R.id.chongzhi_choose);
        etMoney = (EditText) findViewById(R.id.chongzhi_money);
        btn = (Button) findViewById(R.id.activity_chongzhi_btn);
        tvTiShi = (TextView) findViewById(R.id.activity_chongzhi_tishi);
        tv_withdraw = (TextView) findViewById(R.id.activity_chongzhi_tvone);
        tv_amountLabel = (TextView) findViewById(R.id.activity_chongzhi_tv_two);
        tv_cash_label = (TextView) findViewById(R.id.tv_cash_label);
        tv_award_label = (TextView) findViewById(R.id.tv_award_label);
        tv_serviceAgreementLabel = (TextView) findViewById(R.id.activity_chongzhi_xieyi);
        tv_paymentTypeLabel = (TextView) findViewById(R.id.tv_paytype_label);

        if (type == 1) {
            findViewById(R.id.activity_chonghi_ll).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.activity_chonghi_ll).setVisibility(View.GONE);
        }
        etMoney.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                Log.i("return","source"+source.toString()+" dest"+dest.toString());
                if (source.equals(".") && dest.toString().length() == 0) {
                    return "0.";
                }
                if (!source.equals(".") && dest.toString().equals("0")) {

                    return "";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int length = dest.toString().substring(index).length();
                    if (length == 3) {
                        return "";
                    }
                }
                return null;
            }
        }, new InputFilter.LengthFilter(11)});
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chongzhi_choose:
                if (isSelect) {
                    tvChoose.setImageResource(R.drawable.ic_unchecked);
                } else {
                    tvChoose.setImageResource(R.drawable.ic_checked);
                }
                isSelect = !isSelect;
                break;
            case R.id.activity_chongzhi_btn:
                if (type == 1) {//充值
                    if (checkValue2()) {
//                        String code = getIntent().getStringExtra("code");
                        if (checkModel.equals(IConstants.Key.SMS)) {
                            showLoadingDialog();
                            new HttpHelper().chongZhi(etMoney.getText().toString(), "").subscribe(new Action1<ChongZhiResponse>() {
                                @Override
                                public void call(ChongZhiResponse chongZhiResponse) {
                                    dismissLoadingDialog();
                                    try {
                                        String token = chongZhiResponse.result.getToken();
                                        if (StringUtils.isNotEmpty(token)) {
                                            DataCache.instance.saveCacheData("heng", "token", token);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        if (chongZhiResponse.isSuccess()) {
                                            showMessageDialog(chongZhiResponse.result.smsCodeMaxLen);
                                        } else {
                                            ViewHelper.showToast(getApplicationContext(), chongZhiResponse.errorMsg);
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
                                        ViewHelper.showToast(ChongZhiOrTiXianActivity.this, requestErrorThrowable.getMessage());
                                    }
                                }
                            });
                        } else if (checkModel.equals(IConstants.Key.PASSWORD)) {
                            showDiaLog();
                        }

                    }
                } else if (type == 2) {//提现
                    if (checkValue()) {
                        showDiaLog();
                    }
                }
                break;
        }
    }

    private void showMessageDialog(int maxLength) {
        View view = View.inflate(this, R.layout.dialog_activity_real_name, null);
        ImageView closeImage = view.findViewById(R.id.close);
        TextView tips = view.findViewById(R.id.send_tips);
        final MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        String phone = "";
        if (null != cacheData) {
            phone = cacheData.result.userAccount.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        tips.setText(Html.fromHtml("为了保障您的资金安全<br>已向<big><font color = '#5b9cf8'>" + phone + "</font></big>发送短信验证码"));
        Button sure = view.findViewById(R.id.btSure);
        final TextView errorTips = view.findViewById(R.id.errorTips);

        final GridPasswordView input = view.findViewById(R.id.pswView);
        if (maxLength != 6 && maxLength > 0) {
            input.setmPasswordLength(maxLength);
        }
        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this, R.style.dialog).setView(view).setCancelable(false).create();
        alertDialog.show();
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(input.getPassWord())) {
                    errorTips.setText("请输入验证码");
                    return;
                }
                showLoadingDialog();
                new HttpHelper().baofooPayment(input.getPassWord()).subscribe(new Action1<CommonHttpModel<BaoFooResult>>() {
                    @Override
                    public void call(CommonHttpModel<BaoFooResult> baoFooResultCommonHttpModel) {
                        dismissLoadingDialog();
                        try {
                            Logger.i("result" + baoFooResultCommonHttpModel.result.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            String token = baoFooResultCommonHttpModel.result.getToken();
                            if (StringUtils.isNotEmpty(token)) {
                                DataCache.instance.saveCacheData("heng", "token", token);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            if (null != alertDialog && alertDialog.isShowing()) {
                                alertDialog.dismiss();
                            }
                            String code = baoFooResultCommonHttpModel.code;
                            if (code.equals("0") || code.equals("1") || code.equals("100")) {
                                EventBus.getDefault().post(new AccountRefreshEvent());
                                Intent intent = new Intent(ChongZhiOrTiXianActivity.this, RechargeResultActivity.class);
                                intent.putExtra("code", code);
                                intent.putExtra("msg", baoFooResultCommonHttpModel.message);
                                intent.putExtra("msg2", baoFooResultCommonHttpModel.result.message);
                                intent.putExtra("amount", baoFooResultCommonHttpModel.result.amount);
                                intent.putExtra("phone", baoFooResultCommonHttpModel.result.phone);
                                startActivity(intent);
                            } else {
                                ViewHelper.showToast(getApplicationContext(), baoFooResultCommonHttpModel.result.message + "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dismissLoadingDialog();
                        if (null != throwable) {
                            ViewHelper.showToast(getApplicationContext(), throwable.getMessage() + "");
                        }
                    }
                });
//                }
            }
        });
    }

    private boolean checkValue2() {
        if (!isSelect) {
            if(rechargeResult!=null){
                ViewHelper.showToast(ChongZhiOrTiXianActivity.this, "请阅读并同意《"+rechargeResult.getServiceAgreementLabel()+"》");
            }else {
                ViewHelper.showToast(ChongZhiOrTiXianActivity.this, "请阅读并同意《子壹金服快捷支付服务协议》");
            }
            return false;
        }
        if (!canRecharge) {
            return false;
        }

        if (Double.parseDouble(etMoney.getText().toString()) < 100) {
            ViewHelper.showToast(getApplicationContext(), "最少充值100元");
            return false;
        }
        if (bankSingleLimit > 0 && bankSingleLimit < Double.parseDouble(etMoney.getText().toString())) {
            ViewHelper.showToast(getApplicationContext(), "充值金额超出单笔限额");
            return false;
        }
        return true;
    }


    private void showDiaLog() {
        final WithdrawlDialog withdrawlDialog = new WithdrawlDialog(this, R.style.MyAlertDialog, R.layout.dialog_trader_password_key, "￥" + etMoney.getText().toString(), type);
        withdrawlDialog.show();
        withdrawlDialog.setTrueButton(new TrueButton() {
            @Override
            public void getText(String text) {
                showLoadingDialog();
//                for (int i = 0 ; i <= 10; i++){
                if (type == 2) {//提现
                    new HttpHelper().sureWithdraw(etMoney.getText().toString(), text).subscribe(new Action1<SureWithdrawResponse>() {
                        @Override
                        public void call(SureWithdrawResponse sureWithdrawResponse) {
                            dismissLoadingDialog();

                            try {
                                String token = sureWithdrawResponse.result.getToken();
                                if (StringUtils.isNotEmpty(token)) {
                                    DataCache.instance.saveCacheData("heng", "token", token);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (sureWithdrawResponse.isSuccess()) {
                                withdrawlDialog.dismiss();
                                EventBus.getDefault().post(new AccountRefreshEvent());
                                String tips = StringUtils.trimToEmpty(sureWithdrawResponse.result.getTips());
                                startActivity(new Intent(ChongZhiOrTiXianActivity.this, WithDrawResultActivity.class)
                                        .putExtra(IConstants.Extra.EXTRA_CASH, sureWithdrawResponse.result.getWithdrawAmount())
                                        .putExtra(IConstants.Extra.BANK_NAME, tvBankName.getText().toString())
                                        .putExtra(IConstants.Extra.BANK_CODE, mTxyhkCode)
                                        .putExtra(IConstants.Extra.BANK_TIPS, tips)
                                );
                            } else {
                                if (!sureWithdrawResponse.errorCode.equals("402")) {
                                    withdrawlDialog.showPSWError(sureWithdrawResponse.errorMsg);
                                }
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            dismissLoadingDialog();
                            if (throwable instanceof RequestErrorThrowable) {
                                RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                withdrawlDialog.showPSWError(requestErrorThrowable.getErrorCode() + requestErrorThrowable.getMessage());
                            }
                        }
                    });
//                }
                } else if (type == 1) {//充值
                    HashMap hashMap = new HashMap();
                    hashMap.put("amount", etMoney.getText().toString().trim());
                    hashMap.put("payPassword", text);
                    new HttpHelper().paymentConfirm(hashMap).subscribe(new Subscriber<CommonHttpModel<BaoFooResult>>() {
                        @Override
                        public void onCompleted() {
                            dismissLoadingDialog();
                        }

                        @Override
                        public void onError(Throwable e) {
                            dismissLoadingDialog();
                        }

                        @Override
                        public void onNext(CommonHttpModel<BaoFooResult> baoFooResultCommonHttpModel) {
                            try {
                                String token = baoFooResultCommonHttpModel.result.getToken();
                                if (StringUtils.isNotEmpty(token)) {
                                    DataCache.instance.saveCacheData("heng", "token", token);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                String code = baoFooResultCommonHttpModel.code;
                                if (baoFooResultCommonHttpModel.result.islink) {
                                    withdrawlDialog.dismiss();
                                    EventBus.getDefault().post(new AccountRefreshEvent());
                                    Intent intent = new Intent(ChongZhiOrTiXianActivity.this, RechargeResultActivity.class);
                                    intent.putExtra("code", code);
                                    intent.putExtra("msg", baoFooResultCommonHttpModel.message);
                                    intent.putExtra("msg2", baoFooResultCommonHttpModel.result.message);
                                    intent.putExtra("amount", baoFooResultCommonHttpModel.result.amount);
                                    intent.putExtra("phone", baoFooResultCommonHttpModel.result.phone);
                                    startActivity(intent);
                                } else {
                                    withdrawlDialog.showPSWError(baoFooResultCommonHttpModel.result.message + "");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                    });
                }

            }
        });
    }

    private boolean checkValue() {
        if (Double.parseDouble(etMoney.getText().toString()) < 100) {
            ViewHelper.showToast(getApplicationContext(), "最少提现100元");
            return false;
        }
        if (!canWithDraw) {
            return false;
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (etMoney.getText().toString().length() > 0) {
            btn.setEnabled(true);
            btn.setBackgroundResource(R.drawable.btn_click_round_rect);
        } else {
            btn.setEnabled(false);
            btn.setBackgroundResource(R.drawable.btn_unclick);
        }
        if (!canRecharge || !canWithDraw) {
            btn.setEnabled(false);
            btn.setBackgroundResource(R.drawable.btn_unclick);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
