package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import finan.heng.com.apps.http.ResponseData;

public class MoreModelResponse extends ResponseData {

    private static final long serialVersionUID = -8426339414555997935L;

    @Expose
    @SerializedName("result")
    public MoreModel result;

}
