package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.RiskDefaultResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/12/7.
 */

public class EvaluationSuccessActivity extends BaseActivity{

    public static final String  INVEST_TYPE = "InvestType";
    public static final String  DESCRIPTION = "Description";
    public static final String  TYPE_DETAIL = "TypeDetail";

    private AlertDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_risk_evaluation_success);
        setUpToolbar();
        setBarTitle("风险测评");
        TextView typeDescription = findViewById(R.id.invest_description);
        TextView typeDetailView = findViewById(R.id.type_detail);
        TextView rightText  =  findViewById(R.id.tv_guizeshuoming);
        ImageView riskImage = findViewById(R.id.risk_result_pic);

        //<big><b><font color='#333333'>稳健型</font></b></big>这是没有加粗的文本<font color='#fc291d'>这是颜色字体</font>
        String investType = getIntent().getStringExtra(INVEST_TYPE);
        String description = getIntent().getStringExtra(DESCRIPTION);
        String typeDetail = getIntent().getStringExtra(TYPE_DETAIL);


        try {
            if (!TextUtils.isEmpty(typeDetail)){
                typeDetailView.setText(Html.fromHtml(typeDetail));
                typeDescription.setText(description);
            }
            rightText.setText("一键默认");

            if (!TextUtils.isEmpty(investType)){
                rightText.setVisibility(View.VISIBLE);
                switch (investType){

                    case "2"://保守型
                        riskImage.setImageResource(R.drawable.activity_risk_conservativ_pic);
                        break;
                    case "3"://稳健型
                        riskImage.setImageResource(R.drawable.activity_risk_steady_pic);
                        break;
                    case "4"://进取型
                        rightText.setVisibility(View.GONE);//进取型不显示一键默认
                        findViewById(R.id.re_evaluation).setVisibility(View.GONE);
                        riskImage.setImageResource(R.drawable.activity_risk_aggressive_pic);
                        break;
                    case "1"://基础型
                    default:
                        finish();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLoadingDialog();
                new HttpHelper().riskDefault().subscribe(new Action1<RiskDefaultResponse>() {
                    @Override
                    public void call(RiskDefaultResponse riskDefaultResponse) {
                        try {
                            dismissLoadingDialog();
                            if (riskDefaultResponse.code.equals("0")){
                                finish();
                            }else {
                                ViewHelper.showToast(getApplicationContext(),riskDefaultResponse.message);
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
                                ViewHelper.showToast(getApplicationContext(),requestErrorThrowable.getMessage());
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    public void viewClick(View view){
        switch (view.getId()){
            case R.id.complete:
                finish();
                break;
            case R.id.re_evaluation:
                startActivity(new Intent(this,RiskEvaluationActivity.class));
                finish();
                break;
        }
    }

}
