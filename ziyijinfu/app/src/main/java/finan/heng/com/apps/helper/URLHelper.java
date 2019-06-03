package finan.heng.com.apps.helper;

import android.text.TextUtils;
import java.util.ArrayList;
import finan.heng.com.apps.IConstants;
import finan.heng.com.apps.http.BusinessEnum;

/*
 * Created by lfu on 2017/2/21.
 */

public class URLHelper {
    public static final String BASE_URL = "baseUrl";
    public static final ArrayList<String> baseUrls = new ArrayList<>();
    static {
//        baseUrls.add("http://192.168.0.200:8080/");
        baseUrls.add("https://api.ziyijinfu.com/");
//        baseUrls.add("http://coolcuii.eicp.net/");
    }
    public static String URL;

    public String getRequestUrl(BusinessEnum model, String methodName) {
        if (TextUtils.isEmpty(methodName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        com.orhanobut.logger.Logger.i("urlhelper" + methodName);
        sb.append(IConstants.Server.BASE_ADDRESS2);
        switch (model) {
            case BUSINESS_USER:
                sb.append("user");
                break;
        }
        sb.append(methodName);
        return sb.toString();
    }

    public static URLHelper getInstance() {
        URL = baseUrls.get(0);
        return Singleton.instance;
    }

    private static final class Singleton {
        public static final URLHelper instance = new URLHelper();
    }

    public String buildUrl(String url) {
        return URL + url;
    }
}
