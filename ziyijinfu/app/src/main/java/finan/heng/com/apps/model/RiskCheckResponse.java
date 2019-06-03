package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20.
 */
public class RiskCheckResponse implements Serializable {
    public String code;//1表示有弹窗，0表示没有弹窗
    public String message;
    public RiskCheckResult result;
}
