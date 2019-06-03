package finan.heng.com.apps.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxiaoke.bus.Bus;
import com.umeng.analytics.MobclickAgent;

import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.heng.com.apps.utils.DialogUtil;
import finan.zhimabao.com.apps.BuildConfig;
import finan.zhimabao.com.apps.R;

/*
 * Created by hhm on 2017/1/7.
 */

public class BaseFragment extends Fragment {
    AlertDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bus.getDefault().register(this);
        initBaseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        loadingDialog = DialogUtil.getInstance().showLoading(getActivity());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void showLoadingDialog() {
        mBaseLoading = DialogUtil.getInstance().showLoading(getActivity());
    }

    protected void dismissLoadingDialog() {
        if (mBaseLoading != null && mBaseLoading.isShowing()) {
            mBaseLoading.dismiss();
            mBaseLoading = null;
        }
    }

    private void initBaseFragment() {
    }

    protected AlertDialog mBaseLoading;

    @Override
    public void onDestroy() {
        super.onDestroy();
        Bus.getDefault().unregister(this);
        dismissLoadingDialog();
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    public Toolbar toolbar;

    public void setUpToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backBtn = (RelativeLayout) view.findViewById(R.id.back_btn);
        actionView = toolbar.getRootView();
        toolbar.getBackground().setAlpha(255);
        View contentview = view.findViewById(R.id.top_bar);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentview.getLayoutParams();
        params.height = DeviceUtils.getStatusBarHeight(getActivity());
        contentview.setLayoutParams(params);
        backBtn.setVisibility(View.GONE);
        if (BuildConfig.isDebug) {
            titleText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DialogUtil.showDialog(getActivity());
                    return false;
                }
            });
        }
    }

    public void setBarTitle(String title) {
        titleText.setText(title);
    }

    protected View actionView;
    protected TextView titleText;
    protected RelativeLayout backBtn;
    public void toast(String message) {
        if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(message)) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isLogin() {
        try {
            LoginResponse cacheData = DataCache.instance.getCacheData("heng", "LoginResponse");
            if (cacheData == null) {
                return false;
            }
            String sessionId = cacheData.result.sessionId;
            return com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(sessionId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void showDialog(boolean isShow) {
        if (isShow) {
            if (loadingDialog != null && !loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        } else {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    public void showLoading() {
        showDialog(true);
    }

    // 标识view 是否初始化完成
    protected boolean isViewInit = false;

}
