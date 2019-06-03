package finan.heng.com.apps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.util.AbJsonUtil;
import com.ab.util.AbSharedUtil;
import com.dynamic.foundations.common.assist.Log;
import com.dynamic.foundations.common.utils.StringUtils;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.app.finance.fragment.ImageDialogFragment;
import finan.heng.com.apps.app.home.activity.EvaluationSuccessActivity;
import finan.heng.com.apps.app.home.activity.RiskEvaluationActivity;
import finan.heng.com.apps.app.my.activity.CardActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiListActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiOrTiXianActivity;
import finan.heng.com.apps.app.my.activity.InviteFriendActivity;
import finan.heng.com.apps.app.my.activity.RegisterActivity;
import finan.heng.com.apps.app.my.activity.YouHuiQuanActivity;
import finan.heng.com.apps.app.my.fragment.BottomDialogFragment;
import finan.heng.com.apps.app.ui.activity.CheckPhoneActivity;
import finan.heng.com.apps.app.ui.activity.VerifyInfoActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.http.KeyHolder;
import finan.heng.com.apps.manager.entity.model.CommonHttpModel;
import finan.heng.com.apps.model.EvaluationSubmitResult;
import finan.heng.com.apps.model.GetCompanyInfoResponse;
import finan.heng.com.apps.model.H5Demo;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.ShareModel;
import finan.heng.com.apps.model.event.AccountRefreshEvent;
import finan.heng.com.apps.model.event.PurchaseEvent;
import finan.heng.com.apps.save.DataCache;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 11:05
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class WebActivity extends BaseActivity {

    private MyWebView webView;
    private String mUrl;
    private String mTitle;

//    private String js = "var btn=document.getElementById('btn');  btn.onclick = function(){ obj.invite()}";

    private String js = "function hi(){alert('hello');}";
    private static final int LOGIN = 0x101;
    private static final int LOGIN_INVITE = 0x102;
    private String url;
    private CircularProgressView progressView;
    private String payType;
    private Map<String, String> map = new HashMap();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setUpToolbar();
        map.put(IConstants.Header.APPCHANNEL, KeyHolder.getInstance().getAppChannel());
        map.put(IConstants.Header.DEVICEMODEL, KeyHolder.getInstance().getDeviceModel());
        map.put(IConstants.Header.OSTYPE, KeyHolder.getInstance().getOSType());
        map.put(IConstants.Header.OSVERSION, KeyHolder.getInstance().getOSVersion());
        map.put(IConstants.Header.APPVERSION, KeyHolder.getInstance().getAppVersion());

        progressView = (CircularProgressView) findViewById(R.id.progress_view);
        getSupportActionBar().setTitle("");
        url = getIntent().getStringExtra(IConstants.Extra.URL) + "";
        payType = getIntent().getStringExtra(IConstants.Extra.PAY_PURCHASE);
        if (payType == null) {
            payType = "";
        }
        Logger.i("url" + url);
        mTitle = getIntent().getStringExtra(IConstants.Extra.TITLE);
        setBarTitle(mTitle);
        initView();
        init();
    }

    private void syncCookie() {
        LoginResponse response = DataCache.instance.getCacheData("heng", "LoginResponse");
        if (response != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                CookieSyncManager.createInstance(getApplicationContext());
            }
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.setCookie(mUrl, "jsessionid=" + response.result.sessionId);//如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
        }
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }
        webView.getSettings().setJavaScriptEnabled(true);// 设置支持javascript脚本
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportZoom(true);// 支持缩放
        webView.getSettings().setBuiltInZoomControls(true);// 设置显示缩放按钮
        webView.getSettings().setUseWideViewPort(true);//关键点
        webView.getSettings().setLoadWithOverviewMode(true);//最小显示

        webView.getSettings().setCacheMode(LOAD_NO_CACHE);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webView.addJavascriptInterface(new JSObject(), "obj");
        webView.addJavascriptInterface(new JSObject(), "android");
        String ua = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(ua + "android/zy");
        webView.setWebViewClient(new MyWebViewClient() {
            @Override//点击时的
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url, map);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                webView.loadUrl("javascript:" + js);
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressView.setVisibility(View.VISIBLE);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title.contains("Error")) {
                    webView.setVisibility(View.GONE);
                }
            }
        });
        String sessionId = "";
