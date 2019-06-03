package finan.heng.com.apps.app.my.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.ui.activity.WithDrawResultActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.zhimabao.com.apps.R;

/**
 *
 */

public class RechargeResultActivity extends BaseActivity {
    TextView mTipTwo;
    TextView mRechargeAmount;
    TextView mTipOne;
    TextView mBackAccount;
    TextView mInvestNow;
    TextView mTelephone;
    ImageView mResultImage;
    RelativeLayout mSuccessLayout;
    String mPhoneNumber;
    private final String SUCCESS = "0";
    private final String FAILED = "1";
    private final String HANDLLING = "100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_result);
        setUpToolbar();
//        getSupportActionBar().setTitle("提现结果");
        setBarTitle("充值");
        initViews();
        String msg = getIntent().getStringExtra("msg");
        String code = getIntent().getStringExtra("code");
        String msg2 = getIntent().getStringExtra("msg2");
        String amount = getIntent().getStringExtra("amount");
        mPhoneNumber = getIntent().getStringExtra("phone");
        try {
            mTipOne.setText(msg);
            mTipTwo.setText(msg2);
            mRechargeAmount.setText(amount + "元");
            switch (code) {
                case SUCCESS://成功
                    mTipTwo.setVisibility(View.GONE);
                    mSuccessLayout.setVisibility(View.VISIBLE);
                    mInvestNow.setVisibility(View.VISIBLE);
                    mBackAccount.setVisibility(View.VISIBLE);
                    mResultImage.setImageResource(R.drawable.recharge_success);
                    break;
                case FAILED://失败
                    mTipTwo.setVisibility(View.VISIBLE);
                    mSuccessLayout.setVisibility(View.GONE);
                    mInvestNow.setVisibility(View.GONE);
                    mBackAccount.setVisibility(View.GONE);
                    mTelephone.setVisibility(View.VISIBLE);
                    mTelephone.setText("客服电话:" + mPhoneNumber);
                    mResultImage.setImageResource(R.drawable.recharge_fail);
                    break;
                case HANDLLING:
                    mTipTwo.setVisibility(View.VISIBLE);
                    mSuccessLayout.setVisibility(View.GONE);
                    mInvestNow.setVisibility(View.GONE);
                    mBackAccount.setVisibility(View.VISIBLE);
                    mBackAccount.setBackgroundResource(R.drawable.btn_click);
                    mBackAccount.setTextColor(Color.WHITE);
                    mResultImage.setImageResource(R.drawable.recharge_handlling);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void initViews() {
        mTipOne = findViewById(R.id.tip_one);
        mTipTwo = findViewById(R.id.tip_two);
        mRechargeAmount = findViewById(R.id.recharge_amount);
        mBackAccount = findViewById(R.id.back_to_account);
        mInvestNow = findViewById(R.id.invest_now);
        mSuccessLayout = findViewById(R.id.success_layout);
        mTelephone = findViewById(R.id.telephone);
        mResultImage = findViewById(R.id.result_img);
        mInvestNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechargeResultActivity.this, MainActivity.class);
                intent.putExtra("fromWeb", "fromWeb");
                startActivity(intent);
                finish();
            }
        });
        mBackAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechargeResultActivity.this, MainActivity.class);
                intent.putExtra("fromWeb", "gotoaccount");
                startActivity(intent);
                finish();
            }
        });
        mTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mPhoneNumber)) {
                    showPhoneDialog(mPhoneNumber);
                }
            }
        });

    }

    private void showPhoneDialog(final String phone) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(RechargeResultActivity.this, R.style.dialog);
        View inflate = View.inflate(RechargeResultActivity.this, R.layout.loginout_dialog, null);
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
