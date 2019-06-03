package finan.heng.com.apps.app.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.util.AbDialogUtil;
import com.jungly.gridpasswordview.GridPasswordView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.home.activity.RiskEvaluationActivity;
import finan.heng.com.apps.app.my.activity.BankListActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.model.BankInfo;
import finan.heng.com.apps.model.BankListInfo;
import finan.heng.com.apps.model.BankListResponse;
import finan.heng.com.apps.model.EvaluationSubmitResult;
import finan.heng.com.apps.model.GetRenZhengMessageResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.RiskDefaultResponse;
import finan.heng.com.apps.model.RuleNameModel;
import finan.heng.com.apps.model.RuleNameResponse;
import finan.heng.com.apps.model.SetBuyPasswordResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.DialogUtil;
import finan.heng.com.apps.utils.StringUtils;
import finan.heng.com.apps.utils.TimeCount;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class VerifyInfoActivity extends BaseActivity implements TextWatcher {
    private LinearLayout fragment_realname, fragment_tenderpwd, fragment_risk;
    private LinearLayout ll_unrisk, ll_risk;
    private TextView tv_description, tv_risk;
    private CheckBox checkBox;
    private String name;
    private ImageView line1, line2;
    private EditText et_tenderpwd_init, et_tenderpwd_retrive;
    private int code = 0;
    private TextView invest_description, type_detail;
    private ImageView riskImage;
    private TextView tv_purchase_point, ic_showstepview_point2;
    private TextView tv_purchase, tv_step_risk;
    ArrayList<BankInfo> bankList;
    private TextView tv_xieyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyinfo);
        setUpToolbar();
        getSupportActionBar().setTitle("");

        tv_xieyi = findViewById(R.id.tv_xieyi);
        tv_purchase_point = findViewById(R.id.tv_purchase_point);
        ic_showstepview_point2 = findViewById(R.id.ic_showstepview_point2);
        tv_purchase = findViewById(R.id.tv_purchase);
        tv_step_risk = findViewById(R.id.tv_step_risk);

        fragment_realname = findViewById(R.id.fragment_realname);
        fragment_tenderpwd = findViewById(R.id.fragment_tenderpwd);
        fragment_risk = findViewById(R.id.fragment_risk);
        ll_unrisk = findViewById(R.id.ll_unrisk);
        ll_risk = findViewById(R.id.ll_risk);
        tv_description = findViewById(R.id.tv_description);

        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        et_tenderpwd_init = findViewById(R.id.et_tenderpwd_init);
        et_tenderpwd_retrive = findViewById(R.id.et_tenderpwd_retrive);
        btn_tenderpwd = findViewById(R.id.btn_tenderpwd);
        iv_close_card = findViewById(R.id.iv_close_card);

        checkBox = findViewById(R.id.iv_select4);
        mEt_name = findViewById(R.id.et_name);
        mEt_shenfennum = findViewById(R.id.et_card);
        mEt_yinhangcard = findViewById(R.id.et_yinhangcard);
        mEt_yuliu_phone = findViewById(R.id.et_yuliu_phone);
        mEt_yanzhengma = findViewById(R.id.et_yanzhengma);
        mTv_getyanzhengma = findViewById(R.id.tv_getyanzhengma);
        iv_eye_open = findViewById(R.id.iv_eye_open);
        iv_eye_retirve = findViewById(R.id.iv_eye_retirve);
        tv_risk = findViewById(R.id.tv_risk);
        riskImage = findViewById(R.id.risk_result_pic);
        iv_close_number = findViewById(R.id.iv_close_number);

        invest_description = findViewById(R.id.invest_description);
        type_detail = findViewById(R.id.type_detail);

        mEt_name.addTextChangedListener(this);
        mEt_shenfennum.addTextChangedListener(this);
        mEt_yinhangcard.addTextChangedListener(this);
        mEt_yuliu_phone.addTextChangedListener(this);
        mEt_yanzhengma.addTextChangedListener(this);
        mBt_shenfenyanzheng = findViewById(R.id.bt_shenfenyanzheng);

        MyUserInfo mCacheData = null;
        try {
            mCacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            name = mCacheData.result.userAccount;
            String username = mCacheData.result.userRealname;
            if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(username)) {
                mEt_name.setText(username);
            }
            String cardId = mCacheData.result.userIdCard;
            if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(cardId)) {
                mEt_shenfennum.setText(cardId);
            }

            String title;
            if ("0".equals(mCacheData.result.realnameStatus)) {
                getInit();
                setIndex(0);
                title = "实名认证";
            } else if (StringUtils.isEmpty(mCacheData.result.userPaypassword)) {
                setIndex(1);
                title = "设置交易密码";
            } else {
                setIndex(2);
                title = "风险测评";
            }
            setBarTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            setIndex(0);
        }
        doRealName();
        doTenderPwd();
        String description = "根据监管部门规定，您在投资前必须完成风险测评。默认类型为<big><b><font color='#333333'> 进取型</font></b></big>，对投资金额<font color='#fc291d'>无限制</font>";
        tv_description.setText(Html.fromHtml(description));
        tv_risk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyInfoActivity.this, RiskEvaluationActivity.class));
            }
        });
        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riskDefaultLevel();
            }
        });
        findViewById(R.id.re_evaluation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riskDefaultLevel();
            }
        });
        findViewById(R.id.complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getInit() {
        showLoadingDialog();
        new HttpHelper().getBankInfo().subscribe(new Action1<BankListResponse>() {
            @Override
            public void call(BankListResponse bankInfo) {
                dismissLoadingDialog();
                try {
                    String token = bankInfo.result.getToken();
                    if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(token)) {
                        DataCache.instance.saveCacheData("heng", "token", token);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                updateUi(bankInfo.result);
                android.util.Log.e("hhm", bankInfo.errorMsg);
                bankList = bankInfo.result.bankList;
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(VerifyInfoActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private BankListInfo bankListInfo;

    /**
     * 更新界面
     *
     * @param result
     */
    private void updateUi(BankListInfo result) {
        if (result != null) {
            this.bankListInfo = result;
            mEt_yuliu_phone.setText(result.getMobile());
            tv_xieyi.setText("《" + result.getServiceAgreementLabel() + "》");
        }
    }

    private void showRiskResult(MyUserInfo mCacheData) {
        if (!StringUtils.isEmpty(mCacheData.result.investStyle)) {
            ll_risk.setVisibility(View.VISIBLE);
            ll_unrisk.setVisibility(View.GONE);
            switch (mCacheData.result.investStyle) {

                case "2"://保守型
                    riskImage.setImageResource(R.drawable.activity_risk_conservativ_pic);
                    break;
                case "3"://稳健型
                    riskImage.setImageResource(R.drawable.activity_risk_steady_pic);
                    break;
                case "4"://进取型
                    riskImage.setImageResource(R.drawable.activity_risk_aggressive_pic);
                    findViewById(R.id.re_evaluation).setVisibility(View.GONE);
                    break;
                case "1"://基础型
                default:
                    finish();
            }

            alertDialog = DialogUtil.getInstance().showLoading(this);
//            Log.i("return", mCacheData.result.investStyle);
            new HttpHelper().getInvestTypeInfo(mCacheData.result.investStyle).subscribe(new Action1<CommonHttpModel<EvaluationSubmitResult>>() {
                @Override
                public void call(CommonHttpModel<EvaluationSubmitResult> commonResult) {

                    dismissDialog();
                    invest_description.setText(Html.fromHtml(commonResult.result.tipsOne));
                    type_detail.setText(Html.fromHtml(commonResult.result.tipsTwo));

                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    dismissDialog();

                }
            });
        } else {
            ll_unrisk.setVisibility(View.VISIBLE);
            ll_risk.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyUserInfo mCacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        showRiskResult(mCacheData);
    }

    //一键默认等级
    public void riskDefaultLevel() {
        new HttpHelper().riskDefault().subscribe(new Action1<RiskDefaultResponse>() {
            @Override
            public void call(RiskDefaultResponse riskDefaultResponse) {
                ViewHelper.showToast(VerifyInfoActivity.this, "测评成功");
                finish();

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(VerifyInfoActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private ImageView iv_eye_retirve, iv_eye_open;
    private Button btn_tenderpwd;
    private boolean isShowInit = false;
    private boolean isShowRetrive = false;
    private ImageView iv_close_card;

    private void doTenderPwd() {
        iv_eye_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInputMode(isShowInit, et_tenderpwd_init, iv_eye_open);
                isShowInit = !isShowInit;
            }
        });
        iv_eye_retirve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInputMode(isShowRetrive, et_tenderpwd_retrive, iv_eye_retirve);
                isShowRetrive = !isShowRetrive;
            }
        });
        changeInputMode(isShowInit, et_tenderpwd_init, iv_eye_open);
        changeInputMode(isShowRetrive, et_tenderpwd_retrive, iv_eye_retirve);
        isShowInit = !isShowInit;
        isShowRetrive = !isShowRetrive;
        et_tenderpwd_init.addTextChangedListener(this);
        et_tenderpwd_retrive.addTextChangedListener(this);

        btn_tenderpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_tenderpwd_init.getText().toString().length() != 6) {
                    Toast.makeText(getApplicationContext(), "交易密码必须为六位", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!com.dynamic.foundations.common.utils.StringUtils.equals(et_tenderpwd_init.getText().toString()
                        , et_tenderpwd_retrive.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    new HttpHelper().setBuyPassword(et_tenderpwd_init.getText().toString()).subscribe(new Action1<SetBuyPasswordResponse>() {
                        @Override
                        public void call(SetBuyPasswordResponse setBuyPasswordResponse) {
                            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                            cacheData.result.userPaypassword = setBuyPasswordResponse.result.getTradePassword();
                            DataCache.instance.saveCacheData("heng", "MyUserInfo", cacheData);
                            ViewHelper.showToast(VerifyInfoActivity.this, "设置交易密码成功");
                            setIndex(2);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            if (throwable instanceof RequestErrorThrowable) {
                                RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                ViewHelper.showToast(VerifyInfoActivity.this, requestErrorThrowable.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }

    private void changeInputMode(boolean isShow, EditText inputView, ImageView ivEye) {
        if (isShow) {
            //设置EditText文本为可见的
            inputView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ivEye.setImageResource(R.drawable.ic_tenderpwd_open);
        } else {
            //设置EditText文本为隐藏的
            inputView.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivEye.setImageResource(R.drawable.ic_tenderpwd_close);
        }
        inputView.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = inputView.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    private void doRealName() {
        mEt_yuliu_phone.setText(name);
        tv_xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyInfoActivity.this, WebActivity.class);
                intent.putExtra("url", URLHelper.getInstance().URL + "/protocol/payment");
                if (bankListInfo != null) {
                    intent.putExtra("title", bankListInfo.getServiceAgreementLabel());
                } else {
                    intent.putExtra("title", "支付开通说明");
                }
                startActivity(intent);
            }
        });

        findViewById(R.id.rl_bank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getBaseContext(), BankListActivity.class).putExtra("list", bankList), 100);
            }
        });
        findViewById(R.id.tv_getyanzhengma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mEt_yuliu_phone.getText().toString();
                if (StringUtils.isPhone(phone)) {
                    getCaptcha(phone);
                } else {
                    ViewHelper.showToast(VerifyInfoActivity.this, "手机号码格式不正确");
                }
            }
        });

        mBt_shenfenyanzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruleName();
            }
        });
        findViewById(R.id.iv_close_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt_shenfennum.setText("");
                mEt_shenfennum.setHint("请输入真实身份证号");
            }
        });
        findViewById(R.id.iv_close_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt_yinhangcard.setText("");
                mEt_yinhangcard.setHint("请输入银行卡号");
            }
        });
    }

    private void getCaptcha(String phone) {
        alertDialog = DialogUtil.getInstance().showLoading(this);

        new HttpHelper().renzhengMessageCode(phone).subscribe(new Action1<GetRenZhengMessageResponse>() {
            @Override
            public void call(GetRenZhengMessageResponse getRenZhengMessageResponse) {
                dismissDialog();
                TimeCount mTimeCount = new TimeCount(60000, 1000
                        , mTv_getyanzhengma, VerifyInfoActivity.this, 0);
                mTimeCount.start();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(VerifyInfoActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });

    }

    android.support.v7.app.AlertDialog alertDialog;
    private EditText mEt_shenfennum, mEt_name, et_bankname,
            mEt_yinhangcard, mEt_yanzhengma;
    TextView mEt_yuliu_phone;
    private TextView mTv_getyanzhengma;
    private Button mBt_shenfenyanzheng;
    private ImageView iv_close_number;

    private void ruleName() {
        if (!checkBox.isChecked()) {
            String tip = null;
            if (bankListInfo != null) {
                tip = bankListInfo.getServiceAgreementLabel();
            }
            if (!TextUtils.isEmpty(tip)) {
                ViewHelper.showToast(VerifyInfoActivity.this, "请阅读并同意《" + tip + "》");
            } else {
                ViewHelper.showToast(VerifyInfoActivity.this, "请阅读并同意《支付开通说明》");
            }
            return;
        }
        if (mEt_shenfennum.getText().toString().length() == 18) {
            alertDialog = DialogUtil.getInstance().showLoading(this);
            new HttpHelper().ruleName(mEt_name.getText().toString(), mBankId, mEt_shenfennum.getText().toString(),
                    mEt_yinhangcard.getText().toString(), mEt_yuliu_phone.getText().toString()
                    , mEt_yanzhengma.getText().toString()).subscribe(new Action1<RuleNameResponse>() {
                @Override
                public void call(RuleNameResponse ruleNameResponse) {
                    try {
                        dismissDialog();
                        try {
                            String token = ruleNameResponse.result.getToken();
                            if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(token)) {
                                DataCache.instance.saveCacheData("heng", "token", token);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (ruleNameResponse.isSuccess()) {
                            RuleNameModel result = ruleNameResponse.result;
                            int maxLength = result.smsCodeMaxLen;
                            if (maxLength > 0) {
                                showMessageDialog(maxLength);
                            }

                        } else {
                            ViewHelper.showToast(getApplicationContext(), ruleNameResponse.errorMsg);
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
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(VerifyInfoActivity.this, requestErrorThrowable.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } else {
            ViewHelper.showToast(VerifyInfoActivity.this, "请输入正确的身份证号");
        }

    }

    private void showMessageDialog(int maxLength) {
        View view = View.inflate(this, R.layout.dialog_activity_real_name, null);
        ImageView closeImage = view.findViewById(R.id.close);
        TextView tips = view.findViewById(R.id.send_tips);
        MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        String phone = "";
        if (null != cacheData) {
            phone = cacheData.result.userAccount.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        tips.setText(Html.fromHtml("为了保障您的资金安全<br>已向<big><font color = '#fc291d'>" + phone + "</font></big>发送短信验证码"));
        Button sure = view.findViewById(R.id.btSure);
        final GridPasswordView passwordView = view.findViewById(R.id.pswView);
        if (maxLength != 6) {
            passwordView.setmPasswordLength(maxLength);
        }


        final TextView errorTips = view.findViewById(R.id.errorTips);


        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this, R.style.dialog).setView(view).setCancelable(false).create();
        alertDialog.show();
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(passwordView.getPassWord())) {
                    errorTips.setText("请输入验证码");
                    return;
                }

                Logger.i("result" + "click");
                showLoadingDialog();
                new HttpHelper().bindCardSendSmgCode(passwordView.getPassWord()).subscribe(new Action1<CommonHttpModel<RuleNameModel>>() {
                    @Override
                    public void call(CommonHttpModel<RuleNameModel> commonHttpModel) {
                        dismissLoadingDialog();
                        if (alertDialog != null && alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                        try {
                            String token = commonHttpModel.result.getToken();
                            if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(token)) {
                                DataCache.instance.saveCacheData("heng", "token", token);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (commonHttpModel.isSuccess()) {
                            RuleNameModel result = commonHttpModel.result;
                            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                            cacheData.result.userRealname = result.realName;
                            cacheData.result.realnameStatus = result.realNameStatus;
                            cacheData.result.cardStatus = result.cardStatus;
                            cacheData.result.userIdCard = result.idCard;
                            DataCache.instance.saveCacheData("heng", "MyUserInfo", cacheData);
                            if (com.dynamic.foundations.common.utils.StringUtils.isEmpty(cacheData.result.userPaypassword)) {
                                setIndex(1);
                            } else {
                                setIndex(2);
                            }
                        } else {
                            ViewHelper.showToast(getApplicationContext(), commonHttpModel.message + "");
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dismissLoadingDialog();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }

    private void dismissDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    private String mBankId;
    private String mBankName;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == 101) {
            mBankId = data.getStringExtra("bankId");
            mBankName = data.getStringExtra("bankName");
            ((TextView) findViewById(R.id.activity_home_shiming_kaihu)).setText(mBankName);
            ((TextView) findViewById(R.id.activity_home_shiming_kaihu)).setTextColor(Color.parseColor("#333333"));
        }
    }

    private void setIndex(int i) {
        switch (i) {
            case 0:
                fragment_risk.setVisibility(View.GONE);
                fragment_tenderpwd.setVisibility(View.GONE);
                fragment_realname.setVisibility(View.VISIBLE);
                line1.setImageResource(R.drawable.ic_stepview_line_on);
                line2.setImageResource(R.drawable.ic_stepview_line_un);

                tv_purchase_point.setBackgroundResource(R.mipmap.ic_showstepview_point_default);
                tv_purchase.setTextColor(getResources().getColor(R.color.color_999999));
                ic_showstepview_point2.setBackgroundResource(R.mipmap.ic_showstepview_point_default);
                tv_step_risk.setTextColor(getResources().getColor(R.color.color_999999));
                code = 1;
                break;
            case 1:
                fragment_risk.setVisibility(View.GONE);
                fragment_tenderpwd.setVisibility(View.VISIBLE);
                fragment_realname.setVisibility(View.GONE);
                line1.setImageResource(R.drawable.ic_stepview_line_over);
                line2.setImageResource(R.drawable.ic_stepview_line_on);
                code = 2;
                tv_purchase_point.setBackgroundResource(R.mipmap.ic_showstepview_point);
                tv_purchase.setTextColor(getResources().getColor(R.color.color_333333));
                ic_showstepview_point2.setBackgroundResource(R.mipmap.ic_showstepview_point_default);
                tv_step_risk.setTextColor(getResources().getColor(R.color.color_999999));
                break;
            case 2:
                fragment_risk.setVisibility(View.VISIBLE);
                fragment_tenderpwd.setVisibility(View.GONE);
                fragment_realname.setVisibility(View.GONE);
                line1.setImageResource(R.drawable.ic_stepview_line_over);
                line2.setImageResource(R.drawable.ic_stepview_line_over);
                tv_purchase_point.setBackgroundResource(R.mipmap.ic_showstepview_point);
                tv_purchase.setTextColor(getResources().getColor(R.color.color_333333));
                ic_showstepview_point2.setBackgroundResource(R.mipmap.ic_showstepview_point);
                tv_step_risk.setTextColor(getResources().getColor(R.color.color_333333));
                code = 0;
                break;
        }
    }

    @Override
    public void onBackPressed() {
        final View view = LayoutInflater.from(VerifyInfoActivity.this).inflate(R.layout.dialog_reaname_back, null);
        TextView tv_content = view.findViewById(R.id.tv_content);
        ImageView iv_cancel = view.findViewById(R.id.iv_cancel);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbDialogUtil.removeDialog(view);
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbDialogUtil.removeDialog(view);
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyInfoActivity.this.finish();
            }
        });
        if (code == 1) {
            tv_content.setText("退出实名认证后无法进行投资理财，确认退出吗？");
            tv_commit.setText("继续认证");
            AbDialogUtil.showDialog(view, Gravity.CENTER);
            return;
        } else if (code == 2) {
            tv_content.setText("设置交易密码可保障您的账户资金安全，确认退出吗？");
            tv_commit.setText("前往设置");
            AbDialogUtil.showDialog(view, Gravity.CENTER);
            return;
        }
        super.onBackPressed();

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEt_shenfennum.getText().toString().length() > 0) {
            iv_close_card.setVisibility(View.VISIBLE);
        } else {
            iv_close_card.setVisibility(View.GONE);
        }
        if (mEt_yinhangcard.getText().toString().trim().length() > 0) {
            iv_close_number.setVisibility(View.VISIBLE);
        } else {
            iv_close_number.setVisibility(View.GONE);
        }

        if (mEt_name.getText().toString().length() > 0 &&
                mEt_shenfennum.getText().toString().length() > 0 &&
                mEt_yinhangcard.getText().toString().length() > 0 &&
                mEt_yuliu_phone.getText().toString().length() > 0
                ) {
            mBt_shenfenyanzheng.setEnabled(true);
            mBt_shenfenyanzheng.setBackgroundResource(R.drawable.bg_submit_round_rect);
        } else {
            mBt_shenfenyanzheng.setEnabled(false);
            mBt_shenfenyanzheng.setBackgroundResource(R.drawable.bg_submit_gray);

        }

        if (et_tenderpwd_init.getText().toString().length() > 0
                && et_tenderpwd_retrive.getText().toString().length() > 0
                ) {
            btn_tenderpwd.setEnabled(true);
            btn_tenderpwd.setBackgroundResource(R.drawable.bg_submit_round_rect);

        } else {
            btn_tenderpwd.setEnabled(false);
            btn_tenderpwd.setBackgroundResource(R.drawable.bg_submit_gray);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
