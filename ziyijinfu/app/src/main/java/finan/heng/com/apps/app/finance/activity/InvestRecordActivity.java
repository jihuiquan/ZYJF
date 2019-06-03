package finan.heng.com.apps.app.finance.activity;
/*
 * Created by hhm on 2017/5/3.
 */

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.finance.fragment.TouZiJiLuFragment;
import finan.heng.com.apps.base.BaseActivity;
import finan.zhimabao.com.apps.R;

/**
 * 投资记录
 */
public class InvestRecordActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_record);
        try {
            initViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        setUpToolbar();
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)) {
            setBarTitle(title);
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        TouZiJiLuFragment jiLuFragment = new TouZiJiLuFragment();
        jiLuFragment.setArguments(getIntent().getExtras());
        transaction.replace(R.id.content_layout, jiLuFragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 跳转webActivity
     *
     * @param id
     * @param title
     * @param mContext
     */
    public static void show(int id, String title, Context mContext) {
        Intent intent = new Intent(mContext, InvestRecordActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        mContext.startActivity(intent);
    }
}
