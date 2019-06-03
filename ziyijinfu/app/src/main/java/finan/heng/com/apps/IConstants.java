package finan.heng.com.apps;

import com.orhanobut.logger.LogLevel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import finan.heng.com.apps.save.DataCache;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class IConstants {
    public static LogLevel configLevel = LogLevel.NONE;

    public interface Formatter {
        SimpleDateFormat integralDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat yyMMddDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat simpleHoursDateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat chineseHoursDateFormat = new SimpleDateFormat("HH小时mm分ss秒");
        DecimalFormat amountFormat = new DecimalFormat("##.##");
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        DecimalFormat rateFormat = new DecimalFormat("0.0");
        DecimalFormat moneyFormat = new DecimalFormat("0.00万元");
        DecimalFormat moneyNorFormat = new DecimalFormat("0元");
        DecimalFormat rateFormatWithPer = new DecimalFormat("0.00%");
        DecimalFormat dayFormat = new DecimalFormat("天");
        DecimalFormat monthFormat = new DecimalFormat("月");
        DecimalFormat yearFormat = new DecimalFormat("年");
        DecimalFormat moneyWithDrawFormat = new DecimalFormat("0.00元");
        DecimalFormat moneylimitformat = new DecimalFormat("0万");


    }
    public interface Header{

        String DEVICEMODEL = "deviceModel";
        String OSTYPE = "OSType";
        String OSVERSION = "OSVersion";
        String APPVERSION = "appVersion";
        String APPCHANNEL = "appChannel";
        String TOKEN = "token";
        String UNIQUEIDENTIFIER = "uniqueIdentifier";
    }
    public interface Extra {
        String ID = "id";
        String UPDATE_TAG = "UPDATE_TAG";
        String UPDATE_URL = "UPDATE_URL";
        String UPDATE_DESC = "UPDATE_DESC";
        String UPDATE_VERSION = "UPDATE_VERSION";
        String URL = "url";
        String TITLE = "title";
        String ADINFO = "ADINFO";
        String EXTRA_CASH = "EXTRA_CASH";
        String RECHARGE_TYPE = "type";
        String PAY_PURCHASE = "PAY_PURCHASE";
        String BANK_CODE = "BANK_CODE";
        String BANK_NAME = "BANK_NAME";
        String EXTRA_SHOWSTEP_LOGO = "EXTRA_SHOWSTEP_LOGO";
        String BANK_TIPS = "BANK_TIPS";
        String MOBILE = "MOBILE";
        String EXTRA_TYPE = "EXTRA_TYPE";
    }

    public interface RequestCode {
        int CODE_UPDATE = 1; // 版本升级
    }

    public interface ResultCode {
        int CODE_UPDATE = 2; // 版本更新结果
        int CODE_CANCEL = 3;
    }

    public interface RequestTag {
        String TAG_DEFAULT = "TAG_DEFAULT";

    }
//        public static final Boolean  HTTP_LOG = false;//标记是否打印网络请求log
    public static final Boolean  HTTP_LOG = true;//标记是否打印网络请求log
    public interface Server {

                String BASE_ADDRESS = DataCache.instance.getCacheData("heng", "ServerInfo") + "/";
//                String BASE_ADDRESS2 = "http://coolcuii.eicp.net/";
                String BASE_ADDRESS2 = "https://api.ziyijinfu.com/";
//                String BASE_ADDRESS2 = "http://192.168.0.200:8080/";//崔的本机

        String ADDRESS_HOMEINFO = "home/home";
        String ADDRESS_PRODUCTLIST = "products/productsList";
        String ADDRESS_PURCHASE_PROTACAL = "protocol/" +
                "" +
                "investment"; //投资注册协议
        String ADDRESS_RISK_PROTACAL = "protocol/reminder"; //风险邀请函
        String ADDRESS_PURCHESED_PROTACAL = BASE_ADDRESS + "protocol/investment2?id=";// 募集期协议
        String ADDRESS_ABOUT_US = "hotspot/compony";//关于我们
        String NEWER_GUIDE = "wap/hotspot/mixAppGuide";//新手指引
        String ADDRESS_SAFE_GUARANTEE = "hotspot/safe";//安全保障
        String ADDRESS_NOTICECENTER = "information/mixApparticle" ; // 公告中心
        String ADDRESS_PLATFORMDATA = "https://www.jincaiwa.com/wap/hotspot/mixAppData"; // 平台数据
//        String ADDRESS_NOTICECENTER = "http://192.168.100.205:8080/landscape-api/hotspot/safe"; // 公告中心测试
//        String ADDRESS_RISK = "https://www.jincaiwa.com/wap/hotspot/mixAppRiskWeb";//风险测评

        String ADDRESS_HELPCENTER = "hotspot/mixApphelp"; // 帮助中心
//       String ADDRESS_HELPCENTER = "http://192.168.100.205:8080/wap/hotspot/mixApproadOfCom"; // 帮助中心测试地址

//        String ADDRESS_BONUSE_RULE = BASE_ADDRESS+"rule/bonuses";//红包奖励规则 hotspot/bonusesRole
        String ADDRESS_BONUSE_RULE = "rule/coupon";//加息券规则 hotspot/bonusesRole
        String ADDRESS_RED_PACKET_RULE = "rule/redPacket";//红包规则 hotspot/bonusesRole
        String ADDRESS_CDKEY_RULE = "rule/cdkey";//卡券规则 hotspot/cdkeyRole
        String ADDRESS_REWARD_RULE = "rule/reward";// 邀请好友奖励规则 hotspot/rewardRole

        String ADDRESS_EXCHANGE_BANK = ""; //更换银行卡地址
    }

    public interface Key {
        String VERSION = "VERSION";
        String UMENG_META_DATA_NAME = "UMENG_CHANNEL";
        String PHONE = "PHONE";
        String OSTYPE = "Android";
        String SMS = "sms";
        String PASSWORD = "pass";
    }
}