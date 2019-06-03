package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInfo implements Serializable {

    @Expose
    @SerializedName("userAccount")
    public String userAccount;//手机号
    @Expose
    @SerializedName("userPaypassword")
    public String userPaypassword;//支付密码
    @Expose
    @SerializedName("userRealname")
    public String userRealname;//姓名
    @Expose
    @SerializedName("userIdCard")
    public String userIdCard;//身份
    @Expose
    @SerializedName("realnameStatus")
    public String realnameStatus;//实名状态
    @Expose
    @SerializedName("cardStatus")
    public String cardStatus;//邦卡状态
    @Expose
    @SerializedName("cusCode")
    public String cusCode;//邀请码
    @Expose
    @SerializedName("accessToken")
    public String accessToken;

    /**
     * 1	基础型
     2	保守型
     3	稳健型
     4	进取型
     */
    @Expose
    @SerializedName("userLevel")
    public String investStyle;//投资风格
}
