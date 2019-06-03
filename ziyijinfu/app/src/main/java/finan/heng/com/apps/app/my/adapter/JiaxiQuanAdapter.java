package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

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
public class JiaxiQuanAdapter extends RecyclerView.Adapter<JiaxiQuanAdapter.MyViewHolder> {

    private ArrayList<GetRedPackListModel.MyredPackListBean> list;
    private Context                  context;
    public boolean                  httpOK;
    private String ksy;

    public JiaxiQuanAdapter(Context context, ArrayList<GetRedPackListModel.MyredPackListBean> list, String isuser) {
        this.context = context;
        this.list = list;
        this.ksy=isuser;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.jiaxiquan_item_new, parent, false);
        return new MyViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


      /*  try {
            SpannableStringBuilder rateText = new SpannableStringBuilder();
            SpannableString label = new SpannableString("%");
            label.setSpan(new RelativeSizeSpan(0.7f), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            String subString = list.get(position).getBonuses();
            rateText.append(subString.substring(0,subString.length()-1)).append(label);
            holder.tv_jine.setText(rateText);
        }catch (Exception e){

            e.printStackTrace();
        }*/
        holder.tv_jine.setText(list.get(position).getBonuses());
        holder.tv_date.setText("("+list.get(position).getDays()+")");
        holder.tv_youxiaoqi.setText(list.get(position).getEndtime()
                .replace("\n","")
                .replaceAll("/","-")
                .replace("有效期至","有效期至:  "));
        holder.xinshou.setText(list.get(position).getTitle());
        holder.tv_danbitouzi.setText(list.get(position).getHbcondition()+", ");
        holder.tv_limit.setText(list.get(position).getHbcondition2());
        holder.tvTitle.setText(list.get(position).getSources()+"  ");
        if (ksy.equals("ysy")) {
//            holder.tv_date.setVisibility(View.GONE);
//            holder.statusImg.setVisibility(View.VISIBLE);
//            holder.tv_jine.setTextColor(Color.parseColor("#ff666666"));
//            holder.head.setBackgroundResource(R.drawable.interest_grey_1);
//            holder.body.setBackgroundResource(R.drawable.red_interest_gray_2);
//            holder.tail.setBackgroundResource(R.drawable.red_interest_gray_3);
            try {
                if (list.get(position).getDays().equals("已使用")){
                    holder.rl_jiaxiquan_bg.setBackgroundResource(R.drawable.be_overdue_bg);
                }else if (list.get(position).getDays().equals("已过期")){
                    holder.rl_jiaxiquan_bg.setBackgroundResource(R.drawable.be_overdue_bg);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
        /*    holder.tv_jine.setTextColor(Color.parseColor("#fff33446"));
            holder.head.setBackgroundResource(R.drawable.interest_normal_1);
            holder.body.setBackgroundResource(R.drawable.interest_normal_2);
            holder.tail.setBackgroundResource(R.drawable.interest_normal_3);
            holder.statusImg.setVisibility(View.GONE);
            holder.tv_date.setVisibility(View.VISIBLE);*/
            holder.rl_jiaxiquan_bg.setBackgroundResource(R.drawable.coupon);

        }
    }

    @Override
    public int getItemCount() {
//        if (httpOK) {
//            return list.size() + 1;
//        }
        return list.size();
    }

    public void setData(ArrayList<GetRedPackListModel.MyredPackListBean> al, String isuser) {
        this.list = al;
        this.ksy=isuser;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetRedPackListModel.MyredPackListBean> al, String isuser) {
        this.list.addAll(al);
        this.ksy=isuser;
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
        private TextView  tv_jine, tv_date, tv_youxiaoqi,xinshou,tv_danbitouzi,tv_limit;
//        private RelativeLayout rl_jiaxi_bg;
        ImageView head,statusImg;
        RelativeLayout body;
        LinearLayout tail;
        RelativeLayout rl_jiaxiquan_bg;
        TextView tvTitle;


        public MyViewHolder(View itemView) {
            super(itemView);
//            image = (ImageView) itemView.findViewById(R.id.image);
            head = itemView.findViewById(R.id.head);
            body = itemView.findViewById(R.id.body);
            tail = itemView.findViewById(R.id.tail);
            statusImg = itemView.findViewById(R.id.status_type);
            rl_jiaxiquan_bg = itemView.findViewById(R.id.rl_hongbao_bg);
            tv_jine = itemView.findViewById(R.id.tv_jine);
            tv_date =  itemView.findViewById(R.id.tv_date);
            tv_youxiaoqi =  itemView.findViewById(R.id.tv_youxiaoqi);
            xinshou =  itemView.findViewById(R.id.xinshou);
            tv_danbitouzi =  itemView.findViewById(R.id.tv_danbitouzi);
//            rl_jiaxi_bg =  itemView.findViewById(R.id.rl_jiaxi_bg);
            tv_limit = itemView.findViewById(R.id.tv_invest_limit);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
