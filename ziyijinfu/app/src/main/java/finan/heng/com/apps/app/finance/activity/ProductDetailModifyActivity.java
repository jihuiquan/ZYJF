package finan.heng.com.apps.app.finance.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dynamic.foundations.common.utils.ArithUtils;
import com.dynamic.foundations.common.utils.StringUtils;
import com.mcxiaoke.bus.annotation.BusReceiver;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.DecimalFormat;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.finance.fragment.ImageDialogFragment;
import finan.heng.com.apps.app.finance.presenter.ProductDetailPresenter;
import finan.heng.com.apps.app.finance.view.IProductDetailView;
import finan.heng.com.apps.app.home.activity.TouziActivity;
import finan.heng.com.apps.app.ui.activity.CheckPhoneActivity;
import finan.heng.com.apps.app.ui.activity.ShowStepViewActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetProductDetailMoneyResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.ProductDetailModel;
import finan.heng.com.apps.model.ProductDetailResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @创建者 YDY
 * @创建时间 2017/12/29 16:48
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailModifyActivity extends BaseActivity implements View.OnClickListener, IProductDetailView {

    private TextView investRate, investLimitDay, investLeftMoney, investTotalMoney, investTotalMoneyName;
    private TextView investToday, startCalculateInterest, startGetback;
    private TextView calculateWay, getbackWay, getStartMoney;
    private Button investNow;
    private ImageView calculateInterest;
    private int id;
    private SmartRefreshLayout refreshLayout;
    private ProductDetailResponse detailResponse;
    private LinearLayout tagLayout;
    private ProductDetailPresenter presenter;
    private TextView mTitle;
    private TextView mProfitDescription;

    private TextView investDayTitle;
    private TextView leftMoneyText, tv_repayment, tv_interest, begin_name, tv_todayInvest, tv_beginInvest, tv_beginReturn,
            tv_projectDetail, tv_safety, tv_investRecord, tv_dateTips;
    private ProductDetailModel detailModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_modify3);
