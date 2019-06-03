package finan.heng.com.apps.model;

import com.dynamic.foundations.common.utils.StringUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import finan.heng.com.apps.http.ResponseData;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class UpdateDetailInfo extends ResponseData implements Serializable{
    public static final String TYPE_FORCEUPDATE = "0"; //强制更新
    public static final String TYPE_UPDATE = "1"; // 非强制更新
    /**
     * "result": {
     "version": "1.0.1",
     "type": "1",
     "description": " 升级新版本！！！",
     "link": "http://xxxxxxx"
     }
     */
    @Expose
    @SerializedName("version")
    public String version;
    @Expose
    @SerializedName("type")
    public String type;
    @Expose
    @SerializedName("description")
    public String description;
    @Expose
    @SerializedName("link")
    public String link;
    public boolean isForceUpdate(){
        return StringUtils.equals(type, TYPE_FORCEUPDATE);
    }
    public boolean isNeedUpdate(String curretVersion){
        return curretVersion.compareTo(version) < 0;
//        return true; //测试用
    }

    @Override
    public String toString() {
        return "UpdateDetailInfo{" +
                "version='" + version + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
