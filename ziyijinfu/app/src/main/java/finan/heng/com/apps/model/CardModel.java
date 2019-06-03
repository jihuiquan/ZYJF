package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20.
 */
public class CardModel implements Serializable {
    public String cdkeyType;//卡券面值类型：-1.元；0.天；1.月；2.年；
    public String cdkeyTypeName;
    public String cdkeyDenomination;//
    public String cdkeyNo;
    public String cdkey;//
    public String grant_time;//
}
