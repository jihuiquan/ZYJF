package finan.heng.com.apps.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.dynamic.foundations.common.utils.AndroidUtil;
import com.dynamic.foundations.common.utils.StringUtils;
import com.igexin.sdk.PushManager;
//import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import finan.heng.com.apps.DemoIntentService;
import finan.heng.com.apps.DemoPushService;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.http.KeyHolder;
import finan.heng.com.apps.manager.ManagerFactory;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.DeviceUtils;
import finan.zhimabao.com.apps.BuildConfig;


/*
 * Created by hhm on 2016/12/22.
 */

public class BaseApplication extends Application {

    public static BaseApplication instance;
    public Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ManagerFactory.getInstance().initialize(getApplicationContext());

        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        init();
//        HttpsURLConnection.setDefaultSSLSocketFactory(HttpRequestHelper.getSslSocketFactory());
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,null);//不集成推送最后一个参数传空
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setCatchUncaughtExceptions(true);
//        UMConfigure.setLogEnabled(true);
//        Log.i("return", BuildConfig.DEBUG+""+BuildConfig.IS_DEGUG);
        if (!BuildConfig.isDebug){
            Logger.init().setLogLevel(LogLevel.NONE);
        }
        /*
        * 集成测试,上线要注销
        * */
        MobclickAgent.setDebugMode(false);
        /**
         * 集成bug
         */
        initBugly();
        /**
         * 测试专用
         */
//        CrashReport.testJavaCrash();
        initKeyHolder();

    }

    /**
     * 初始化头部信息
     */
    private void initKeyHolder() {
        String appVersion = "";
        String appChannel = "";
        String osVersion = "";
        String deviceModel ="";
        String token = "";
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pkg = pm.getPackageInfo(this.getPackageName(), 0);
            ApplicationInfo info = pm.getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            String versionCode = String.valueOf(pkg.versionCode);
            appVersion = pkg.versionName;
            appChannel = info.metaData.getString("UMENG_CHANNEL");
            osVersion = android.os.Build.VERSION.RELEASE;
            deviceModel = Build.MANUFACTURER + " " + Build.MODEL;
            token = DataCache.instance.getCacheData("heng", "token");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        KeyHolder.getInstance().setAppChannel(StringUtils.trimToEmpty(appChannel));
        KeyHolder.getInstance().setAppVersion(StringUtils.trimToEmpty(appVersion));
        KeyHolder.getInstance().setOSVersion(StringUtils.trimToEmpty(osVersion));
        KeyHolder.getInstance().setDeviceModel(StringUtils.trimToEmpty(deviceModel));
        KeyHolder.getInstance().setToken(StringUtils.trimToEmpty(token));
        Log.i("systeminfo", AndroidUtil.printSystemInfo());

        Log.i("header" ,
                 IConstants.Header.DEVICEMODEL + " = " + KeyHolder.getInstance().getDeviceModel() + "\n"
                + IConstants.Header.OSTYPE + " = " + KeyHolder.getInstance().getOSType() + "\n"
                + IConstants.Header.OSVERSION + " = " + KeyHolder.getInstance().getOSVersion() + "\n"
                + IConstants.Header.APPCHANNEL + " = " + KeyHolder.getInstance().getAppChannel() + "\n"
                + IConstants.Header.APPVERSION + " = " + KeyHolder.getInstance().getAppVersion() + "\n"
        );
    }

    private void initBugly() {
        Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
        CrashReport.initCrashReport(context, "050ed4cf25", false, strategy);
// 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
// CrashReport.initCrashReport(context, strategy);
        //...在这里设置strategy的属性，在bugly初始化时传入

        try {
            PackageManager pm = getPackageManager();
            PackageInfo pkg = pm.getPackageInfo(this.getPackageName(), 0);
            ApplicationInfo info = pm.getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            String  versionName = pkg.versionName;
            String channel = info.metaData.getString("UMENG_CHANNEL");
            strategy.setAppChannel(channel);  //设置渠道
            strategy.setAppVersion(versionName);      //App的版本

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        strategy.setAppPackageName("finan.ziyijinfu.com.apps");  //App的包名
        strategy.setAppReportDelay(20000);   //改为20s
        //...
        BuglyLog.setCache(12 * 11024);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private void init() {
        applicationContext = this;
        instance = this;
        DataCache.instance.init(BaseApplication.this);
        DataCache.instance.clearCacheData("haili", "BankListResponse");
    }

    /**
     * We are using the pretty Logger. Changing the loglevel to NONE for release version.
     */


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static BaseApplication getApplication() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}