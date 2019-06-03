package com.dynamic.foundations.common.utils;

import java.util.Date;

/**
 * Created by liuyang on 15/10/14.
 */
public class DateUtils {

    public static long getTimerDiffer(Date d1, Date d2) {
        if(d1==null||d2==null){
            return 0;
        }
        return d1.getTime() - d2.getTime();
    }

}
