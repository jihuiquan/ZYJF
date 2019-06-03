package finan.heng.com.apps.app.my.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.app.my.adapter.JDCardAdapter;
import finan.heng.com.apps.base.LazyFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.CardModel;
import finan.heng.com.apps.model.CardResponse;
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
public class JDCardFragment extends LazyFragment implements View.OnClickListener {

    private SmartRefreshLayout pullToRefreshView;
    private RecyclerView recyclerView;
    private JDCardAdapter jdCardAdapter;
    private int pageNo = 1;
    private String type;
    private String icon;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jdcard, container, false);
        initView(view);
        type = getArguments().getString("id");
//        Log.i("return",type+"type");
        icon = getArguments().getString("icon");
        return view;
    }

    private void getData(){
        HashMap<String,String> map = new HashMap<>();
        map.put("type",type);
        map.put("pageSize",5+"");
        map.put("pageNo",pageNo+"");
        new HttpHelper().getCardList(map).subscribe(new Action1<CardResponse>() {
            @Override
            public void call(CardResponse cardResponse) {
                try {
                    pullToRefreshView.finishLoadmore();
                    pullToRefreshView.finishRefresh();
                    dismissLoadingDialog();
                    if (pageNo ==1){
                        init(cardResponse.result.myCDKeyList);
                    }else {
                        if (cardResponse.result.myCDKeyList != null && cardResponse.result.myCDKeyList.size() > 0){
                            jdCardAdapter.addData(cardResponse.result.myCDKeyList);
                        }else {
                            ViewHelper.showToast(getActivity(),"没有更多数据了");
                            pageNo --;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }



            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    pullToRefreshView.finishLoadmore();
                    pullToRefreshView.finishRefresh();
                    dismissLoadingDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void init(List<CardModel> models) {

        final MyLayoutManager myLayoutManager = new MyLayoutManager(getActivity());
        recyclerView.setLayoutManager(myLayoutManager);

        jdCardAdapter = new JDCardAdapter(getActivity(),models,icon);
        recyclerView.setAdapter(jdCardAdapter);

        pullToRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNo = 1;
                getData();
            }
        });
        pullToRefreshView.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNo++;
                getData();
            }
        });


    }



    private void initView(View view) {
        pullToRefreshView = (SmartRefreshLayout) view.findViewById(R.id.pulltorefreshview);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_touzi_recycler);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 48;
            }
        });
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    protected void loadData() {
        getData();
    }
}