//        setUpToolbar();

        id = getIntent().getIntExtra("id", -1);
        initView();
        init();
        showLoadingDialog();
        presenter = new ProductDetailPresenter(this);
        presenter.getData(id);

    }

    private void initView() {
        investDayTitle = (TextView) findViewById(R.id.invest_time_limit_text);
        leftMoneyText = findViewById(R.id.invest_left_money_text);
        tv_repayment = findViewById(R.id.tv_repayment);
        tv_interest = findViewById(R.id.tv_interest);
        begin_name = findViewById(R.id.begin_name);
        tv_todayInvest = findViewById(R.id.tv_todayInvest);
        tv_beginInvest = findViewById(R.id.tv_beginInvest);
        tv_beginReturn = findViewById(R.id.tv_beginReturn);

        tv_projectDetail = findViewById(R.id.tv_projectDetail);
        tv_safety = findViewById(R.id.tv_safety);
        tv_investRecord = findViewById(R.id.tv_investRecord);
        tv_dateTips = findViewById(R.id.tv_dateTips);

        mTitle = findViewById(R.id.detail_title);
        try {
            View view = findViewById(R.id.top_bar);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.height = DeviceUtils.getStatusBarHeight(getApplicationContext());
            view.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        investRate = findViewById(R.id.invest_rate);
        investLimitDay = findViewById(R.id.invest_limit_day);
        investLeftMoney = findViewById(R.id.invest_left_money);
        investTotalMoney = findViewById(R.id.invest_total_money);
        investTotalMoneyName = findViewById(R.id.invest_total_money_name);
        investToday = findViewById(R.id.tv_today);
        startCalculateInterest = findViewById(R.id.tv_take_effect_date);
        startGetback = findViewById(R.id.tv_return_money_date);

//        rateTag = findViewById(R.id.rate_tag);

        mProfitDescription = findViewById(R.id.detail_rate_explain);

        calculateWay = findViewById(R.id.calculate_way);
        getbackWay = findViewById(R.id.get_back_way);
        getStartMoney = findViewById(R.id.get_start_money);
//        mViewpager =  findViewById(R.id.view_pager);
        investNow = findViewById(R.id.invest_now);//立即投资
        calculateInterest = findViewById(R.id.calculate_get);
//        mTabLayout =  findViewById(R.id.tabLayout);

        refreshLayout = findViewById(R.id.refresh_layout);
        tagLayout = findViewById(R.id.activity_tag_layout);


    }

    @BusReceiver
    public void StringEvent(String event) {

        if (event.equals(ProductDetailModifyActivity.class.getSimpleName() + "investSuccess")) {
            startActivity(new Intent(this, InvestRecordActivity.class).putExtra("id", id));
        }
    }

    private void init() {
        findViewById(R.id.detail_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        investNow.setOnClickListener(this);
        calculateInterest.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.getData(id);
            }
        });

    }

    @Override
    public void requestSuccess(ProductDetailResponse productDetailResponse) {

        detailResponse = productDetailResponse;
        try {
            dismissLoadingDialog();
            DataCache.instance.saveCacheData("heng", "ProductDetailResponse" + id, productDetailResponse);
            setData(productDetailResponse);
            refreshLayout.finishRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void requestFailed(Throwable throwable) {

        try {
            dismissLoadingDialog();
            ProductDetailResponse cacheData = DataCache.instance.getCacheData("heng", "ProductDetailResponse" + id);

            if (cacheData != null) {
                setData(cacheData);
            }
            if (throwable instanceof RequestErrorThrowable) {
                RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                ViewHelper.showToast(ProductDetailModifyActivity.this, requestErrorThrowable.getMessage());
            }
            refreshLayout.finishRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SpannableStringBuilder getRateText(String mainProfit, String secondProfit) {
        SpannableStringBuilder rateText = new SpannableStringBuilder();
        double baseRate;
        try {
            baseRate = ArithUtils.round(Double.parseDouble(mainProfit) * 100, 2);
        } catch (Exception e) {
            e.printStackTrace();
            baseRate = 0;
        }
        double rate;
        try {
            rate = Double.parseDouble(secondProfit);
        } catch (Exception e) {
            e.printStackTrace();
            rate = 0;
        }
        SpannableString addRates;
        if (rate > 0) {
            double addRate = ArithUtils.round(rate * 100, 2);
            addRates = new SpannableString("% + " + IConstants.Formatter.rateFormat.format(addRate) + "%");
        } else {
            addRates = new SpannableString("%");
        }
        addRates.setSpan(new RelativeSizeSpan(1.0f), 0, addRates.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        rateText.append(String.valueOf(IConstants.Formatter.rateFormat.format(baseRate))).append(addRates);
        return rateText;
    }

    private SpannableStringBuilder getDiffSizeText(String main, String second) {
        SpannableStringBuilder rateText = new SpannableStringBuilder();
        SpannableString addRates;
        addRates = new SpannableString(second);
        addRates.setSpan(new RelativeSizeSpan(0.7f), 0, addRates.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        rateText.append(main).append(addRates);
        return rateText;
    }

    private void setData(ProductDetailResponse response) {
        detailModel = response.result;
        detailModel.getProducts();
        mProfitDescription.setText(detailModel.getProfitLabel());
        tv_repayment.setText(detailModel.getRepaymentTypeLabel2());
        tv_interest.setText(detailModel.getCalInterestTypeLabel2());

        final ProductDetailModel.ProductsBean products = detailModel.getProducts();
        mTitle.setText(products.getTitle());

        tv_todayInvest.setText(detailModel.getInvestDateLabel2());
        tv_beginInvest.setText(detailModel.getBeginDateLabel2());
        tv_beginReturn.setText(detailModel.getEndDateLabel2());

        tv_projectDetail.setText(detailModel.getProductsDetailsLabel());
        tv_safety.setText(detailModel.getProductsReportLabel());
        tv_investRecord.setText(detailModel.getOrderListLabel());
        tv_dateTips.setText(detailModel.getDateTips());

        if (detailModel.isCalculatorStatus()) {
            calculateInterest.setVisibility(VISIBLE);
        } else {
            calculateInterest.setVisibility(GONE);
        }

        if (products.getType().equals("-1")) {//新手
            leftMoneyText.setText(detailModel.getPlstimeLimitLabel());
            investTotalMoneyName.setText(detailModel.getInvestEndLabel());
            investDayTitle.setText(detailModel.getInvestBeginLabel());

            investRate.setText(getRateText(response.result.getProducts().getMinProfit(), "-1"));

            begin_name.setText(detailModel.getNewRuleLabel2());
            getStartMoney.setText(detailModel.getNewRuleLabel());
            try {
                int beginMoney = (int) Double.parseDouble(products.getInvestBegin());
                investLimitDay.setText(beginMoney + "元");
            } catch (Exception e) {
                investLimitDay.setText(products.getInvestBegin() + "元");
            }

            if ("1".equals(products.getPlstimeLimitType())) {
                investLeftMoney.setText(products.getPlstimeLimitValue() + "个月");
            } else if ("0".equals(products.getPlstimeLimitType())) {
                investLeftMoney.setText(products.getPlstimeLimitValue() + "天");
            }
            findViewById(R.id.get_start_layout).setVisibility(VISIBLE);
            findViewById(R.id.red_pack_layout).setVisibility(GONE);
        } else {//非新手
            begin_name.setText(detailModel.getCashRateLabel2());
            leftMoneyText.setText(detailModel.getSurplusAmountLabel());
            investTotalMoneyName.setText(detailModel.getProductsScaleLabel());
            investDayTitle.setText(detailModel.getPlstimeLimitLabel());
            float floatRate;

            try {
                floatRate = Float.parseFloat(products.getProfitFloat());
            } catch (Exception e) {
                floatRate = 0;
                e.printStackTrace();
            }
            boolean isShowRate = products.getCashRateShow();
            if (isShowRate) {
                investRate.setText(getRateText(products.getProfit(), products.getCashRateProfit()));
            } else {
                investRate.setText(getRateText(products.getProfit(), products.getProfitFloat()));
            }
            getStartMoney.setText(detailModel.cashRateLabel);
            getStartMoney.setTextColor(Color.parseColor("#333333"));
            if ("1".equals(products.getPlstimeLimitType())) {
                investLimitDay.setText(products.getPlstimeLimitValue() + "个月");
            } else if ("0".equals(products.getPlstimeLimitType())) {
                investLimitDay.setText(products.getPlstimeLimitValue() + "天");
            }
            double leftMoney = Double.parseDouble(products.getSurplusAmount());
            if (leftMoney < 10000) {
                investLeftMoney.setText((int) leftMoney + "元");
            } else {
                Double surplus = ArithUtils.div2point(leftMoney, 10000, 2);
                investLeftMoney.setText(IConstants.Formatter.rateFormat.format(surplus) + "万");
            }
            findViewById(R.id.get_start_layout).setVisibility(VISIBLE);
            findViewById(R.id.red_pack_layout).setVisibility(GONE);
            TextView redPackText = findViewById(R.id.red_pack_text);
            redPackText.setText(detailModel.cashRateLabel);
        }

        investToday.setText(detailModel.getInvestDateLabel());
        startCalculateInterest.setText(detailModel.getBeginDateLabel());
        startGetback.setText(detailModel.getEndDateLabel());
        investNow.setText(detailModel.getButtonLabel());
        switch (products.getStatus()) {// //-41.复审未通过；-11.初审未通过；10.待初审；11.初审通过；20.预告中；30.募集中；40.待复审；41.复审通过；50.还款中（计息中）；60.已完成；
            case "20":
                investNow.setEnabled(false);
                investNow.setBackgroundResource(R.drawable.detail_bottom_gray_gradient);
                break;
            case "30":
                investNow.setEnabled(true);
                investNow.setBackgroundResource(R.drawable.detail_bottom_red_gradient);
                break;
            case "40":
            case "41":
            case "50":
            case "60":
                investNow.setEnabled(false);
                investNow.setBackgroundResource(R.drawable.detail_bottom_gray_gradient);
                break;
        }
        double v1 = 0;
        try {
            if (products.getType().equals("-1")) {//新手
                v1 = Double.parseDouble(products.getInvestEnd());//项目总额
            } else {
                v1 = Double.parseDouble(products.getProductsScale());//投资限额
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (v1 < 10000) {
            investTotalMoney.setText((int) v1 + "元");
        } else {
            double v = v1 / 10000;
            investTotalMoney.setText(Double.toString(v) + "万");
        }
        calculateWay.setText(detailModel.getCalInterestTypeLabel());
        getbackWay.setText(detailModel.getRepaymentTypeLabel());
        tagLayout.removeAllViews();
        String tags = response.result.getProducts().getTags();
        if (tags.length() > 0) {
            String[] tag = tags.split(",");
            for (int i = 0; i < tag.length; i++) {
                TextView textView1 = new TextView(this);
                textView1.setBackgroundResource(R.drawable.home_fragment_activity_tv_bg_white);
                textView1.setTextSize(12);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (i != tag.length - 1) {
                    layoutParams.setMargins(0, 0, 10, 0);
                }
                textView1.setLayoutParams(layoutParams);
                textView1.setPadding(24, 0, 24, 0);
                textView1.setText(tag[i]);
                textView1.setTextColor(getResources().getColor(R.color.bg_home_notice));
                if (StringUtils.isNotEmpty(tag[i])) {
                    tagLayout.addView(textView1);
                }
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_detail:
                if(detailModel==null){
                    return;
                }
                WebActivity.show(URLHelper.getInstance().URL + "products/productsDetails?id=" + id,detailModel.getProductsDetailsLabel(),this);
                break;
            case R.id.safe_guaranty:
                if(detailModel==null){
                    return;
                }
                WebActivity.show(URLHelper.getInstance().URL + "products/productsReport?id=" + id,detailModel.getProductsReportLabel(),this);
                break;
            case R.id.invest_record:
                if(detailModel==null){
                    return;
                }
                InvestRecordActivity.show(id,detailModel.getOrderListLabel(),this);
                break;
            case R.id.invest_now://投资
                MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                if (cacheData == null) {
                    Intent intent = new Intent(ProductDetailModifyActivity.this, CheckPhoneActivity.class);
                    startActivity(intent);
                } else if ("0".equals(cacheData.result.realnameStatus) || TextUtils.isEmpty(cacheData.result.userPaypassword)) {
//                    startActivity(new Intent(ProductDetailModifyActivity.this, ShiMingRenZhengActivity.class));
//                } else if (TextUtils.isEmpty(cacheData.result.userPaypassword)) {
//                    startActivity(new Intent(ProductDetailModifyActivity.this, SetJiaoYiMimaActivity.class));
                    startActivity(new Intent(ProductDetailModifyActivity.this, ShowStepViewActivity.class)
                            .putExtra(IConstants.Extra.EXTRA_SHOWSTEP_LOGO, R.mipmap.ic_showstep_logoother)
                    );
                } else {
                    Intent intent = new Intent(ProductDetailModifyActivity.this, TouziActivity.class);
                    intent.putExtra("id", id);
                    startActivityForResult(intent, 10020);
                }
                break;
            case R.id.calculate_get://计算预估收益
                View view = View.inflate(this, R.layout.activity_product_detail_dialog, null);
                if (detailModel == null) {
                    return;
                }
                TextView rate = (TextView) view.findViewById(R.id.rate);
                TextView tv_calculatorTitle = (TextView) view.findViewById(R.id.tv_calculatorTitle);//计算器标题
                TextView tv_calculatorProfitLabel = (TextView) view.findViewById(R.id.tv_calculatorProfitLabel);//计算器年化收益
                tv_calculatorTitle.setText(detailModel.getCalculatorTitle());
                tv_calculatorProfitLabel.setText(detailModel.getCalculatorProfitLabel());
                TextView timeTitle = (TextView) view.findViewById(R.id.time_title);//计算器投资期限
                timeTitle.setText(detailModel.getCalculatorPlstimeLimitLabel());
                TextView tv_calculatorResultLabel = (TextView) view.findViewById(R.id.tv_calculatorResultLabel);//计算器投资收益
                tv_calculatorResultLabel.setText(detailModel.getCalculatorResultLabel());

                TextView timeValue = (TextView) view.findViewById(R.id.time_value);
                ImageView closeImage = (ImageView) view.findViewById(R.id.close);
                final ImageView clearImage = (ImageView) view.findViewById(R.id.clear_input);
                final EditText inputText = (EditText) view.findViewById(R.id.input_value);
                inputText.setHint(detailModel.getCalculatorAmountTips());

                final TextView estimateValue = (TextView) view.findViewById(R.id.estimate_value);
                Button calculate = (Button) view.findViewById(R.id.calculate);
                calculate.setText(detailModel.getCalculatorButtonLabel());
                Double d;
                try {
                    d = ArithUtils.round(Double.parseDouble(detailResponse.result.getProducts().getMinProfit()) * 100, 2);
                    rate.setText(d + "%");
                } catch (Exception e) {
                    e.printStackTrace();
                    rate.setText("0%");
                }
                if ("1".equals(detailResponse.result.getProducts().getPlstimeLimitType())) {
                    timeValue.setText(detailResponse.result.getProducts().getPlstimeLimitValue() + "个月");

                } else if ("0".equals(detailResponse.result.getProducts().getPlstimeLimitType())) {
                    timeValue.setText(detailResponse.result.getProducts().getPlstimeLimitValue() + "天");
                }
                calculate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String value = inputText.getText().toString();
                        if (!TextUtils.isEmpty(value)) {
                            new HttpHelper().getProductDetailMoney(id, Integer.parseInt(value)).subscribe(new Action1<GetProductDetailMoneyResponse>() {
                                @Override
                                public void call(GetProductDetailMoneyResponse getProductDetailMoneyResponse) {
                                    try {
                                        DecimalFormat df = new DecimalFormat("0.00");
                                        estimateValue.setText(df.format(Double.parseDouble(getProductDetailMoneyResponse.result.profit)) + "");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    if (throwable instanceof RequestErrorThrowable) {
                                        RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;

                                        ViewHelper.showToast(getApplicationContext(), requestErrorThrowable.getMessage());

                                    }
                                }
                            });
                        }
                    }
                });
                clearImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputText.setText("");//清空输入框
                    }
                });
                inputText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() > 0) {
                            clearImage.setVisibility(VISIBLE);
                        } else {
                            clearImage.setVisibility(GONE);
                        }
                        estimateValue.setText("0.00");//变动时清空预估
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                final AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.dialog).setView(view).create();
                alertDialog.show();

                closeImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10020 && resultCode == 10021) {
            finish();
        }
    }

    public void showImage(String url) {
        ImageDialogFragment dialogFragment = new ImageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(), "tag");
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        dismissLoadingDialog();
        super.onDestroy();
    }
}
