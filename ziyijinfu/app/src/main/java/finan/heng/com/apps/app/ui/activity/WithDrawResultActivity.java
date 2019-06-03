package finan.heng.com.apps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.zhimabao.com.apps.R;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class WithDrawResultActivity extends BaseActivity {
    TextView tv_cash;
    TextView tv_card;
    Button btn_commit;
    TextView tv_warning;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawresult);
        setUpToolbar();
        getSupportActionBar().setTitle("提现结果");
        setBarTitle("提现结果");
        initViews();
        String cash = getIntent().getStringExtra(IConstants.Extra.EXTRA_CASH);
        String code = getIntent().getStringExtra(IConstants.Extra.BANK_CODE);
        String name = getIntent().getStringExtra(IConstants.Extra.BANK_NAME);
        String tips = getIntent().getStringExtra(IConstants.Extra.BANK_TIPS);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WithDrawResultActivity.this, MainActivity.class);
                intent.putExtra("fromWeb", "gotoaccount");
                startActivity(intent);
                finish();
            }
        });
        try {
            tv_cash.setText(IConstants.Formatter.moneyWithDrawFormat.format(Double.parseDouble(cash)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        tv_card.setText(name + "(" + code + ")");
        tv_warning.setText(tips);
    }

    private void initViews() {
        btn_commit = findViewById(R.id.btn_commit);
        tv_card = findViewById(R.id.tv_card);
        tv_cash = findViewById(R.id.tv_cash);
        tv_warning = findViewById(R.id.tv_warning);
    }
}
