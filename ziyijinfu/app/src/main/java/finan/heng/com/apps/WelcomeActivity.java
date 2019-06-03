package finan.heng.com.apps;
/*
 * Created by hhm on 2017/5/26.
 */

import android.content.Intent;
import android.os.Bundle;

import com.dynamic.foundations.common.assist.Log;
import com.mcxiaoke.bus.Bus;

import finan.heng.com.apps.app.presenter.WelcomePresenter;
import finan.heng.com.apps.app.ui.activity.ActivityAdActivity;
import finan.heng.com.apps.app.view.IWelcomeView;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.model.AdInfoResponse;
import finan.heng.com.apps.model.IsOpenWebModel;
import finan.heng.com.apps.model.IsOpenWebModelResponse;
import finan.heng.com.apps.utils.SharedpreferenceUtil;
import finan.zhimabao.com.apps.R;
import rx.functions.Action1;

public class WelcomeActivity extends BaseActivity implements IWelcomeView {
    WelcomePresenter presenter = new WelcomePresenter(this);
    public final int FREEZE_TIME = 1000; //延迟毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initData();
        presenter.getAdActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取是否打开网页
     */
    private void initData() {
        new HttpHelper().getIsOpenWeb().subscribe(new Action1<IsOpenWebModelResponse>() {
            @Override
            public void call(IsOpenWebModelResponse response) {
                IsOpenWebModel result = response.result;
                String type = result.getType();
                if (type.equals("0")) {//比对版本更新
                    SharedpreferenceUtil.put("description",result.getDescription());
                    SharedpreferenceUtil.put("link",result.getLink());
                    SharedpreferenceUtil.put("version",result.getVersion());
                    SharedpreferenceUtil.put("type",result.getType());
                    SharedpreferenceUtil.put("openWeb","");
                } else if (type.equals("-1")) {//打开网页
                    SharedpreferenceUtil.put("openWeb",result.getLink());
                }
                Log.e("type",type);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissLoadingDialog();
            }
        });
    }

    @Override
    protected boolean getIsTransparent() {
        return true;
    }

    @Override
    public void showAdInfo(final AdInfoResponse response) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedpreferenceUtil.getBoolean("first_use", true)) {
                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class)
                            .putExtra(IConstants.Extra.ADINFO, response)
                    );
                } else {
                    if (response != null && response.isShowAd()) {
                        startActivity(new Intent(WelcomeActivity.this, ActivityAdActivity.class)
                                .putExtra(IConstants.Extra.ADINFO, response));
                    } else {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    }
                }
                finish();
            }
        }, FREEZE_TIME);

    }

}
