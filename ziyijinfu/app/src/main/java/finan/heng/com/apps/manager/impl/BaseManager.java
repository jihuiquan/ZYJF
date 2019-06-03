package finan.heng.com.apps.manager.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dynamic.foundations.common.utils.StringUtils;
import com.dynamic.foundations.network.HttpManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.manager.BaseModel;
import finan.heng.com.apps.manager.ManagerFactory;
import finan.heng.com.apps.manager.entity.request.BaseRequest;
import finan.heng.com.apps.manager.entity.respond.BaseRespond;
import finan.heng.com.apps.manager.entity.respond.MsgRespond;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.save.DataCache;

/**
 */
public class BaseManager {
    protected Handler handler = new Handler(Looper.getMainLooper());
    protected Context context = ManagerFactory.getInstance().getContext();
    protected HttpManager httpManager = ManagerFactory.getInstance().getHttpManager();
    protected ObjectMapper mapper = new ObjectMapper();

    public void post(Object params, String url, final Class returnType, final BaseModel.OnDataLoadListener listener) {
        post(params, url, returnType, listener, IConstants.RequestTag.TAG_DEFAULT);
    }

    public void post(Object params, String url, final Class returnType, final BaseModel.OnDataLoadListener listener, String tag) {
        BaseRequest baseRequest = (BaseRequest) params;

        try {
            Logger.i("post---" + mapper.writeValueAsString(params));
            try {
                StringBuilder sb = new StringBuilder();
                Map map = mapper.readValue(mapper.writeValueAsString(params), Map.class);
                Iterator signBody = map.entrySet().iterator();

                while (signBody.hasNext()) {
                    Map.Entry entry = (Map.Entry) signBody.next();
                    sb.append((String) entry.getKey());
                    sb.append('=');
                    sb.append((String) entry.getValue());
                    sb.append('&');
                }
                if (url.contains("?")) {
                    Logger.i("request--" + url + "&" + sb);
                } else {
                    Logger.i("request--" + url + "?" + sb);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {

        }

        try {
            httpManager.newBuilder().setPostAddress(url)
                    .setParameter(mapper.readValue(mapper.writeValueAsString(params), Map.class))
                    .setSuccessListener(new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Logger.i("result---:" + response.toString());
                            Logger.json(response.toString());
                            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                            Object resp = null;
                            try {
                                resp = mapper.readValue(response.toString(), returnType);
                            } catch (Exception e) {
                                MsgRespond respond = new MsgRespond();
                                respond.setMessage(e.getMessage());
                                listener.onFail(respond);
                            }

                            listener.onSuccess(resp);
                            listener.onFinish();
                        }
                    })
                    .setErrorListener(new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            listener.onNetworkError(error.getMessage());
                            listener.onFinish();
                        }
                    })
                    .setTag(tag)
                    .buildPost();
        } catch (Exception e) {
//            MsgRespond respond = new MsgRespond();
//            respond.setMessage(e.getMessage());
//            listener.onFail(respond);
//            listener.onFinish();
//            e.printStackTrace();
            Logger.i("result---:" + e.toString());
        }
    }

    public void cancelRequest(String tag) {
        httpManager.cancelAllRequests(tag);
    }

    public void cancenlAllRequest() {
//        httpManager.cancelAllRequests();
    }
}
