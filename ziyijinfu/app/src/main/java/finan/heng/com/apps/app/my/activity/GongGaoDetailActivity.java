package finan.heng.com.apps.app.my.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.app.my.fragment.GongGaoMessageFragment;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/6.
 */
public class GongGaoDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gonggaodetail);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle(getIntent().getStringExtra(IConstants.Extra.TITLE)+"");
        try {
            init();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void init(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new GongGaoMessageFragment());
        transaction.commitAllowingStateLoss();
    }

    /**
     * 跳转GongGaoDetailActivity
     * @param url
     * @param title
     * @param mContext
     */
    public static void show(String url,String title, Context mContext){
        Intent intent = new Intent(mContext, GongGaoDetailActivity.class);
        intent.putExtra(IConstants.Extra.URL, url);
        intent.putExtra(IConstants.Extra.TITLE, title);
        mContext.startActivity(intent);
    }
}
