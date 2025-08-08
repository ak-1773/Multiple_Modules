-optimizationpasses 5
-ignorewarnings
-verbose
-overloadaggressively
-dontusemixedcaseclassnames
-keepattributes EnclosingMethod, *Annotation*, *JavascriptInterface*, InnerClasses, Signature
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable


# Android Framework
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}

-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}

-keep class * extends android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-dontwarn javax.lang.model.element.Element

###### room  #######
#-keep class * extends androidx.room.RoomDatabase
#-dontwarn androidx.room.paging.**
####################

-keep class com.example.network.model.** {*;}

-keep class androidx.work.** { *; }
-keepclassmembers class androidx.work.** { *; }

-include mmkv.pro
-include arouter.pro
-include retrofit2.pro
-include conscrypt.pro