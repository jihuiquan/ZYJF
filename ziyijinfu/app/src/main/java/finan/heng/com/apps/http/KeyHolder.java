package finan.heng.com.apps.http;

import finan.heng.com.apps.IConstants;

/**
 */
public class KeyHolder {
    private static KeyHolder instance = new KeyHolder();

    private KeyHolder() {}

    public static KeyHolder getInstance() {
        return instance;
    }

    private String OSVersion;
    private String appVersion;
    private String appChannel;
    private String OSType = IConstants.Key.OSTYPE;
    private String deviceModel;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOSVersion() {
        return OSVersion;
    }

    public void setOSVersion(String OSVersion) {
        this.OSVersion = OSVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public String getOSType() {
        return OSType;
    }

    public void setOSType(String OSType) {
        this.OSType = OSType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
