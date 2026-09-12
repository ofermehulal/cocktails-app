# Firebase
-keepnames class com.firebase.** { *; }
-keepnames class com.google.firebase.** { *; }
-keep class com.google.firebase.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }

# Gson
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-keep class com.google.gson.** { *; }
-keep interface com.google.gson.** { *; }

# App classes
-keep class com.ofermehulal.cocktailsapp.** { *; }
