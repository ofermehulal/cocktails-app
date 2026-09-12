# Android Setup Guide

## Prerequisites

- Android Studio 2023.1+
- Android SDK 34 (API level 34)
- Kotlin 1.9.10
- Gradle 8.1.4

## Step 1: Firebase Setup

1. Go to [Firebase Console](https://console.firebase.google.com)
2. Create a new project named `cocktails-app`
3. Add an Android app:
   - Package name: `com.ofermehulal.cocktailsapp`
   - SHA-1 certificate fingerprint (optional for development)
4. Download `google-services.json`
5. Place it in: `android/app/google-services.json`

## Step 2: Firestore Setup

1. Create a Firestore Database (in test mode for development)
2. Create a collection named `recipes`
3. Import the recipes from `recipes.json`

### Import recipes with Firebase CLI:

```bash
# Install Firebase CLI
npm install -g firebase-tools

# Login
firebase login

# Import recipes
firebase firestore:set recipes recipes.json
```

## Step 3: Open in Android Studio

1. Open Android Studio
2. Select "Open an existing Android Studio project"
3. Navigate to `cocktails-app/android/`
4. Wait for Gradle sync to complete

## Step 4: Build & Run

```bash
# Build
./gradlew build

# Run on emulator or device
./gradlew installDebug

# Run with Logcat
./gradlew connectedAndroidTest
```

## Project Structure

```
android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/ofermehulal/cocktailsapp/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── ui/theme/
│   │   │   ├── res/
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── mipmap/
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts
├── settings.gradle.kts
└── SETUP.md (this file)
```

## Dependencies

### Firebase
- `com.google.firebase:firebase-firestore-ktx`
- `com.google.firebase:firebase-storage-ktx`
- `com.google.firebase:firebase-analytics-ktx`

### Jetpack Compose
- `androidx.compose.ui:ui`
- `androidx.compose.material3:material3`
- `androidx.compose.material:material-icons-extended`

### Image Loading
- `io.coil-kt:coil-compose`

### Networking
- `com.squareup.retrofit2:retrofit`
- `com.squareup.retrofit2:converter-gson`

## Important Notes

1. **google-services.json**: Add this file to `.gitignore` (already done)
2. **API Levels**: Min SDK 24 (Android 7.0), Target SDK 34 (Android 14)
3. **RTL Support**: Hebrew RTL is already configured in themes
4. **Theme Colors**:
   - Primary: `#FF6B35` (Orange)
   - Secondary: `#004E89` (Dark Blue)
   - Accent: `#F7931E` (Gold)

## Troubleshooting

### Gradle Sync Fails
```bash
# Clear cache and re-sync
./gradlew clean
./gradlew sync
```

### Firebase Connection Issues
- Ensure `google-services.json` is in `android/app/`
- Check Internet connection
- Verify Firebase project is active

### Compose Preview Issues
- Update Android Studio to latest version
- Clear cache: Invalidate Caches → Restart

## Next Steps

1. Implement home screen UI
2. Create browse by taste/spirit feature
3. Create search by ingredients feature
4. Add recipe detail screen
5. Integrate Firebase Firestore
6. Add Firebase Storage for images

---

**Last Updated**: 2026-09-12
