package finan.heng.com.apps.http;

import android.content.Intent;
import android.telephony.TelephonyManager;

import com.dynamic.foundations.common.assist.Log;
import com.orhanobut.logger.Logger;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.MainActivity;
import finan.heng.com.apps.app.ui.activity.CheckPhoneActivity;
import finan.heng.com.apps.base.BaseApplication;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.save.DataCache;
import finan.zhimabao.com.apps.BuildConfig;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/27.
 */

public class HttpRequestHelper {

    private static Interceptor customInterceptor;
    private static X509TrustManager trustManager;
    private static SSLSocketFactory sslSocketFactory;

    private static class Holder {
        private static final HttpLoggingInterceptor defaultInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.i("request" + message);
            }
        });


        private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(getDefaultLogger())
                .addInterceptor(getCustomLogger())
                .sslSocketFactory(getSslSocketFactory(), getAllTrustManager()).hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
        private static final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(URLHelper.getInstance().URL)
                .client(getOkHttpClient())
                .build();
        private static final InterfaceService interfaceService = getRetrofit().create(InterfaceService.class);
    }

    public static OkHttpClient getOkHttpClient() {

        return Holder.okHttpClient;
    }

    public static Retrofit getRetrofit() {

        return Holder.retrofit;
    }


    public static InterfaceService getHttpInterface() {

        return Holder.interfaceService;
    }

    /**
     * 通用拦截器用于打印日志
     *
     * @return
     */
    public static HttpLoggingInterceptor getDefaultLogger() {


        if (BuildConfig.isDebug) {
            return Holder.defaultInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            return Holder.defaultInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }


    }


    /**
     * 定制拦截器，用于添加请求头
     *
     * @return
     */
    public static Interceptor getCustomLogger() {
        if (customInterceptor == null) {
            synchronized (HttpRequestHelper.class) {
                if (customInterceptor == null) {
                    customInterceptor = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            LoginResponse cacheData = null;
                            try {
                                cacheData = DataCache.instance.getCacheData("heng", "LoginResponse");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Request request = chain.request();
                            String token = "";
                            try {
                                token = DataCache.instance.getCacheData("heng", "token");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            FormBody.Builder newFormBody = new FormBody.Builder();
                            if (request.body() instanceof FormBody) {
                                FormBody oidFormBody = (FormBody) request.body();
                                for (int i = 0; i < oidFormBody.size(); i++) {
                                    newFormBody.addEncoded(oidFormBody.encodedName(i), oidFormBody.encodedValue(i));
                                }
                                newFormBody.add("token", com.dynamic.foundations.common.utils.StringUtils.trimToEmpty(token));
                            }

                            Logger.i("body" + "request----" + "token=" + com.dynamic.foundations.common.utils.StringUtils.trimToEmpty(token));
                            TelephonyManager telephonyManager = (TelephonyManager)BaseApplication.getApplication().getSystemService(BaseApplication.getApplication().TELEPHONY_SERVICE);
                            String imei = telephonyManager.getDeviceId();
                            if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(token)) {
                                if (cacheData != null) {
                                    Logger.i("body" + "request-sessionId= " + cacheData.result.sessionId);
                                    request = chain.request().newBuilder()
                                            .method(request.method(), newFormBody.build())
                                            .addHeader("jsessionid", cacheData.result.sessionId)
                                            .addHeader(IConstants.Header.DEVICEMODEL, KeyHolder.getInstance().getDeviceModel())
                                            .addHeader(IConstants.Header.OSTYPE, KeyHolder.getInstance().getOSType())
                                            .addHeader(IConstants.Header.OSVERSION, KeyHolder.getInstance().getOSVersion())
                                            .addHeader(IConstants.Header.APPCHANNEL, KeyHolder.getInstance().getAppChannel())
                                            .addHeader(IConstants.Header.APPVERSION, KeyHolder.getInstance().getAppVersion())
                                            .addHeader(IConstants.Header.TOKEN, token)
                                            .addHeader(IConstants.Header.UNIQUEIDENTIFIER, "").build();
                                } else {
                                    request = chain.request().newBuilder()
                                            .method(request.method(), newFormBody.build())
                                            .addHeader("Request-From", "androidApp")
                                            .addHeader(IConstants.Header.DEVICEMODEL, KeyHolder.getInstance().getDeviceModel())
                                            .addHeader(IConstants.Header.OSTYPE, KeyHolder.getInstance().getOSType())
                                            .addHeader(IConstants.Header.OSVERSION, KeyHolder.getInstance().getOSVersion())
                                            .addHeader(IConstants.Header.APPCHANNEL, KeyHolder.getInstance().getAppChannel())
                                            .addHeader(IConstants.Header.APPVERSION, KeyHolder.getInstance().getAppVersion())
                                            .addHeader(IConstants.Header.TOKEN, token).build();
                                }
                            } else {
                                if (cacheData != null) {
                                    Logger.i("body" + "request-sessionId= " + cacheData.result.sessionId);
                                    request = chain.request().newBuilder()
                                            .method(request.method(), newFormBody.build())
                                            .addHeader("jsessionid", cacheData.result.sessionId)
                                            .addHeader(IConstants.Header.DEVICEMODEL, KeyHolder.getInstance().getDeviceModel())
                                            .addHeader(IConstants.Header.OSTYPE, KeyHolder.getInstance().getOSType())
                                            .addHeader(IConstants.Header.OSVERSION, KeyHolder.getInstance().getOSVersion())
                                            .addHeader(IConstants.Header.APPCHANNEL, KeyHolder.getInstance().getAppChannel())
                                            .addHeader(IConstants.Header.APPVERSION, KeyHolder.getInstance().getAppVersion()).build();

                                } else {
                                    request = chain.request().newBuilder()
                                            .method(request.method(), newFormBody.build())
                                            .addHeader("Request-From", "androidApp")
                                            .addHeader(IConstants.Header.DEVICEMODEL, KeyHolder.getInstance().getDeviceModel())
                                            .addHeader(IConstants.Header.OSTYPE, KeyHolder.getInstance().getOSType())
                                            .addHeader(IConstants.Header.OSVERSION, KeyHolder.getInstance().getOSVersion())
                                            .addHeader(IConstants.Header.APPCHANNEL, KeyHolder.getInstance().getAppChannel())
                                            .addHeader(IConstants.Header.APPVERSION, KeyHolder.getInstance().getAppVersion()).build();
                                }
                            }

                            Response proceed = chain.proceed(request);
//                            if (proceed.body() != null && proceed.body().contentType() != null) {
//                                MediaType mediaType = proceed.body().contentType();
//                                String string = proceed.body().string();
//                                ResponseBody responseBody = ResponseBody.create(mediaType, string);
//                                Logger.i("responsebody" + responseBody.string());
//                            }

//                            Logger.i("header" + "response----" + "token=" + com.dynamic.foundations.common.utils.StringUtils.trimToEmpty(proceed.header("token")));
//                            if (com.dynamic.foundations.common.utils.StringUtils.isNotEmpty(proceed.header("token"))){
//                                DataCache.instance.saveCacheData("heng", "token", proceed.header("token"));
//                            }


                            if (proceed.code() == 401) {
                                DataCache.instance.clearCacheData("heng", "LoginResponse");
                                DataCache.instance.clearCacheData("heng", "MyUserInfo");
                                DataCache.instance.clearCacheData("heng", "MyWalletResponse");
                                BaseApplication.getApplication().startActivity(new Intent(BaseApplication.getApplication(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                BaseApplication.getApplication().startActivity(new Intent(BaseApplication.getApplication(), CheckPhoneActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("fromServer", "newLogin"));

                            }
                            return proceed;
                        }
                    };
                }
            }
        }
        return customInterceptor;
    }

    public static X509TrustManager getAllTrustManager() {
        if (trustManager == null) {
            synchronized (HttpRequestHelper.class) {
                if (trustManager == null) {
                    trustManager = new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] x509Certificates = new X509Certificate[0];
                            return new X509Certificate[]{};
                        }
                    };
                }
            }
        }
        return trustManager;
    }

    public static SSLSocketFactory getSslSocketFactory() {
        if (sslSocketFactory == null) {
            synchronized (HttpRequestHelper.class) {
                if (sslSocketFactory == null) {
                    SSLContext sslContext;
                    try {
                        sslContext = SSLContext.getInstance("TLS");
                        sslContext.init(null, new TrustManager[]{getAllTrustManager()}, new SecureRandom());
                        sslSocketFactory = sslContext.getSocketFactory();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return sslSocketFactory;
    }

    private HttpRequestHelper() {

    }

    public static HttpRequestHelper getInstance() {

        return null;
    }

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}
