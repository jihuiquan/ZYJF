package finan.heng.com.apps;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dynamic.foundations.common.assist.Log;
import com.dynamic.foundations.common.utils.StringUtils;
import com.mcxiaoke.bus.Bus;
import com.mcxiaoke.bus.annotation.BusReceiver;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.app.finance.fragment.InvestListTabFragment;
import finan.heng.com.apps.app.home.fragment.HomeFragment;
import finan.heng.com.apps.app.my.fragment.MyFragment;
import finan.heng.com.apps.app.presenter.MainPresenter;
import finan.heng.com.apps.app.ui.activity.CheckPhoneActivity;
import finan.heng.com.apps.app.ui.activity.ShowStepViewActivity;
import finan.heng.com.apps.app.ui.activity.UpdateActivity;
import finan.heng.com.apps.app.ui.fragment.MoreFragment;
import finan.heng.com.apps.app.view.IMainView;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.model.event.HomeInfoRefreshEvent;
import finan.heng.com.apps.model.event.ProductListRefreshEvent;
import finan.heng.com.apps.model.event.ShowStepViewEvent;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.SharedpreferenceUtil;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

/**
 * 主页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, IMainView {

    private LinearLayout llHome, llLicai, llMy, llMore;
    private ImageView ivHome, ivLicai, ivMy, ivMore;
    private TextView tvMy, tvLicai, tvHome, tvMore;
    private FrameLayout flMain;
    private boolean isFirst = true, isOne = true;
    private HomeFragment mHomeFragment;
    private InvestListTabFragment mfinanceFragment;
    private MyFragment mMyFragment;
    private MoreFragment moreFragment;
    MainPresenter presenter = new MainPresenter(this);
    private boolean isCancelUpdate = false;
    private boolean isDownload = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSelect(0);
        EventBus.getDefault().register(this);

        isNeedAppUpdate();//app是否要更新
    }

    /**
     * app更新判断
     */
    private void isNeedAppUpdate() {
        String description = SharedpreferenceUtil.getString("description");
        String link = SharedpreferenceUtil.getString("link");
        String version = SharedpreferenceUtil.getString("version");
        String type = SharedpreferenceUtil.getString("type");
        try {
            if (isNeedUpdate(getVersionName(), version)) {
                startActivityForResult(new Intent(MainActivity.this, UpdateActivity.class)
                        .putExtra(IConstants.Extra.UPDATE_TAG, type)
                        .putExtra(IConstants.Extra.UPDATE_URL, link)
                        .putExtra(IConstants.Extra.UPDATE_DESC, description)
                        .putExtra(IConstants.Extra.UPDATE_VERSION, version), IConstants.RequestCode.CODE_UPDATE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNeedUpdate(String curretVersion, String appVersion) {
        return curretVersion.compareTo(appVersion) < 0;
    }

    @Override
    protected boolean getIsTransparent() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (!isCancelUpdate && !isDownload) {
//                presenter.getUpdateInfo(getVersionName(), SystemUtils.getMetaData(getApplicationContext(), IConstants.Key.UMENG_META_DATA_NAME));
            } else {
                isCancelUpdate = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isShowStepView) {
            startActivity(new Intent(MainActivity.this, ShowStepViewActivity.class)
                    .putExtra(IConstants.Extra.EXTRA_SHOWSTEP_LOGO, R.mipmap.ic_showstep_logo)
            );
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isShowStepView = false;
    }

    private void initView() {
        llHome = (LinearLayout) findViewById(R.id.main_home_ll);
        ivHome = (ImageView) findViewById(R.id.main_home_iv);
        tvHome = (TextView) findViewById(R.id.main_home_tv);
        ivMore = (ImageView) findViewById(R.id.iv_tab_more);
        llLicai = (LinearLayout) findViewById(R.id.main_licai_ll);
        ivLicai = (ImageView) findViewById(R.id.main_licai_iv);
        tvLicai = (TextView) findViewById(R.id.main_licai_tv);
        llMore = (LinearLayout) findViewById(R.id.main_mo);
        llMy = (LinearLayout) findViewById(R.id.main_my_ll);
        ivMy = (ImageView) findViewById(R.id.main_my_iv);
        tvMy = (TextView) findViewById(R.id.main_my_tv);
        tvMore = (TextView) findViewById(R.id.tv_tab_more);
        llHome.setOnClickListener(this);
        llLicai.setOnClickListener(this);
        llMy.setOnClickListener(this);
        llMore.setOnClickListener(this);
        flMain = (FrameLayout) findViewById(R.id.fragment_ui);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_home_ll:
                EventBus.getDefault().post(new HomeInfoRefreshEvent());
                setSelect(0);
                break;
            case R.id.main_licai_ll:
                EventBus.getDefault().post(new ProductListRefreshEvent());
                setSelect(1);
                break;
            case R.id.main_my_ll:
                setSelect(2);
                break;
            case R.id.main_mo:
                setSelect(3);
                break;
        }
    }

    private void setSelect(int i) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (i) {
            case 0:
                hideFragment(transaction);
                clearClick();
                ivHome.setImageResource(R.drawable.iv_tab_home_focus);
                tvHome.setTextColor(getResources().getColor(R.color.red));
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.fragment_ui, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                transaction.commitAllowingStateLoss();
                if (!isOne) {
                    mHomeFragment.initUpData();
                } else {
                    isOne = false;
                }
                break;
            case 1:
                hideFragment(transaction);
                clearClick();
                ivLicai.setImageResource(R.drawable.iv_tab_finance_focus);
                tvLicai.setTextColor(getResources().getColor(R.color.red));
                if (mfinanceFragment == null) {
//                    mfinanceFragment = new AssetFragment(0, "-1");
                    mfinanceFragment = new InvestListTabFragment();
                    transaction.add(R.id.fragment_ui, mfinanceFragment);
                } else {
                    transaction.show(mfinanceFragment);
                }
                transaction.commitAllowingStateLoss();
//                if (!isFirst) {
//                    Bus.getDefault().post("heng_AssetFragment0");
//                    Bus.getDefault().post("heng_AssetFragment1");
//                    Bus.getDefault().post("heng_AssetFragment2");
//                    Bus.getDefault().post("heng_AssetFragment3");
//                } else {
//                    isFirst = false;
//                }
                break;
            case 2:

                MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                if (cacheData != null) {
                    getWallet2();

//                }
                    hideFragment(transaction);
                    clearClick();
                    ivMy.setImageResource(R.drawable.iv_tab_account_foucus);
                    tvMy.setTextColor(getResources().getColor(R.color.red));
                    if (mMyFragment == null) {
                        mMyFragment = new MyFragment();
                        transaction.add(R.id.fragment_ui, mMyFragment);
                    } else {
                        transaction.show(mMyFragment);
                    }
                    transaction.commitAllowingStateLoss();
                } else {
                    startActivity(new Intent(this, CheckPhoneActivity.class));
                }
                break;
            case 3:
                hideFragment(transaction);
                clearClick();
                ivMore.setImageResource(R.drawable.iv_tab_more_focus);
                tvMore.setTextColor(getResources().getColor(R.color.red));
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.fragment_ui, moreFragment);
                } else {
                    transaction.show(moreFragment);
                }
                transaction.commitAllowingStateLoss();
                break;
        }

    }

    private void clearClick() {
        ivHome.setImageResource(R.drawable.iv_tab_home);
        ivMy.setImageResource(R.drawable.iv_tab_account);
        ivLicai.setImageResource(R.drawable.iv_tab_finance);
        tvHome.setTextColor(getResources().getColor(R.color.txt_gray_tab));
        tvLicai.setTextColor(getResources().getColor(R.color.txt_gray_tab));
        tvMy.setTextColor(getResources().getColor(R.color.txt_gray_tab));
        tvMore.setTextColor(getResources().getColor(R.color.txt_gray_tab));
        ivMore.setImageResource(R.drawable.iv_tab_more);
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }

        if (mfinanceFragment != null) {
            transaction.hide(mfinanceFragment);
        }

        if (mMyFragment != null) {
            transaction.hide(mMyFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
    }

    @BusReceiver
    public void StringEvent(String event) {
        if (event.equals("LoginSuccess")) {
            getUserInfo();
            getWallet();
            setSelect(0);
        } else if ("PAY_SUCCESS".equals(event)) {
            getWallet();
        }
//        else if("exit".equals(event)){
//            setSelect(0);
//        }
    }

    public void getUserInfo() {
        new HttpHelper().getUserInfo().subscribe(new Action1<MyUserInfo>() {
            @Override
            public void call(MyUserInfo myUserInfo) {
                DataCache.instance.saveCacheData("heng", "MyUserInfo", myUserInfo);//保存用户信息
                Bus.getDefault().post("getUserInfoDone");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(MainActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    public void getWallet() {
        new HttpHelper().getMyWallet().subscribe(new Action1<MyWalletResponse>() {
            @Override
            public void call(MyWalletResponse myWalletResponse) {
                DataCache.instance.saveCacheData("heng", "MyWalletResponse", myWalletResponse);
                Bus.getDefault().post("getMyWalletFinish");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(MainActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    public void getWallet2() {
        new HttpHelper().getMyWallet().subscribe(new Action1<MyWalletResponse>() {
            @Override
            public void call(MyWalletResponse myWalletResponse) {
                DataCache.instance.saveCacheData("heng", "MyWalletResponse", myWalletResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
//                if (throwable instanceof RequestErrorThrowable) {
//                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
//                    ViewHelper.showToast(MainActivity.this, requestErrorThrowable.getMessage());
//                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getStringExtra("fromWeb") != null) {
            if (intent.getStringExtra("fromWeb").equals("fromWeb")) {
                setSelect(1);
            } else if (intent.getStringExtra("fromWeb").equals("gotoaccount")) {
                setSelect(2);
            }
        } else {
            setSelect(0);
        }

        super.onNewIntent(intent);
    }

    @Override
    public void viewUpdateView(boolean forceUpdate, String link, String description, String version) {
//        startActivityForResult(new Intent(MainActivity.this, UpdateActivity.class)
//                        .putExtra(IConstants.Extra.UPDATE_TAG, forceUpdate)
//                        .putExtra(IConstants.Extra.UPDATE_URL, link)
//                        .putExtra(IConstants.Extra.UPDATE_DESC, description)
//                        .putExtra(IConstants.Extra.UPDATE_VERSION, version), IConstants.RequestCode.CODE_UPDATE);
    }

    @Override
    public void setUpdateValue() {
        isDownload = false;
        isCancelUpdate = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == IConstants.ResultCode.CODE_UPDATE) {
                String url = data.getExtras().getString(IConstants.Extra.UPDATE_URL);
                Log.i("urltag", url);
                isDownload = true;
                presenter.downFile(MainActivity.this, StringUtils.trimToEmpty(url));
            } else if (resultCode == IConstants.ResultCode.CODE_CANCEL) {
                isCancelUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isShowStepView = false;

    public void onEvent(ShowStepViewEvent event) {
        com.orhanobut.logger.Logger.i("eventbus" + event.toString());
        isShowStepView = true;
    }
}
