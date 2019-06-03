package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import finan.heng.com.apps.http.ResponseData;

public class GetGenreListResponse extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Expose
    @SerializedName("result")
    public GetGenreListModel result;
}
