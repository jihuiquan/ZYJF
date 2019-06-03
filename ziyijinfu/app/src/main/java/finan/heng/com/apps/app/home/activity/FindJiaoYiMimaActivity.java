package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.CheckForgetBuyPasswordMsgCodeResponse;
import finan.heng.com.apps.model.ForgetBuyPasswordMsgCodeResponse;
import finan.heng.com.apps.model.MyUserInfo;

import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.TimeCount;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/10.
 */
public class FindJiaoYiMimaActivity extends BaseActivity implements View.OnClickListener, TextWatcher {


    private Button mBt_next;
    private TextView mTvMsgCode;
    private EditText mEtPhone, mEtMsgCode;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findjiaoyimima);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("找回交易密码");
        initView();
        init();
    }

    private void init() {
        mBt_next.setOnClickListener(this);
        mTvMsgCode.setOnClickListener(this);
        mEtPhone.addTextChangedListener(this);
        mEtMsgCode.addTextChangedListener(this);
    }

    private void initView() {
        mBt_next = (Button) findViewById(R.id.bt_next);
        mTvMsgCode = (TextView) findViewById(R.id.activity_findjiaoyimima_msg_tv);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtMsgCode = (EditText) findViewById(R.id.et_msgcode);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                check();
                break;
            case R.id.activity_findjiaoyimima_msg_tv:
                getMsgCode();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==100&&resultCode==1002) {
            setResult(10010);
            finish();
        }
    }

    private void check() {
        new HttpHelper().checkForgetBuyPasswordMsgCode(mEtMsgCode.getText().toString())
                .subscribe(new Action1<CheckForgetBuyPasswordMsgCodeResponse>() {
                    @Override
                    public void call(CheckForgetBuyPasswordMsgCodeResponse checkForgetBuyPasswordMsgCodeResponse) {
                        startActivityForResult(new Intent(FindJiaoYiMimaActivity.this, SetJiaoYiMimaActivity.class).putExtra("action","reSetPW"),100);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(FindJiaoYiMimaActivity.this, requestErrorThrowable.getMessage());
                        }
                    }
                });
    }

    public void getMsgCode() {
        new HttpHelper().forgetBuyPasswordMsgCode(mEtPhone.getText().toString())
                .subscribe(new Action1<ForgetBuyPasswordMsgCodeResponse>() {
                    @Override
                    public void call(ForgetBuyPasswordMsgCodeResponse forgetBuyPasswordMsgCodeResponse) {
                        TimeCount mTimeCount = new TimeCount(60000, 1000, mTvMsgCode, FindJiaoYiMimaActivity.this, 0);
                        mTimeCount.start();

                        MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                        if (cacheData != null){
                            TextView tel = (TextView) findViewById(R.id.tel_num);
                            String telText = cacheData.result.userAccount;
                            tel.setText(telText.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                            findViewById(R.id.tel_notice).setVisibility(View.VISIBLE);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(FindJiaoYiMimaActivity.this, requestErrorThrowable.getMessage());
                        }
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (mEtPhone.getText().toString().length() > 0 && mEtMsgCode.getText().toString().length() > 0) {
            mBt_next.setEnabled(true);
            mBt_next.setBackgroundResource(R.drawable.bg_submit_round_rect);

        } else {
            mBt_next.setEnabled(false);
            mBt_next.setBackgroundResource(R.drawable.bg_submit_gray);

        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
