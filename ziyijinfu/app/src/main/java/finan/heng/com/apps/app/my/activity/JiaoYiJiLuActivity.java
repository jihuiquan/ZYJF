package finan.heng.com.apps.app.my.activity;

/*
 * Created by hhm on 2017/4/25.
 */
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.List;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.TradeTypeModel;
import finan.heng.com.apps.model.TradeTypeModelList;
import finan.heng.com.apps.model.TradeTypeModelResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.my.fragment.AllTradeFragment;
import finan.heng.com.apps.base.BaseActivity;
import rx.functions.Action1;

/**
 * 交易记录页面
 */
public class JiaoYiJiLuActivity extends BaseActivity {

    private TabLayout tab;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaoyijilu);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        String title = getIntent().getStringExtra("title");
        setBarTitle(title);
        init();
    }

    private void init() {
        new HttpHelper().getTradeTypeList().subscribe(new Action1<TradeTypeModelResponse>() {
            @Override
            public void call(TradeTypeModelResponse response) {
                TradeTypeModelList result = response.result;
                initView(result.getCdkeyTypeList());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    if (JiaoYiJiLuActivity.this != null) {
                        ViewHelper.showToast(JiaoYiJiLuActivity.this, requestErrorThrowable.getMessage());
                    }
                }
            }
        });
    }

    private void initView(List<TradeTypeModel> list) {
        int size = list.size();
        tab = (TabLayout) findViewById(R.id.activity_jiaoyi_tab);
        viewPager = (ViewPager) findViewById(R.id.activity_jiaoyijilu_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TradeTypeModel tradeTypeModel = list.get(i);
            AllTradeFragment tradeFragment1 = new AllTradeFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("type", Integer.parseInt(tradeTypeModel.getType()));
            tradeFragment1.setArguments(bundle1);
            fragments.add(tradeFragment1);
            title.add(tradeTypeModel.getTitle());
        }
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);

        //设置TabLayout的模式
        tab.setTabMode(TabLayout.MODE_FIXED);
        //viewpager加载adapter
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(7);
        //TabLayout加载viewpager
        tab.setupWithViewPager(viewPager);
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list_fragment;
        private List<String> list_Title;

        public MyFragmentPagerAdapter(FragmentManager fm, List<String> list_Title, List<Fragment> list_fragment) {
            super(fm);
            this.list_Title = list_Title;
            this.list_fragment = list_fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }
    }

    /**
     * 跳转Activity
     * @param title
     * @param mContext
     */
    public static void show( String title, Context mContext){
        Intent intent = new Intent(mContext, JiaoYiJiLuActivity.class);
        intent.putExtra("title", title);
        mContext.startActivity(intent);
    }
}
