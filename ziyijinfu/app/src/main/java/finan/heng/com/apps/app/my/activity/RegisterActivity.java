package finan.heng.com.apps.app.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.igexin.sdk.PushManager;
import com.mcxiaoke.bus.Bus;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.http.InterfaceService;
import finan.heng.com.apps.model.GetCompanyInfoResponse;
import finan.heng.com.apps.model.GetGenreListModel;
import finan.heng.com.apps.model.GetGenreListResponse;
import finan.heng.com.apps.model.GetMessageCodeResponse;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.model.RegisterResponse;
import finan.heng.com.apps.model.event.RegisterSuccessEvent;
import finan.heng.com.apps.model.event.ShowStepViewEvent;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.StringUtils;
import finan.heng.com.apps.utils.TimeCount;
import finan.heng.com.apps.widgets.EditTextWithDel;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/6 9:20
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener {

    private EditText mEtPhone, mEtPicCode, mEtCode,  mEtPwdTwo, mEtTuiJian;
    private EditTextWithDel mEtPwdOne;
    private Button mBtn;
    private ImageView mIvPic, iv_eye_open;
    private TextView mTvGetMsgCode;
    private TimeCount mTimeCount;
    private CheckBox mCheckBox;
    private TextView mTv_xieyi, tv_phone;
    String phone = "";
    private TextInputLayout til_verify_pic, ll_verify, til_pwd, til_invite;
    private View line1, line2, line3, line4;
    private TextView tv_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        setUpToolbar();
//        getSupportActionBar().setTitle("");
        String mobile = getIntent().getStringExtra(IConstants.Extra.MOBILE);
//        setBarTitle("注册");
        initView();
        initData();
        init();
        mEtPhone.setText(mobile);
    }

    /**
     * 获取公司信息
     */
    private void initData() {
        new HttpHelper().getCompanyInfo().subscribe(new Action1<GetCompanyInfoResponse>() {
            @Override
            public void call(GetCompanyInfoResponse response) {
                String phone = response.result.phone;
                tv_mobile.setText(phone);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
            }
        });
    }

    private void init() {
        mIvPic.setOnClickListener(this);
        mBtn.setOnClickListener(this);
        mTvGetMsgCode.setOnClickListener(this);
        mEtPicCode.addTextChangedListener(this);
        mEtPhone.addTextChangedListener(this);
        mEtCode.addTextChangedListener(this);
        mEtPwdOne.addTextChangedListener(this);
        mEtPwdTwo.addTextChangedListener(this);
        mEtTuiJian.addTextChangedListener(this);
        mTv_xieyi.setOnClickListener(this);
        mCheckBox.setOnCheckedChangeListener(this);
        GetCompanyInfoResponse ca = DataCache.instance.getCacheData("heng", "GetCompanyInfoResponse");

        if (ca != null) {
            try {
                phone = ca.result.phone;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = tv_phone.getText().toString();
                if(TextUtils.isEmpty(p)){
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + p));
                startActivity(intent);
            }
        });
//        tv_phone.setText(phone);
        iv_eye_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    //设置EditText文本为可见的
                    mEtPwdOne.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    iv_eye_open.setImageResource(R.drawable.ic_tenderpwd_open);
                } else {
                    //设置EditText文本为隐藏的
                    mEtPwdOne.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    iv_eye_open.setImageResource(R.drawable.ic_tenderpwd_close);
                }
                mEtPwdOne.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = mEtPwdOne.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
                isShow = !isShow;
            }
        });

