package finan.heng.com.apps.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.util.AbSharedUtil;
import com.dynamic.foundations.common.assist.Check;
import com.orhanobut.logger.Logger;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.LoginActivity;
import finan.heng.com.apps.app.home.activity.TouziActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiOrTiXianActivity;
import finan.heng.com.apps.app.my.activity.ForgetPasswordActivity2;
import finan.heng.com.apps.app.my.activity.RegisterActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.http.RetrofitClient;
import finan.heng.com.apps.model.CheckMobileResponse;
import finan.heng.com.apps.model.UpdateInfoRespond;
import finan.heng.com.apps.model.event.LoginSuccessEvent;
import finan.heng.com.apps.model.event.RegisterSuccessEvent;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.heng.com.apps.utils.StringUtils;
import finan.heng.com.apps.widgets.EditTextWithDel;
import finan.zhimabao.com.apps.R;
import retrofit2.Retrofit;
import rx.functions.Action1;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class CheckPhoneActivity extends BaseActivity {
    private Button btn_commit;
    private EditTextWithDel et_phone;
    private TextInputLayout input_phone;
    private View line;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkphone);
        EventBus.getDefault().register(this);
        if (getIntent().getStringExtra("fromServer") != null){

//            ViewHelper.showToast(this,"您的账号在别处登录，请重新登录");
        }



        et_phone = findViewById(R.id.et_phone);
        btn_commit = findViewById(R.id.btn_commit);
        input_phone = findViewById(R.id.input_phone);
        line = findViewById(R.id.line);
//        iv_close_card = findViewById(R.id.iv_close_card);
//        iv_close_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                et_phone.setText("");
//                et_phone.setHint("请输入您的手机号码");
//            }
//        });
        if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(AbSharedUtil.getString(CheckPhoneActivity.this, "login_mobile"))){
            et_phone.setText(AbSharedUtil.getString(CheckPhoneActivity.this, "login_mobile"));
            btn_commit.setEnabled(true);
            btn_commit.setBackgroundResource(R.drawable.btn_click);
            input_phone.setHintEnabled(true);
            input_phone.setHint("请输入您的手机号码");
            line.setBackgroundResource(R.color.white);
//            iv_close_card.setVisibility(View.VISIBLE);
        }
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_phone.getText().toString().length() == 11) {
                    btn_commit.setEnabled(true);
                    btn_commit.setBackgroundResource(R.drawable.btn_click);
                } else {
                    btn_commit.setEnabled(false);
                    btn_commit.setBackgroundResource(R.drawable.btn_unclick_a);
                }
                if (et_phone.getText().toString().trim().length() >=1){
                    input_phone.setHintEnabled(true);
                    input_phone.setHint("请输入您的手机号码");
                    line.setBackgroundResource(R.color.white);
//                    iv_close_card.setVisibility(View.VISIBLE);
                } else {
                    input_phone.setHintEnabled(false);
                    line.setBackgroundResource(R.color.bg_line);
//                    iv_close_card.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpHelper().checkMobile(et_phone.getText().toString().trim()).subscribe(new Action1<CheckMobileResponse>() {
                    @Override
                    public void call(CheckMobileResponse checkMobileResponse) {
                        Logger.i("updateinfo" + checkMobileResponse);
                        AbSharedUtil.putString(CheckPhoneActivity.this, "login_mobile", et_phone.getText().toString().trim());

                        if (checkMobileResponse.isSuccess()) {
                            startActivity(new Intent(CheckPhoneActivity.this, LoginActivity.class)
                                    .putExtra(IConstants.Extra.MOBILE, et_phone.getText().toString().trim())
                            );
                        }else if(checkMobileResponse.needReSetPwd()){
                            showResetDialog();
                        }else {
                            startActivity(new Intent(CheckPhoneActivity.this, RegisterActivity.class)
                                    .putExtra(IConstants.Extra.MOBILE, et_phone.getText().toString().trim())
                            );
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            toast(requestErrorThrowable.getMessage());
                        }
                    }
                });
//                startActivity(new Intent(CheckPhoneActivity.this, LoginActivity.class)
//                        .putExtra(IConstants.Extra.MOBILE, et_phone.getText().toString().trim())
//                );

            }
        });
    }
    private void showResetDialog() {
        View view = View.inflate(this, R.layout.activity_home_touzi_dialog_not_enough, null);
        Button button =  view.findViewById(R.id.goto_charge);
        ImageView close =  view.findViewById(R.id.dialog_close);
        TextView textView = view.findViewById(R.id.content);
        textView.setText(getString(R.string.resetPwd));
        button.setText("确定");
        close.setVisibility(View.GONE);
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.dialog).setCancelable(false).setView(view).create();
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                startActivity(new Intent(TouziActivity.this, ChongZhiOrTiXianActivity.class).putExtra("type", 1));
                startActivity(new Intent(CheckPhoneActivity.this, ForgetPasswordActivity2.class));
            }
        });
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
    }
//    private ImageView iv_close_card;
    @Override
    protected boolean getIsTransparent() {
        return true;
    }

    public void onEvent(LoginSuccessEvent event){
        finish();
    }
    public void onEvent(RegisterSuccessEvent event){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
