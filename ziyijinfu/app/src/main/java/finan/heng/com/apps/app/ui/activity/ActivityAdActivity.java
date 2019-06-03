package finan.heng.com.apps.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dynamic.foundations.common.assist.Log;
import com.dynamic.foundations.common.utils.StringUtils;

import java.lang.ref.SoftReference;

import butterknife.ButterKnife;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.WebOpenActivity;
import finan.heng.com.apps.WelcomeActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.model.AdInfoResponse;
import finan.heng.com.apps.utils.SharedpreferenceUtil;
import finan.zhimabao.com.apps.R;


/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class ActivityAdActivity extends BaseActivity {
    ImageView ivAd;
    TextView btnSubmit;
    public final int FREEZE_TIME = 1000; //延迟毫秒
    public int TOTLE_TIME = 4000; // 总毫秒数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityad);
        ivAd = (ImageView) findViewById(R.id.iv_ad);
        btnSubmit = (TextView) findViewById(R.id.btn_submit);
        AdInfoResponse response = (AdInfoResponse) getIntent().getExtras().get(IConstants.Extra.ADINFO);
        String imgUrl = StringUtils.trimToEmpty(response.getAdImageUrl());
        final String title = StringUtils.trimToEmpty(response.getAdTitle());

        final String linkUrl = response.getAdImageLinkUrl();
        TOTLE_TIME = response.getAdShowTime();

//        imgUrl = imgUrl.replace("https:","http:");
        Glide.with(this)
                .load(imgUrl).placeholder(R.color.bg_white)//添加默认的图片
                .error(R.color.bg_white).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                Log.i("return",e.getMessage()+" onException");
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.i("return"," onResourceReady");
                return false;
            }
        }).into(ivAd);

        ivAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityAdActivity.this, MainActivity.class));

                startActivity(new Intent(ActivityAdActivity.this, WebActivity.class)
                        .putExtra(IConstants.Extra.TITLE, title)
                        .putExtra(IConstants.Extra.URL, linkUrl));
                finish();
            }
        });

        btnSubmit.setText("跳过" + TOTLE_TIME / 1000 + "S");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityAdActivity.this, MainActivity.class));
                finish();
            }
        });
        timer = new SoftReference<CountDownTimer>(new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnSubmit.setText("跳过" + millisUntilFinished / 1000 + "S");
            }

            @Override
            public void onFinish() {
                btnSubmit.setText("跳过" + "0"+ "S");
                startActivity(new Intent(ActivityAdActivity.this, MainActivity.class));
                finish();
            }
        });
        String link = SharedpreferenceUtil.getString("openWeb");
        if(!TextUtils.isEmpty(link)){
            WebOpenActivity.show(link,ActivityAdActivity.this);
            finish();
        }else {
            timer.get().start();
        }
    }

    SoftReference<CountDownTimer> timer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            timer.get().cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean getIsTransparent() {
        return true;
    }
}
