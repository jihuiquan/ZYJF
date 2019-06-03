package finan.heng.com.apps.app.finance.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.base.LazyFragment;
import finan.heng.com.apps.model.GetGenreListModel;
import finan.heng.com.apps.model.GetGenreListResponse;
import finan.heng.com.apps.utils.ProjectUtil;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2018/11/12
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class InvestListTabFragment extends LazyFragment {
    private TabLayout tableLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invest_tab, container, false);
//        setUpToolbar(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    /**
     * 获取产品类型
     */
    protected void loadData() {
        new HttpHelper().getGenreList().subscribe(new Action1<GetGenreListResponse>() {
            @Override
            public void call(GetGenreListResponse response) {
                List<GetGenreListModel.productsGenreVoBean> productsGenreVoList = response.result.getProductsGenreVoList();
                initData(productsGenreVoList);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
            }
        });
    }

    /**
     * 初始化标的分类
     *
     * @param list
     */
    private void initData(List<GetGenreListModel.productsGenreVoBean> list) {
        if (list == null) {
            return;
        }
        int size = list.size();
        if (size == 0) {
            return;
        }
        tableLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        //动态设置距离顶部padding值
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ProjectUtil.margin(tableLayout, 0, ProjectUtil.getStatusBarHeight(getActivity()), 0, 0);
        }
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(size);
        viewPager.setCurrentItem(0);
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            GetGenreListModel.productsGenreVoBean getGenreListModel = list.get(i);
            title.add(getGenreListModel.getGenreTitle());
            fragments.add(new AssetFragment(0, getGenreListModel.getId(), i));
        }
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(myFragmentPagerAdapter);
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
}
