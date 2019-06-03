package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;

public class GetInviteInvestListModel implements Serializable {

    /**
     * invateRegList : []
     * pageNo : 1
     * totalPage : 0
     * pageSize : 10
     */

    private String      pageNo;
    private String      totalPage;
    private String      pageSize;
    private String invateRegCount;
    private ArrayList<Beans> invateRegList;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public ArrayList<Beans> getInvateRegList() {
        return invateRegList;
    }

    public String getInvateRegCount() {
        return invateRegCount;
    }

    public void setInvateRegCount(String invateRegCount) {
        this.invateRegCount = invateRegCount;
    }

    public void setInvateRegList(ArrayList<Beans> invateRegList) {
        this.invateRegList = invateRegList;
    }

   public class Beans {
        public String tjyh;//xingming
        public String level;//
        public String time;//

    }

    @Override
    public String toString() {
        return "GetInviteInvestListModel{" +
                "pageNo='" + pageNo + '\'' +
                ", totalPage='" + totalPage + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", invateRegCount='" + invateRegCount + '\'' +
                ", invateRegList=" + invateRegList +
                '}';
    }
}
