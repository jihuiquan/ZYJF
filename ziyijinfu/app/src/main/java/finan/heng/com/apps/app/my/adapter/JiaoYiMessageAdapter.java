package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.model.GetJiaoYi;

/**
 * Created by Administrator on 2017/5/6.
 */
public class JiaoYiMessageAdapter extends BaseAdapter {
    private ArrayList<GetJiaoYi> list;
    private Context              context;

    public JiaoYiMessageAdapter(Context context, ArrayList<GetJiaoYi> list) {
        this.context = context;
        this.list = list;
    }
    public void addData(ArrayList<GetJiaoYi> list){
        if (list != null){
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void reFresh(ArrayList<GetJiaoYi> list){
        if (list != null){
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_messagecenter, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mJiaxiaoname.setText(list.get(position).content);
        holder.mStartdate.setText(list.get(position).date);
        return view;
    }

    static class ViewHolder {
        TextView  mJiaxiaoname;
        TextView  mStartdate;

        ViewHolder(View view) {
            mJiaxiaoname= (TextView) view.findViewById(R.id.jiaxiaoname);
            mStartdate= (TextView) view.findViewById(R.id.startdate);
        }
    }
}
