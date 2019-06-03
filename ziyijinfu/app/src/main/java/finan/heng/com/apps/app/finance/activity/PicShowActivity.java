package finan.heng.com.apps.app.finance.activity;
/*
 * Created by hhm on 2017/5/3.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

public class PicShowActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private GridView          gridView;
    private ArrayList<String> mStrings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_show);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("");
        initView();
        init();
    }

    private void init() {
        mStrings = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            mStrings.add("a");
        }
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, mStrings);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(this);
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.activity_pic_show_gridview);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, BigPicActivity.class);
        intent.putExtra("num", "" + position);
        intent.putExtra("size", "" + mStrings.size());
        startActivity(intent);
    }

    class GridViewAdapter extends BaseAdapter {

        private Context           context;
        private ArrayList<String> list;

        public GridViewAdapter(Context context, ArrayList<String> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.pic_show_item, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.iv.setImageResource(R.mipmap.icon);
            return viewHolder.view;
        }

        class ViewHolder {
            private View      view;
            private ImageView iv;

            public ViewHolder(View view) {
                this.view = view;
                iv = (ImageView) view.findViewById(R.id.pic_show_item_iv);
            }
        }
    }
}
