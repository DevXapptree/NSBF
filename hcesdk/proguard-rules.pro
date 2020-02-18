# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
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
# for DexGuard only
#-keep manifest/application/meta-data@value=GlideModule

# Preserve the special static methods that are required in all enumeration
# classes.
# Add any project specific keep options here:

##---------------Begin: proguard configuration common for all Android apps ----------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-allowaccessmodification
-keepattributes *Annotation*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-repackageclasses ''

-ignorewarnings
-keep class * {
    public private *;
}

-keepattributes *Annotation*,Signature,EnclosingMethod,ElementList,Root,Annotation,InnerClasses

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
-keep public class * implements java.io.Serializable
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.support.v4.app.ListFragment
-keep public class * extends android.os.AsyncTask

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Preserve all native method names and the names of their classes.
-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Preserve static fields of inner classes of R classes that might be accessed
# through introspection.
-keepclassmembers class **.R$* {
  public static <fields>;
}

# Preserve the special static methods that are required in all enumeration classes.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class * {
    public protected *;
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
##---------------End: proguard configuration common for all Android apps ----------

#---------------Begin: proguard configuration for support library  ----------
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class com.actionbarsherlock.** { *; }
-keep interface com.actionbarsherlock.** { *; }

-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
-keep class android.support.v7.widget.RoundRectDrawable { *; }
-keep class !android.support.v7.internal.view.menu.**,android.support.** {*;}

-keep class android.support.v13.app.** { *; }
-keep interface android.support.v13.app.** { *; }
# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version. We know about them, and they are safe.
-dontwarn android.support.**
-dontwarn com.google.ads.**

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }


#-------- Easy Interpolater daasuu
-keep class com.daasuu.** { *; }

-dontwarn com.moengage.location.GeoManager
-dontwarn com.moengage.core.GeoTask
-dontwarn com.moengage.receiver.*
-dontwarn com.moengage.worker.*
-dontwarn com.moengage.inapp.ViewEngine

-keep class com.delight.**  { *; }

#Instructure
-keep public class com.instructure.** { *; }

#Apache

-dontwarn org.apache.lang.**
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }

#Room
-dontwarn android.arch.util.paging.CountedDataSource
-dontwarn android.arch.persistence.room.paging.LimitOffsetDataSource
-keep public class * extends android.arch.persistence.room.RoomDatabase
-keep class android.arch.persistence.room.** {*;}
-dontwarn android.arch.persistence.room.paging.**
#abstract classes
-keep !abstract class android.arch.persistence.room.** { *; }
-keep !abstract class room.** { *; }
-keep abstract class android.arch.persistence.room.** { *; }

#google
-keep class com.google.** {*; }

### Retrofit 2
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-dontwarn retrofit2.adapter.rxjava.CompletableHelper$** # https://github.com/square/retrofit/issues/2034
#To use Single instead of Observable in Retrofit interface
-keepnames class rx.Single
#Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod
# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**
# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit
# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.-KotlinExtensions
#To use Pojo classes in Retrofit
-keep class com.conduent.hcesdk.entities.** {*;}
-keep class com.conduent.hcesdk.entities.valuesapi** {*;}

### OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

#Project
-keep class com.conduent.hcesdk.** {*;}
-keep class com.conduent.hcesdk.room.** {*;}
-keep class com.conduent.hcesdk.room.tables.** {*;}
-keep abstract class com.conduent.hcesdk.room.AppRoomDataBase.** {*;}
-keepattributes MethodParameters
