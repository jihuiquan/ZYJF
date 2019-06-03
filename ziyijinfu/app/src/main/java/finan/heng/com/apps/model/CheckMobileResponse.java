package finan.heng.com.apps.model;

import com.dynamic.foundations.common.utils.StringUtils;

import java.io.Serializable;

import finan.heng.com.apps.http.ResponseData;

/**
 * author：sparkhuu
 * email:sparkhuu@gmail.com
 */

public class CheckMobileResponse extends ResponseData implements Serializable{
    private static String UNREGISTER = "-1";

    public boolean isUnregister(){
        return StringUtils.equals(errorCode, UNREGISTER);
    }
}
