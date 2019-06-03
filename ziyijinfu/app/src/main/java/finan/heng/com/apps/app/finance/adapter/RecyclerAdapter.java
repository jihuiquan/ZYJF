package finan.heng.com.apps.app.finance.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dynamic.foundations.common.utils.ArithUtils;

import java.util.ArrayList;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.app.finance.activity.ProductDetailModifyActivity;
import finan.heng.com.apps.app.home.activity.SetJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ShiMingRenZhengActivity;
import finan.heng.com.apps.app.home.activity.TouziActivity;
import finan.heng.com.apps.app.ui.activity.CheckPhoneActivity;
import finan.heng.com.apps.model.GetProductListModel;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.DensityUtil;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.heng.com.apps.utils.StringUtils;
import finan.heng.com.apps.widgets.CircleProgressView;
import finan.heng.com.apps.widgets.ProgressView;
import finan.zhimabao.com.apps.R;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/23 16:26
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    public ArrayList<GetProductListModel.ProductsListBean> arrayList = new ArrayList<>();
    public boolean httpOK;
    public RecyclerAdapter(Context context, ArrayList<GetProductListModel.ProductsListBean> al) {
        this.context = context;
        this.arrayList = al;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_product_home, parent, false);
            return new ViewHolder(inflate);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.refresh_foot_layout, parent, false);
            return new ViewHolder(view);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position == arrayList.size() && httpOK) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.progressBar.getLayoutParams();
            float density = context.getResources().getDisplayMetrics().density;
            lp.height = Math.round((float) 60 * density);
            holder.progressBar.setLayoutParams(lp);
//            holder.image.setImageResource(R.drawable.refresh_animlist);
//            AnimationDrawable animationDrawable = (AnimationDrawable) holder.image.getDrawable();
//            animationDrawable.start();
            return;
        }
        final GetProductListModel.ProductsListBean productsListBean = arrayList.get(position);

        /**投资期限*/
//        holder.tv_timeLimit.setText(productsListBean.getPlstimeLimitLabel());
        /**剩余可投*/
//        holder.tv_label.setText(productsListBean.getSurplusAmountLabel());
        holder.tvName.setText(productsListBean.getTitle());
        String status = productsListBean.getStatus();
        if(!TextUtils.isEmpty(status)){
            int i = Integer.parseInt(status);
            //20.预告中；30.募集中：立即出借；40+.已售罄
            if(i==20){
                holder.tv_immediateLoan.setBackgroundResource(R.drawable.shape_rect_white_stroke_yellow);
                holder.tv_immediateLoan.setTextColor(context.getResources().getColor(R.color.color_FDD912));
            }else if(i==30){
                holder.tv_immediateLoan.setBackgroundResource(R.drawable.shape_rect_white_stroke_blue);
                holder.tv_immediateLoan.setTextColor(context.getResources().getColor(R.color.color_5B9CF8));
            }if(i>=40){
                holder.tv_immediateLoan.setBackgroundResource(R.drawable.shape_rect_white_stroke_gray);
                holder.tv_immediateLoan.setTextColor(context.getResources().getColor(R.color.color_9F9F9F));
            }
            holder.tv_immediateLoan.setText(productsListBean.getButtonLabel());
        }

        switch (productsListBean.getStatus()) {// //-41.复审未通过；-11.初审未通过；10.待初审；11.初审通过；20.预告中；30.募集中；40.待复审；41.复审通过；50.还款中（计息中）；60.已完成；
            case "-41":
                break;
            case "-11":
                break;
            case "10":
                break;
            case "11":
                break;
            case "20":
//                holder.progressView.setVisibility(View.GONE);
//                holder.progressText.setVisibility(View.GONE);
                holder.iv_status.setImageResource(R.drawable.ic_product_status_pre);
//                holder.tv_label.setText("募集总额");
                setMoneyValue(holder, productsListBean.getProductsScale());
                break;
            case "30":
//                holder.progressView.setVisibility(View.VISIBLE);
//                holder.progressText.setVisibility(View.VISIBLE);
                holder.iv_status.setImageDrawable(null);
//                holder.tv_label.setText("剩余可投");
                setMoneyValue(holder, productsListBean.getSurplusAmount());
                break;
            case "40":
            case "41":
            case "50":
            case "60":
//                holder.progressView.setVisibility(View.VISIBLE);
//                holder.progressText.setVisibility(View.VISIBLE);
                holder.iv_status.setVisibility(View.VISIBLE);
                holder.iv_status.setImageResource(R.drawable.ic_product_status_over);
//                holder.tv_label.setText("募集总额");
                setMoneyValue(holder, productsListBean.getProductsScale());
                break;
        }
        SpannableStringBuilder rateText = new SpannableStringBuilder();
        double baseRate = ArithUtils.round(Double.parseDouble(productsListBean.getProfit()) * 100, 1);
        SpannableString label = new SpannableString("%");
        label.setSpan(new RelativeSizeSpan(0.7f), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        rateText.append(String.valueOf(IConstants.Formatter.rateFormat.format(baseRate))).append(label);
        if (productsListBean.isHasAddRate()) {
            double addRate = ArithUtils.round(Double.parseDouble(productsListBean.getProfitFloat()) * 100, 1);
            SpannableString addRates = new SpannableString("+" + IConstants.Formatter.rateFormat.format(addRate) + "%");
            addRates.setSpan(new RelativeSizeSpan(0.7f), 0, addRates.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            rateText.append(addRates);
        }else if(productsListBean.getCashRateShow()){
            if (!TextUtils.isEmpty(productsListBean.getCashRateProfit())){
                double addRate = ArithUtils.round(Double.parseDouble(productsListBean.getCashRateProfit()) * 100, 1);
                SpannableString addRates = new SpannableString("+" + IConstants.Formatter.rateFormat.format(addRate) + "%");
                addRates.setSpan(new RelativeSizeSpan(0.7f), 0, addRates.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                rateText.append(addRates);
            }
        }
        holder.tvRate.setText(rateText);

        if ("1".equals(productsListBean.getPlstimeLimitType())) {
            holder.tvTime.setText(productsListBean.getPlstimeLimitValue());
        } else if ("0".equals(productsListBean.getPlstimeLimitType())) {
            holder.tvTime.setText(productsListBean.getPlstimeLimitValue());
        }

        holder.tvTimeDW.setText(productsListBean.getPrdtimeLimitTypeLabel());//还款方式
        holder.tv_repaymentTypeLabel.setText(productsListBean.getRepaymentTypeLabel());//募集期限类型
        holder.tv_profitLabel.setText(productsListBean.getProfitLabel());//约定年化利率
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailModifyActivity.class);
                Log.i("id", productsListBean.getId() + "");
                intent.putExtra("id", productsListBean.getId());
                context.startActivity(intent);
            }
        });

        holder.ll_activity_label.setVisibility(StringUtils.isEmpty(productsListBean.getTags()) ? View.GONE : View.VISIBLE);
        try {

            String[] tags = productsListBean.getTags().split(",");
            holder.ll_activity_label.removeAllViews();
            for (int i = 0; i < tags.length; i++) {
                TextView textView1 = new TextView(context);
                textView1.setBackgroundResource(R.drawable.home_fragment_activity_tv_bg);
                textView1.setTextSize(12);
                textView1.setPadding(10, 10, 10, 10);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (i > 0){
                    layoutParams.setMargins(15, 0, 0, 0);
                }
                textView1.setLayoutParams(layoutParams);
                textView1.setText(tags[i]);
                textView1.setTextColor(context.getResources().getColor(R.color.txt_product_blue));
                if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(tags[i])) {
                    holder.ll_activity_label.addView(textView1);
                }
            }
