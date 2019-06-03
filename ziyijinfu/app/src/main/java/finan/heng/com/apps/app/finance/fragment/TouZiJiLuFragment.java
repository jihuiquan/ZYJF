package finan.heng.com.apps.app.finance.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcxiaoke.bus.Bus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.finance.adapter.FragmentTouZiJiLuAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetProductDetailListModel;
import finan.heng.com.apps.model.GetProductDetailListResponse;

import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/30 22:46
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class TouZiJiLuFragment extends BaseFragment {

    private int id;
    private RecyclerView mRecyclerView;
    private int pageIndex = 1;
    private int pageSize = 15;
    private FragmentTouZiJiLuAdapter adapter;
    private SmartRefreshLayout smartRefreshLayout;
    GetProductDetailListModel detailListModel;
    Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_touzijilu, container, false);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
        }
        initView(view);
        init();
        return view;
    }

    private void init() {
        adapter = new FragmentTouZiJiLuAdapter(context);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 1;
            }
        });
        final MyLayoutManager myLayoutManager = new MyLayoutManager(context);
        mRecyclerView.setLayoutManager(myLayoutManager);
        mRecyclerView.setAdapter(adapter);


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                getData();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                try {
                    if (!detailListModel.getPageNo().equals(detailListModel.getTotalPage())) {
                        getData();
                    } else {
                        smartRefreshLayout.finishLoadmore();
                        ViewHelper.showToast(context, "没有更多数据了！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        showLoadingDialog();
        getData();
    }

    private void getData() {
        new HttpHelper().getProductDetailList(id, pageIndex + "", pageSize + "").subscribe(new Action1<GetProductDetailListResponse>() {
            @Override
            public void call(GetProductDetailListResponse getProductDetailListResponse) {
                try {
                    dismissLoadingDialog();
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.finishLoadmore();
                    detailListModel = getProductDetailListResponse.result;
                    ArrayList<GetProductDetailListModel.UserOrderListBean> userOrderList = detailListModel.getUserOrderList();
                    if (userOrderList == null) {
                        userOrderList = new ArrayList<>();
                    }
                    if (pageIndex == 1) {
                        adapter.reFresh(userOrderList);
                    } else {
                        adapter.addData(userOrderList);
                    }
                    pageIndex++;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(context, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private void initView(View view) {
        smartRefreshLayout = view.findViewById(R.id.fragment_touzijilu_pulltorefreshview);
        mRecyclerView = view.findViewById(R.id.fragment_touzijilu_recyclerview);
    }

}
