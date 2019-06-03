package finan.heng.com.apps.model;

import java.io.Serializable;

public class MoreModel implements Serializable {
    /**
     "phone": "400-100-3116",
     "title": "更多*",
     "feedbackLabel": "意见反馈*",
     "wechatLabel": "微信公众号：子壹金服*",
     "noticeLabel": "公告中心*",
     "helpCenterUrl": "https://www.ziyijinfu.com/wap/hotspot/mixApphelp",
     "wechat": "ziyijinfu",
     "helpLabel": "帮助中心*",
     "artNoRead": "0",
     "phoneImgUrl": "https://download.ziyijinfu.com/other/phoneNumPic.png?v04",
     "scoreLabel": "给子壹金服打分*"
     */
    private String phone;
    private String title;
    private String feedbackLabel;
    private String wechatLabel;
    private String noticeLabel;
    private String helpCenterUrl;
    private String wechat;
    private String helpLabel;
    private String artNoRead;
    private String phoneImgUrl;
    private String scoreLabel;

    public MoreModel() {
    }

    public MoreModel(String phone, String title, String feedbackLabel, String wechatLabel, String noticeLabel, String helpCenterUrl, String wechat, String helpLabel, String artNoRead, String phoneImgUrl, String scoreLabel) {
        this.phone = phone;
        this.title = title;
        this.feedbackLabel = feedbackLabel;
        this.wechatLabel = wechatLabel;
        this.noticeLabel = noticeLabel;
        this.helpCenterUrl = helpCenterUrl;
        this.wechat = wechat;
        this.helpLabel = helpLabel;
        this.artNoRead = artNoRead;
        this.phoneImgUrl = phoneImgUrl;
        this.scoreLabel = scoreLabel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String getHelpCenterUrl() {
        return helpCenterUrl;
    }

    public void setHelpCenterUrl(String helpCenterUrl) {
        this.helpCenterUrl = helpCenterUrl;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getHelpLabel() {
        return helpLabel;
    }

    public void setHelpLabel(String helpLabel) {
        this.helpLabel = helpLabel;
    }

    public String getArtNoRead() {
        return artNoRead;
    }

    public void setArtNoRead(String artNoRead) {
        this.artNoRead = artNoRead;
    }

    public String getPhoneImgUrl() {
        return phoneImgUrl;
    }

    public void setPhoneImgUrl(String phoneImgUrl) {
        this.phoneImgUrl = phoneImgUrl;
    }

    public String getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(String scoreLabel) {
        this.scoreLabel = scoreLabel;
    }
}
