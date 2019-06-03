package finan.heng.com.apps.app.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mcxiaoke.bus.Bus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.my.activity.GongGaoDetailActivity;
import finan.heng.com.apps.app.my.adapter.GongGaoMessageAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetGongGaoModel;
import finan.heng.com.apps.model.GetGongGaoResponse;
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
public class GongGaoMessageFragment extends BaseFragment {

    private ListView fragment_message_listview;
    private SmartRefreshLayout refreshLayout;
    private int pageNo = 1;
    GongGaoMessageAdapter allTradeAdapter;
    GetGongGaoResponse response;

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
//        showDialog(true);
        new HttpHelper().getGongGao(pageNo).subscribe(new Action1<GetGongGaoResponse>() {
            @Override
            public void call(GetGongGaoResponse getGongGaoResponse) {

                try {
                    refreshLayout.finishLoadmore();
                    refreshLayout.finishRefresh();
                    response = getGongGaoResponse;
                    showDialog(false);
                    final ArrayList<GetGongGaoModel.ArticleListBean> articleList = getGongGaoResponse.result.getArticleList();
                    if (articleList == null || articleList.size() == 0) {
                        return;
                    }
                    if (allTradeAdapter == null){
                        allTradeAdapter = new GongGaoMessageAdapter(getActivity(), articleList);
                        fragment_message_listview.setAdapter(allTradeAdapter);
                    }else {
                        if (pageNo == 1){
                            allTradeAdapter.refresh(articleList);
                        }else {
                            allTradeAdapter.addData(articleList);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }



//                fragment_message_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(getActivity(), GongGaoDetailActivity.class);
//                        intent.putExtra("body", articleList.get(position));
//                        startActivity(intent);
//                    }
//                });
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    showDialog(false);
                    if (throwable instanceof RequestErrorThrowable) {
                        RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                        if (getActivity() != null) {
                            ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                        }
                        refreshLayout.finishLoadmore();
                        refreshLayout.finishRefresh();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void initView(View view) {
        fragment_message_listview = view.findViewById(R.id.fragment_message_listview);
        refreshLayout = view.findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNo =1;
                init();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (response.result.getPageNo().equals(response.result.getTotalPage())){
                    ViewHelper.showToast(getActivity(),"没有更多数据了");
                    refreshLayout.finishLoadmore();
                }else {
                    pageNo++;
                    init();
                }
            }
        });
    }

}
