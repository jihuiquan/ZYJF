package finan.heng.com.apps;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.dynamic.foundations.common.assist.Log;
import com.dynamic.foundations.common.utils.StringUtils;
import com.orhanobut.logger.Logger;

import de.greenrobot.event.EventBus;
import finan.heng.com.apps.app.my.activity.ChongZhiListActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiOrTiXianActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.model.GetCompanyInfoResponse;
import finan.heng.com.apps.model.event.AccountRefreshEvent;
import finan.heng.com.apps.model.event.PurchaseEvent;
import finan.heng.com.apps.save.DataCache;
import finan.zhimabao.com.apps.R;

/**
 * Created by Administrator on 2016/8/16.
 */
public class PayH5Activity extends BaseActivity {

    private WebView webView;
    private String urls = "https://jzh.fuiou.com/app/app500001_res_reto.action";
    private String strHTML = "";
    private String payType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h5_activity);
        setUpToolbar();
        if (getIntent() != null) {
            strHTML = getIntent().getStringExtra("html");
        }
        Logger.i("strHTML" + strHTML);
        getSupportActionBar().setTitle("");
        setBarTitle("充值");
        getData();
        initView();
        try {
            payType = getIntent().getStringExtra(IConstants.Extra.PAY_PURCHASE);
        } catch (Exception e) {
            e.printStackTrace();
            payType = "";
        }
        Logger.i("strHTML" + strHTML);

    }

    public void initView() {
        webView.getSettings().setJavaScriptEnabled(true);// 设置支持javascript脚本
        webView.getSettings().setSupportZoom(false);// 支持缩放
        webView.getSettings().setBuiltInZoomControls(false);// 设置显示缩放按钮
        webView.getSettings().setUseWideViewPort(true);//关键点
        webView.getSettings().setLoadWithOverviewMode(true);//最小显示
        webView.addJavascriptInterface(new JSObject(), "android");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new webViewClient());
        String ua = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(ua + "android/jincaiwa");
    }

    public void getData() {
        webView = (WebView) findViewById(R.id.h5_act_webView);
        Logger.i("url" + strHTML);
        webView.loadDataWithBaseURL("about:blank", strHTML, "text/html", "utf-8", null);
    }

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

    private class webViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }


        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            String successUrl = "https://api.jincaiwa.com/payment/paymentAppNotify";
//            if (urls.contains(url)) {
//                Intent intent = new Intent(PayH5Activity.this, ChongZhiOrTiXianSuccessActivity.class);
//                intent.putExtra("type", 1);
//                intent.putExtra("amount",getIntent().getStringExtra("amount"));
//                intent.putExtra("bankCode",getIntent().getStringExtra("bankCode"));
//                intent.putExtra("iconUrl",getIntent().getStringExtra("iconUrl"));
//                startActivity(intent);
//                finish();
//            }
//            if (url.toLowerCase().contains(successUrl)) {
//                Intent intent = new Intent(PayH5Activity.this, ChongZhiOrTiXianSuccessActivity.class);
//                intent.putExtra("type", 1);
//                intent.putExtra("amount",getIntent().getStringExtra("amount"));
//                intent.putExtra("bankCode",getIntent().getStringExtra("bankCode"));
//                intent.putExtra("iconUrl",getIntent().getStringExtra("iconUrl"));
//                startActivity(intent);
//                finish();
//            } else {
//                view.loadUrl(url);
//            }
            view.loadUrl(url);
            return true;
        }
    }

    class JSObject {


        @JavascriptInterface
        public void recharge() {
            Intent intent = new Intent(PayH5Activity.this, ChongZhiOrTiXianActivity.class);
            intent.putExtra(IConstants.Extra.RECHARGE_TYPE, 1);
            startActivity(intent);
            finish();

        }

        @JavascriptInterface
        public void invest() {
            Intent intent = new Intent(PayH5Activity.this, MainActivity.class);
            intent.putExtra("fromWeb", "fromWeb");
            startActivity(intent);
            finish();
        }

        /**
         * 去首页
         */
        @JavascriptInterface
        public void appToIndex() {
            Logger.i("result" + "appToIndex");
            Intent intent = new Intent(PayH5Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

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
                Intent intent = new Intent(PayH5Activity.this, MainActivity.class);
                intent.putExtra("fromWeb", "fromWeb");
                startActivity(intent);
                finish();
            }
        }

        // 重新刷新个人中心页面
        @JavascriptInterface
        public void appToReloadAccount() {
            EventBus.getDefault().post(new AccountRefreshEvent());
            finish();
        }

        @JavascriptInterface
        public void appToAccount() {
            EventBus.getDefault().post(new AccountRefreshEvent());
            Intent intent = new Intent(PayH5Activity.this, MainActivity.class);
            intent.putExtra("fromWeb", "gotoaccount");
            startActivity(intent);
            finish();
        }
        @JavascriptInterface
        public void appToTel(){
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
    }

    private void showPointDialog(final String phone) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(PayH5Activity.this, R.style.dialog);
        View inflate = View.inflate(PayH5Activity.this, R.layout.loginout_dialog, null);
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
}