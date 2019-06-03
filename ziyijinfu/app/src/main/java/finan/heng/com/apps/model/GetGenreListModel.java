package finan.heng.com.apps.model;

import java.io.Serializable;
import java.util.List;

public class GetGenreListModel implements Serializable {

    private List<productsGenreVoBean> productsGenreVoList;

    public List<productsGenreVoBean> getProductsGenreVoList() {
        return productsGenreVoList;
    }

    public void setProductsGenreVoList(List<productsGenreVoBean> productsGenreVoList) {
        this.productsGenreVoList = productsGenreVoList;
    }

    public GetGenreListModel() {
    }

    public class productsGenreVoBean{
        private String id;
        private String genreTitle;
        private String genreContent;
        private String genreStatus;
        private String genreAdduser;
        private String genreAddtime;
        private String icon;
        private String productsCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGenreTitle() {
            return genreTitle;
        }

        public void setGenreTitle(String genreTitle) {
            this.genreTitle = genreTitle;
        }

        public String getGenreContent() {
            return genreContent;
        }

        public void setGenreContent(String genreContent) {
            this.genreContent = genreContent;
        }

        public String getGenreStatus() {
            return genreStatus;
        }

        public void setGenreStatus(String genreStatus) {
            this.genreStatus = genreStatus;
        }

        public String getGenreAdduser() {
            return genreAdduser;
        }

        public void setGenreAdduser(String genreAdduser) {
            this.genreAdduser = genreAdduser;
        }

        public String getGenreAddtime() {
            return genreAddtime;
        }

        public void setGenreAddtime(String genreAddtime) {
            this.genreAddtime = genreAddtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getProductsCount() {
            return productsCount;
        }

        public void setProductsCount(String productsCount) {
            this.productsCount = productsCount;
        }
    }
}
