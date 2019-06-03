package finan.heng.com.apps.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/9/20.
 */

public class RiskDefaultResult {
    public String current_level_description;
    public String max_level_description;

    @SerializedName("max_type")
    public String max_type;//默认值得type 1,2,3,4
}