//        syncCookie();
        LoginResponse response = DataCache.instance.getCacheData("heng", "LoginResponse");
        if (response != null) {
            if (response.result != null) {
                sessionId = StringUtils.trimToEmpty(response.result.sessionId);
            }
        }
        if (url.contains("mixApp")) {
            if (url.contains("?")) {
                mUrl = url + "&sessionId=" + sessionId + "&version=" + AbSharedUtil.getString(WebActivity.this, IConstants.Key.VERSION);
            } else {
                mUrl = url + "?sessionId=" + sessionId + "&version=" + AbSharedUtil.getString(WebActivity.this, IConstants.Key.VERSION);
            }
            rl_content.setVisibility(View.GONE);
        } else {
            mUrl = url;
            rl_content.setVisibility(View.VISIBLE);
        }
        if (StringUtils.isEmpty(mUrl)) {
            toast("url is empty");
        }
        Logger.i("url" + mUrl);
        webView.loadUrl(mUrl, map);
    }

    private void initView() {
        webView = (MyWebView) findViewById(R.id.activity_web_web);
    }
    // 设置cookie

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.setVisibility(View.GONE);
            long timeout = ViewConfiguration.getZoomControlsTimeout();//timeout ==3000
            Log.i("time==", timeout + "");
            webView.postDelayed(new Runnable() {

                @Override
                public void run() {
                    try {
                        webView.destroy();
                    } catch (Exception ex) {

                    }
                }
            }, timeout);
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LOGIN:
                try {
                    LoginResponse response = DataCache.instance.getCacheData("heng", "LoginResponse");

                    String sessionId = "";
                    if (response != null) {
                        sessionId = response.result.sessionId;
                    }
                    if (url.contains("mixApp")) {
                        if (url.contains("?")) {
                            mUrl = url + "&sessionId=" + sessionId + "&version=" + AbSharedUtil.getString(WebActivity.this, IConstants.Key.VERSION);
                        } else {
                            mUrl = url + "?sessionId=" + sessionId + "&version=" + AbSharedUtil.getString(WebActivity.this, IConstants.Key.VERSION);
                        }
                    } else {
                        mUrl = url;
                    }
                    Logger.i("url" + mUrl);
                    webView.loadUrl(mUrl, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case LOGIN_INVITE:
                LoginResponse response = DataCache.instance.getCacheData("heng", "LoginResponse");
                if (response != null) {//已登录,否则不跳转
                    startActivity(new Intent(WebActivity.this, InviteFriendActivity.class));
                }

                break;
        }
    }

    class JSObject {

        @JavascriptInterface
        public void invite() {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN_INVITE);
            } else {
                startActivity(new Intent(WebActivity.this, InviteFriendActivity.class));
            }
        }

        @JavascriptInterface
        public void login() {
            startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN);

        }

        @JavascriptInterface
        public void register() {
            startActivityForResult(new Intent(WebActivity.this, RegisterActivity.class), LOGIN);
            finish();
        }

        @JavascriptInterface
        public void invest() {
            Intent intent = new Intent(WebActivity.this, MainActivity.class);
            intent.putExtra("fromWeb", "fromWeb");
            startActivity(intent);
            finish();
        }

        /**
         * 去首页
         */
        @JavascriptInterface
        public void appToIndex() {
            Intent intent = new Intent(WebActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        /**
         * 去登录
         */
        @JavascriptInterface
        public void appToLogin() {
            startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN);

        }

        /**
         * 去注册
         */
        @JavascriptInterface
        public void appToRegister() {
            startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN);
            finish();
        }

        /**
         * 去实名认证
         */
        @JavascriptInterface
        public void appToRealName() {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN);
            } else {
                startActivity(new Intent(WebActivity.this, VerifyInfoActivity.class));
            }
            finish();
        }

        /**
         * 去充值
         */
        @JavascriptInterface
        public void appToRecharge() {
            startActivity(new Intent(WebActivity.this, ChongZhiListActivity.class)
                    .putExtra(IConstants.Extra.RECHARGE_TYPE, "1")
            );
            finish();
        }

        /**
         * 去产品列表页
         */
        @JavascriptInterface
        public void appToGetList() {
            startActivity(new Intent(WebActivity.this, MainActivity.class)
                    .putExtra("fromWeb", "fromWeb")
            );
            finish();
        }

        // 重新刷新个人中心页面
        @JavascriptInterface
        public void appToReloadAccount() {
            EventBus.getDefault().post(new AccountRefreshEvent());
            finish();
        }

        /**
         * 向app传值
         *
         * @param json
         */
        @JavascriptInterface
        public void appAcceptVal(String json) {

        }

        /**
         * 向app传值并关闭当前webview
         */
        @JavascriptInterface
        public void appPassVal(String json) {
            finish();
        }

        /**
         * 打开进度条
         */
        @JavascriptInterface
        public void appOpenLoading() {
            progressView.post(new Runnable() {
                @Override
                public void run() {
                    progressView.setVisibility(View.VISIBLE);
                }
            });

        }

        /**
         * 关闭进度条
         */
        @JavascriptInterface
        public void appCloseLoading() {
            progressView.post(new Runnable() {
                @Override
                public void run() {
                    progressView.setVisibility(View.GONE);
                }
            });

        }

        /**
         * 打开新的webview
         */
        @JavascriptInterface
        public void appOpenView(String url) {
            Logger.i("result" + url);
//            H5Demo demo = (H5Demo) AbJsonUtil.fromJson(url, H5Demo.class);
            startActivity(new Intent(WebActivity.this, WebActivity.class)
                    .putExtra(IConstants.Extra.URL, url));
        }

        /**
         * 关闭新的webview
         */
        @JavascriptInterface
        public void appCloseView() {
            finish();
        }

        /**
         * 分享
         */
        @JavascriptInterface
        public void appShare() {
            Logger.i("hybrid" + "appshare");
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN_INVITE);
            } else {
                startActivity(new Intent(WebActivity.this, InviteFriendActivity.class));
            }
        }

        /**
         * 分享
         */
        @JavascriptInterface
        public void appToShare() {
            Logger.i("hybrid" + "appshare");
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN_INVITE);
            } else {
                startActivity(new Intent(WebActivity.this, InviteFriendActivity.class));
            }
        }

        @JavascriptInterface
        public void appShare(String list) {
            Gson gs = new Gson();
            ShareModel shareModel = gs.fromJson(list, ShareModel.class);
            BottomDialogFragment dialogFragment = new BottomDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", shareModel.getTitle());
            bundle.putString("content", shareModel.getDescription());
            bundle.putString("url", shareModel.getUrl());
            bundle.putString("shareImageUrl", shareModel.getUrl());
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(), "dialog");
        }
