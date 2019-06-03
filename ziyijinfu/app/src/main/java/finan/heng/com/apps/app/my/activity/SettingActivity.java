package finan.heng.com.apps.app.my.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dynamic.foundations.common.assist.Log;
import com.mcxiaoke.bus.Bus;
import com.umeng.analytics.MobclickAgent;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.app.home.activity.EvaluationSuccessActivity;
import finan.heng.com.apps.app.home.activity.RiskEvaluationActivity;
import finan.heng.com.apps.app.ui.activity.CheckPhoneActivity;
import finan.heng.com.apps.app.ui.activity.ShowStepViewActivity;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.model.EvaluationSubmitResult;
import finan.heng.com.apps.utils.DialogUtil;
import finan.heng.com.apps.utils.StringUtils;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.home.activity.MyBankCardActivity;
import finan.heng.com.apps.app.home.activity.ReviseJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ReviseLoginMimaActivity;
import finan.heng.com.apps.app.home.activity.SetJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ShiMingPassActivity;
import finan.heng.com.apps.app.home.activity.ShiMingRenZhengActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.LogOutResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/6.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mRl_exit;
    private RelativeLayout mRl_shiming;
    private RelativeLayout mRl_my_yinhangka;
    private RelativeLayout mRl_loginmima;
    private RelativeLayout mRl_jiaoyimima;
    private TextView mTv_shiming_name;
    private MyUserInfo mCacheData;
    private TextView mIsSet;
    private TextView mBindphone;
    private TextView mTv_isbind;
    private TextView investStyle;
    private AlertDialog loadingDialog;
    private boolean isShowStepView = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("个人设置");


    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            initView();
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        mRl_exit.setOnClickListener(this);
        mRl_shiming.setOnClickListener(this);
        mRl_my_yinhangka.setOnClickListener(this);
        mRl_loginmima.setOnClickListener(this);
        mRl_jiaoyimima.setOnClickListener(this);
        findViewById(R.id.invest_style_layout).setOnClickListener(this);
    }

    private void initView() {
        mRl_exit = findViewById(R.id.rl_exit);
        mRl_shiming = findViewById(R.id.rl_shiming);
        mRl_my_yinhangka = findViewById(R.id.rl_my_yinhangka);
        mRl_loginmima = findViewById(R.id.rl_loginmima);
        mRl_jiaoyimima = findViewById(R.id.rl_jiaoyimima);
        mTv_shiming_name = findViewById(R.id.tv_shiming_name);
        mIsSet = findViewById(R.id.isSet);
        mBindphone = findViewById(R.id.bindphone);
        mTv_isbind = findViewById(R.id.tv_isbind);
        investStyle = findViewById(R.id.invest_style);

        mCacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        isShowStepView = "0".equals(mCacheData.result.realnameStatus) || StringUtils.isEmpty(mCacheData.result.userPaypassword);
        if ("0".equals(mCacheData.result.realnameStatus)) {
            mTv_shiming_name.setText("未认证");
            mTv_shiming_name.setTextColor(Color.parseColor("#5b9cf8"));
        } else if ("1".equals(mCacheData.result.realnameStatus)) {
            String name = mCacheData.result.userRealname.toString().substring(0, 1);
            mTv_shiming_name.setText(name + "**");
        }
        if ("0".equals(mCacheData.result.cardStatus)) {
            mTv_isbind.setText("未绑定");
            mTv_isbind.setTextColor(Color.parseColor("#5b9cf8"));
        } else if ("1".equals(mCacheData.result.cardStatus)) {
            mTv_isbind.setText("已绑定");
            mTv_isbind.setTextColor(Color.parseColor("#999999"));
        }
        if (TextUtils.isEmpty(mCacheData.result.userPaypassword)) {
            mIsSet.setText("未设置");
            mIsSet.setTextColor(Color.parseColor("#5b9cf8"));
        } else {
            mIsSet.setText("已设置");
            mIsSet.setTextColor(Color.parseColor("#999999"));
        }
        if (!TextUtils.isEmpty(mCacheData.result.investStyle)) {
            investStyle.setTextColor(Color.parseColor("#999999"));
            switch (mCacheData.result.investStyle.trim()) {
                case "1":
                    investStyle.setText("基础型");
                    break;
                case "2":
                    investStyle.setText("保守型");
                    break;
                case "3":
                    investStyle.setText("稳健型");
                    break;
                case "4":
                    investStyle.setText("进取型");
                    break;
                default:
                    investStyle.setText("未知型");
            }
        } else {
            investStyle.setTextColor(Color.parseColor("#5b9cf8"));
            investStyle.setText("去测评");
        }

//        String substring = mCacheData.result.userAccount.toString().substring(0, 3);
//        String substring1 = mCacheData.result.userAccount.toString().substring(mCacheData.result.userAccount.toString().length() - 4);
        mBindphone.setText(mCacheData.result.userAccount.toString().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        if (requestCode == 1001 && resultCode == 1001) {
            mTv_shiming_name.setText(mCacheData.result.userRealname);
            mTv_isbind.setText("已绑定");
            mTv_isbind.setTextColor(Color.parseColor("#999999"));
            return;
        }
        if (requestCode == 1002 && resultCode == 1002) {
            mIsSet.setText("已设置");
            mIsSet.setTextColor(Color.parseColor("#999999"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_exit:
                showPointDialog();
                break;
            case R.id.rl_shiming:
                if ("0".equals(mCacheData.result.realnameStatus)) {
                    startActivity(new Intent(SettingActivity.this, ShowStepViewActivity.class)
                            .putExtra(IConstants.Extra.EXTRA_SHOWSTEP_LOGO, R.mipmap.ic_showstep_logoother)
                    );
                    return;
                }
                if ("0".equals(mCacheData.result.realnameStatus)) {
                    mTv_shiming_name.setText("未认证");
                    Intent intent = new Intent(SettingActivity.this, ShiMingRenZhengActivity.class);
                    intent.putExtra("need", false);
                    startActivityForResult(intent, 1001);
                } else if ("1".equals(mCacheData.result.realnameStatus)) {
                    Intent intent = new Intent(SettingActivity.this, ShiMingPassActivity.class);
                    intent.putExtra("realname", mCacheData.result.userRealname.toString());
                    intent.putExtra("idcard", mCacheData.result.userIdCard.toString());
                    startActivity(intent);
                }
                break;
            case R.id.rl_my_yinhangka:
                if (isShowStepView) {
                    startActivity(new Intent(SettingActivity.this, ShowStepViewActivity.class)
                            .putExtra(IConstants.Extra.EXTRA_SHOWSTEP_LOGO, R.mipmap.ic_showstep_logoother)
                    );
                    return;
                }
                if ("0".equals(mCacheData.result.realnameStatus)) {
                    mTv_isbind.setText("未绑定");
                    startActivityForResult(new Intent(SettingActivity.this, ShiMingRenZhengActivity.class), 1001);
                } else if ("1".equals(mCacheData.result.realnameStatus)) {
                    mTv_isbind.setText("已绑定");
                    startActivity(new Intent(SettingActivity.this, MyBankCardActivity.class));
                }
                break;
            case R.id.rl_loginmima:
                startActivity(new Intent(SettingActivity.this, ReviseLoginMimaActivity.class));
                break;
            case R.id.rl_jiaoyimima:
                if (StringUtils.isEmpty(mCacheData.result.userPaypassword)) {
                    startActivity(new Intent(SettingActivity.this, ShowStepViewActivity.class)
                            .putExtra(IConstants.Extra.EXTRA_SHOWSTEP_LOGO, R.mipmap.ic_showstep_logoother)
                    );
                    return;
                }
//                if ("0".equals(mCacheData.result.realnameStatus)) {
////                    Toast.makeText(this,"您还未进行实名认证,请先实名认证!",Toast.LENGTH_SHORT).show();
//                    ViewHelper.showToast(this, "您还未进行实名认证,请先实名认证!");
//                } else {
                if (TextUtils.isEmpty(mCacheData.result.userPaypassword)) {
                    mIsSet.setText("未设置");
                    startActivityForResult(new Intent(SettingActivity.this, SetJiaoYiMimaActivity.class), 1002);
                } else {
                    mIsSet.setText("已设置");
                    startActivity(new Intent(SettingActivity.this, ReviseJiaoYiMimaActivity.class));
                }
//                }
                break;
            case R.id.invest_style_layout:

                if (!TextUtils.isEmpty(mCacheData.result.investStyle)) {
                    loadingDialog = DialogUtil.getInstance().showLoading(this);
                    Log.i("return", mCacheData.result.investStyle);
                    new HttpHelper().getInvestTypeInfo(mCacheData.result.investStyle).subscribe(new Action1<CommonHttpModel<EvaluationSubmitResult>>() {
                        @Override
                        public void call(CommonHttpModel<EvaluationSubmitResult> commonResult) {

                            try {
                                dismissDialog();
                                if (commonResult.code.equals("0")) {
                                    startActivity(new Intent(SettingActivity.this, EvaluationSuccessActivity.class)
                                            .putExtra(EvaluationSuccessActivity.INVEST_TYPE, mCacheData.result.investStyle)
                                            .putExtra(EvaluationSuccessActivity.DESCRIPTION, commonResult.result.tipsOne)
                                            .putExtra(EvaluationSuccessActivity.TYPE_DETAIL, commonResult.result.tipsTwo));
                                } else {
                                    ViewHelper.showToast(getApplicationContext(), commonResult.message);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            try {
                                dismissDialog();
                                ViewHelper.showToast(getApplicationContext(), throwable.getMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } else {
                    startActivity(new Intent(this, RiskEvaluationActivity.class));
                }

                break;
        }

    }

    private void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    private void showPointDialog() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(SettingActivity.this, R.style.dialog);
        View inflate = View.inflate(SettingActivity.this, R.layout.loginout_dialog, null);
        normalDialog.setView(inflate);
        // 显示
        final AlertDialog alertDialog = normalDialog.create();
        alertDialog.show();
        inflate.findViewById(R.id.loginout_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        inflate.findViewById(R.id.loginout_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpHelper().logout().subscribe(new Action1<LogOutResponse>() {
                    @Override
                    public void call(LogOutResponse logOutResponse) {
                        DataCache.instance.clearCacheData("heng", "MyUserInfo");
                        DataCache.instance.clearCacheData("heng", "LoginResponse");
                        DataCache.instance.clearCacheData("heng", "MyWalletResponse");
                        Bus.getDefault().post("exit");
                        MobclickAgent.onProfileSignOff();
                        startActivity(new Intent(SettingActivity.this, MainActivity.class));

                        startActivity(new Intent(SettingActivity.this, CheckPhoneActivity.class));
                        alertDialog.dismiss();
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(SettingActivity.this, requestErrorThrowable.getMessage());
                        }

                    }
                });
            }
        });
    }
}
