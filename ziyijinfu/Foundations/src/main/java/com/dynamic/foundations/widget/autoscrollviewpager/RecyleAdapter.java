package com.dynamic.foundations.widget.autoscrollviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dynamic.foundations.widget.autoscrollviewpager.RecyclingPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyleAdapter extends RecyclingPagerAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<View> mListViews;
    private boolean isLoop = true;
    RecyclingPagerAdapter.DataChangeListener mDataChangeListener;

    public RecyleAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mListViews = new ArrayList<View>();
    }

    public int getRealCount() {
        return mListViews.size();
    }

    public <T extends View> void addSlider(T view) {
        mListViews.add(view);
        notifyDataSetChanged();
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    public int getPosition(int position) {
        return isLoop ? position % getRealCount() : position;
    }

    @Override
    public int getCount() {
        return isLoop ? getRealCount() * 100 : getRealCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        return mListViews.get(getPosition(position));
    }

    public <T extends View> void removeSlider(T slider) {
        if (mListViews.contains(slider)) {
            mListViews.remove(slider);
            notifyDataSetChanged();
        }
    }

    public void removeSliderAt(int position) {
        if (mListViews.size() < position) {
            mListViews.remove(position);
            notifyDataSetChanged();
        }
    }

    public void removeAllSliders() {
        mListViews.clear();
        notifyDataSetChanged();
    }




    /**
     * @return the is Loop
     */
    public boolean isLoop() {
        return isLoop;
    }

    /**
     * @param isLoop the is InfiniteLoop to set
     */
    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        notifyDataSetChanged();
    }

}
