# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



#1.基本指令区
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 不进行优化，建议使用此选项，
-dontoptimize
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify 能够加快混淆速度。
-dontpreverify
# 使我们的项目混淆后产生映射文件包含有类名->混淆后类名的映射关系
-verbose
# 使用printmapping指定映射文件的名称
-printmapping proguardMapping.txt
# 屏蔽警告
-ignorewarnings
# 指定混淆是采用的算法，后面的参数是一个过滤器这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*
# 保留Annotation不混淆
-keepattributes *Annotation*
# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable



#2.默认保留区
# 保留我们使用的四大组件，自定义的Application等等这些类不被混淆
# 因为这些子类都有可能被外部调用
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep public class com.google.vending.licensing.ILicensingService
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

# 保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留在Activity中的方法参数是view的方法，这样一来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class androidx.recyclerview.widget.**{*;}
-keep class androidx.viewpager2.widget.**{*;}

# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保留R下面的资源
-keep class **.R$* {
 *;
}

# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

# 避免layout中onclick方法（android:onclick="onClick"）混淆
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

# webview
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#在app中与HTML5的JavaScript的交互进行特殊处理
#我们需要确保这些js要调用的原生方法不能够被混淆，于是我们需要做如下处理：
#-keepclassmembers class com.XXX.XXX.JSInterface {
#    <methods>;
#}


# AndroidX混淆
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**


#自己常用的依赖库
# utils
-keep class com.lsn.lib.utils.**
-keep public class * extends com.lsn.lib.utils.**
-keep interface com.lsn.lib.utils.** {*;}
-dontwarn com.lsn.lib.utils.**
-dontnote com.lsn.lib.utils.**

# lib-res-pillar
-keep class com.lsn.lib.pillar.**
-keep public class * extends com.lsn.lib.pillar.**
-keep interface com.lsn.lib.pillar.** {*;}
-dontwarn com.lsn.lib.pillar.**
-dontnote com.lsn.lib.pillar.**


# lib-ui
-keep class com.lsn.lib.ui.**
-keep public class * extends com.lsn.lib.ui.**
-keep interface com.lsn.lib.ui.** {*;}
-dontwarn com.lsn.lib.ui.**
-dontnote com.lsn.lib.ui.**


# lib-net
-keep class com.lsn.lib.net.**
-keep public class * extends com.lsn.lib.net.**
-keep interface com.lsn.lib.net.** {*;}
-dontwarn com.lsn.lib.net.**
-dontnote com.lsn.lib.net.**

# lib-base
-keep class com.lsn.lib.base.**
-keep public class * extends com.lsn.lib.base.**
-keep interface com.lsn.lib.base.** {*;}
-dontwarn com.lsn.lib.base.**
-dontnote com.lsn.lib.base.**

# comm-core
-keep class com.lsn.comm.provider.**
-keep public class * extends com.lsn.comm.provider.**
-keep interface com.lsn.comm.provider.** {*;}
-dontwarn com.lsn.comm.provider.**
-dontnote com.lsn.comm.provider.**

# comm-core
-keep class com.lsn.comm.core.**
-keep public class * extends com.lsn.comm.core.**
-keep interface com.lsn.comm.core.** {*;}
-dontwarn com.lsn.comm.core.**
-dontnote com.lsn.comm.core.**


# comm-version
-keep class com.lsn.comm.version.**
-keep public class * extends com.lsn.comm.version.**
-keep interface com.lsn.comm.version.** {*;}
-dontwarn com.lsn.comm.version.**
-dontnote com.lsn.comm.version.**


# 主APP
-keep class com.pmisy.roomkb.**
-keep public class * extends com.pmisy.roomkb.**
-keep interface com.pmisy.roomkb.** {*;}
-dontwarn com.pmisy.roomkb.**
-dontnote com.pmisy.roomkb.**


# pgyersdk 蒲公英
-keep class com.pgyer.pgyersdk.**
-keep public class * extends com.pgyer.pgyersdk.**
-keep interface com.pgyer.pgyersdk.** {*;}
-dontwarn com.pgyer.pgyersdk.**
-dontnote com.pgyer.pgyersdk.**



# pinyin4j
-keep class com.hp.hpl.sparta.**
-keep class net.sourceforge.pinyin4j.**
-keep public class * extends com.hp.hpl.sparta.**
-keep public class * extends net.sourceforge.pinyin4j.**
-keep interface com.hp.hpl.sparta.** {*;}
-keep interface net.sourceforge.pinyin4j.** {*;}
-dontwarn com.hp.hpl.sparta.**
-dontwarn net.sourceforge.pinyin4j.**
-dontnote com.hp.hpl.sparta.**
-dontnote net.sourceforge.pinyin4j.**


#第三方框架
# Glide
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.**{*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


# Gson
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { <fields>; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# OkHttp3
-dontwarn okhttp3.**
-keep class okhttp3.** { *;}
-dontwarn okio.**

# Retrofit
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-keepattributes Exceptions
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }


# 微信支付
#-dontwarn com.tencent.mm.**
#-dontwarn com.tencent.wxop.stat.**
#-keep class com.tencent.mm.** {*;}
#-keep class com.tencent.wxop.stat.**{*;}

# 支付宝钱包
#-dontwarn com.alipay.**
#-dontwarn HttpUtils.HttpFetcher
#-dontwarn com.ta.utdid2.**
#-dontwarn com.ut.device.**
#-keep class com.alipay.android.app.IAlixPay{*;}
#-keep class com.alipay.android.app.IAlixPay$Stub{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
#-keep class com.alipay.sdk.app.PayTask{ public *;}
#-keep class com.alipay.sdk.app.AuthTask{ public *;}
#-keep class com.alipay.mobilesecuritysdk.*
#-keep class com.ut.*

# banner
-dontwarn com.youth.banner.**
-keep class com.youth.banner.**{*;}

# loading
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }

# JPTabBar
-keep class com.jpeng.** {*;}

# SmartRefreshLayout
-keep class com.scwang.** {*;}

# 万能适配器 BaseRecyclerViewAdapterHelper
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers public class * extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(android.view.View);
}

#友盟
-keep class com.umeng.** { *; }
-keep class com.uc.** { *; }
-keep class com.efs.** { *; }
-keepclassmembers class *{
     public<init>(org.json.JSONObject);
}
-keepclassmembers enum *{
      publicstatic**[] values();
      publicstatic** valueOf(java.lang.String);
}

# 环信
-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**


# ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
# -keep class * implements com.alibaba.android.arouter.facade.template.IProvider

# 屏幕适配
#-keep class me.jessyan.autosize.** { *; }
#-keep interface me.jessyan.autosize.** { *; }

# 版本更新
#-dontwarn com.king.app.updater.**
#-keep class com.king.app.updater.**{ *;}
#-keep class * extends com.king.app.updater.**{ *;}
#-keep class * implements com.king.app.updater.**{ *;}
#-keepattributes InnerClasses
#-dontwarn com.king.app.dialog.**
#-keep class com.king.app.dialog.**{ *;}

#shareSDK
#-keep class cn.sharesdk.**{*;}
#-keep class com.sina.**{*;}
#-keep class com.mob.**{*;}
#-keep class com.bytedance.**{*;}
#-dontwarn cn.sharesdk.**
#-dontwarn com.sina.**
#-dontwarn com.mob.**


