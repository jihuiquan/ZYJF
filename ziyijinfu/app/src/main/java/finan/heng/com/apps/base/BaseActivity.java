package finan.heng.com.apps.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxiaoke.bus.Bus;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import finan.heng.com.apps.utils.DeviceUtils;
import finan.heng.com.apps.utils.DialogUtil;
import finan.zhimabao.com.apps.R;

/*
 * Created by hhm on 2016/12/23.
 */

public class BaseActivity extends AppCompatActivity {
    protected static Handler handler = new Handler(Looper.getMainLooper());

    private View actionView;
    private TextView titleText;
    private RelativeLayout backBtn;
    protected RelativeLayout rl_content;
    protected AlertDialog mBaseLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bus.getDefault().register(this);
        FlymeSetStatusBarLightMode(getWindow(), true);
        MIUISetStatusBarLightMode(getWindow(), true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        }
        applyTheme(getIsTransparent());
//        Logger.i("activity" + this.getClass().getName() + "----" + "onCreate");

    }

    protected void showLoadingDialog() {
        mBaseLoading = DialogUtil.getInstance().showLoading(this);
    }

    protected void dismissLoadingDialog() {
        if (mBaseLoading != null && mBaseLoading.isShowing()) {
            mBaseLoading.dismiss();
            mBaseLoading = null;
        }
    }

    public Toolbar toolbar;

    public void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleText = (TextView) findViewById(R.id.title_text);
        backBtn = (RelativeLayout) findViewById(R.id.back_btn);
        rl_content = (RelativeLayout) findViewById(R.id.rl_content);
        actionView = toolbar.getRootView();
        toolbar.getBackground().setAlpha(255);
        setSupportActionBar(toolbar);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        try {
            View view = findViewById(R.id.top_bar);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
            params.height = DeviceUtils.getStatusBarHeight(getApplicationContext());
            view.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setBarTitle(String title) {
        titleText.setText(title);
    }

    public void showBackBtn(boolean isShow) {
        if (isShow) {
            backBtn.setVisibility(View.VISIBLE);
        } else {
            backBtn.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Logger.i("activity" + this.getClass().getName() + "----" + "onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
//        Logger.i("activity" + this.getClass().getName() + "----" + "onStop");

    }

    /*
     * 显示透明状态栏
     */
    protected boolean getIsTransparent() {
        return false;
    }

    protected void applyTheme(boolean isTransparent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//                Window window = getWindow();


            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

         /*   if (isTransparent) {
//                window.setNavigationBarColor(Color.TRANSPARENT);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                window.setStatusBarColor(Color.parseColor("#77000000"));
            }*/
        }
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

//    public void addLoadingFragment(int viewId, String event) {
//        LoadingFragment mLoadingFragment = LoadingFragment.newInstance(event);
//        getFragmentManager().beginTransaction().replace(viewId, mLoadingFragment, LoadingFragment.TAG).commitAllowingStateLoss();
//    }

//    public void removeLoadingFragment() {
//        LoadingFragment mLoadingFragment = findLoadingFragment();
//        if (mLoadingFragment != null) {
//            mLoadingFragment.removeSelf(getFragmentManager());
//        }
//    }


//    public LoadingFragment findLoadingFragment() {
//        Fragment fragment = getFragmentManager().findFragmentByTag(LoadingFragment.TAG);
//        if (fragment != null) {
//            return (LoadingFragment) fragment;
//        }
//        return null;
//    }

    /*
     * 显示键盘
     */
    public void showkeybord(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /*
     * 隐藏键盘
     */
    public void hintkeybord(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onResume(this);
//        Logger.i("activity" + this.getClass().getName() + "----" + "onResume");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        Logger.i("activity" + this.getClass().getName() + "----" + "onNewIntent");

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
        MobclickAgent.onPause(this);
//        Logger.i("activity" + this.getClass().getName() + "----" + "onPause");

    }

    @Override
    protected void onDestroy() {
        Bus.getDefault().unregister(this);
        dismissLoadingDialog();
        super.onDestroy();
//        Logger.i("activity" + this.getClass().getName() + "----" + "onDestroy");
    }

    /**
     * 获取应用版本号
     *
     * @return
     * @throws Exception
     */
    protected String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    public void toast(String message) {
        Toast.makeText(BaseActivity.this, com.dynamic.foundations.common.utils.StringUtils.trimToEmpty(message), Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
//        showDialog(true);
    }
}
