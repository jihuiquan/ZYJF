package finan.heng.com.apps.testhttp;

import android.os.IInterface;

import java.io.IOException;
import java.util.HashMap;

import finan.heng.com.apps.http.HttpRequestHelper;
import finan.heng.com.apps.http.InterfaceService;
import finan.heng.com.apps.http.RetrofitClient;
import finan.heng.com.apps.rx.GsonHelper;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by YDYWork on 2018/7/17.
 */

public class RetrofitRequest implements IRequest{
    private static final RetrofitRequest ourInstance = new RetrofitRequest();
    private Class<?> aClass;

    public static RetrofitRequest getInstance() {
        return ourInstance;
    }

    private RetrofitRequest() {

    }

    public void setAClass(Class<?> aClass){
        this.aClass = aClass;
    }

    @Override
    public Observable<String> get(String url) {

        HashMap params = new HashMap();
        return HttpRequestHelper.getRetrofit().create(IHttpRetrofit.class)
                .get(url,params)
                .subscribeOn(Schedulers.io()).map(new Func1<ResponseBody,String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        String response = "";
                        try {
                            response = responseBody.string();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                       Object o =  GsonHelper.getGson().fromJson(response,aClass);
                        return response;
                    }
                });
    }

    @Override
    public Observable<String> post(String url) {
        HashMap params = new HashMap();
        return HttpRequestHelper.getRetrofit().create(IHttpRetrofit.class)
                .post(url,params)
                .subscribeOn(Schedulers.io()).map(new Func1<ResponseBody,String>() {


            @Override
            public String call(ResponseBody responseBody) {
                String response = "";
                try {
                    response = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }
        });

    }
}
