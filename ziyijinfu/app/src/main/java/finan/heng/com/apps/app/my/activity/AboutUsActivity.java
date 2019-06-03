package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/5/2.
 */

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dynamic.foundations.common.utils.StringUtils;

import java.util.List;

import finan.heng.com.apps.http.KeyHolder;
import finan.zhimabao.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetCompanyInfoResponse;
import finan.heng.com.apps.save.DataCache;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    private TextView     tv;
    private LinearLayout llJianJie, llDaFen, llYiJian;
    private TextView     mTvCompany,mInfo;
    private ImageView iv_platform_logo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("关于我们");
        initView();
        init();
    }

    private void init() {
        llJianJie.setOnClickListener(this);
        llDaFen.setOnClickListener(this);
        llYiJian.setOnClickListener(this);
        initData();
    }

    private void initData() {
        try {
            String versinName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            tv.setText("子壹金服 v" + versinName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        GetCompanyInfoResponse ca = DataCache.instance.getCacheData("heng", "GetCompanyInfoResponse");
        if (ca!=null) {
            mInfo.setText(ca.result.recordNum);
            mTvCompany.setText(ca.result.compony);
        }
    }

    private void initView() {
        mTvCompany = (TextView) findViewById(R.id.activity_about_us_company);
        mInfo = (TextView) findViewById(R.id.activity_about_us_info);
        tv = (TextView) findViewById(R.id.activity_about_us_tv);
        llJianJie = (LinearLayout) findViewById(R.id.activity_about_us_jianjie);
        llDaFen = (LinearLayout) findViewById(R.id.activity_about_us_dafen);
        llYiJian = (LinearLayout) findViewById(R.id.activity_about_us_yijian);
        iv_platform_logo = (ImageView) findViewById(R.id.iv_platform_logo);
        iv_platform_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String channel = StringUtils.trimToEmpty(KeyHolder.getInstance().getAppChannel());
                String verionName = StringUtils.trimToEmpty(KeyHolder.getInstance().getAppVersion());
                if (isShowAppChannel){
                    if (StringUtils.isNotEmpty(channel)){
                        tv.setText("子壹金服 " + channel);
                        isShowAppChannel = false;
                    }
                } else {
                    if (StringUtils.isNotEmpty(verionName)){
                        tv.setText("子壹金服 v" + verionName);
                        isShowAppChannel = true;
                    }
                }
            }
        });
    }
    private boolean isShowAppChannel = true; // 是否显示渠道号
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_about_us_jianjie:
                Intent intent = new Intent(AboutUsActivity.this, WebActivity.class);//hotspot/compony
                intent.putExtra("title", "平台简介");
                intent.putExtra("url", URLHelper.getInstance().URL + "hotspot/compony");
                startActivity(intent);
                break;
            case R.id.activity_about_us_dafen:
                //调应用市场
                boolean isHad = false;
                final PackageManager packageManager = getPackageManager();
                List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
                if (pinfo != null) {
                    for (int i = 0; i < pinfo.size(); i++) {
                        String pn = pinfo.get(i).packageName;
                        if (TextUtils.equals("com.tencent.android.qqdownloader",pn)) {
                            isHad = true;
                        }
                    }
                }
                if (isHad) {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent in = new Intent(Intent.ACTION_VIEW, uri);
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                } else {
                    ViewHelper.showToast(AboutUsActivity.this,"请先安装应用宝");
                }
                break;
            case R.id.activity_about_us_yijian:
                startActivity(new Intent(AboutUsActivity.this, YiJianFanKuiActivity.class));
                break;
        }
    }
}
