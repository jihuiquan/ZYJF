package finan.heng.com.apps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import finan.zhimabao.com.apps.R;

/**
 * home/version200接口code==-1打开这个页面
 */
public class WebOpenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_open);
        String url = getIntent().getStringExtra("url");
        MyWebView webView = findViewById(R.id.webView);
        webView.load(url);
    }

    /**
     * 跳转webActivity
     * @param url
     * @param mContext
     */
    public static void show(String url, Context mContext){
        Intent intent = new Intent(mContext, WebOpenActivity.class);
        intent.putExtra("url", url);
        mContext.startActivity(intent);
    }
}
