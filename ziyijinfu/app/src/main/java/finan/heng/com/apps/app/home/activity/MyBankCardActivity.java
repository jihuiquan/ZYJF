package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.model.GetCompanyInfoResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.StringUtils;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.BankCardInfo;
import finan.heng.com.apps.model.BankCardResponse;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/10.
 */
public class MyBankCardActivity extends BaseActivity {

    private TextView mTv_bank_name;
    private TextView mTv_bank_num;
    private ImageView mIv_bank_icon;
    private TextView tv_single_value, tv_daily_value;
    private TextView tv_phone, tv_warning;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybankcard);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("我的银行卡");
        initView();
        init();
    }

    private String url;

    private void init() {
        new HttpHelper().myBankCard().subscribe(new Action1<BankCardResponse>() {
            @Override
            public void call(BankCardResponse bankCardResponse) {
                BankCardInfo result = bankCardResponse.result;
                Logger.i("result" + result.toString());
                mTv_bank_name.setText(result.getBankName());
                String bankCode = result.getBankCode();
                String s = "";
                String pre = bankCode.substring(0, 4);
                String end = bankCode.substring(bankCode.length() - 4, bankCode.length());
//                    char[] chars = bankCode.toCharArray();
//                    for (int i = 0; i < chars.length; i++) {
//                        if (i < chars.length - 4) {
//                            s += "*";
//                        } else {
//                            s += chars[i];
//                        }
//                    }
                s = pre + " **** **** **** " + end;
                mTv_bank_num.setText(com.dynamic.foundations.common.utils.StringUtils.trimToEmpty(bankCode));
                url = result.getBankChangeRoleURL();
                if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(result.getBankSingleLimit())) {
                    double singleLimit = 0;
                    try {
                        singleLimit = Double.parseDouble(result.getBankSingleLimit());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    if (singleLimit >= 10000) {
                        tv_single_value.setText(IConstants.Formatter.moneylimitformat.format(singleLimit / 10000));
                    } else {
                        tv_single_value.setText(IConstants.Formatter.rateFormat.format(singleLimit));
                    }
                } else {
                    tv_single_value.setText("无限制");
                }
                if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(result.getBankDailyLimit())){
                    double dailyLimit = 0;
                    try {
                        dailyLimit = Double.parseDouble(result.getBankDailyLimit());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    if (dailyLimit >= 10000) {
                        tv_daily_value.setText(IConstants.Formatter.moneylimitformat.format(dailyLimit / 10000));
                    } else {
                        tv_daily_value.setText(IConstants.Formatter.rateFormat.format(dailyLimit));
                    }
                } else {
                    tv_daily_value.setText("无限制");
                }

                Glide.with(MyBankCardActivity.this).load(result.getBankImg()).into(mIv_bank_icon);

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(MyBankCardActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private void initView() {
        mTv_bank_name = (TextView) findViewById(R.id.tv_bank_name);
        mTv_bank_num = (TextView) findViewById(R.id.tv_bank_num);
        mIv_bank_icon = (ImageView) findViewById(R.id.iv_bank_icon);
        tv_single_value = findViewById(R.id.tv_single_value);
        tv_daily_value = findViewById(R.id.tv_daily_value);
        tv_phone = findViewById(R.id.tv_phone);
        tv_warning = findViewById(R.id.tv_warning);
        tv_warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyBankCardActivity.this, WebActivity.class)
                        .putExtra(IConstants.Extra.URL, url)
                        .putExtra(IConstants.Extra.TITLE, getString(R.string.menu_exchange_bank))
                );
            }
        });
        GetCompanyInfoResponse ca = DataCache.instance.getCacheData("heng", "GetCompanyInfoResponse");
        String phone = "";
        if (ca != null) {
            try {
                phone = ca.result.phone;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tv_phone.setText(com.dynamic.foundations.common.utils.StringUtils.trimToEmpty(phone));
        final String finalPhone = phone;
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPointDialog(finalPhone);
            }
        });
    }

    private void showPointDialog(final String phone) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MyBankCardActivity.this, R.style.dialog);
        View inflate = View.inflate(MyBankCardActivity.this, R.layout.loginout_dialog, null);
        normalDialog.setView(inflate);
        // 显示
        final AlertDialog alertDialog = normalDialog.create();
        alertDialog.show();
        TextView loginout_content = (TextView) inflate.findViewById(R.id.loginout_content);
        loginout_content.setText(phone);
        TextView loginout_quxiao = (TextView) inflate.findViewById(R.id.loginout_quxiao);
        loginout_quxiao.setText("取消");
        loginout_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        TextView loginout_sure = (TextView) inflate.findViewById(R.id.loginout_sure);
        loginout_sure.setText("拨打");
        loginout_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });
    }
}
