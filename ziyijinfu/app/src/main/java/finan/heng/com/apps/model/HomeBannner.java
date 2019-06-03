package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 10:51
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomeBannner implements Serializable {

    @Expose
    @SerializedName("title")
    public String imgTitle;
    @Expose
    @SerializedName("url")
    public String bannerImgae;
    @Expose
    @SerializedName("link")
    public String imgaeLinkUrl;
    /*
    *  "imgTitle": "123123",
        "bannerImgae": "http://admin.99landscape.com/upload/image/20170421173830454.png",
        "imgaeLinkUrl": "123123"
    * */

    @Override
    public String toString() {
        return "HomeBannner{" +
                "imgTitle='" + imgTitle + '\'' +
                ", bannerImgae='" + bannerImgae + '\'' +
                ", imgaeLinkUrl='" + imgaeLinkUrl + '\'' +
                '}';
    }
}
