package finan.heng.com.apps.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dynamic.foundations.widget.listview.PullToRefreshListView;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.Bind;
import butterknife.ButterKnife;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.app.ui.fragment.*;

/**
 * sparkhuu
 */
public abstract class BaseListFragment extends finan.heng.com.apps.app.ui.fragment.BaseFragment {
    @Bind(R.id.list_main_product)
    PullToRefreshListView listView;

    @Bind(R.id.progress_view)
    CircularProgressView progressView;
    @Bind(R.id.iv_emptyview)
    ImageView ivEmptyview;

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    abstract public void setupViews(View root);

    public PullToRefreshListView getListView() {
        return listView;
    }

    public CircularProgressView getProgressView() {
        return progressView;
    }

    public void showProgressView(boolean b) {
        if (progressView == null) {
            return;
        }
        progressView.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
