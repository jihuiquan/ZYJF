package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dynamic.foundations.common.assist.Log;

import java.util.ArrayList;
import java.util.logging.Logger;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.model.GetRedPackListModel;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 15:52
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class XianJinHongBaoAdapter extends RecyclerView.Adapter<XianJinHongBaoAdapter.MyViewHolder> {

    private ArrayList<GetRedPackListModel.MyredPackListBean> list;
    private Context context;
    public boolean httpOK;
    private String isuser;

    public XianJinHongBaoAdapter(Context context, ArrayList<GetRedPackListModel.MyredPackListBean> list, String useOrNoUse) {
        this.context = context;
        this.list = list;
        this.isuser = useOrNoUse;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.youhuiquan_item, parent, false);
            return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_banlance.setText(list.get(position).getBonuses());
        holder.tv_jine.setText(list.get(position).getHbcondition()+","+list.get(position).getHbcondition2());
        holder.hongbao_name.setText(list.get(position).getSources()+"");
        String endtime = list.get(position).getEndtime();
        String subEndtime = endtime.substring(5, endtime.length()).replaceAll("/","-");
        holder.tv_youxiaoqi.setText(subEndtime);
        if (isuser.equals("ysy")) {
         /*   holder.tv_banlance.setTextColor(Color.parseColor("#666666"));
            holder.tv_label.setTextColor(Color.parseColor("#666666"));
            holder.head.setBackgroundResource(R.drawable.red_pack_gray_1);
            holder.body.setBackgroundResource(R.drawable.red_interest_gray_2);
            holder.tail.setBackgroundResource(R.drawable.red_interest_gray_3);
            holder.statusType.setVisibility(View.VISIBLE);*/
            if (list.get(position).getDays().equals("已使用")){
                holder.rl_hongbao_bg.setBackgroundResource(R.drawable.be_overdue_bg);
            }else if (list.get(position).getDays().equals("已过期")){
                holder.rl_hongbao_bg.setBackgroundResource(R.drawable.be_overdue_bg);
            }

        } else {
            holder.statusType.setVisibility(View.GONE);
         /*   holder.head.setBackgroundResource(R.drawable.red_pack_red_1);
            holder.body.setBackgroundResource(R.drawable.red_pack_red_2);
            holder.tail.setBackgroundResource(R.drawable.red_pack_red_3);
            holder.tv_label.setTextColor(Color.parseColor("#f33446"));
            holder.tv_banlance.setTextColor(Color.parseColor("#f33446"));*/
            holder.rl_hongbao_bg.setBackgroundResource(R.drawable.redenvelopes);
        }

    }

    @Override
    public int getItemCount() {
//        if (httpOK) {
//            return list.size() + 1;
//        }
        return list.size();
    }

    public void setData(ArrayList<GetRedPackListModel.MyredPackListBean> al, String useOrNoUse) {
        this.list = al;
        this.isuser = useOrNoUse;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetRedPackListModel.MyredPackListBean> al, String useOrNoUse) {
        this.list.addAll(al);
        this.isuser = useOrNoUse;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
//        if (position == list.size() && httpOK) {
//            return 1;
//        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

//        private ImageView image;
        private TextView tv_jine, hongbao_name, tv_youxiaoqi, xinshou, tv_danbitouzi,tvLimit,tv_banlance,tv_label;
        private RelativeLayout rl_hongbao_bg;
        private ImageView head,statusType;
        private RelativeLayout body,tail;

        public MyViewHolder(View itemView) {
            super(itemView);
//            image = (ImageView) itemView.findViewById(R.id.image);
            tv_jine =  itemView.findViewById(R.id.tv_jine);
            hongbao_name = (TextView) itemView.findViewById(R.id.hongbao_name);
            tv_youxiaoqi = itemView.findViewById(R.id.tv_youxiaoqi);
//            xinshou = (TextView) itemView.findViewById(R.id.xinshou);
//            tv_danbitouzi = (TextView) itemView.findViewById(R.id.tv_danbitouzi);
            rl_hongbao_bg = itemView.findViewById(R.id.rl_hongbao_bg);
//            tvLimit = (TextView) itemView.findViewById(R.id.tv_invest_limit);
//            rl_banlance = itemView.findViewById(R.id.rl_banlance);
            tv_banlance = itemView.findViewById(R.id.tv_banlance);
            tv_label = itemView.findViewById(R.id.tv_label);
            head = itemView.findViewById(R.id.head);
            body = itemView.findViewById(R.id.body);
//            tail = itemView.findViewById(R.id.tail);
            statusType = itemView.findViewById(R.id.status_type);
        }
    }
}
