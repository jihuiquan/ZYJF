package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.home.presenter.RiskPresenter;
import finan.heng.com.apps.app.home.view.IRiskEvaluationView;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.EvaluationModel;
import finan.heng.com.apps.model.EvaluationResponse;
import finan.heng.com.apps.model.EvaluationSubmitResponse;
import finan.heng.com.apps.model.EvaluationSubmitResult;

/**
 * Created by Administrator on 2017/5/3.
 */
public class RiskEvaluationActivity extends BaseActivity implements View.OnClickListener,IRiskEvaluationView{

    TextView rightText;
    RelativeLayout backLayout;
    RiskPresenter presenter;
    EvaluationResponse mResponse;
    AlertDialog alertDialog;
    Button submit;
    ArrayList<EvaluationModel> models;
    private RadioGroup mRadioGroup;
    private TextView mQuestion,mPageIndex;//标题、页面指示
    private Button mPrevious;//上一题
    private int mCurIndex = 1;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_evaluation_version2);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("风险测评");
        init();
        presenter = new RiskPresenter(this);
        presenter.getData();

    }
    private void init(){
        rightText =  findViewById(R.id.tv_guizeshuoming);
        backLayout = findViewById(R.id.back_btn);
        submit = findViewById(R.id.bt_sure);
        mPrevious = findViewById(R.id.previous);
        mRadioGroup = findViewById(R.id.radio_group);
        mQuestion = findViewById(R.id.question);
        mPageIndex = findViewById(R.id.page_tip);
        mProgressBar = findViewById(R.id.progressBar);
        rightText.setText("一键默认");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);
        backLayout.setOnClickListener(this);
        submit.setOnClickListener(this);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1:
                        models.get(mCurIndex-1).selectedScore = models.get(mCurIndex-1).scoreA;
                        break;
                    case R.id.radio2:
                        models.get(mCurIndex-1).selectedScore = models.get(mCurIndex-1).scoreB;
                        break;
                    case R.id.radio3:
                        models.get(mCurIndex-1).selectedScore = models.get(mCurIndex-1).scoreC;
                        break;
                    case R.id.radio4:
                        models.get(mCurIndex-1).selectedScore = models.get(mCurIndex-1).scoreD;
                        break;
                    case R.id.radio5:
                        models.get(mCurIndex-1).selectedScore = models.get(mCurIndex-1).scoreE;
                        break;
                    case R.id.radio6:
                        models.get(mCurIndex-1).selectedScore = models.get(mCurIndex-1).scoreF;
                        break;
                }
            }
        });
    }
    public void radioClick(View view){
            if (mCurIndex == models.size()){
                submit.setEnabled(true);//最后一页的时候显示提交按钮
            }else {
                mCurIndex++;
                setQuestion(models.get(mCurIndex-1));
            }
    }
    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()){
                case R.id.right_button://点击返回的确定
                    dismissDialog();
                    presenter.defaultRisk();
                    break;
                case R.id.tv_guizeshuoming://右侧的点击事件
                    presenter.defaultRisk();
                    break;
                case R.id.back_btn://需要弹框
                    showEvaluationDialog(mResponse.result.max_level,mResponse.result.max_level_description,"继续测评","确定","您的风险测评结果默认为",R.id.right_button,R.id.left_button);
                    break;
                case R.id.left_button://点击返回的继续测评
                    dismissDialog();
                    break;
                case R.id.bt_sure:
                    HashMap<String,String> params = new HashMap<>();
//                    List<EvaluationModel> modelList = evaluationAdapter.getModels();
                    for (int i= 1;i<=models.size();i++){
                        params.put("question"+i,models.get(i-1).selectedScore);
                    }
                    presenter.submitAnswer(params);
                    break;

                case R.id.previous://上一题
                    if (mCurIndex > 1){
                        mCurIndex --;
                        setQuestion(models.get(mCurIndex -1));
                        submit.setVisibility(View.GONE);//点击上一题的时候提交按钮消失
                        if (mCurIndex == 1){
                            mPrevious.setVisibility(View.GONE);
                        }
                    }
                    break;


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void dismissDialog(){
        if (alertDialog != null && alertDialog.isShowing()){
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
    private void showEvaluationDialog(String text1,String text2,String left,String right,String titleText,int id,int leftId) {
        View view = View.inflate(this, R.layout.dialog_risk_evaluation, null);
        TextView title =  view.findViewById(R.id.title);
        title.setText(titleText);
        TextView textView1 =  view.findViewById(R.id.text1);
        textView1.setText("“"+text1+"”");
        textView1.setTextSize(20);
        textView1.setTextColor(Color.parseColor("#Fc291d"));
        TextView textView2 =  view.findViewById(R.id.text2);
        textView2.setText(text2);
        TextView leftButton =  view.findViewById(R.id.left_button);
        TextView rightButton =  view.findViewById(R.id.right_button);
        rightButton.setText(right);
        if (TextUtils.isEmpty(left)){
            leftButton.setVisibility(View.GONE);
            view.findViewById(R.id.middle_line).setVisibility(View.GONE);
        }else {
            leftButton.setText(left);
        }
        rightButton.setId(id);
        leftButton.setId(leftId);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
        alertDialog = new AlertDialog.Builder(this,R.style.dialog).setView(view).setCancelable(false).create();
        alertDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            onClick(backLayout);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void toast(String message) {
        dismissLoadingDialog();
        ViewHelper.showToast(RiskEvaluationActivity.this, message);
    }

    @Override
    public void finishActivity() {
        dismissDialog();
        dismissLoadingDialog();
        finish();
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }


    @Override
    public void initViews(EvaluationResponse responseBodyResponse) {
        dismissLoadingDialog();
        mResponse = responseBodyResponse;
        models = mResponse.result.questionList;
        mProgressBar.setMax(models.size());
        setQuestion(models.get(mCurIndex-1));//页数从1开始计算
    }

    private void setQuestion(EvaluationModel model){
        mQuestion.setText(model.questionContent);
        RadioButton button1 = (RadioButton) mRadioGroup.getChildAt(0);
        button1.setText("A. "+model.answerA);
        if (TextUtils.isEmpty(model.answerA)){
            button1.setVisibility(View.GONE);
        }else {
            button1.setVisibility(View.VISIBLE);
        }
        RadioButton button2 = (RadioButton) mRadioGroup.getChildAt(1);
        button2.setText("B. "+model.answerB);
        if (TextUtils.isEmpty(model.answerB)){
            button2.setVisibility(View.GONE);
        }else {
            button2.setVisibility(View.VISIBLE);
        }
        RadioButton button3 = (RadioButton) mRadioGroup.getChildAt(2);
        button3.setText("C. "+model.answerC);
        if (TextUtils.isEmpty(model.answerC)){
            button3.setVisibility(View.GONE);
        }else {
            button3.setVisibility(View.VISIBLE);
        }
        RadioButton button4 = (RadioButton) mRadioGroup.getChildAt(3);
        button4.setText("D. "+model.answerD);
        if (TextUtils.isEmpty(model.answerD)){
            button4.setVisibility(View.GONE);
        }else {
            button4.setVisibility(View.VISIBLE);
        }
        RadioButton button5 = (RadioButton) mRadioGroup.getChildAt(4);
        button5.setText("E. "+model.answerE);
        if (TextUtils.isEmpty(model.answerE)){
            button5.setVisibility(View.GONE);
        }else {
            button5.setVisibility(View.VISIBLE);
        }
        RadioButton button6 = (RadioButton) mRadioGroup.getChildAt(5);
        button6.setText("F. "+model.answerF);
        if (TextUtils.isEmpty(model.answerF)){
            button6.setVisibility(View.GONE);
        }else {
            button6.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(model.selectedScore)){
            mRadioGroup.clearCheck();
        }else {
            if (mCurIndex == models.size()){
                submit.setVisibility(View.VISIBLE);//最后一页的时候显示提交按钮
            }
            if (model.selectedScore.equals(model.scoreA)){
                mRadioGroup.check(R.id.radio1);
            }else if (model.selectedScore.equals(model.scoreB)){
                mRadioGroup.check(R.id.radio2);
            }else if (model.selectedScore.equals(model.scoreC)){
                mRadioGroup.check(R.id.radio3);
            }else if (model.selectedScore.equals(model.scoreD)){
                mRadioGroup.check(R.id.radio4);
            }else if (model.selectedScore.equals(model.scoreE)){
                mRadioGroup.check(R.id.radio5);
            }else if (model.selectedScore.equals(model.scoreF)){
                mRadioGroup.check(R.id.radio6);
            }
        }
        if (mCurIndex > 1){
            mPrevious.setVisibility(View.VISIBLE);
            if (mCurIndex == models.size()){//最后一页显示提交
                submit.setVisibility(View.VISIBLE);
            }
        }
        String text = "<font color='#fc291d'><big>"+mCurIndex+"</big></font>"+"<small>/"+models.size()+"</small>";
        mPageIndex.setText(Html.fromHtml(text));
        mProgressBar.setProgress(mCurIndex);
    }
    @Override
    public void showResult(EvaluationSubmitResponse evaluationSubmitResponse) {
        dismissLoadingDialog();
        EvaluationSubmitResult result = evaluationSubmitResponse.result;
        startActivity(new Intent(this,EvaluationSuccessActivity.class)
                .putExtra(EvaluationSuccessActivity.INVEST_TYPE,result.levelType)
                .putExtra(EvaluationSuccessActivity.DESCRIPTION,result.tipsOne)
                .putExtra(EvaluationSuccessActivity.TYPE_DETAIL,result.tipsTwo)

        );
        finish();
    }

//    @BusReceiver
//    public void StringEvent(String event) {
//        if (event.equals("check")){
//            if (!submit.isEnabled()){
//                List<EvaluationModel> modelList = evaluationAdapter.getModels();
//                boolean isEnable = true;
//                for (int i=0;i<modelList.size();i++){
//                    if (TextUtils.isEmpty(modelList.get(i).selectedScore)){
//                        isEnable = false;
//                    }
//                }
//                if (isEnable){
//                    submit.setEnabled(true);
//                    submit.setBackgroundResource(R.drawable.btn_click);
//                }
//            }
//
//        }
//    }
}
