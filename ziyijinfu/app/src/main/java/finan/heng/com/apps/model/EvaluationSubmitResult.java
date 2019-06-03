package finan.heng.com.apps.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/18 23:16
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class EvaluationSubmitResult implements Serializable{
    public String max_level;
    public String level;
    public String isMax;//是否是最大等级，0表示最大
    public String description;
    public String max_level_description;

    @SerializedName("levelType")
    public String levelType;
    @SerializedName("tipsOne")
    public String tipsOne;
    @SerializedName("tipsTwo")
    public String tipsTwo;



}
