package finan.heng.com.apps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import finan.zhimabao.com.apps.R;

/**
 * Created by hdh203 on 2016/12/9.
 */

public class MyWebView extends WebView {

//    private ProgressBar           mProgressBar;
    private OnWebviewLoadListener listener;

    private boolean isLoadError;

    public MyWebView(Context context) {
        this(context, null);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        addProgressBar(context);
        initDefaultWebSettings();
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new GIWebViewClient());
        requestFocus();
    }


    public WebSettings initDefaultWebSettings() {
        WebSettings webSettings = null;
        if (!isInEditMode()) {
            webSettings = getSettings();
            webSettings.setSavePassword(false);
            webSettings.setSaveFormData(false);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
        }
        return webSettings;
    }

    //初始化进度条并且添加，因为WebView是一个ViewGroup
    private void addProgressBar(Context context) {
//        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
//        int height = 8;
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
//        mProgressBar.setLayoutParams(layoutParams);
//       Drawable drawable = context.getResources().getDrawable(R.drawable.load1);
//        mProgressBar.setProgressDrawable(drawable);
//        addView(mProgressBar);
    }


    //监听加载结果
    class GIWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (listener != null && !isLoadError) {
                listener.onLoadSuccess();
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            isLoadError = true;
            if (listener != null)
                listener.onLoadError();
        }
    }

    public void resetHight(){
        onSizeChanged(0,0,0,0);
    }

    //监听加载过程
    class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            if (newProgress == 100) {
//                mProgressBar.setVisibility(GONE);
//            } else {
//                if (mProgressBar.getVisibility() == GONE) {
//                    mProgressBar.setVisibility(VISIBLE);
//                }
//                mProgressBar.setProgress(newProgress);
//            }
            super.onProgressChanged(view, newProgress);
        }

    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
//        lp.x = l;
//        lp.y = t;
//        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }


    public interface OnWebviewLoadListener {
        //加载路径成功
        void onLoadSuccess();

        //加载路径失败
        void onLoadError();
    }

    public void setOnWebviewLoadListener(OnWebviewLoadListener listener) {
        this.listener = listener;
    }

    /**
     * 加载路径的方法，直接调用就可以了
     *
     * @param path
     */
    public void load(String path) {
        isLoadError = false;
        loadUrl(path);
    }
}