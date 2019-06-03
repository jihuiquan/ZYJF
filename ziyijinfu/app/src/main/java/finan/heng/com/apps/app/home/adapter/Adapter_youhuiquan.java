package finan.heng.com.apps.app.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import finan.heng.com.apps.manager.entity.model.WelfareModel;
import finan.zhimabao.com.apps.R;

/**
 * Created by Administrator on 2017/4/28.
 */
public class Adapter_youhuiquan extends BaseAdapter {

    private String                                         money;
    private String                                         name;
    private Context                                        context;
    private List<WelfareModel> list;
    private int investLimit;

    /**
     * 创建接口
     */
    public Adapter_youhuiquan(Context context, List<WelfareModel> list, String mname, String money) {
        this.context = context;
        this.list = list;
        this.name = mname;
        this.money = money;
    }

    public void setInvestLimit(int investLimit) {
        this.investLimit = investLimit;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder holder;
        if (null == view){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_youhuiquan, viewGroup, false);
            holder.tv_jine =  view.findViewById(R.id.tv_jine);
            holder.tv_youxiaoqi =  view.findViewById(R.id.tv_date);
            holder.tvTitle =  view.findViewById(R.id.tv_title);
            holder.tv_date =  view.findViewById(R.id.tv_youxiaoqi);
            holder.xinshou =  view.findViewById(R.id.xinshou);
            holder.isselect =  view.findViewById(R.id.isselect);
            holder.tv_danbitouzi =  view.findViewById(R.id.tv_danbitouzi);
            holder.timeLimit =  view.findViewById(R.id.tv_invest_limit);
            holder.head = view.findViewById(R.id.head);
            holder.body = view.findViewById(R.id.body);
            holder.tail = view.findViewById(R.id.tail);
            holder.cardBg = view.findViewById(R.id.rl_hongbao_bg);
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }
        holder.tv_jine.setText(list.get(position).getBonuses());
        holder.tv_date.setText("("+list.get(position).getDays()+")");
        holder.tv_youxiaoqi.setText(list.get(position).getEndtime()
                .replace("\n","")
                .replaceAll("/","-")
                .replace("有效期至","有效期至:  "));
        holder.tv_danbitouzi.setText(list.get(position).getHbcondition()+", ");
        holder.timeLimit.setText(list.get(position).getHbcondition2());
        if (name.equals("红包")) {
            if (list.get(position).isSelect) {
//            holder.isselect.setVisibility(View.VISIBLE);
                holder.cardBg.setBackgroundResource(R.drawable.redenvelopes);
            } else {
//            holder.isselect.setVisibility(View.GONE);
                holder.cardBg.setBackgroundResource(R.drawable.red_notselect);
            }
            if (!list.get(position).isAvailable()) {
                holder.cardBg.setBackgroundResource(R.drawable.be_overdue_bg);
            }
            holder.xinshou.setText("投资红包");
            holder.tvTitle.setText(list.get(position).getSources()+"￥");
        }else {
            if (list.get(position).isSelect) {
//            holder.isselect.setVisibility(View.VISIBLE);
                holder.cardBg.setBackgroundResource(R.drawable.coupon);
            } else {
//            holder.isselect.setVisibility(View.GONE);
                holder.cardBg.setBackgroundResource(R.drawable.notselect);
            }
            if (!list.get(position).isAvailable()) {
                holder.cardBg.setBackgroundResource(R.drawable.be_overdue_bg);
            }
            holder.xinshou.setText("加息券");
            holder.tvTitle.setText(list.get(position).getSources()+"  ");
        }

        return view;
    }

    class Holder{
        TextView tv_jine;
        TextView tv_youxiaoqi;
        TextView tv_date;
        TextView xinshou;
        ImageView isselect;
        RelativeLayout rlbg;
        TextView tv_danbitouzi;
        TextView timeLimit;
        ImageView head;
        RelativeLayout body;
        LinearLayout tail;
        TextView tvTitle;
        RelativeLayout cardBg;
    }
}