//        /**
//         * 去投资页
//         */
//        @JavascriptInterface
//        public void appToPurchase() {
//            Logger.i("hybrid" + "appToPurchase");
//        }

        /**
         * 去风险测评页
         */
        @JavascriptInterface
        public void appToRiskAssessment() {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN);
            } else {
                if (TextUtils.isEmpty(cacheData.result.investStyle)) {//未测评
                    startActivity(new Intent(WebActivity.this, RiskEvaluationActivity.class));
                } else {
                    getData(cacheData.result.investStyle);
                }
            }
        }

        /**
         * 去充值页
         */
        @JavascriptInterface
        public void appToCharge() {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN);
            } else {
                startActivity(new Intent(WebActivity.this, ChongZhiOrTiXianActivity.class).putExtra("type", 1));
            }
        }

        /**
         * 去我的红包页
         */
        @JavascriptInterface
        public void appToRedPackage() {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN);
            } else {
                startActivity(new Intent(WebActivity.this, YouHuiQuanActivity.class));
            }
        }

        /**
         * 去礼品卡页
         */
        @JavascriptInterface
        public void appToGiftCard() {
            MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
            if (cacheData == null) {
                startActivityForResult(new Intent(WebActivity.this, CheckPhoneActivity.class), LOGIN);
            } else {
                startActivity(new Intent(WebActivity.this, CardActivity.class));
            }
        }

        @JavascriptInterface
        public void appEnlarge(String img) {
            ImageDialogFragment dialogFragment = new ImageDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", img);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(), "tag");
        }



        /*以下从PayH5中copy过来的-begin*/

        @JavascriptInterface
        public void recharge() {
            Intent intent = new Intent(WebActivity.this, ChongZhiOrTiXianActivity.class);
            intent.putExtra(IConstants.Extra.RECHARGE_TYPE, 1);
            startActivity(intent);
            finish();

        }

