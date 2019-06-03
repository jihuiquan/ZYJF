# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Library/Android/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}



-ignorewarnings                     # 忽略警告，避免打包时某些警告出现
-optimizationpasses 5               # 指定代码的压缩级别
-dontusemixedcaseclassnames         # 是否使用大小写混合
-dontskipnonpubliclibraryclasses    # 是否混淆第三方jar
-dontpreverify                      # 混淆时是否做预校验
-verbose                            # 混淆时是否记录日志
-dontobfuscate
-dontoptimize
-dontusemixedcaseclassnames
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法
#-libraryjars   libs/com.umeng.message.lib_v2.5.0.jar
#-libraryjars   libs/httpmime-4.1.3.jar
#-libraryjars   libs/payecoplugin.jar
#-libraryjars   libs/securepay_auth_2.4.2.04.jar
#-libraryjars   libs/SocialSDK_QQZone_1.jar
#-libraryjars   libs/SocialSDK_QQZone_2.jar
#-libraryjars   libs/SocialSDK_QQZone_3.jar
#-libraryjars   libs/SocialSDK_Sina.jar
#-libraryjars   libs/SocialSDK_WeiXin_1.jar
#-libraryjars   libs/SocialSDK_WeiXin_2.jar
#-libraryjars   libs/umeng-analytics-v5.6.1.jar
#-libraryjars   libs/umeng-update-v2.6.0.1.jar
#-libraryjars   libs/umeng_social_sdk.jar
#-libraryjars   libs/zxing.jar


#Android
-dontwarn android.os.**
-dontwarn android.support.v4.**
-keep class android.** { *; }
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keep class * implements java.io.Serializable { *; }
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepattributes Signature

-keepattributes *Annotation*

-keep class **.R$* { *; }

-keepclassmembers class **.R$* {
       public static <fields>;
}

-keep class **.R* { *; }

-keep public class finan.zhimabao.com.apps.R$*{
    public static final int *;
}

-keep public class ** {
  public *** get*();
  public void set*(***);
}


#Eventbus
-keepclassmembers class ** {
    public void onEvent*(***);
}

-keep public class * extends com.guojinbao.app.model.entity.event.BaseEvent

# Only required if you use AsyncExecutor
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

# UMENG
-keep public class com.umeng.fb.ui.ThreadView {
}

-keep public class * extends com.umeng.**

-keep class com.umeng.** { *; }

-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-dontwarn


-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**

-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}


-keep class com.umeng.message.* {
        public <fields>;
        public <methods>;
}

-keep class com.umeng.message.protobuffer.* {
        public <fields>;
        public <methods>;
}

-keep class com.squareup.wire.* {
        public <fields>;
        public <methods>;
}

-keep class com.umeng.message.local.* {
        public <fields>;
        public <methods>;
}
-keep class org.android.agoo.impl.*{
        public <fields>;
        public <methods>;
}

-keep class org.android.agoo.service.* {*;}

-keep class org.android.spdy.**{*;}

# butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#custom
-keep class butterknife.** { *; }
-keep class org.android.** { *; }
-keep class com.fasterxml.jackson.** { *; }
-keep class com.github.rahatarmanahmed.** { *; }
-keep class com.astuetz.** { *; }
-keep class com.github.johnpersano.** { *; }
-keep class com.avast.** { *; }
-keep class de.greenrobot.** { *; }
-keep class com.squareup.okhttp.* { *; }
-keep class com.nineoldandroids.* { *; }
-keep class me.relex.** { *; }
-keep class mse.emilsjolander.** { *; }
-keep class okio.Okio.** { *; }
-keep class com.squareup.okio.** { *; }
-keep class org.codehaus.mojo.* { *; }
-keep class android.webkit.WebViewClient.* { *; }
-keep class android.net.http.** { *; }

-dontwarn org.apache.**
-dontwarn java.lang.invoke**
-dontwarn org.apache.commons.**
-dontwarn se.emilsjolander.**
-dontwarn okio.Okio.**
-dontwarn com.squareup.okio.**
-dontwarn java.nio.**
-dontwarn com.fasterxml.jackson.**
-dontwarn org.codehaus.mojo.**
-dontwarn android.webkit.WebViewClient.**
-dontwarn android.net.http.**


-keep class * extends com.guojinbao.app.model.entity.request.BaseRequest
-keep class * extends com.guojinbao.app.model.entity.respond.BaseRespond
-keepclasseswithmembernames public class * extends com.guojinbao.app.model.entity.request.BaseRequest {*;}
-keepclasseswithmembernames public class * extends com.guojinbao.app.model.entity.respond.BaseRespond {*;}

#-keep class com.guojinbao.app.model.entity.request.BaseRequest {
#public *** get*();
#                                                                   public void set*(***);}
-keep class com.guojinbao.app.model.entity.request.BaseRequest {*;}
-keepclassmembers class com.guojinbao.app.model.entity.request.BaseRequest {*;}
-keepclasseswithmembers class com.guojinbao.app.model.entity.request.BaseRequest
-keep class com.guojinbao.app.model.entity.respond.BaseRespond
-keep public class * extends com.guojinbao.app.model.entity.BaseEntity { *; }

#yilian
-keep class com.payeco.android.plugin.** {*;}
-keep class org.apache.http.entity.mime.** {*;}
-dontwarn com.payeco.android.plugin.**

-keepclassmembers class com.payeco.android.plugin {
  public *;
}

-keepattributes *JavascriptInterface*
-keep class com.payeco.android.plugin.js.JsBridge
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#HotFix
#基线包使用，生成mapping.txt
-printmapping mapping.txt
#生成的mapping.txt在app/buidl/outputs/mapping/release路径下，移动到/app路径下

#修复后的项目使用，保证混淆结果一致
#-applymapping mapping.txt

#hotfix
-keep class com.taobao.sophix.**{*;}
-keep class com.ta.utdid2.device.**{*;}

# 连连Demo混淆keep规则，如果使用了Demo中工具包com.yintong.pay.utils下面的类，请对应添加keep规则，否则混下会包签名错误
-keep public class com.yintong.pay.utils.** {
    <fields>;
    <methods>;
}
# 连连混淆keep规则，请添加
-keep class com.yintong.secure.activityproxy.PayIntro$LLJavascriptInterface{*;}

# 连连混淆keep规则
-keep public class com.yintong.** {
    <fields>;
    <methods>;
}
#jpush
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#shareSdk
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-keep class m.framework.**{*;}
-dontwarn cn.sharesdk.**
-dontwarn com.sina.**
-dontwarn com.mob.**
-dontwarn **.R$*
#友盟
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
