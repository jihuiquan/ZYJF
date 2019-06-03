package finan.heng.com.apps.app.finance.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


import finan.heng.com.apps.base.BaseActivity;
import finan.zhimabao.com.apps.R;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/4 23:40
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class BigPicActivity extends BaseActivity {

    private ViewPager mViewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_pic);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        String size = getIntent().getStringExtra("size");
        String num = getIntent().getStringExtra("num");
        setBarTitle(num + 1 + "/" + size);
        initView();
        init();
    }

    private void init() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(getIntent().getStringExtra("size")); i++) {
            strings.add("a");
        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this, strings);
        mViewpager.setAdapter(myPagerAdapter);
        mViewpager.setCurrentItem(Integer.parseInt(getIntent().getStringExtra("num")));
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setBarTitle(position + 1 + "/" + getIntent().getStringExtra("size"));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    class MyPagerAdapter extends PagerAdapter {

        private Context           context;
        private ArrayList<String> list;

        public MyPagerAdapter(Context context, ArrayList<String> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(R.mipmap.icon);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
