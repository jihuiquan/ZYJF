package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dynamic.foundations.common.utils.ArithUtils;
import com.mcxiaoke.bus.Bus;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.home.presenter.TouziPresenter;
import finan.heng.com.apps.app.home.view.ITouziView;
import finan.heng.com.apps.app.my.activity.ChongZhiOrTiXianActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.manager.entity.model.WelfareModel;
import finan.heng.com.apps.manager.entity.model.WelfareResult;
import finan.heng.com.apps.model.GetProductDetailMoneyResponse;
import finan.heng.com.apps.model.GetProductRedPackModel;
import finan.heng.com.apps.model.RiskCheckResponse;
import finan.heng.com.apps.model.RiskCheckResult;
import finan.heng.com.apps.model.RiskDefaultResponse;
import finan.heng.com.apps.model.SureBuyModel;
import finan.heng.com.apps.model.SureBuyResponse;
import finan.heng.com.apps.model.SurePayResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.utils.MyPopupWindow;
import finan.heng.com.apps.utils.OnSelectItemListener;
import finan.heng.com.apps.utils.TrueButton;
import finan.heng.com.apps.utils.WithdrawlDialog;
import finan.zhimabao.com.apps.R;


/**
 * Created by Administrator on 2017/4/24.
 */
public class TouziActivity extends BaseActivity implements View.OnClickListener, ITouziView {

