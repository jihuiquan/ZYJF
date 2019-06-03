package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20.
 */
public class RiskCheckResult implements Serializable {
    public String current_level;//-1表示没测评，非-1表示测评过
    public String max_level;
    public String current_level_description;
    public String max_level_description;
}
