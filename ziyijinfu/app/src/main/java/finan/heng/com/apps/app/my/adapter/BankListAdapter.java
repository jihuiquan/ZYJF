package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.model.BankInfo;

/**
 * Created by Administrator on 2017/5/15.
 */
public class BankListAdapter extends BaseAdapter {

    private ArrayList<BankInfo> list;
    private Context context;
    private boolean httpOK;

    public BankListAdapter(Context context, ArrayList<BankInfo> list) {
        this.context = context;
        this.list = list;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_banklist, parent, false);
            holder = new ViewHolder(view);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.bank_name.setText(list.get(position).bankName);
        Glide.with(context).load(list.get(position).bankImage).into(holder.bank_icon);
        holder.rootView.setBackgroundColor((list.get(position).isNotInService() ? Color.parseColor("#f2f2f2") : Color.parseColor("#ffffff")));
        holder.bank_hot.setVisibility(list.get(position).isShow() ? View.VISIBLE : View.GONE);
        if (TextUtils.isEmpty(list.get(position).bankLimit)){
            holder.tips.setText("");
        }else {
            holder.tips.setText(list.get(position).bankLimit);
        }
        return view;
    }

    static class ViewHolder {

        ImageView bank_icon;
        TextView bank_name;
        TextView tips;
        RelativeLayout rootView;
        ImageView bank_hot;
        ViewHolder(View view) {
            bank_icon = (ImageView) view.findViewById(R.id.bank_icon);
            bank_name = (TextView) view.findViewById(R.id.bank_name);
            rootView = view.findViewById(R.id.rootView);
            tips = view.findViewById(R.id.tv_bank_desc);
            bank_hot = view.findViewById(R.id.bank_hot);
        }
    }
}
