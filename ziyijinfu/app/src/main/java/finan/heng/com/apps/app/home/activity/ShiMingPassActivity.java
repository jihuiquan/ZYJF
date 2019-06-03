package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.nfc.tech.NfcB;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/10.
 */
public class ShiMingPassActivity extends BaseActivity {

    private TextView mRealname;
    private TextView mIdcard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimingpass);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("实名认证");
        initView();
        init();
    }

    private void init() {




    }

    private void initView() {
        mRealname = (TextView) findViewById(R.id.realname);
        mIdcard = (TextView) findViewById(R.id.idcard);
        Intent intent=getIntent();
        String realname =  intent.getStringExtra("realname");
        String idcard = intent.getStringExtra("idcard");
        mRealname.setText(realname.substring(0,1)+"**");
        mIdcard.setText(idcard.toString().substring(0,6)+"********"+idcard.toString().substring(idcard.length()-4));

    }
}
