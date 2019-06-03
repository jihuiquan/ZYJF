package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class GetInviteHistoryModel implements Serializable {


    /**
     * invateRegList : []
     * pageNo : 1
     * totalPage : 0
     * pageSize : 10
     */

    private String pageNo;
    private String totalPage;
    private String pageSize;
    private ArrayList<Bean> invateRegList;
    private String sumProfitAmt;

    public String getSumProfitAmt() {
        return sumProfitAmt;
    }

    public void setSumProfitAmt(String sumProfitAmt) {
        this.sumProfitAmt = sumProfitAmt;
    }

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

    public ArrayList<Bean> getInvateRegList() {
        return invateRegList;
    }

    public void setInvateRegList(ArrayList<Bean> invateRegList) {
        this.invateRegList = invateRegList;
    }

    public class Bean {
        public String tjyh;//xingming
        public String time;//
        public String fljl;//
        public String state;
    }
}
