package finan.heng.com.apps.manager.entity.model;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class WelfareResult {
    public String availableRedCount;//可用红包数
    public String availableCouponCount;//可用加息券
    public List<WelfareModel> couponList;//加息券
    public List<WelfareModel> redList;//红包

}
