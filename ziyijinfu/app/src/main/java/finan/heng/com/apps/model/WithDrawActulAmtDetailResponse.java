package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class WithDrawActulAmtDetailResponse implements Serializable{
    public String withdrawAmount;
    public String withdrawFee;

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
}
