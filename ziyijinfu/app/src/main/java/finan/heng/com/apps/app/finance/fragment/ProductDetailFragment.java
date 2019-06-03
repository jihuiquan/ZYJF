package finan.heng.com.apps.app.finance.fragment;

import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mcxiaoke.bus.Bus;

import finan.heng.com.apps.MyWebView;
import finan.heng.com.apps.MyWebViewClient;
import finan.heng.com.apps.app.finance.activity.ProductDetailModifyActivity;
import finan.heng.com.apps.base.BaseFragment;
import finan.zhimabao.com.apps.R;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/30 22:43
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailFragment extends BaseFragment {

    private  String url;
    private MyWebView webView;
    private String jsName =
            "     var imgs=document.getElementsByTagName('img');" +
            "     var length=imgs.length;" +
            "     for(var i=0;i<length;i++){" +
            "     img=imgs[i];" +
            "     img.onclick=function(){" +
            "     obj.showImage(this.src); }" +
            "     }" ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_detail_product, container, false);
        if (getArguments() != null){
            url = getArguments().getString("url");
        }

        initView(view);
        init();
        initData();
        return view;
    }

    private void init() {
        //        llOne.setOnClickListener(this);
        //        llTwo.setOnClickListener(this);
        //        mLlOne.setOnClickListener(this);
        //        mLlTwo.setOnClickListener(this);
        //        initData();
    }

    private void initData() {
        webView.getSettings().setJavaScriptEnabled(true);// 设置支持javascript脚本
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportZoom(true);// 支持缩放
        webView.getSettings().setBuiltInZoomControls(true);// 设置显示缩放按钮
        webView.getSettings().setUseWideViewPort(true);//关键点
        webView.getSettings().setLoadWithOverviewMode(true);//最小显示
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        String ua = webView.getSettings().getUserAgentString();

        webView.getSettings().setUserAgentString(ua + "android/jincaiwa");

        webView.addJavascriptInterface(new JSObject(),"android");

        webView.setWebViewClient(new MyWebViewClient() {
            @Override//点击时的
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("productsDetails")){
//                    webView.loadUrl("javascript:" + jsName);//js注入
                }
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
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
        webView.loadUrl(url);
    }

    private void initView(View view) {
        webView = (MyWebView) view.findViewById(R.id.fragment_detail_product_webView);
    }


    class JSObject{
        @JavascriptInterface
        public void appEnlarge(String img){
            Log.i("return",img+"");
           if (getActivity() instanceof ProductDetailModifyActivity){
                ProductDetailModifyActivity detailActivity = (ProductDetailModifyActivity) getActivity();
                detailActivity.showImage(img);
            }

        }
    }
}