//        @JavascriptInterface
//        public void invest() {
//            Intent intent = new Intent(PayH5Activity.this, MainActivity.class);
//            intent.putExtra("fromWeb", "fromWeb");
//            startActivity(intent);
//            finish();
//        }

//        /**
//         * 去首页
//         */
//        @JavascriptInterface
//        public void appToIndex() {
//            Logger.i("result" + "appToIndex");
//            Intent intent = new Intent(PayH5Activity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        /**
         * 去投资页
         */
        @JavascriptInterface
        public void appToPurchase() {
            Logger.i("hybrid" + "appToPurchase");
            if (StringUtils.equals(payType, IConstants.Extra.PAY_PURCHASE)) {
                EventBus.getDefault().post(new PurchaseEvent());
                finish();
            } else {
                Intent intent = new Intent(WebActivity.this, MainActivity.class);
                intent.putExtra("fromWeb", "fromWeb");
                startActivity(intent);
                finish();
            }
        }

//        // 重新刷新个人中心页面
//        @JavascriptInterface
//        public void appToReloadAccount() {
//            EventBus.getDefault().post(new AccountRefreshEvent());
//            finish();
//        }

        @JavascriptInterface
        public void appToAccount() {
            EventBus.getDefault().post(new AccountRefreshEvent());
            Intent intent = new Intent(WebActivity.this, MainActivity.class);
            intent.putExtra("fromWeb", "gotoaccount");
            startActivity(intent);
            finish();
        }

        @JavascriptInterface
        public void appToTel() {
            Logger.i("hybrid" + "appToTel");
            String phone = "";
            GetCompanyInfoResponse ca = DataCache.instance.getCacheData("heng", "GetCompanyInfoResponse");

            if (ca != null) {
                try {
                    phone = ca.result.phone;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            showPointDialog(phone);
        }

        /*以下从PayH5中copy过来的-end*/
    }

    private void getData(final String investStyle) {
        showLoadingDialog();
        new HttpHelper().getInvestTypeInfo(investStyle).subscribe(new Action1<CommonHttpModel<EvaluationSubmitResult>>() {
            @Override
            public void call(CommonHttpModel<EvaluationSubmitResult> commonResult) {

                try {
                    dismissLoadingDialog();
                    if (commonResult.code.equals("0")) {
                        startActivity(new Intent(WebActivity.this, EvaluationSuccessActivity.class)
                                .putExtra(EvaluationSuccessActivity.INVEST_TYPE, investStyle)
                                .putExtra(EvaluationSuccessActivity.DESCRIPTION, commonResult.result.tipsOne)
                                .putExtra(EvaluationSuccessActivity.TYPE_DETAIL, commonResult.result.tipsTwo));
                    } else {
                        ViewHelper.showToast(WebActivity.this, commonResult.message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                try {
                    dismissLoadingDialog();
                    ViewHelper.showToast(getApplicationContext(), throwable.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void showPointDialog(final String phone) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(WebActivity.this, R.style.dialog);
        View inflate = View.inflate(WebActivity.this, R.layout.loginout_dialog, null);
        normalDialog.setView(inflate);
        // 显示
        final AlertDialog alertDialog = normalDialog.create();
        alertDialog.show();
        TextView loginout_content = (TextView) inflate.findViewById(R.id.loginout_content);
        loginout_content.setText(phone);
        TextView loginout_quxiao = (TextView) inflate.findViewById(R.id.loginout_quxiao);
        loginout_quxiao.setText("取消");
        loginout_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        TextView loginout_sure = (TextView) inflate.findViewById(R.id.loginout_sure);
        loginout_sure.setText("拨打");
        loginout_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });
    }

    /**
     * 跳转webActivity
     *
     * @param url
     * @param title
     * @param mContext
     */
    public static void show(String url, String title, Context mContext) {
        Intent intent = new Intent(mContext, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        mContext.startActivity(intent);
    }
}