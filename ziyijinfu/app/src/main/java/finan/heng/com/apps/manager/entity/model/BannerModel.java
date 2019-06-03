package finan.heng.com.apps.manager.entity.model;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class BannerModel extends BaseModel{

    /**
     * imgaeLinkUrl : https://api.jincaiwa.com/hotspot/banner1?login
     * bannerImgae : https://admin.jincaiwa.com/upload/image/20170916151123746.jpg
     * imgTitle : 银行存管
     */

    private String imgaeLinkUrl;
    private String bannerImgae;
    private String imgTitle;

    public String getImgaeLinkUrl() {
        return imgaeLinkUrl;
    }

    public void setImgaeLinkUrl(String imgaeLinkUrl) {
        this.imgaeLinkUrl = imgaeLinkUrl;
    }

    public String getBannerImgae() {
        return bannerImgae;
    }

    public void setBannerImgae(String bannerImgae) {
        this.bannerImgae = bannerImgae;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    @Override
    public String toString() {
        return "BannerModel{" +
                "imgaeLinkUrl='" + imgaeLinkUrl + '\'' +
                ", bannerImgae='" + bannerImgae + '\'' +
                ", imgTitle='" + imgTitle + '\'' +
                '}';
    }
}
