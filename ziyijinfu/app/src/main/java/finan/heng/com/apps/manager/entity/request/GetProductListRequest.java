package finan.heng.com.apps.manager.entity.request;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class GetProductListRequest extends BaseRequest{
    private String genreId;
    private String pageNo;

    public GetProductListRequest() {
    }

    public GetProductListRequest(String genreId, String pageNo) {
        this.genreId = genreId;
        this.pageNo = pageNo;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
}
