# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Root\android\android-sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn java.nio.**
-dontwarn org.jdom.**
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn com.squareup.okhttp.**
-dontwarn javax.naming.**
-dontwarn android.support.**



-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class ir.packpay.iablib.model.ServiceClient { *; }
-keep public class ir.packpay.iablib.handler.AsyncCallback { *; }
-keep public class ir.packpay.iablib.data.dto.FaultResponse { *; }
-keep public class ir.packpay.iablib.data.dto.InternalPurchaseInformationDto { *; }
-keep public class ir.packpay.iablib.data.dto.BaseResponse { *; }
-keep public class ir.packpay.iablib.data.dto.ConsumablePurchaseResponse { *; }
-keep public class ir.packpay.iablib.data.dto.NonconsumablePurchaseResponse { *; }
-keep public class ir.packpay.iablib.data.dto.PurchasesInfo { *; }
-keep public class ir.packpay.iablib.data.dto.ServerResponse { *; }
-keep public class ir.packpay.iablib.data.dto.SubscriptionPurchaseResponse { *; }
-keep public class ir.packpay.iablib.data.dto.SubscriptionResponse { *; }

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
     @retrofit2.http.* <methods>;
 }