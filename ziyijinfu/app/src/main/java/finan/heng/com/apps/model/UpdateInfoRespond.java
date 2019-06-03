package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import finan.heng.com.apps.http.ResponseData;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class UpdateInfoRespond extends ResponseData implements Serializable{

    @Expose
    @SerializedName("result")
    public UpdateDetailInfo result;

}