//        tv_mobile.setText(phone);
        tv_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = tv_mobile.getText().toString();
                if(TextUtils.isEmpty(p)){
                    return;
                }
                showPointDialog(p);
            }
        });
    }
    private void showPointDialog(final String phone) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(RegisterActivity.this, R.style.dialog);
        View inflate = View.inflate(RegisterActivity.this, R.layout.loginout_dialog, null);
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
    private boolean isShow = true;

    private void initView() {
        mBtn = (Button) findViewById(R.id.bt_submit);//提交按钮
        mTv_xieyi = (TextView) findViewById(R.id.tv_xieyi);
        mEtPhone = (EditText) findViewById(R.id.cet_input_account);//手机号码
        mEtPicCode = (EditText) findViewById(R.id.cet_input_tupian);//图片验证码
        mIvPic = (ImageView) findViewById(R.id.bt_iv_yangzhen);//图片
        mEtCode = (EditText) findViewById(R.id.cet_input_yanzhenma);//短信验证码
        mTvGetMsgCode = (TextView) findViewById(R.id.btr_getyanzhengma);//获取验证码
        mEtPwdOne =  findViewById(R.id.cet_input_password);//密码输入框
        mEtPwdTwo =  findViewById(R.id.cet_isok_password);//密码输入框
        mCheckBox =  findViewById(R.id.cb_agreed);//同意协议
        mEtTuiJian = (EditText) findViewById(R.id.cet_tuijian_code);//推荐码框
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        iv_eye_open = findViewById(R.id.iv_eye_open);
//        private TextInputLayout til_verify_pic, ll_verify, til_pwd, til_invite;
//        private View line1, line2, line3, line4;
        til_verify_pic = findViewById(R.id.til_verify_pic);
        ll_verify = findViewById(R.id.ll_verify);
        til_pwd = findViewById(R.id.til_pwd);
        til_invite = findViewById(R.id.til_invite);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        tv_mobile = findViewById(R.id.tv_mobile);

        Log.i("imgurl",URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE + "?" + System.currentTimeMillis());
        Glide.with(this).load(URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE + "?" + System.currentTimeMillis()).into(mIvPic);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_iv_yangzhen:
                Glide.with(this).load(URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE + "?" + System.currentTimeMillis()).into(mIvPic);
                break;
            case R.id.tv_xieyi:
                Intent intent = new Intent(RegisterActivity.this, WebActivity.class);
                intent.putExtra("url", URLHelper.getInstance().URL + "/protocol/register");
                intent.putExtra("title", "用户注册协议");
                startActivity(intent);
                break;
            case R.id.bt_submit://提交

                if (!StringUtils.isPhone(mEtPhone.getText().toString())) {
                    ViewHelper.showToast(RegisterActivity.this, "请输入正确的电话号码");
                    return;
                }
                if (mEtCode.getText().toString().length() == 0) {
                    ViewHelper.showToast(RegisterActivity.this, "请输入短信验证码");
                    return;
                }
//                if (!mEtPwdOne.getText().toString().equals(mEtPwdTwo.getText().toString())) {
//                    ViewHelper.showToast(RegisterActivity.this, "两次密码输入不一致");
//                    return;
//                }
                if (!mCheckBox.isChecked()) {
                    ViewHelper.showToast(RegisterActivity.this, "请同意签署协议");
                    return;
                }
                doRegister();
                break;
            case R.id.btr_getyanzhengma://获取短信验证码
                if (!StringUtils.isPhone(mEtPhone.getText().toString())) {
                    ViewHelper.showToast(RegisterActivity.this, "请输入正确的电话号码");
                    return;
                }
                if (TextUtils.isEmpty(mEtPicCode.getText().toString())) {
                    ViewHelper.showToast(RegisterActivity.this, "请输入图形验证码");
                    return;
                }
                getMsgCode();
                break;
        }
    }

    private void doRegister() {
        HashMap params = new HashMap();

        params.put("mobile",mEtPhone.getText().toString());
        params.put("passwd",mEtPwdOne.getText().toString());
        params.put("mobileCode",mEtCode.getText().toString());
        params.put("signCode",mEtTuiJian.getText().toString());
//        params.put("source","21");//21表示是Android端

//        params.put("channel", SystemUtils.getMetaData(getApplicationContext(), IConstants.Key.UMENG_META_DATA_NAME));
        showLoadingDialog();
        new HttpHelper().register(params).subscribe(new Action1<RegisterResponse>() {
            @Override
            public void call(RegisterResponse registerResponse) {
                try {
                    if (registerResponse.errorCode.equals("0")) {
                        ViewHelper.showToast(RegisterActivity.this, "注册成功");
                        login();
                    }
                }catch (Exception e){
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
                        ViewHelper.showToast(RegisterActivity.this, requestErrorThrowable.getMessage());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void login() {
        String clientid = PushManager.getInstance().getClientid(this.getApplicationContext());
        new HttpHelper().login(mEtPhone.getText().toString(), clientid, mEtPwdOne.getText().toString()).subscribe(new Action1<LoginResponse>() {
            @Override
            public void call(LoginResponse loginResponse) {
//                LoadingFragment loadingFragment = findLoadingFragment();
//                if (loadingFragment != null) {
//                    loadingFragment.removeSelf(getFragmentManager());
//                }
                if (loginResponse.errorCode.equals("0")) {
                    DataCache.instance.saveCacheData("heng", "LoginResponse", loginResponse);

                    Log.e("hhm", loginResponse.result.sessionId);
                    Bus.getDefault().post("LoginSuccess");
                    Bus.getDefault().post("registerLogin");
                    EventBus.getDefault().post(new ShowStepViewEvent());
                    EventBus.getDefault().post(new RegisterSuccessEvent());
                    finish();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
//                LoadingFragment loadingFragment = findLoadingFragment();
//                if (loadingFragment != null) {
//                    loadingFragment.removeSelf(getFragmentManager());
//                }

                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(RegisterActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEtPicCode.getText().toString().length() >= 1){
            til_verify_pic.setHintEnabled(true);
            til_verify_pic.setHint("请输入图片验证码");
            line1.setBackgroundResource(R.color.white);
        } else {
            til_verify_pic.setHintEnabled(false);
            line1.setBackgroundResource(R.color.bg_line);
        }

        if ( mEtCode.getText().toString().length() >= 1){
            ll_verify.setHintEnabled(true);
            ll_verify.setHint("手机验证码");
            line2.setBackgroundResource(R.color.white);
        } else {
            line2.setBackgroundResource(R.color.bg_line);
            ll_verify.setHintEnabled(false);
        }

        if (mEtPwdOne.getText().toString().length() >= 1){
            til_pwd.setHintEnabled(true);
            til_pwd.setHint("请设置8-16位数字字母组合密码");
            line3.setBackgroundResource(R.color.white);
        } else {
            til_pwd.setHintEnabled(false);
            line3.setBackgroundResource(R.color.bg_line);
        }

        if (mEtTuiJian.getText().toString().trim().length() >= 1){
            til_invite.setHintEnabled(true);
            til_invite.setHint("邀请码");
            line4.setBackgroundResource(R.color.white);
        } else {
            til_invite.setHintEnabled(false);
            line4.setBackgroundResource(R.color.bg_line);
        }

        if (mEtPicCode.getText().toString().length() > 0 && mEtPhone.getText().toString().length() > 0 && mEtCode.getText().toString().length() > 0 && mEtPwdOne.getText().toString().length() > 0  && mCheckBox.isChecked()) {
            mBtn.setBackgroundResource(R.drawable.btn_click);
            mBtn.setEnabled(true);
        } else {
            mBtn.setBackgroundResource(R.drawable.btn_unclick_a);
            mBtn.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mEtPicCode.getText().toString().length() > 0 && mEtPhone.getText().toString().length() > 0 && mEtCode.getText().toString().length() > 0 && mEtPwdOne.getText().toString().length() > 0 && mCheckBox.isChecked()) {
            mBtn.setBackgroundResource(R.drawable.btn_click);
            mBtn.setEnabled(true);
        } else {
            mBtn.setBackgroundResource(R.drawable.btn_unclick_a);
            mBtn.setEnabled(false);
        }
    }

    public void getMsgCode() {
        new HttpHelper().getMessageCode(mEtPhone.getText().toString(), mEtPicCode.getText().toString()).subscribe(new Action1<GetMessageCodeResponse>() {
            @Override
            public void call(GetMessageCodeResponse getMessageCodeResponse) {
                mTimeCount = new TimeCount(60000, 1000, mTvGetMsgCode, RegisterActivity.this, 0);
                mTimeCount.start();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Glide.with(RegisterActivity.this).load(URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE + "?" + System.currentTimeMillis()).into(mIvPic);
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(RegisterActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

}
