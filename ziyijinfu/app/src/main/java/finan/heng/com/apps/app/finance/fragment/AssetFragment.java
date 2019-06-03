package finan.heng.com.apps.app.finance.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;
import com.mcxiaoke.bus.annotation.BusReceiver;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.finance.adapter.RecyclerAdapter;
import finan.heng.com.apps.app.presenter.AssetPresenter;
import finan.heng.com.apps.app.view.IAssetView;
import finan.heng.com.apps.base.LazyFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetProductListModel;
import finan.heng.com.apps.model.GetProductListResponse;
import finan.heng.com.apps.model.event.ProductListRefreshEvent;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/23 15:15
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AssetFragment extends LazyFragment implements IAssetView {

    private int httpNum;
    private String id;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mPullToRefreshView;
    private boolean isLoad = false;
    private boolean hasMoreItems = true;
    private RecyclerAdapter recyclerAdapter;
    private int pageIndex = 1;
    /**
     * 理财产品类型
     */
    private int type;

    public AssetFragment(int i, String id, int type) {
        this.type = type;
        this.id = id;
        this.httpNum = i;
    }

    AssetPresenter presenter = new AssetPresenter(this);

    public AssetFragment() {
        super();
    }

    public AssetFragment(int i, String id) {
        this.id = id;
        this.httpNum = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        initView(view);
        return view;
    }

    @BusReceiver
    public void StringEvent(String s) {
        if (s.equals("heng_AssetFragment" + httpNum)) {
            pageIndex = 1;
            hasMoreItems = true;
            initData();
        }
    }

    public void onEvent(ProductListRefreshEvent event) {
        Logger.i("onevent" + "ProductListRefreshEvent");
        pageIndex = 1;
        hasMoreItems = true;
        initData();
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mPullToRefreshView = (SmartRefreshLayout) view.findViewById(R.id.pulltorefreshview);
        final MyLayoutManager myLayoutManager = new MyLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(myLayoutManager);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = myLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = myLayoutManager.getItemCount();
                if (!isLoad && hasMoreItems && lastVisibleItem >= totalItemCount - 1 && dy > 0) {//加载更多
                    pageIndex++;
                    isLoad = true;
                    recyclerAdapter.httpOK = true;
                    initData();
                }
            }
        });
        mPullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                hasMoreItems = true;
                initData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        pageIndex = 1;
        hasMoreItems = true;
        initData();
    }

    private void initData() {
        new HttpHelper().getProductList(id, pageIndex + "")
                .subscribe(new Action1<GetProductListResponse>() {
                    @Override
                    public void call(GetProductListResponse getProductListResponse) {
                        dismissLoadingDialog();
                        mPullToRefreshView.finishRefresh();

                        if (pageIndex == 1) {
                            DataCache.instance.saveCacheData("heng", "GetProductListResponse", getProductListResponse);
                            analyseData(getProductListResponse, 2);
                        } else {
                            isLoad = false;
                            if (getProductListResponse.result.getProductsList().size() > 0) {
                                analyseData(getProductListResponse, 1);
                            } else {//没有更多数据了
                                hasMoreItems = false;
                                recyclerAdapter.httpOK = false;
                                recyclerAdapter.notifyDataSetChanged();
                            }
                        }


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dismissLoadingDialog();
//                        LoadingFragment loadingFragment = findLoadingFragment();
//                        if (loadingFragment != null) {
//                            loadingFragment.removeSelf(getFragmentManager());
//                        }

                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            if (getActivity() != null) {
                                ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                            }
                        }
                    }
                });
    }

    /**
     * @param response 集合数据
     * @param type     添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     */
    public void analyseData(GetProductListResponse response, int type) {
        ArrayList<GetProductListModel.ProductsListBean> productsList = response.result.getProductsList();
        Logger.i("listsize" + productsList.size());
        if (productsList.size() >= 0) {
            switch (type) {
                case 0:
                    recyclerAdapter = new RecyclerAdapter(getActivity(), productsList);
                    mRecyclerView.setAdapter(recyclerAdapter);
                    break;
                case 1:
                    recyclerAdapter.addData(productsList);
                    break;
                case 2:
                    recyclerAdapter = new RecyclerAdapter(getActivity(), productsList);
                    mRecyclerView.setAdapter(recyclerAdapter);
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Bus.getDefault().unregister(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void loadData() {
        initData();

    }
}
