package finan.heng.com.apps.app.my.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.base.LazyFragment;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.my.adapter.TouZiJiLuAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetInvestHistoryListModel;
import finan.heng.com.apps.model.GetInvestHistoryListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;

import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 14:38
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class TouZiFragment extends LazyFragment {

    private int               type;
    private SmartRefreshLayout pullToRefreshView;
    private RecyclerView      recyclerView;
    private boolean isLoad       = false;
    private boolean hasMoreItems = true;
    private int     pageIndex    = 1;
    private TouZiJiLuAdapter mTouZiJiLuAdapter;

    public TouZiFragment(int i) {
        this.type = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_touzi, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 12;
            }
        });
        final MyLayoutManager myLayoutManager = new MyLayoutManager(getActivity());
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = myLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = myLayoutManager.getItemCount();
                if (!isLoad && hasMoreItems && lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    pageIndex++;
                    isLoad = true;
                    mTouZiJiLuAdapter.httpOK = true;
                    getData();
                }
            }
        });
        pullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                hasMoreItems = true;
                getData();
            }
        });
    }

    private void getData() {
        new HttpHelper().getInvestHistoryList(pageIndex + "", "10", type).subscribe(new Action1<GetInvestHistoryListResponse>() {
            @Override
            public void call(GetInvestHistoryListResponse getInvestHistoryListResponse) {
                GetInvestHistoryListModel result = getInvestHistoryListResponse.result;
//                tv_title.setText(result.getTitle());
                pullToRefreshView.finishRefresh();
                dismissLoadingDialog();
                if (pageIndex == 1) {
                    DataCache.instance.saveCacheData("heng", "GetInvestHistoryListResponse", getInvestHistoryListResponse);
                    analyseData(getInvestHistoryListResponse, 2);
                    mTouZiJiLuAdapter.setType(type);
                } else {
                    isLoad = false;
                    if (getInvestHistoryListResponse.result.getInvestList().size() > 0) {
                        analyseData(getInvestHistoryListResponse, 1);
                        mTouZiJiLuAdapter.setType(type);
                    } else {//没有更多数据了
                        hasMoreItems = false;
                        mTouZiJiLuAdapter.httpOK = false;
                        mTouZiJiLuAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                }
            }
        });
    }

    /**
     * @param response 集合数据
     * @param type     添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     */
    public void analyseData(GetInvestHistoryListResponse response, int type) {
        ArrayList<GetInvestHistoryListModel.InvestListBean> productsList = response.result.getInvestList();
        if (productsList.size() >= 0) {
            switch (type) {
                case 0:
                    mTouZiJiLuAdapter = new TouZiJiLuAdapter(getActivity(), productsList);
                    recyclerView.setAdapter(mTouZiJiLuAdapter);
                    break;
                case 1:
                    mTouZiJiLuAdapter.addData(productsList);
                    break;
                case 2:
                    if (mTouZiJiLuAdapter == null) {
                        mTouZiJiLuAdapter = new TouZiJiLuAdapter(getActivity(), productsList);
                        recyclerView.setAdapter(mTouZiJiLuAdapter);
                    } else {
                        mTouZiJiLuAdapter.setData(productsList);
                    }
                    break;
            }
        }
    }

    private TextView tv_title;
    private void initView(View view) {
        pullToRefreshView = (SmartRefreshLayout) view.findViewById(R.id.fragment_touzi_pulltorefreshview);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_touzi_recycler);
        tv_title = (TextView) getActivity().findViewById(R.id.title_text);
    }

    @Override
    protected void loadData() {
        getData();
    }
}
