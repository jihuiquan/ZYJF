package com.dynamic.foundations.network;

import android.content.Context;
import com.android.volley.*;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuyang on 15/9/13.
 */
public class HttpManager {

    private Context context;
    private RequestQueue mRequestQueue;


    private HttpManager(Context context) {
        this.context = context;
    }

    public static HttpManager getInstance(Context context) {
        return new HttpManager(context);
    }

    /**
     * Returns a Volley request queue for creating network requests
     *
     * @return {@link com.android.volley.RequestQueue}
     */
    public RequestQueue getVolleyRequestQueue() {
        if (mRequestQueue == null) {
            OkHttpClient okHttpClient =new OkHttpClient();
            okHttpClient.setRetryOnConnectionFailure(false);
            mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack(okHttpClient));
        }
        return mRequestQueue;
    }

    /**
     * Adds a request to the Volley request queue with a given tag
     *
     * @param request is the request to be added
     * @param tag     is the tag identifying the request
     */
    public void addRequest(Request<?> request, String tag) {
        request.setTag(tag);
        addRequest(request);
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queue
     */
    public void addRequest(Request<?> request) {
        getVolleyRequestQueue().add(request);
    }

    /**
     * Cancels all the request in the Volley queue for a given tag
     *
     * @param tag associated with the Volley requests to be cancelled
     */
    public void cancelAllRequests(String tag) {
        if (getVolleyRequestQueue() != null) {
            getVolleyRequestQueue().cancelAll(tag);
        }
    }

    public VolleyBuilder newBuilder() {
        return new VolleyBuilder();
    }


    public class VolleyBuilder {
        private Request.Priority connectPriority;
        private int connectTimeout = 30000;
        private int retriesNum = 0;
        private LinkedHashMap<String, String> nameValuePairs;
//        private JSONObject jsonObject;
        private String postAddress;
        private Response.Listener<JSONObject> successListener;
        private Response.ErrorListener errorListener;
        private boolean shouldCache = true;
        private String tag;

        public VolleyBuilder() {
            this.connectPriority = Request.Priority.NORMAL;
            this.nameValuePairs = new LinkedHashMap();
        }

        public VolleyBuilder setPostAddress(String postAddress) {
            this.postAddress = postAddress;
            return this;
        }

        public VolleyBuilder setConnectPriority(Request.Priority connectPriority) {
            this.connectPriority = connectPriority;
            return this;
        }

        public VolleyBuilder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public VolleyBuilder setRetriesNum(int retriesNum) {
            this.retriesNum = retriesNum;
            return this;
        }

        public VolleyBuilder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public VolleyBuilder setParameter(String key, String value) {
            if (value != null) {
                this.nameValuePairs.put(key, value);
            }

            return this;
        }

        public VolleyBuilder setParameter(Map prams) {
            if (prams != null) {
                this.nameValuePairs.putAll(prams);
            }
            return this;
        }

//        public VolleyBuilder setParameter(JSONObject jsonObject) {
//            if (jsonObject != null) {
//                this.jsonObject = jsonObject;
//            }
//
//            return this;
//        }


        public VolleyBuilder setSuccessListener(Response.Listener<JSONObject> successListener) {
            this.successListener = successListener;
            return this;
        }

        public VolleyBuilder setErrorListener(Response.ErrorListener errorListener) {
            this.errorListener = errorListener;
            return this;
        }


        private String rebuildNameValueParameters(boolean post) {
            StringBuilder sb = new StringBuilder();
            if (this.nameValuePairs.size() > 0) {
                try {
                    Iterator signBody = this.nameValuePairs.entrySet().iterator();

                    while (signBody.hasNext()) {
                        Map.Entry entry = (Map.Entry) signBody.next();
                        sb.append((String) entry.getKey());
                        sb.append('=');
                        sb.append((String) entry.getValue());
                        sb.append('&');
                    }
                } catch (Exception var5) {
                    var5.printStackTrace();
                }
            }

            return sb.toString();
        }

        public void buildGet() {
            this.buildGet(true);
        }

        public void buildPost() {
            this.buildPost(true);
        }

        public void buildGet(boolean async) {
            add(async, Request.Method.GET);
        }

        public void buildPost(boolean async) {
            add(async, Request.Method.POST);
        }

        private void add(boolean async, int method) {
            BaseRequest request = new BaseRequest(method, this.postAddress,this.nameValuePairs, successListener, errorListener);
//            request.setParams(this.nameValuePairs);
            request.setPriority(Priority.NORMAL);
            request.setRetryPolicy(new DefaultRetryPolicy(connectTimeout, retriesNum, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            request.setShouldCache(shouldCache);
            addRequest(request, this.tag);
            RequestFuture future = RequestFuture.newFuture();
            if (!async) {
                try {
                    JSONObject e = (JSONObject) future.get((long) request.getRetryPolicy().getCurrentTimeout(), TimeUnit.MILLISECONDS);
                    this.successListener.onResponse(e);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
            }
        }
    }
}
