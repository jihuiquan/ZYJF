package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/5/3.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.WebActivity;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.ReBackResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

public class YiJianFanKuiActivity extends BaseActivity implements View.OnClickListener,TextWatcher {

    private EditText etOne, etTwo;
    private Button btn;
    private TextView leftNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yijianfankui);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        String title = getIntent().getStringExtra("title");
        setBarTitle(title);
        initView();
        init();
    }

    private void init() {
        etOne.addTextChangedListener(this);
        btn.setOnClickListener(this);
    }

    private void initView() {
        etOne = (EditText) findViewById(R.id.activity_yijianfankui_et1);
        //        etTwo = (EditText) findViewById(R.id.activity_yijianfankui_et2);
        btn = (Button) findViewById(R.id.activity_yijianfankui_btn);
        leftNum = (TextView) findViewById(R.id.left_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_yijianfankui_btn:
                new HttpHelper().reBack(etOne.getText().toString()).subscribe(new Action1<ReBackResponse>() {
                    @Override
                    public void call(ReBackResponse reBackResponse) {
                        ViewHelper.showToast(YiJianFanKuiActivity.this, "提交成功，感谢您的反馈");
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(YiJianFanKuiActivity.this, requestErrorThrowable.getMessage());
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        if (s.length() > 0){
            btn.setEnabled(true);
            btn.setBackgroundResource(R.drawable.bg_submit_round_rect);
        }else {
            btn.setEnabled(false);
            btn.setBackgroundResource(R.drawable.bg_submit_gray);
        }
        leftNum.setText((200-s.length())+"");
    }

    /**
     * 跳转YiJianFanKuiActivity
     * @param title
     * @param mContext
     */
    public static void show(String title, Context mContext){
        Intent intent = new Intent(mContext, YiJianFanKuiActivity.class);
        intent.putExtra("title", title);
        mContext.startActivity(intent);
    }
}
