package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;

public class SureWithdrawModel implements Serializable {


    /**
     * code : 2
     * msg : 提现金额超过账户可用余额！
     */

    private String code;
    private String msg;
    private String withdrawAmount;
    private String withdrawFee;
    private String tips;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(String withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SureWithdrawModel{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", withdrawAmount='" + withdrawAmount + '\'' +
                ", withdrawFee='" + withdrawFee + '\'' +
                ", tips='" + tips + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
