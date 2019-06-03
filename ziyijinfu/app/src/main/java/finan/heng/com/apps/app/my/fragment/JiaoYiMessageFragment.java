package finan.heng.com.apps.app.my.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mcxiaoke.bus.Bus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.model.GetJiaoYiListModel;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.my.adapter.JiaoYiMessageAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetJiaoYi;
import finan.heng.com.apps.model.GetJiaoYiListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 14:38
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class JiaoYiMessageFragment extends BaseFragment {

    private ListView mListView;
    private SmartRefreshLayout refreshLayout;
    private int mPageNo = 1;
    private final int mPageSize = 15;
    JiaoYiMessageAdapter allTradeAdapter;
    private GetJiaoYiListModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_jiaoyimessage, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {
        new HttpHelper().getJiaoYiList(mPageNo+"",mPageSize+"").subscribe(new Action1<GetJiaoYiListResponse>() {
            @Override
            public void call(GetJiaoYiListResponse getJiaoYiListResponse) {
                try {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadmore();
                    showDialog(false);
                    model = getJiaoYiListResponse.result;
                    ArrayList<GetJiaoYi> smgList = model.smgList;
                    if (smgList == null ){
                        smgList = new ArrayList<>();
                    }
                    if (allTradeAdapter == null){
                        allTradeAdapter = new JiaoYiMessageAdapter(getActivity(), smgList);
                        mListView.setAdapter(allTradeAdapter);
                    }else {
                        if (mPageNo > 1){
                            allTradeAdapter.addData(smgList);
                        }else if (mPageNo == 1){
                            allTradeAdapter.reFresh(smgList);
                        }
                    }
                    mPageNo++;
                    Bus.getDefault().post("MyFragmentUpdate");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

                try {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadmore();
                    showDialog(false);
                    if (throwable instanceof RequestErrorThrowable) {
                        RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                        ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                    }
                    Bus.getDefault().post("MyFragmentUpdate");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void initView(View view) {
        mListView = view.findViewById(R.id.fragment_message_listview);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                    mPageNo =1;
                    init();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                    if (model.getPageNo() < model.getTotalPage()){
                        init();
                    }else {
                        refreshlayout.finishLoadmore();
                        ViewHelper.showToast(getActivity(),"没有更多消息了");
                    }

            }
        });
//        refreshLayout.setEnablePureScrollMode(true);
//        refreshLayout.setDragRate(1f);
    }

}
