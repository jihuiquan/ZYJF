package finan.heng.com.apps.model.event;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */
public class PurchaseEvent extends BaseEvent{
    private String code;
    private String msg;

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
}
