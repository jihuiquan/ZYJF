package finan.heng.com.apps.app.my.adapter;
/*
 * Created by hhm on 2017/5/5.
 */

import android.content.Context;
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
import finan.heng.com.apps.model.GetInviteHistoryModel;

public class InviteFriendRegisterAdapter extends RecyclerView.Adapter<InviteFriendRegisterAdapter.MyViewHolder> {
    public  boolean                     httpOK;
    private Context                     context;
    private ArrayList<GetInviteHistoryModel.Bean> list;

    public InviteFriendRegisterAdapter(Context context, ArrayList<GetInviteHistoryModel.Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.invite_register_item, parent, false);
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
        GetInviteHistoryModel.Bean bean = list.get(position);
        holder.mTvOne.setText(bean.tjyh);
        holder.mTvTwo.setText(bean.state);
        holder.mTvThree.setText(bean.time.length()>10?bean.time.substring(0,10):bean.time);
        holder.mTvFour.setText(bean.fljl);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size() && httpOK) {
            return 1;
        }
        return 0;
    }

    public void setData(ArrayList<GetInviteHistoryModel.Bean> al) {
        this.list = al;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetInviteHistoryModel.Bean> al) {
        this.list.addAll(al);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (httpOK) {
            return list.size() + 1;
        }
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private TextView  mTvOne, mTvTwo, mTvThree, mTvFour;

        public MyViewHolder(View itemView) {
            super(itemView);
            progressBar =  itemView.findViewById(R.id.progress_foot_bar);
            mTvOne = (TextView) itemView.findViewById(R.id.invite_register_item_tv_one);
            mTvTwo = (TextView) itemView.findViewById(R.id.invite_register_item_tv_two);
            mTvThree = (TextView) itemView.findViewById(R.id.invite_register_item_tv_three);
            mTvFour = (TextView) itemView.findViewById(R.id.invite_register_item_tv_four);
        }
    }
}