    private RelativeLayout mCouponLayout, mRedPackLayout;
    private MyPopupWindow myPopupWindow;
    private TextView mCharge, mExtraRate, mRedPac, mTimeLimit, mEstimateResult, mProfitRate, mRemainingInvest, mBalance;
    private Button mBtInvest;
    private int id;
    private EditText etTouZi;
    private int couId;
    private int startMoney;
    private CheckBox checkBox;
    private int redId;
    private TextView mTvXieYi;
    private int mRedMoney;
    private AlertDialog alertDialog;
    private int investLimit;
    private SureBuyResponse mSureBuyResponse;
    private TouziPresenter presenter;
    private WithdrawlDialog withdrawlDialog;
    //    Subscription subscription;
    private WelfareResult result;
    private TextView basicProfit;//基本收益
    private Spanned mSpaced;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_touzi);
        setUpToolbar();
        id = getIntent().getIntExtra("id", -1);
        getSupportActionBar().setTitle("");
        initView();
        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (!isOpen) {
                    String value = etTouZi.getText().toString();
                    getRedCouponAndEstimatedEarnings(value);
                }
            }
        });

        presenter = new TouziPresenter(this);
        showLoadingDialog();
        presenter.getData(id);
    }

    private void getRedCouponAndEstimatedEarnings(String value) {
        if (!TextUtils.isEmpty(value)) {
            HashMap<String, String> map = new HashMap();
            map.put("id", id + "");
            map.put("amount", value);
            presenter.getAvailableRedAndCoupon(map);
            if (couId != 0) {
                map.put("couponId", couId + "");
            }
            presenter.getEstimatedEarnings(map);

        } else {
            showCoupon("");
            showRedPack("");
            basicProfit.setText("");
            mEstimateResult.setText("");
        }
    }

    private TextView third_title, tv_profitLabel, tv_plstimeLimitLabel, account_title, tv_touzijine, all_in, tv_redPacketLabel, tv_couponsLabel, tv_basicProfitLabel, tv_totalProfitLabel, tv_riskReminderName;

    private void initView() {
        third_title = (TextView) findViewById(R.id.third_title);
        tv_profitLabel = (TextView) findViewById(R.id.tv_profitLabel);
        tv_plstimeLimitLabel = (TextView) findViewById(R.id.tv_plstimeLimitLabel);
        account_title = (TextView) findViewById(R.id.account_title);
        tv_touzijine = (TextView) findViewById(R.id.tv_touzijine);
        tv_redPacketLabel = (TextView) findViewById(R.id.tv_redPacketLabel);
        tv_couponsLabel = (TextView) findViewById(R.id.tv_couponsLabel);
        tv_basicProfitLabel = (TextView) findViewById(R.id.tv_basicProfitLabel);
        tv_totalProfitLabel = (TextView) findViewById(R.id.tv_totalProfitLabel);

        etTouZi = findViewById(R.id.activity_home_touzi_et);
        mCouponLayout = findViewById(R.id.rl_jiaxiquan);
        mRedPackLayout = findViewById(R.id.rl_youhuiquan);
        mBtInvest = findViewById(R.id.bt_touzi);
        mTvXieYi = findViewById(R.id.tv_xieyi);
        mBalance = findViewById(R.id.activity_home_touzi_money);
        mTimeLimit = findViewById(R.id.tv_qixian);
        checkBox = (CheckBox) findViewById(R.id.activity_home_touzi_check_box_btn);
        mRedPac = (TextView) findViewById(R.id.tv_lingqu);
        mCharge = (TextView) findViewById(R.id.activity_home_touzi_chonghzi);
        mExtraRate = (TextView) findViewById(R.id.tv_jiaxi);
        mRemainingInvest = (TextView) findViewById(R.id.tv_num);
        mEstimateResult = (TextView) findViewById(R.id.tv_yuan);
        mProfitRate = (TextView) findViewById(R.id.tv_shouyilv);
        basicProfit = findViewById(R.id.base_text);
        mBtInvest.setOnClickListener(this);
        mCharge.setOnClickListener(this);
        mRedPackLayout.setOnClickListener(this);
        mTvXieYi.setOnClickListener(this);
        mCouponLayout.setOnClickListener(this);
        tv_riskReminderName = findViewById(R.id.tv_xieyi2);
        tv_riskReminderName.setOnClickListener(this);
        all_in = findViewById(R.id.all_in);
        all_in.setOnClickListener(this);
        findViewById(R.id.check_layout).setOnClickListener(this);
        etTouZi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = etTouZi.getText().toString();
                if (text.length() > 0) {
                    mBtInvest.setEnabled(true);
                    mBtInvest.setBackgroundResource(R.drawable.btn_click_round_rect);
                } else {
                    mBtInvest.setEnabled(false);
                    mBtInvest.setBackgroundResource(R.drawable.btn_unclick);
                    showCoupon("");
                    showRedPack("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                redId = 0;
                mRedMoney = 0;
                couId = 0;
                mCouponLayout.setClickable(true);
                mRedPackLayout.setClickable(true);
                result = null;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_layout:
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                } else {
                    checkBox.setChecked(true);
                }
                break;
            case R.id.rl_jiaxiquan:
                if (TextUtils.isEmpty(etTouZi.getText().toString())) {
                    ViewHelper.showToast(this, getString(R.string.touzi_empty_tip));
                    return;
                }
                if (result != null) {
                    showPopCoupon(result.couponList);
                }
                break;
            case R.id.activity_home_touzi_chonghzi:
                startActivity(new Intent(TouziActivity.this, ChongZhiOrTiXianActivity.class)
                        .putExtra(IConstants.Extra.RECHARGE_TYPE, 1)
                        .putExtra(IConstants.Extra.PAY_PURCHASE, IConstants.Extra.PAY_PURCHASE)
                );
//                DialogUtil.getInstance().showLoading(this);
                break;
            case R.id.tv_xieyi:
                startActivity(new Intent(TouziActivity.this, WebActivity.class).
                        putExtra("url", URLHelper.getInstance().buildUrl(IConstants.Server.ADDRESS_PURCHASE_PROTACAL)).putExtra("title", sureBuyResult.getInvestmentAgreementName()));
                break;
            case R.id.tv_xieyi2:
                startActivity(new Intent(TouziActivity.this, WebActivity.class).
                        putExtra("url", URLHelper.getInstance().buildUrl(IConstants.Server.ADDRESS_RISK_PROTACAL)).putExtra("title", sureBuyResult.getRiskReminderName()));
                break;
            case R.id.rl_youhuiquan://红包的点击去掉
                if (TextUtils.isEmpty(etTouZi.getText().toString())) {
                    ViewHelper.showToast(this, getString(R.string.touzi_empty_tip));
                    return;
                }
                if (result != null) {
                    showPopRedPack(result.redList);
                }
                break;
            case R.id.all_in:

                try {
                    String userBalance = mSureBuyResponse.result.getUserAccount().getAvailableMoney();//账户可用余额
                    String productLeft;//标的剩余

                    if (mSureBuyResponse.result.getProducts().getType().equals("-1")) {
                        productLeft = mSureBuyResponse.result.getSurplusAmount2();//个人剩余
                        try {
                            if (Double.parseDouble(mSureBuyResponse.result.getSurplusAmount2()) > Double.parseDouble(mSureBuyResponse.result.getProducts().getSurplusAmount())) {
                                productLeft = mSureBuyResponse.result.getProducts().getSurplusAmount();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        productLeft = mSureBuyResponse.result.getProducts().getSurplusAmount();
                    }
                    try {
                        if (Double.parseDouble(productLeft) > Double.parseDouble(mSureBuyResponse.result.getProducts().getInvestEnd())) {
                            productLeft = mSureBuyResponse.result.getProducts().getInvestEnd();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    double userBalanceDouble;
                    double productLeftDouble;
                    double investBegin;//起投金额
                    try {
                        investBegin = Double.parseDouble(mSureBuyResponse.result.getProducts().getInvestBegin());
                    } catch (Exception e) {
                        e.printStackTrace();
                        investBegin = 0;
                    }
                    try {
                        userBalanceDouble = Double.parseDouble(userBalance);
                        productLeftDouble = Double.parseDouble(productLeft);
                    } catch (Exception e) {
                        e.printStackTrace();
                        userBalanceDouble = 0;
                        productLeftDouble = 0;
                    }
                    if ((int) investBegin <= (int) userBalanceDouble && (int) userBalanceDouble <= (int) productLeftDouble) {
                        int num = (int) (userBalanceDouble) / 100;
                        etTouZi.setText(num * 100 + "");
                    } else if ((int) userBalanceDouble <= (int) investBegin && (int) investBegin <= (int) productLeftDouble) {
                        etTouZi.setText((int) investBegin + "");
                    } else {
                        etTouZi.setText((int) productLeftDouble + "");
                    }
                    getRedCouponAndEstimatedEarnings(etTouZi.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.bt_touzi:
                String value;
                value = etTouZi.getText().toString();
                double left = 0;
                double inputValue = 0;
                try {
                    left = Double.parseDouble(mSureBuyResponse.result.getProducts().getSurplusAmount());
                    inputValue = Double.parseDouble(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if ((int) inputValue > (int) left) {
                    ViewHelper.showToast(getApplicationContext(), "该标仅剩" + left + "元可投");
                    etTouZi.setText((int) left + "");
                    getRedCouponAndEstimatedEarnings(etTouZi.getText().toString());
                    return;
                }
                if (checkValue(value)) {
                    presenter.checkInvestMoney(value);
                }
                break;
            case R.id.left_button:
                dismissEvaluationDialog();
                startActivity(new Intent(this, RiskEvaluationActivity.class));
                break;
            case R.id.right_button:
                dismissEvaluationDialog();
                presenter.riskDefaultLevel();
                break;
        }
    }

    private void dismissEvaluationDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    private void showEvaluationDialog(RiskCheckResult result) {
        View view = View.inflate(this, R.layout.dialog_risk_evaluation, null);
        TextView textView1 = (TextView) view.findViewById(R.id.text1);
        textView1.setText(result.current_level_description);
        TextView textView2 = (TextView) view.findViewById(R.id.text2);
        textView2.setText(result.max_level_description);
        TextView leftButton = (TextView) view.findViewById(R.id.left_button);
        TextView rightButton = (TextView) view.findViewById(R.id.right_button);
        rightButton.setText("一键默认");
        if (result.current_level.equals("-1")) {//表示没有测评过
            leftButton.setText("立即测评");
        } else {
            leftButton.setText("重新测评");
        }
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
        alertDialog = new AlertDialog.Builder(this, R.style.dialog).setView(view).create();
        alertDialog.show();
    }

    private void showInvestDialog() {
        withdrawlDialog = new WithdrawlDialog(this, R.style.MyAlertDialog, R.layout.dialog_trader_password_key, "<font color='#333333'>" + "投资 ￥ " + "</font>" + (Integer.parseInt(etTouZi.getText().toString())) + "元", 0);
        withdrawlDialog.show();
        withdrawlDialog.setTrueButton(new TrueButton() {
            @Override
            public void getText(String text) {//优惠券id，红包id，投资金额,投资金额-红包金额，
                showLoadingDialog();
                HashMap<String, String> map = new HashMap();
                map.put("productsId", id + "");
                map.put("amount", etTouZi.getText().toString());//投资金额
                map.put("paypassword", text);//交易密码
                if (redId != 0 && couId != 0) {//为0表示没有使用过
                    map.put("couponId", couId + "");
                    map.put("redId", redId + "");
                    map.put("actualAmount", "" + (Integer.parseInt(etTouZi.getText().toString()) - mRedMoney));
                } else if (redId != 0 && couId == 0) {
                    map.put("redId", redId + "");
                    map.put("actualAmount", "" + (Integer.parseInt(etTouZi.getText().toString()) - mRedMoney));
                } else if (redId == 0 && couId != 0) {
                    map.put("couponId", couId + "");
                    map.put("actualAmount", etTouZi.getText().toString());
                } else {
                    map.put("actualAmount", etTouZi.getText().toString());
                }
                presenter.investRequest(map);
            }
        });
    }

    private void showNotEnoughDialog() {
        View view = View.inflate(this, R.layout.activity_home_touzi_dialog_not_enough, null);
        Button button = (Button) view.findViewById(R.id.goto_charge);
        ImageView close = (ImageView) view.findViewById(R.id.dialog_close);
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.dialog).setView(view).create();
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(TouziActivity.this, ChongZhiOrTiXianActivity.class).putExtra("type", 1));
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void touZiSuccess(SurePayResponse surePayResponse) {
        Intent intent = new Intent(TouziActivity.this, TouziSuccessActivity.class);
        intent.putExtra("body", surePayResponse);
        startActivity(intent);
        Bus.getDefault().post("PAY_SUCCESS");
        finish();
    }

    private void showPopCoupon(List<WelfareModel> list) {//加息券
        List<WelfareModel> mJiaxiList = list;
        if (mJiaxiList != null && mJiaxiList.size() > 0) {
//                    tv_jiaxi.setText("未使用加息券");
            for (int i = 0; i < mJiaxiList.size(); i++) {
                if (mJiaxiList.get(i).getId().equals(couId + "")) {
                    mJiaxiList.get(i).isSelect = true;
                    break;
                }
            }
            myPopupWindow = new MyPopupWindow(TouziActivity.this, mJiaxiList, "加息券", etTouZi.getText().toString(), investLimit);
            myPopupWindow.setOnSelectItemListener(new OnSelectItemListener() {
                @Override
                public void selectItem(String name, int type) {

                    switch (type) {
                        case MyPopupWindow.POP_WINDOW_ITEM_1://不再显示加息百分率而显示加息金额
                            break;
                        case MyPopupWindow.POP_WINDOW_ITEM_2://对应的id
                            String value = etTouZi.getText().toString();
                            if (value.length() == 0) {
                                value = "0";
                            }
                            HashMap<String, String> map = new HashMap();
                            map.put("id", id + "");
                            map.put("amount", value);
                            if (TextUtils.isEmpty(name)) {
                                couId = 0;
                                if (result != null) {
                                    showCoupon("您有" + result.availableCouponCount + "个加息券可用");
                                }
                            } else {
                                couId = Integer.parseInt(name);
                                map.put("couponId", couId + "");

                            }
                            presenter.getEstimatedEarnings(map);
                            break;
                    }
                }
            });
        } else {
            showCoupon(getString(R.string.touzi_no_coupon));
        }
    }

    private void showPopRedPack(List<WelfareModel> list) {//红包
        List<WelfareModel> mHongbaoList = list;
        if (mHongbaoList != null && mHongbaoList.size() > 0) {
            for (int i = 0; i < mHongbaoList.size(); i++) {
                if (mHongbaoList.get(i).getId().equals(redId + "")) {
                    mHongbaoList.get(i).isSelect = true;
                    break;
                }
            }
            myPopupWindow = new MyPopupWindow(TouziActivity.this, mHongbaoList, "红包", etTouZi.getText().toString(), investLimit);
            myPopupWindow.setInvestDay(investLimit);
            myPopupWindow.setOnSelectItemListener(new OnSelectItemListener() {
                @Override
                public void selectItem(String name, int type) {
                    switch (type) {
                        case MyPopupWindow.POP_WINDOW_ITEM_1://金额
                            if (TextUtils.isEmpty(name)) {
                                mRedMoney = 0;
                            } else {
                                try {
                                    mRedPac.setText(Html.fromHtml("红包抵扣" + "<font color='#5b9cf8'>" + ((int) Double.parseDouble(name)) + "元" + "</font>"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    mRedPac.setText(Html.fromHtml("红包抵扣" + "<font color='#5b9cf8'>" + name + "元" + "</font>"));
                                }
                                mRedMoney = (int) Double.parseDouble(name);
                            }
                            break;
                        case MyPopupWindow.POP_WINDOW_ITEM_2://对应的id
                            if (TextUtils.isEmpty(name)) {
                                redId = 0;
                                showRedPack(getString(R.string.touzi_hava_redpack));
                                if (result != null) {
                                    showRedPack("您有" + result.availableRedCount + "个红包可用");
                                }
                            } else {
                                redId = Integer.parseInt(name);
                            }
                            break;
                    }
                }
            });
        } else {
            showRedPack(getString(R.string.touzi_no_redpack));
        }
    }

    private boolean checkValue(String value) {
        int intValue;
        try {
            intValue = Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
            ViewHelper.showToast(this, "请输入正确的整数");
            return false;
        }

        if (!checkBox.isChecked()) {
            ViewHelper.showToast(this, "请阅读并同意《子壹金服投资协议》和《风险提示函》");
            return false;
        }
        double left;
        try {
            left = Double.parseDouble(mSureBuyResponse.result.getProducts().getSurplusAmount());
        } catch (Exception e) {
            left = 0;
        }

        if ((int) left >= startMoney) {
            if (intValue < startMoney) {
                ViewHelper.showToast(this, "投资金额必须大于" + startMoney + "元");
                return false;
            }
        }
        if (intValue <= 0) {
            ViewHelper.showToast(this, "请输入大于0的整数");
            return false;
        }
        if (intValue % 100 != 0) {
            ViewHelper.showToast(this, "投资金额必须是一百元的整数倍");
            return false;
        }
        return true;
    }

    private SureBuyModel sureBuyResult;

    /**
     * 更新界面
     *
     * @param data
     */
    public void setData(SureBuyResponse data) {
        SureBuyModel result = data.result;
        sureBuyResult = result;
        setBarTitle(result.getTitle());
        if(result.getProducts().getType().equals("-1")){//新手
            third_title.setText(result.getInvestEndLabel());
        }else {
            third_title.setText(result.getSurplusAmountLabel());
        }
        tv_profitLabel.setText(result.getProfitLabel());
        tv_plstimeLimitLabel.setText(result.getPlstimeLimitLabel());
        account_title.setText(result.getBalanceLabel());
        tv_touzijine.setText(result.getAmountLabel());
        mCharge.setText(result.getRechargeButtonLabel());
        all_in.setText(result.getAmountButtonLabel());
        tv_redPacketLabel.setText(result.getRedPacketLabel());
        tv_couponsLabel.setText(result.getCouponsLabel());
        tv_basicProfitLabel.setText(result.getBasicProfitLabel());
        tv_totalProfitLabel.setText(result.getTotalProfitLabel());
        mBtInvest.setText(result.getButtonLabel());
        etTouZi.setHint(result.getAmountTips());
        mTvXieYi.setText("《" + result.getInvestmentAgreementName() + "》");
        tv_riskReminderName.setText("《" + result.getRiskReminderName() + "》");

        TextView productTitle = (TextView) findViewById(R.id.product_title);
        productTitle.setText(data.result.getProducts().getTitle());
        if ("1".equals(data.result.getProducts().getPlstimeLimitType())) {
            mTimeLimit.setText(data.result.getProducts().getPlstimeLimitValue() + "个月");
            investLimit = (int) Double.parseDouble(data.result.getProducts().getPlstimeLimitValue()) * 30;
        } else if ("0".equals(data.result.getProducts().getPlstimeLimitType())) {
            mTimeLimit.setText(data.result.getProducts().getPlstimeLimitValue() + "天");
            investLimit = (int) Double.parseDouble(data.result.getProducts().getPlstimeLimitValue());
        }
        if (result.isRedPacketStatus()) {//红包的显示隐藏
            mRedPackLayout.setVisibility(View.VISIBLE);
        } else {
            mRedPackLayout.setVisibility(View.GONE);
        }
        if (result.isCouponsStatus()) {//优惠劵的显示隐藏
            mCouponLayout.setVisibility(View.VISIBLE);
        } else {
            mCouponLayout.setVisibility(View.GONE);
        }
        String value;
        if (data.result.getProducts().getType().equals("-1")) {
            value = data.result.getProducts().getInvestEnd();
            findViewById(R.id.base_layout).setVisibility(View.GONE);
        } else {
            value = data.result.getProducts().getSurplusAmount();
            findViewById(R.id.base_layout).setVisibility(View.VISIBLE);
        }
        mCharge.setEnabled(true);
        findViewById(R.id.all_in).setEnabled(true);

        mBalance.setText(data.result.getUserAccount().getAvailableMoney() + "元");
        double money = Double.parseDouble(value);
        if (money < 10000) {
            mRemainingInvest.setText((int) money + "元");
        } else {
            Double surplus = ArithUtils.div2point(money, 10000, 2);
            mRemainingInvest.setText(IConstants.Formatter.rateFormat.format(surplus) + "万");
        }
        DecimalFormat df = new DecimalFormat("0.00");
        mProfitRate.setText(df.format(Double.parseDouble(data.result.getProducts().getMinProfit()) * 100) + "%");
        startMoney = (int) Double.parseDouble(data.result.getProducts().getInvestBegin());
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        alertDialog = null;
        withdrawlDialog = null;
        super.onDestroy();
    }

    @Override
    public void requestSuccess(SureBuyResponse buyResponse) {
        dismissLoadingDialog();
        mSureBuyResponse = buyResponse;
        setData(buyResponse);
    }

    @Override
    public void requestFailed(Throwable throwable) {
        dismissLoadingDialog();
        if (throwable instanceof RequestErrorThrowable) {
            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
            ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
        }
    }

    @Override
    public void showRedPack(String text) {
        if (etTouZi.getText().length() > 0) {
            mRedPac.setText(text);
        } else {
            mRedPac.setText("");
        }

    }

    @Override
    public void showCoupon(String text) {
        mExtraRate.setText(text);
    }

    @Override
    public void getEstimatedSuccess(GetProductDetailMoneyResponse getProductDetailMoneyResponse) {
        if (etTouZi.getText().toString().length() != 0) {
            DecimalFormat df = new DecimalFormat("0.00");
            mEstimateResult.setText("" + df.format(Double.parseDouble(getProductDetailMoneyResponse.result.profit)) + "元");
        } else {
            mEstimateResult.setText("" + "0.00" + "元");
        }
        try {
            float profit2 = Float.parseFloat(getProductDetailMoneyResponse.result.profit2);
            if (profit2 > 0) {
                mExtraRate.setText(Html.fromHtml("加息券收益 " + "<font color='#5b9cf8'>" + getProductDetailMoneyResponse.result.profit2 + "元</font>"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        basicProfit.setText(getProductDetailMoneyResponse.result.profit1 + "元");
    }

    @Override
    public void checkInvestMoneySuccess(RiskCheckResponse riskCheckResponse) {
        if (riskCheckResponse.code.equals("0")) {
            showInvestDialog();
        } else {
            showEvaluationDialog(riskCheckResponse.result);
        }
    }

    @Override
    public void riskDefaultSuccess(RiskDefaultResponse riskDefaultResponse) {
        if (riskDefaultResponse.code.equals("0")) {
            showInvestDialog();
        } else {
            toast(riskDefaultResponse.message);
        }
    }

    @Override
    public void investRequestSuccess(SurePayResponse surePayResponse) {
        dismissLoadingDialog();
        if (surePayResponse.errorCode.equals("0")) {
            if (withdrawlDialog != null && withdrawlDialog.isShowing()) {
                withdrawlDialog.dismiss();
            }
            touZiSuccess(surePayResponse);
        } else {
            if (surePayResponse.errorCode.equals("200")) {//余额不足 错误码200，
                if (withdrawlDialog != null && withdrawlDialog.isShowing()) {
                    withdrawlDialog.dismiss();
                }
                showNotEnoughDialog();
            } else if (surePayResponse.errorCode.equals("100")) {//输入密码错误 错误码100
                if (withdrawlDialog != null) {
                    withdrawlDialog.showPSWError(surePayResponse.errorMsg);
                }
            } else {
                if (withdrawlDialog != null && withdrawlDialog.isShowing()) {
                    withdrawlDialog.dismiss();
                }
                toast(surePayResponse.errorMsg);
            }

        }
    }

    @Override
    public void getAvailableRedAndCouponSuccess(WelfareResult result) {
        this.result = result;
        if (couId == 0) {
            if (result.availableCouponCount.equals("0")) {
                showCoupon(getString(R.string.touzi_no_coupon));
            } else {
                showCoupon("有" + result.availableCouponCount + "个可用加息券");
            }
            if (result.availableRedCount.equals("0")) {
                showRedPack(getString(R.string.touzi_no_redpack));
            } else {
                showRedPack("有" + result.availableRedCount + "个可用红包");
            }
        }
    }

    @Override
    public void getAvailableRed(WelfareResult result) {}

    @Override
    public void getAvailableCoupon(WelfareResult result) {
    }


    @Override
    public void toast(String message) {
        dismissLoadingDialog();
        ViewHelper.showToast(this, message);
    }
}
