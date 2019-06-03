package finan.heng.com.apps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.base.BaseActivity;
import finan.zhimabao.com.apps.R;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class ShowStepViewActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstepview);
        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowStepViewActivity.this, VerifyInfoActivity.class));
                finish();
            }
        });

        findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        ImageView iv_logo = findViewById(R.id.iv_logo);
        iv_logo.setImageResource(getIntent().getIntExtra(IConstants.Extra.EXTRA_SHOWSTEP_LOGO, R.mipmap.ic_showstep_logo));
    }
}
