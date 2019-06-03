package com.dynamic.foundations.network;

import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by liuyang on 15/9/14.
 */
public class BaseRequest extends NormalPostRequest {
    Priority mPriority;
    RetryPolicy retryPolicy;
    Map<String, String> heads;

    public BaseRequest(int method, String url, Map<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, params, listener, errorListener);
    }

    //    public BaseRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        super(url, listener, errorListener);
//    }
//
//    public BaseRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        super(method, url, listener, errorListener);
//    }

    public void setHeads(Map<String, String> heads) {
        this.heads = heads;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    @Override
    public Priority getPriority() {
        // If you didn't use the setPriority method,
        // the priority is automatically set to NORMAL
        return mPriority != null ? mPriority : Priority.NORMAL;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return heads == null ? Collections.EMPTY_MAP : heads;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

}
