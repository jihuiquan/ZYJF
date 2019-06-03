package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.model.GetGongGaoModel;

/**
 * Created by Administrator on 2017/5/6.
 */
public class GongGaoMessageAdapter extends BaseAdapter {
    private ArrayList<GetGongGaoModel.ArticleListBean> list;
    private Context                                    context;
    private boolean                                    httpOK;

    public GongGaoMessageAdapter(Context context, ArrayList<GetGongGaoModel.ArticleListBean> list) {
        this.context = context;
        this.list = list;
    }
    public void addData(ArrayList<GetGongGaoModel.ArticleListBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void refresh(ArrayList<GetGongGaoModel.ArticleListBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_gonggaomessagecenter, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mJiaxiaoname.setText(list.get(position).getArticleTitle());
        holder.mStartdate.setText(list.get(position).getCreateTime());
        final String noRead = list.get(position).getArtNoRead();
        if (!TextUtils.isEmpty(noRead)){
            if (noRead.equals("1")){
                holder.redCircle.setVisibility(View.VISIBLE);
            }else {
                holder.redCircle.setVisibility(View.GONE);
            }
        }else {
            holder.redCircle.setVisibility(View.GONE);
        }

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(context, WebActivity.class);
                intent2.putExtra("title", list.get(position).getArticleTitle());
                intent2.putExtra("url", list.get(position).getHotUrl());
                if (!TextUtils.isEmpty(noRead)){
                    list.get(position).setArtNoRead("2");
                    notifyDataSetChanged();
                }
                context.startActivity(intent2);
            }
        });
        return view;
    }

    static class ViewHolder {

        TextView       mJiaxiaoname;
        TextView       mStartdate;
        RelativeLayout rl;
        View redCircle;

        ViewHolder(View view) {
            mJiaxiaoname = (TextView) view.findViewById(R.id.item_jiaxiaoname);
            mStartdate = (TextView) view.findViewById(R.id.startdate);
            rl = (RelativeLayout) view.findViewById(R.id.rl_item);
            redCircle = view.findViewById(R.id.red_circle);
        }
    }
}
