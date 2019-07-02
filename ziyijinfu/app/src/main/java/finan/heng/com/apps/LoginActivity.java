package finan.heng.com.apps;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.mcxiaoke.bus.Bus;
import com.mcxiaoke.bus.annotation.BusReceiver;
import com.umeng.analytics.MobclickAgent;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.app.my.activity.ForgetPasswordActivity2;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.event.AccountRefreshEvent;
import finan.heng.com.apps.model.event.LoginSuccessEvent;
import finan.heng.com.apps.model.event.ShowStepViewEvent;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.widgets.EditTextWithDel;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/6 9:09
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private EditTextWithDel mEtPwd;
    private Button mBtn;
    private TextView mTvForget;
    private String phone;
    private TextInputLayout input_pwd;
//    private ImageView iv_close_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = getIntent().getStringExtra(IConstants.Extra.MOBILE);
        initView();
        init();

        EventBus.getDefault().register(this);
    }

    @Override
    protected boolean getIsTransparent() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        dismissLoadingDialog();
    }

    private void init() {
        mBtn.setOnClickListener(this);
        mTvForget.setOnClickListener(this);
        mEtPwd.addTextChangedListener(this);
//        iv_close_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEtPwd.setText("");
//                mEtPwd.setHint("请输入登录密码");
//            }
//        });
    }

    private void initView() {
        mEtPwd =  findViewById(R.id.cet_input_pwd);
        mBtn = (Button) findViewById(R.id.bt_login);
        mTvForget = (TextView) findViewById(R.id.tv_forget_pwd);
        input_pwd = findViewById(R.id.input_pwd);
//        iv_close_card = findViewById(R.id.iv_close_card);
    }

    public void onEvent(ShowStepViewEvent showStepViewEvent){
        finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this, ForgetPasswordActivity2.class));
                break;
        }
    }

    @BusReceiver
    public void StringEvent(String event) {
        if (event.equals("HENG_LoginActivity")) {
            login();
        }else if (event.equals("registerLogin")){
            finish();
        }
    }

    private void login() {
        String clientid = PushManager.getInstance().getClientid(this.getApplicationContext());
        showLoadingDialog();
        new HttpHelper().login(phone, clientid, mEtPwd.getText().toString().trim()).subscribe(new Action1<LoginResponse>() {
            @Override
            public void call(LoginResponse loginResponse) {
                if (loginResponse.errorCode.equals("0")) {
                    DataCache.instance.saveCacheData("heng", "LoginResponse", loginResponse);
                    getUserInfo();
                    Log.e("hhm", loginResponse.result.sessionId);
//                    Bus.getDefault().setDebug(true);
                    Bus.getDefault().post("LoginSuccess");
                    EventBus.getDefault().post(new LoginSuccessEvent());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(LoginActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }
    public void getUserInfo() {
        new HttpHelper().getUserInfo().subscribe(new Action1<MyUserInfo>() {
            @Override
            public void call(MyUserInfo myUserInfo) {
                DataCache.instance.saveCacheData("heng", "MyUserInfo", myUserInfo);//保存用户信息
                dismissLoadingDialog();
                EventBus.getDefault().post(new AccountRefreshEvent());
                try {
                    MobclickAgent.onProfileSignIn(myUserInfo.result.userAccount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(LoginActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEtPwd.getText().toString().trim().length() > 0) {
            mBtn.setEnabled(true);
            mBtn.setBackgroundResource(R.drawable.btn_click);
            input_pwd.setHintEnabled(true);
//            iv_close_card.setVisibility(View.VISIBLE);
        } else {
            mBtn.setEnabled(false);
            mBtn.setBackgroundResource(R.drawable.btn_unclick);
            input_pwd.setHintEnabled(false);
//            iv_close_card.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
