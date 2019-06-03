package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dynamic.foundations.common.assist.Log;
import com.dynamic.foundations.common.utils.ArithUtils;
import com.mcxiaoke.bus.Bus;
import com.orhanobut.logger.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.app.finance.activity.ProductDetailModifyActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.model.SurePayBonusModel;
import finan.heng.com.apps.model.SurePayModel;
import finan.heng.com.apps.model.SurePayResponse;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.zhimabao.com.apps.R;

/**
 * Created by Administrator on 2017/5/3.
 */
public class TouziSuccessActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout back_btn, rl_award;
    private Button         btn;
    private LinearLayout horizontalScrollView;
    private TextView tv_award, tv_warning;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_touzisuccess);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        initView();
    }

    private TextView tv_paymentSuccessTips,reward_title,tv_paymentSuccessAmountLabel,tv_paymentSuccessPlstimeLimitLabel,tv_paymentSuccessProfitLabel,tv_label;
    private void initView() {
        tv_paymentSuccessTips = findViewById(R.id.tv_paymentSuccessTips);
        reward_title = findViewById(R.id.reward_title);
        tv_paymentSuccessAmountLabel = findViewById(R.id.tv_paymentSuccessAmountLabel);
        tv_paymentSuccessPlstimeLimitLabel = findViewById(R.id.tv_paymentSuccessPlstimeLimitLabel);
        tv_paymentSuccessProfitLabel = findViewById(R.id.tv_paymentSuccessProfitLabel);
        tv_label = findViewById(R.id.tv_label);

        back_btn = (RelativeLayout) findViewById(R.id.back_btn);
        btn = (Button) findViewById(R.id.bt_touzisuccess);
        horizontalScrollView = (LinearLayout) findViewById(R.id.scroll_view);
        rl_award = (RelativeLayout) findViewById(R.id.rl_award);
        tv_award = (TextView) findViewById(R.id.tv_award);
        tv_warning = findViewById(R.id.tv_warning);
        back_btn.setOnClickListener(this);
        btn.setOnClickListener(this);
        SurePayResponse body = (SurePayResponse) getIntent().getSerializableExtra("body");
        ArrayList<SurePayBonusModel> bonusModels = body.result.getUserBonusesList();

        Log.e("bonusModels:",bonusModels.toString());
        if (bonusModels != null && bonusModels.size() >0){
            findViewById(R.id.invest_reword).setVisibility(View.GONE);
            SpannableStringBuilder show = new SpannableStringBuilder();

            for (SurePayBonusModel model : bonusModels){
                Logger.i("award" + model.toString());

                String bonus = model.getDesc(model.getType(), model.getCdkeyType());
                String title = model.getTitle();

                SpannableString bonusString = new SpannableString(" " + bonus);
                bonusString.setSpan(new ForegroundColorSpan(Color.parseColor("#5b9cf8")), 0, bonusString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                SpannableString titleString = new SpannableString(title);
                titleString.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, titleString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                show.append(bonusString).append(titleString);
            }
            tv_warning.setVisibility(View.VISIBLE);
            rl_award.setVisibility(View.VISIBLE);
            tv_award.setText(show);
        }else {
            findViewById(R.id.invest_reword).setVisibility(View.GONE);
            rl_award.setVisibility(View.GONE);
            tv_warning.setVisibility(View.GONE);
        }
        SurePayModel result = body.result;
        setBarTitle(result.getPaymentSuccessTitle());
        tv_paymentSuccessTips.setText(result.getPaymentSuccessTips());
        reward_title.setText(result.getPaymentSuccessRewardLabel());
        tv_paymentSuccessAmountLabel.setText(result.getPaymentSuccessAmountLabel());
        tv_paymentSuccessPlstimeLimitLabel.setText(result.getPaymentSuccessPlstimeLimitLabel());
        tv_paymentSuccessProfitLabel.setText(result.getPaymentSuccessProfitLabel());
        tv_label.setText(result.getPaymentSuccessRewardLabel());

        TextView tvJine = (TextView) findViewById(R.id.tv_jine);
        TextView tvQiXian = (TextView) findViewById(R.id.tv_qixian);
        TextView tvShouYi = (TextView) findViewById(R.id.tv_zongshouyi);
        tvJine.setText(body.result.getAmount());
        if ("1".equals(body.result.getPrdtimeLimitType())) {
            tvQiXian.setText(body.result.getPlstimeLimitValue() + "个月");
        } else if ("0".equals(body.result.getPrdtimeLimitType())) {
            tvQiXian.setText(body.result.getPlstimeLimitValue() + "天");
        }
        tvShouYi.setText(body.result.getProfit());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                startActivity(new Intent(TouziSuccessActivity.this, MainActivity.class).putExtra("fromWeb","fromWeb"));//跳转到首页的第二个页面名字没起好，延用了web的跳转
                finish();
//                Bus.getDefault().post(ProductDetailModifyActivity.class.getSimpleName()+"investSuccess");

                break;
            case R.id.bt_touzisuccess:
                startActivity(new Intent(TouziSuccessActivity.this, MainActivity.class).putExtra("fromWeb","fromWeb"));
                finish();
//                Bus.getDefault().post(ProductDetailModifyActivity.class.getSimpleName()+"investSuccess");

                break;
        }
    }
}
