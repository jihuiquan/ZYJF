package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.content.Intent;
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
import finan.heng.com.apps.app.finance.activity.ProductDetailListActivity;
import finan.heng.com.apps.model.GetInvestHistoryListModel;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 15:52
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class TouZiJiLuAdapter extends RecyclerView.Adapter<TouZiJiLuAdapter.MyViewHolder> {

    private ArrayList<GetInvestHistoryListModel.InvestListBean> list;
    private Context context;
    public boolean httpOK;
    private int type;

    public TouZiJiLuAdapter(Context context, ArrayList<GetInvestHistoryListModel.InvestListBean> list) {
        this.context = context;
        this.list = list;
    }
    public void setType(int type) {
        this.type = type;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.touzijilu_item, parent, false);
            return new MyViewHolder(inflate);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.refresh_foot_layout, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == list.size() && httpOK) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.progressBar.getLayoutParams();
            float density = context.getResources().getDisplayMetrics().density;
            lp.height = Math.round((float) 60 * density);
            holder.progressBar.setLayoutParams(lp);
            return;
        }
      final  GetInvestHistoryListModel.InvestListBean investListBean = list.get(position);
        holder.tvName.setText(investListBean.getTitle());
        holder.tvMoney.setText(investListBean.getAmount());
        holder.tvTime.setText(investListBean.getTzqx());
        holder.tvYuGu.setText(investListBean.getYgsy());
        holder.tvFootOne.setText(investListBean.getAddTimeLabel()+"：" + investListBean.getAddTime());
        holder.tvFootTwo.setText(investListBean.getEndTimeLabel()+"：" + investListBean.getEndTime());
        holder.tv_amountLabel.setText(investListBean.getAmountLabel());
        holder.tv_plstimeLimitLabel.setText(investListBean.getPlstimeLimitLabel());
        holder.tv_profitLabel.setText(investListBean.getProfitLabel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailListActivity.class);
                intent.putExtra("id",investListBean.getOrderId());
                intent.putExtra("type",type);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (httpOK) {
            return list.size() + 1;
        }
        return list.size();
    }

    public void setData(ArrayList<GetInvestHistoryListModel.InvestListBean> al) {
        this.list = al;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetInvestHistoryListModel.InvestListBean> al) {
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

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private TextView tvName, tvMoney, tvTime, tvYuGu, tvFootOne, tvFootTwo,tv_amountLabel,tv_plstimeLimitLabel,tv_profitLabel;

        public MyViewHolder(View itemView) {
            super(itemView);
            progressBar =  itemView.findViewById(R.id.progress_foot_bar);
            tvName = (TextView) itemView.findViewById(R.id.touzijilu_item_name);
            tvMoney = (TextView) itemView.findViewById(R.id.touzijilu_item_momey);
            tvTime = (TextView) itemView.findViewById(R.id.touzijilu_item_time);
            tvYuGu = (TextView) itemView.findViewById(R.id.touzijilu_item_yugu);
            tvFootOne = (TextView) itemView.findViewById(R.id.touzijilu_item_foot_one);
            tvFootTwo = (TextView) itemView.findViewById(R.id.touzijilu_item_foot_two);

            tv_amountLabel = (TextView) itemView.findViewById(R.id.tv_amountLabel);
            tv_plstimeLimitLabel = (TextView) itemView.findViewById(R.id.tv_plstimeLimitLabel);
            tv_profitLabel = (TextView) itemView.findViewById(R.id.tv_profitLabel);
        }
    }
}
