package com.dynamic.foundations.view;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.dynamic.foundations.R;

import java.util.List;

/**
 * Created by liuyang on 15/9/16.
 */
public abstract class BaseListAdapter<T extends IListItem, E> extends BaseAdapter {
    List<?> datalist;
    Context mContext;
    int mLayout;
    private LayoutInflater mInflater;

    public BaseListAdapter(Context context, int layout, List datalist) {
        this.mContext = context;
        this.mLayout = layout;
        this.datalist = datalist;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datalist == null ? 0 : datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseListAdapter.ViewHolder holder = new BaseListAdapter.ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(mLayout, null);
            IListItem listItem = (IListItem) convertView;
            listItem.setupChildView();
            holder.view = listItem.getView();
            if(holder.view!=null)
            holder.view.setTag(-1, holder);
        }
        bindView(convertView, mContext, datalist.get(position));
        return convertView;
    }

    public static class ViewHolder {
        public View view;

        public ViewHolder() {
        }
    }

//    private View fetchView(int layout, ViewGroup parent) {
//        return mInflater.inflate(layout, parent, false);
//    }

    public final void bindView(View view, Context context, Object object) {
        BaseListAdapter.ViewHolder holder = (BaseListAdapter.ViewHolder) view.getTag(-1);
        this.populateListItem((T) holder.view, context, object);
    }


    protected abstract E populateListItem(T listItem, Context context, Object object);

    public List<?> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<?> datalist) {
        this.datalist = datalist;
    }

    public void refresh() {
        this.notifyDataSetChanged();
    }

    public void refreshData(List<?> datalist) {
        setDatalist(datalist);
        notifyDataSetChanged();
    }
}
