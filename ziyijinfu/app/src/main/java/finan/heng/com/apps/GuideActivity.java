package finan.heng.com.apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.app.ui.activity.ActivityAdActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.model.AdInfoResponse;
import finan.heng.com.apps.utils.SharedpreferenceUtil;
import finan.zhimabao.com.apps.R;

public class GuideActivity extends BaseActivity {

    ViewPager viewPager;
    int[] guides = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private AdInfoResponse response;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.guide_pager);
        ArrayList<View> views = new ArrayList<>();
        views.add(LayoutInflater.from(this).inflate(R.layout.activity_guide_item, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.activity_guide_item, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.activity_guide_item, null));
//        views.add(LayoutInflater.from(this).inflate(R.layout.activity_guide_item, null));


        viewPager.setAdapter(new MyAdapter(views));

        response = (AdInfoResponse) getIntent().getExtras().get(IConstants.Extra.ADINFO);

    }

    @Override
    protected boolean getIsTransparent() {
        return true;
    }

    @Override
    protected void onDestroy() {
        viewPager = null;
        super.onDestroy();
    }

    class MyAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyAdapter(List<View> list) {
            mViewList = list;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViewList.get(position);
            container.addView(view);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setBackgroundResource(guides[position]);
            if (position == mViewList.size() - 1) {
                Button button = (Button) view.findViewById(R.id.sure);
//                button.setVisibility(View.VISIBLE);
                view.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doJump();
                    }
                });
                view.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doJump();
                    }
                });
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }
    }

    private void doJump() {
        SharedpreferenceUtil.put("first_use", false);
        if (response != null && response.isShowAd()) {
            startActivity(new Intent(GuideActivity.this, ActivityAdActivity.class)
                    .putExtra(IConstants.Extra.ADINFO, response)
            );
        } else {
            startActivity(new Intent(GuideActivity.this, MainActivity.class));
        }
        finish();

    }
}
