package finan.heng.com.apps.app.my.fragment;
/*
 * Created by hhm on 2017/5/5.
 */

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.base.LazyFragment;
import finan.heng.com.apps.utils.StringUtils;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.my.adapter.InviteFriendFinanceAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetInviteInvestListModel;
import finan.heng.com.apps.model.GetInviteInvestListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;

import rx.functions.Action1;

public class InviteFriendFinanceFragment extends LazyFragment {

    private SmartRefreshLayout pullToRefreshView;
    private RecyclerView recyclerView;
    private boolean isLoad = false;
    private InviteFriendFinanceAdapter mInviteFriendFinanceAdapter;
    private boolean hasMoreItems = true;
    private int pageIndex = 1;
    private TextView tv_invitenum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_invite_friend_finance, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 1;
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
                    isLoad = true;
                    pageIndex++;
                    mInviteFriendFinanceAdapter.httpOK = true;
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
        new HttpHelper().getInviteInvestList(pageIndex + "", "10").subscribe(new Action1<GetInviteInvestListResponse>() {
            @Override
            public void call(GetInviteInvestListResponse getInviteInvestListResponse) {
                pullToRefreshView.finishRefresh();
                dismissLoadingDialog();
                Logger.i("result" + getInviteInvestListResponse.toString());
                try {
                    tv_invitenum.setText(com.dynamic.foundations.common.utils.StringUtils.trimToEmpty(getInviteInvestListResponse.result.getInvateRegCount()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (pageIndex == 1) {
                    DataCache.instance.saveCacheData("heng", "GetInviteInvestListResponse", getInviteInvestListResponse);
                    analyseData(getInviteInvestListResponse, 2);
                } else {
                    isLoad = false;
                    if (getInviteInvestListResponse.result.getInvateRegList().size() > 0) {
                        analyseData(getInviteInvestListResponse, 1);
                    } else {//没有更多数据了
                        hasMoreItems = false;
                        mInviteFriendFinanceAdapter.httpOK = false;
                        mInviteFriendFinanceAdapter.notifyDataSetChanged();
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

    //InviteFriendFinanceAdapter inviteFriendFinanceAdapter = new InviteFriendFinanceAdapter(getActivity(), list);

    /**
     * @param response 集合数据
     * @param type     添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     */
    public void analyseData(GetInviteInvestListResponse response, int type) {
        ArrayList<GetInviteInvestListModel.Beans> productsList = response.result.getInvateRegList();
        if (productsList.size() >= 0) {
            switch (type) {
                case 0:
                    mInviteFriendFinanceAdapter = new InviteFriendFinanceAdapter(getActivity(), productsList);
                    recyclerView.setAdapter(mInviteFriendFinanceAdapter);
                    break;
                case 1:
                    mInviteFriendFinanceAdapter.addData(productsList);
                    break;
                case 2:
                    if (mInviteFriendFinanceAdapter == null) {
                        mInviteFriendFinanceAdapter = new InviteFriendFinanceAdapter(getActivity(), productsList);
                        recyclerView.setAdapter(mInviteFriendFinanceAdapter);
                    } else {
                        mInviteFriendFinanceAdapter.setData(productsList);
                    }
                    break;
            }
        }
    }

    private void initView(View view) {
        pullToRefreshView = (SmartRefreshLayout) view.findViewById(R.id.fragment_invite_friend_register_pulltorefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_invite_friend_register_rv);
        tv_invitenum = view.findViewById(R.id.tv_invitenum);
    }

    @Override
    protected void loadData() {
        getData();
    }
}