//            holder.progressText.setText((int) (productsListBean.getInvestPercent() * 100)+"%");
//            holder.progressView.setProgress((int) (productsListBean.getInvestPercent() * 100));
        } catch (Exception e) {
            e.printStackTrace();
            holder.ll_activity_label.setVisibility(View.GONE);
        }
    }

    private void setMoneyValue(ViewHolder holder, String productsScale) {
        double surplusAmount;
        try {
            surplusAmount = Double.parseDouble(productsScale);
        } catch (Exception e) {
            surplusAmount = 0;
        }
//        if (surplusAmount > 10000) {
//            Double surplus = ArithUtils.div2point(surplusAmount, 10000, 2);
//            holder.tvLeft.setText(IConstants.Formatter.moneyFormat.format(surplus));
//        } else {
//            holder.tvLeft.setText((int) surplusAmount + "元");
//        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == arrayList.size() && httpOK) {
            return 1;
        }
        return 0;
    }
    public void setData(ArrayList<GetProductListModel.ProductsListBean> al) {
        this.arrayList = al;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetProductListModel.ProductsListBean> al) {
        this.arrayList.addAll(al);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (httpOK) {
            return arrayList.size() + 1;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName,  tvRate, tvTime, tvTimeDW,tv_repaymentTypeLabel,tv_profitLabel;
        private ImageView  iv_status;
        private ProgressBar progressBar;
        private View itemView;
        private LinearLayout ll_activity_label;
        private TextView tv_immediateLoan;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
//            secondLine = itemView.findViewById(R.id.second_line);
            progressBar =  itemView.findViewById(R.id.progress_foot_bar);
            tvName = itemView.findViewById(R.id.finance_adapter_item_name);//产品名称
            tvTime = itemView.findViewById(R.id.item_period);
            tvRate =  itemView.findViewById(R.id.finance_adapter_item_rate);//产品利率
            tvTimeDW =  itemView.findViewById(R.id.finance_adapter_item_times);
//            tvLeft = itemView.findViewById(R.id.finance_adapter_item_left);//剩余金额
//            progressText = itemView.findViewById(R.id.progress_text);
            ll_activity_label =  itemView.findViewById(R.id.ll_activity_label);
//            progressView = itemView.findViewById(R.id.progress_bar);
            iv_status =  itemView.findViewById(R.id.iv_status);
            tv_repaymentTypeLabel =  itemView.findViewById(R.id.tv_repaymentTypeLabel);
            tv_profitLabel =  itemView.findViewById(R.id.tv_profitLabel);
            tv_immediateLoan =  itemView.findViewById(R.id.tv_immediateLoan);
//            tv_label =  itemView.findViewById(R.id.tv_label);
//            tv_timeLimit =  itemView.findViewById(R.id.tv_timeLimit);
        }
    }
}
