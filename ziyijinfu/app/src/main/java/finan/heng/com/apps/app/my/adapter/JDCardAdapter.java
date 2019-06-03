package finan.heng.com.apps.app.my.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.CardModel;
import finan.zhimabao.com.apps.R;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 15:52
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class JDCardAdapter extends RecyclerView.Adapter<JDCardAdapter.MyViewHolder> {

    private List<CardModel> list;
    private Context                  context;
    private String icon;

    public JDCardAdapter(Context context, List<CardModel> list,String icon) {
        this.context = context;
        this.list = list;
        this.icon = icon;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_card_item, parent, false);
        return new MyViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", holder.cardNo.getText().toString().replaceAll("-",""));
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
//                Toast.makeText(context,"复制成功",Toast.LENGTH_SHORT).show();
                ViewHelper.showToast(context,"复制成功");
            }
        });
        holder.cardDate.setText(list.get(position).grant_time+"");
        holder.cardNo.setText(list.get(position).cdkey);
        boolean isAboutMoney = true;
        StringBuilder builder = new StringBuilder();
//        builder.append((int)(Float.parseFloat(list.get(position).cdkeyDenomination)));

        holder.cardValue.setText(list.get(position).cdkeyDenomination+"");
        holder.cardName.setText(list.get(position).cdkeyTypeName);
        switch (list.get(position).cdkeyType){
            case "-1":
                holder.cardMark.setText("￥");
                builder.append("元");
//
                break;
            case "0":
//                holder.cardMark.setText("天");
                builder.append("天");
                isAboutMoney = false;
                break;
            case "1":
//                holder.cardMark.setText("月");
                builder.append("月");
                isAboutMoney = false;
                break;
            case "2":
//                holder.cardMark.setText("年");
                builder.append("年");
                isAboutMoney = false;
                break;
        }
//        builder.append(list.get(position).cdkeyTypeName);
        holder.notAboutMoney.setText(builder.toString());
//        if (!isAboutMoney){
//            holder.cardMark.setVisibility(View.GONE);
//            holder.cardName.setVisibility(View.GONE);
//            holder.cardValue.setVisibility(View.GONE);
//            holder.notAboutMoney.setVisibility(View.VISIBLE);
////            holder.cardBackgroud.setBackgroundResource(R.drawable.yk_card);
//        }else {
//            holder.cardMark.setVisibility(View.VISIBLE);
//            holder.cardName.setVisibility(View.VISIBLE);
//            holder.cardValue.setVisibility(View.VISIBLE);
//            holder.notAboutMoney.setVisibility(View.GONE);
////            holder.cardBackgroud.setBackgroundResource(R.drawable.jdcard);
//        }
//        Glide.with(context).load(icon).into(new ViewTarget<RelativeLayout,GlideDrawable>(holder.cardBg){
//
//            @Override
//            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    holder.cardBg.setBackground(resource);
//                }else {
//                    holder.cardBg.setBackgroundDrawable(resource);
//                }
//            }
//        });
        Glide.with(context).load(icon).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.cardBackgroud.setBackground(resource);
                }else {
                    holder.cardBackgroud.setBackgroundDrawable(resource);
                }
            }
            @Override
            public void onLoadCleared(Drawable placeholder) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.cardBackgroud.setBackground(null);
                }else {
                    holder.cardBackgroud.setBackgroundDrawable(null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public void setData(List<CardModel> al) {
        this.list = al;

        notifyDataSetChanged();
    }

    public void addData(List<CardModel> al) {
        this.list.addAll(al);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView  cardNo, cardDate, copy,cardValue,cardMark,cardName,notAboutMoney;
        private RelativeLayout cardBg;
        private LinearLayout cardBackgroud;


        public MyViewHolder(View itemView) {
            super(itemView);
            cardNo =  itemView.findViewById(R.id.card_no);
            cardDate =  itemView.findViewById(R.id.card_date);
            copy =  itemView.findViewById(R.id.copy);
            cardValue =  itemView.findViewById(R.id.card_value);
//            cardBg = itemView.findViewById(R.id.card_bg_layout);
            cardMark =  itemView.findViewById(R.id.card_mark);
            cardName =  itemView.findViewById(R.id.card_name);
            notAboutMoney = itemView.findViewById(R.id.not_money);
            cardBackgroud =itemView.findViewById(R.id.card_bg);
        }
    }
}
