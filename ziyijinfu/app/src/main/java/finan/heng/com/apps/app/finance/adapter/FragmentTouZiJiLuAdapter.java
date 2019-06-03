package finan.heng.com.apps.app.finance.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.model.GetProductDetailListModel;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/30 23:10
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class FragmentTouZiJiLuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context                  context;
    private ArrayList<GetProductDetailListModel.UserOrderListBean> list;


    public FragmentTouZiJiLuAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_touzijilu_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            try {
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                GetProductDetailListModel.UserOrderListBean userOrderListBean = list.get(position);
                myViewHolder.tvMoney.setText(userOrderListBean.getAmount());
                myViewHolder.tvTime.setText(userOrderListBean.getAddTime().length()>10?userOrderListBean.getAddTime().substring(0,10):userOrderListBean.getAddTime());
                myViewHolder.tvUser.setText(userOrderListBean.getUserAccount().substring(0,3)+"****"+userOrderListBean.getUserAccount().substring(7,11));
            }catch (Exception e){
                e.printStackTrace();
            }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void addData(ArrayList<GetProductDetailListModel.UserOrderListBean> al) {
        this.list.addAll(al);
        notifyDataSetChanged();
    }
    public void reFresh(ArrayList<GetProductDetailListModel.UserOrderListBean> al){
        list.clear();
        list.addAll(al);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView  tvUser, tvTime, tvMoney;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvUser = itemView.findViewById(R.id.fragment_touzijili_item_user);
            tvTime =  itemView.findViewById(R.id.fragment_touzijili_item_time);
            tvMoney =  itemView.findViewById(R.id.fragment_touzijili_item_money);
        }
    }
}
