package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.model.GetTradeListModel;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 11:27
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AllTradeAdapter extends RecyclerView.Adapter<AllTradeAdapter.ViewHolder> {
    private ArrayList<GetTradeListModel.TradeDetailListBean> list;
    private Context                                          context;
    public  boolean                                          httpOK;

    public AllTradeAdapter(Context context, ArrayList<GetTradeListModel.TradeDetailListBean> al) {
        this.context = context;
        this.list = al;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.all_trade_item, parent, false);
            return new ViewHolder(inflate);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.refresh_foot_layout, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == list.size() && httpOK) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.progressBar.getLayoutParams();
            float density = context.getResources().getDisplayMetrics().density;
            lp.height = Math.round((float) 60 * density);
            holder.progressBar.setLayoutParams(lp);
            return;
        }
        GetTradeListModel.TradeDetailListBean bean = list.get(position);
        if (bean.getAmount().contains("+")) {
            holder.mTvMoney.setTextColor(Color.parseColor("#5db36b"));
        } else {
            holder.mTvMoney.setTextColor(Color.parseColor("#5b9cf8"));
        }
        holder.mTvMoney.setText(bean.getAmount());
        holder.mTvName.setText(bean.getTitle());
        holder.mTvTime.setText(bean.getTime());
    }

    @Override
    public int getItemCount() {
        if (httpOK) {
            return list.size() + 1;
        }
        return list.size();
    }

    public void setData(ArrayList<GetTradeListModel.TradeDetailListBean> al) {
        this.list = al;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetTradeListModel.TradeDetailListBean> al) {
        this.list.addAll(al);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size() && httpOK) {
            return 1;
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private       View     itemView;
        private final TextView mTvName, mTvMoney, mTvTime;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            progressBar =  itemView.findViewById(R.id.progress_foot_bar);
            mTvName = (TextView) itemView.findViewById(R.id.all_trade_item_name);
            mTvMoney = (TextView) itemView.findViewById(R.id.all_trade_item_money);
            mTvTime = (TextView) itemView.findViewById(R.id.all_trade_item_time);
        }
    }
}
