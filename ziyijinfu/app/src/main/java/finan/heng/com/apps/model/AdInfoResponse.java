package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class AdInfoResponse implements Serializable{

    /**
     * adShowTime : 3
     * adImageLinkUrl : https://hbimg.b0.upaiyun.com/2c9a109be64b2f30eb93b82528a4d1ea2d65ea0624f6c9-eqwsSj_fw658
     * adImageUrl : https://hbimg.b0.upaiyun.com/4b97b60056048193354bca47512d8c5a9f1feef041477-Cvh9s9_fw658
     * adIsShow : 0
     */
    public static final int STATUS_SHOW = 1;
    public static final int STATUS_HIDE = 0;
    private int adShowTime;
    private String adImageLinkUrl;
    private String adImageUrl;
    private int adIsShow;
    private String adTitle;
    public int getAdShowTime() {
        return adShowTime;
    }

    public void setAdShowTime(int adShowTime) {
        this.adShowTime = adShowTime;
    }

    public String getAdImageLinkUrl() {
        return adImageLinkUrl;
    }

    public void setAdImageLinkUrl(String adImageLinkUrl) {
        this.adImageLinkUrl = adImageLinkUrl;
    }

    public String getAdImageUrl() {
        return adImageUrl;
    }

    public void setAdImageUrl(String adImageUrl) {
        this.adImageUrl = adImageUrl;
    }

    public int getAdIsShow() {
        return adIsShow;
    }

    public void setAdIsShow(int adIsShow) {
        this.adIsShow = adIsShow;
    }

    public boolean isShowAd(){
        return adIsShow == STATUS_SHOW;
//        return true; // 测试用
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    @Override
    public String toString() {
        return "AdInfoResponse{" +
                "adShowTime=" + adShowTime +
                ", adImageLinkUrl='" + adImageLinkUrl + '\'' +
                ", adImageUrl='" + adImageUrl + '\'' +
                ", adIsShow=" + adIsShow +
                ", adTitle='" + adTitle + '\'' +
                '}';
    }
}
