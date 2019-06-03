package finan.heng.com.apps.app.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import finan.heng.com.apps.IConstants;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class UpdateActivity extends BaseActivity{
    private TextView tv_version, tv_desc;
    private ImageView iv_cancel;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        String isForce = getIntent().getExtras().getString(IConstants.Extra.UPDATE_TAG);
        final String link = getIntent().getExtras().getString(IConstants.Extra.UPDATE_URL);
        String title = getIntent().getExtras().getString(IConstants.Extra.UPDATE_VERSION);
        String content = getIntent().getExtras().getString(IConstants.Extra.UPDATE_DESC);

        initViews();
        tv_version.setText("新版本号:" + title);

        tv_desc.setText(content);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(IConstants.ResultCode.CODE_UPDATE, getIntent().putExtra(IConstants.Extra.UPDATE_URL, link));
                finish();
//                doDownLoad(link);
            }
        });
        if(isForce.equals("0")){//强制
            iv_cancel.setVisibility(View.GONE);
        }else if(isForce.equals("1")){//不强制
            iv_cancel.setVisibility(View.VISIBLE);
        }

        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(IConstants.ResultCode.CODE_CANCEL, getIntent().putExtra(IConstants.Extra.UPDATE_URL, link));
                finish();
            }
        });
    }

    private void doDownLoad(String link) {


    }

    @Override
    public void onBackPressed() {
        return;
    }

    private void initViews() {
        tv_version = (TextView) findViewById(R.id.tv_version);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        iv_cancel = (ImageView) findViewById(R.id.iv_cancel);
        btn_update = (Button) findViewById(R.id.btn_update);
    }
}
