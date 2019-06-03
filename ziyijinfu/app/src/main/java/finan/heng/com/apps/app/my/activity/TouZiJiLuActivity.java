package finan.heng.com.apps.app.my.activity;

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
import finan.heng.com.apps.app.home.activity.TouziActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetGenreListModel;
import finan.heng.com.apps.model.GetGenreListResponse;
import finan.heng.com.apps.model.InvestRecord;
import finan.heng.com.apps.model.InvestRecordList;
import finan.heng.com.apps.model.InvestRecordResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.my.fragment.TouZiFragment;
import finan.heng.com.apps.base.BaseActivity;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 14:25
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class TouZiJiLuActivity extends BaseActivity {

    private TabLayout tableLayout;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzijilu);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        String title = getIntent().getStringExtra("title");
        setBarTitle(title);
        init();
    }

    private void init() {
        new HttpHelper().getInvestTypeList().subscribe(new Action1<InvestRecordResponse>() {
            @Override
            public void call(InvestRecordResponse response) {
                InvestRecordList result = response.result;
                initView(result.getCdkeyTypeList());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    if (TouZiJiLuActivity.this != null) {
                        ViewHelper.showToast(TouZiJiLuActivity.this, requestErrorThrowable.getMessage());
                    }
                }
            }
        });
    }

    private void initView(List<InvestRecord> list) {
        int size = list.size();
        tableLayout = (TabLayout) findViewById(R.id.activity_touzi_tab);
        viewPager = (ViewPager) findViewById(R.id.activity_touzi_viewpager);
        viewPager.setOffscreenPageLimit(3);
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            InvestRecord investRecord = list.get(i);
            fragments.add(new TouZiFragment(Integer.parseInt(investRecord.getStatus())));
            title.add(investRecord.getTitle());
        }
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);

        //设置TabLayout的模式
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        //viewpager加载adapter
        viewPager.setAdapter(myFragmentPagerAdapter);
        //TabLayout加载viewpager
        tableLayout.setupWithViewPager(viewPager);
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;
        private ArrayList<String> title;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<String> title, ArrayList<Fragment> list) {
            super(fm);
            this.title = title;
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    /**
     * 跳转Activity
     * @param title
     * @param mContext
     */
    public static void show( String title, Context mContext){
        Intent intent = new Intent(mContext, TouZiJiLuActivity.class);
        intent.putExtra("title", title);
        mContext.startActivity(intent);
    }
}
