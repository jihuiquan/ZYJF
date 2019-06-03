package finan.heng.com.apps.app.my.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.home.presenter.CardPresenter;
import finan.heng.com.apps.app.home.view.ICardView;
import finan.heng.com.apps.app.my.fragment.JDCardFragment;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.model.CardTypeModel;
import finan.zhimabao.com.apps.R;

/**
 * Created by Administrator on 2017/5/5.
 */
public class CardActivity extends BaseActivity implements View.OnClickListener,ICardView{
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private TextView mTv_guizeshuoming;

    private CardPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        String title = getIntent().getStringExtra("title");
        setBarTitle(title);
        initView();
        presenter = new CardPresenter(this);
        presenter.getData();
    }
    private void initView() {
        mTv_guizeshuoming =  findViewById(R.id.tv_guizeshuoming);
        mTv_guizeshuoming.setVisibility(View.VISIBLE);
        mTv_guizeshuoming.setOnClickListener(this);
        tableLayout =  findViewById(R.id.activity_touzi_tab);
        viewPager =  findViewById(R.id.activity_touzi_viewpager);
    }
    @Override
     public void init(List<CardTypeModel> models) {
        if (models != null){
            ArrayList<Fragment> fragments = new ArrayList<>();
            ArrayList<String> title = new ArrayList<>();

            for (int i=0;i<models.size();i++){
                JDCardFragment jdCardFragment = new JDCardFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id",models.get(i).id);
                bundle.putString("icon",models.get(i).icon);
                jdCardFragment.setArguments(bundle);
                fragments.add(jdCardFragment);
                title.add(models.get(i).name);
            }
            MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);
            //设置TabLayout的模式
            tableLayout.setTabMode(TabLayout.MODE_FIXED);
            //viewpager加载adapter
            viewPager.setAdapter(myFragmentPagerAdapter);
            //TabLayout加载viewpager
            tableLayout.setupWithViewPager(viewPager);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_guizeshuoming://
                Intent intent2 = new Intent(this, WebActivity.class);
                intent2.putExtra("title", "规则说明");
                intent2.putExtra("url", URLHelper.getInstance().buildUrl(IConstants.Server.ADDRESS_CDKEY_RULE));
                startActivity(intent2);
                break;
        }

    }
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;
        private ArrayList<String>   title;

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
        Intent intent = new Intent(mContext, CardActivity.class);
        intent.putExtra("title", title);
        mContext.startActivity(intent);
    }
}
