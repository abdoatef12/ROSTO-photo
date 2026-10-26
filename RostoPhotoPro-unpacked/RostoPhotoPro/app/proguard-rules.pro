# Kotlin
-keep class kotlin.** { *; }
-keep class kotlin.**$DefaultImpls { *; }

# Compose
-keep class androidx.compose.** { *; }

# Room
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao interface * { *; }

# Coroutines
-keep class kotlinx.coroutines.** { *; }

# TensorFlow Lite
-keep class org.tensorflow.** { *; }

# Serialization
-keepclassmembers class * {
    *** **(android.content.Context, android.util.AttributeSet);
}

# DataStore
-keep class androidx.datastore.** { *; }

# Retrofit
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keepattributes Signature

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**

# Coil
-keep class coil.** { *; }

# Navigation
-keep class androidx.navigation.** { *; }

# General Android
-keep class android.** { *; }
-keep interface android.** { *; }

# Custom App
-keep class com.rosto.photopro.** { *; }
