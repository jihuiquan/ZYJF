package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetProductDetailMoneyModel implements Serializable {
    @Expose
    @SerializedName("profit")
    public String profit;//总收益

    @Expose
    @SerializedName("profit2")
    public String profit2;//加息券收益
    @Expose
    @SerializedName("profit1")
    public String profit1;//基础收益
    @Expose
    @SerializedName("cashBack")
    public String cashBack;//红包收益

}
