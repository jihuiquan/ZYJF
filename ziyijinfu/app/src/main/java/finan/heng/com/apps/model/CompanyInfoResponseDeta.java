package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import finan.heng.com.apps.http.ResponseData;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class CompanyInfoResponseDeta extends ResponseData {
    @Expose
    @SerializedName("phone")
    public String phone;
    @Expose
    @SerializedName("phoneImgUrl")
    public String phoneImgUrl;
    @Expose
    @SerializedName("artNoRead")
    public int artNoRead;
    @Expose
    @SerializedName("wechat")
    public String wechat;
    @Expose
    @SerializedName("helpCenterUrl")
    public String helpCenterUrl;
    private String title;
    private String feedbackLabel;
    private String wechatLabel;
    private String noticeLabel;
    private String helpLabel;
    private String scoreLabel;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeedbackLabel() {
        return feedbackLabel;
    }

    public void setFeedbackLabel(String feedbackLabel) {
        this.feedbackLabel = feedbackLabel;
    }

    public String getWechatLabel() {
        return wechatLabel;
    }

    public void setWechatLabel(String wechatLabel) {
        this.wechatLabel = wechatLabel;
    }

    public String getNoticeLabel() {
        return noticeLabel;
    }

    public void setNoticeLabel(String noticeLabel) {
        this.noticeLabel = noticeLabel;
    }

    public String getHelpLabel() {
        return helpLabel;
    }

    public void setHelpLabel(String helpLabel) {
        this.helpLabel = helpLabel;
    }

    public String getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(String scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public String getHelpCenterUrl() {
        return helpCenterUrl;
    }

    public void setHelpCenterUrl(String helpCenterUrl) {
        this.helpCenterUrl = helpCenterUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneImgUrl() {
        return phoneImgUrl;
    }

    public void setPhoneImgUrl(String phoneImgUrl) {
        this.phoneImgUrl = phoneImgUrl;
    }

    public int getArtNoRead() {
        return artNoRead;
    }

    public void setArtNoRead(int artNoRead) {
        this.artNoRead = artNoRead;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Override
    public String toString() {
        return "CompanyInfoResponseDeta{" +
                "phone='" + phone + '\'' +
                ", phoneImgUrl='" + phoneImgUrl + '\'' +
                ", artNoRead=" + artNoRead +
                ", wechat='" + wechat + '\'' +
                '}';
    }
}
