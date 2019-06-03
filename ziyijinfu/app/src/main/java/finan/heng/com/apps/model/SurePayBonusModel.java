package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import com.dynamic.foundations.common.utils.StringUtils;

import java.io.Serializable;

import finan.heng.com.apps.IConstants;


public class SurePayBonusModel implements Serializable {


    /**
     * paidTime : 2017-05-20 20:53:32
     * orderId : 201705202053321852
     */
    public static final String TYPE_REDPAG = "0"; // 红包
    public static final String TYPE_JD = "2"; // 京东购物卡
    public static final String TYPE_RATE = "1" ; // 加息券

    public static final String CDTYPE_YUAN = "-1"; // 元
    public static final String CDTYPE_DAY = "0"; //天
    public static final String CDTYPE_MONTH = "1"; // 月
    public static final String CDTYPE_YEAR = "2" ; // 年
    private String title;
    private String type;//类型0现金红包1加息券2京东卡
    // 投资金额
    private String bonuses;//金额
    private String cdkeyType;//卡券单位

    public String getCdkeyType() {
        return cdkeyType;
    }

    public void setCdkeyType(String cdkeyType) {
        this.cdkeyType = cdkeyType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBonuses() {
        return bonuses;
    }

    public void setBonuses(String bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    public String toString() {
        return "SurePayBonusModel{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", bonuses='" + bonuses + '\'' +
                ", cdkeyType='" + cdkeyType + '\'' +
                '}';
    }

    public String getDesc(String type, String cdkeyType) {
        try {
            if (StringUtils.equals(TYPE_RATE, type)){
                return IConstants.Formatter.rateFormatWithPer.format(Double.parseDouble(getBonuses()));
            } else if (StringUtils.equals(TYPE_REDPAG, type)){
                return IConstants.Formatter.moneyNorFormat.format(Double.parseDouble(getBonuses()));
            } else if (StringUtils.equals(TYPE_JD, type)){
                if (StringUtils.equals(CDTYPE_YUAN, cdkeyType)){
                    return IConstants.Formatter.moneyNorFormat.format(Double.parseDouble(getBonuses()));
                } else if (StringUtils.equals(CDTYPE_DAY, cdkeyType)){
                    return IConstants.Formatter.dayFormat.format(Double.parseDouble(getBonuses()));
                } else if (StringUtils.equals(CDTYPE_MONTH, cdkeyType)){
                    return IConstants.Formatter.monthFormat.format(Double.parseDouble(getBonuses()));
                } else if (StringUtils.equals(CDTYPE_YEAR, cdkeyType)){
                    return IConstants.Formatter.yearFormat.format(Double.parseDouble(getBonuses()));
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }
    }
}
