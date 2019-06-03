package finan.heng.com.apps.app.my.fragment;

import android.annotation.SuppressLint;
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
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.my.adapter.XianJinHongBaoAdapter;
import finan.heng.com.apps.base.LazyFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetRedPackListModel;
import finan.heng.com.apps.model.GetRedPackListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 14:38
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
@SuppressLint("ValidFragment")
public class XianJinHongBaoFragment extends LazyFragment implements View.OnClickListener {

    private SmartRefreshLayout pullToRefreshView;
    private RecyclerView recyclerView;
    private XianJinHongBaoAdapter recyclerAdapter;
    private int type;
    private boolean isLoad = false;
    private boolean hasMoreItems = true;
    private boolean isShixiaoOrKeyong = true;
    private int pageIndex = 1;
    private TextView mTv_nomore;
    private TextView mTv_chakanshixiao;
    private String useOrNoUse = "ksy";

    public XianJinHongBaoFragment(int i) {
        this.type = i;
    }

    private TextView tv_redpack_banlance, tv_redpack_status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_redpack, container, false);
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
//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int lastVisibleItem = myLayoutManager.findLastVisibleItemPosition();
//                int totalItemCount = myLayoutManager.getItemCount();
//                if (!isLoad && hasMoreItems && lastVisibleItem >= totalItemCount - 1 && dy > 0) {
//                    pageIndex++;
//                    isLoad = true;
//                    recyclerAdapter.httpOK = true;
//                    initData();
//
//                }
//            }
//        });
        pullToRefreshView.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++;
                isLoad = true;
                recyclerAdapter.httpOK = true;
                initData();
            }
        });
        pullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                hasMoreItems = true;
                initData();
            }
        });

        mTv_chakanshixiao.setOnClickListener(this);
//        mTv_nomore.setOnClickListener(this);
    }

    private void initData() {
        new HttpHelper().getRedPackList(pageIndex + "", "10", "" + type, useOrNoUse).subscribe(new Action1<GetRedPackListResponse>() {
            @Override
            public void call(GetRedPackListResponse getRedPackListResponse) {
                pullToRefreshView.finishRefresh();
                pullToRefreshView.finishLoadmore();
                dismissLoadingDialog();
                if (pageIndex == 1) {
                    DataCache.instance.saveCacheData("heng", "GetRedPackListResponse", getRedPackListResponse);
                    analyseData(getRedPackListResponse, 2, useOrNoUse);
                } else {
                    isLoad = false;
                    if (getRedPackListResponse.result.getMyredPackList().size() > 0) {
                        analyseData(getRedPackListResponse, 1, useOrNoUse);
                    } else {//没有更多数据了
                        hasMoreItems = false;
                        recyclerAdapter.httpOK = false;
                        recyclerAdapter.notifyDataSetChanged();
//                            mTv_nomore.setVisibility(View.VISIBLE);
                    }
                }


            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
//                LoadingFragment loadingFragment = findLoadingFragment();
//                if (loadingFragment != null) {
//                    loadingFragment.removeSelf(getFragmentManager());
//                }

                try {
                    pullToRefreshView.finishRefresh();
                    dismissLoadingDialog();
                    pullToRefreshView.finishLoadmore();
                    if (throwable instanceof RequestErrorThrowable) {
                        RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                        if (getActivity() != null) {
                            ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void initView(View view) {
        pullToRefreshView = (SmartRefreshLayout) view.findViewById(R.id.pulltorefreshview);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_touzi_recycler);
//        mTv_nomore = (TextView) view.findViewById(R.id.tv_nomore);
        mTv_chakanshixiao = (TextView) view.findViewById(R.id.tv_chakanshixiao);
        tv_redpack_banlance = view.findViewById(R.id.tv_redpack_banlance);
        tv_redpack_status = view.findViewById(R.id.tv_redpack_status);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_chakanshixiao:
                if (isShixiaoOrKeyong) {
//                    mTv_nomore.setText("已无更多失效红包");
                    mTv_chakanshixiao.setText("查看可使用");
                    isShixiaoOrKeyong = false;
                    pageIndex = 1;
                    useOrNoUse = "ysy";
                    initData();

                } else {
//                    mTv_nomore.setText("已无更多可用红包");
                    mTv_chakanshixiao.setText("查看已用完/已过期");
                    isShixiaoOrKeyong = true;
                    pageIndex = 1;
                    useOrNoUse = "ksy";
                    initData();
                }

                break;
        }

    }

    /**
     * @param response   集合数据
     * @param type       添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     * @param useOrNoUse
     */
    public void analyseData(GetRedPackListResponse response, int type, String useOrNoUse) {
        ArrayList<GetRedPackListModel.MyredPackListBean> myredPackList = (ArrayList<GetRedPackListModel.MyredPackListBean>) response.result.getMyredPackList();
        tv_redpack_banlance.setText(response.result.getBalanceSum());
        tv_redpack_status.setText(response.result.getConsumedSum());
        if (myredPackList.size() >= 0) {
            switch (type) {
                case 0:
                    recyclerAdapter = new XianJinHongBaoAdapter(getActivity(), myredPackList, useOrNoUse);
                    recyclerView.setAdapter(recyclerAdapter);
                    if (myredPackList.size() == 0) {
//                        mTv_nomore.setVisibility(View.GONE);
                    } else {
//                        mTv_nomore.setVisibility(View.GONE);
                    }
                    break;
                case 1:
                    recyclerAdapter.addData(myredPackList, useOrNoUse);
                    if (myredPackList.size() < 10) {
//                        mTv_nomore.setVisibility(View.GONE);
                    } else {
//                        mTv_nomore.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    if (recyclerAdapter == null) {
                        recyclerAdapter = new XianJinHongBaoAdapter(getActivity(), myredPackList, useOrNoUse);
                        recyclerView.setAdapter(recyclerAdapter);
                    } else {
                        recyclerAdapter.setData(myredPackList, useOrNoUse);
                    }
                    if (myredPackList.size() < 10) {
//                        mTv_nomore.setVisibility(View.GONE);
                    } else {
//                        mTv_nomore.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    }

    @Override
    protected void loadData() {
        initData();
    }
}
