# 🍸 קוקטיילים - אפליקציה עברית לקוקטיילים

אפליקציית iOS ו-Android בעברית המספקת מתכונים לקוקטיילים קלאסיים, מודרניים וחדשניים עם מרכיבים זמינים בקל בישראל.

## 🎯 תכונות

### 1. סינון לפי טעם ומשקה בסיס
- בחר משקה בסיס (גין, וויסקי, ראם, וודקה, טקילה וכו')
- בחר טעם (חמוץ, מתוק, חמוץ+מתוק)
- סינון נוסף לפי קושיות הכנה ועלות משוערת

### 2. חיפוש לפי מרכיבים שיש לך
- בחר מרכיבים שיש לך בבית
- קבל רשימה של מתכונים שאתה יכול להכין
- **Full Match** קודם (יש לך את כל המרכיבים)
- **Partial Match** אחרי (חסרים לך כמה מרכיבים אבל אתה יכול להכין)

### 3. תצוגה מפורטת של המתכון
- תמונה של הקוקטיל
- רשימת המרכיבים עם כמויות
- הערות חלופיות לכל מרכיב
- הוראות הכנה צעד-אחר-צעד
- טיפים וטריקים

## 📱 טכנולוגיה

- **Frontend**: Native (Swift + Kotlin)
- **Backend**: Firebase (Firestore + Storage)
- **Database**: Firestore
- **Language**: 100% עברית

## 📋 מתכונים

בקובץ `recipes.json` יש 80 מתכונים:
- **30 קוקטיילים קלאסיים** - מרגריטה, מרטיני, דייקירי וכו'
- **30 קוקטיילים מודרניים** - קוקטיילים עם מרכיבים חדשים וטכניקות
- **20 קוקטיילים ושוטים שונים** - ללא אלכוהול, שוטים וכו'

## 🚀 Getting Started

### שלב 1: Firebase Setup
1. צרו Firebase project חדש ב-[console.firebase.google.com](https://console.firebase.google.com)
2. הוסיפו Firestore database
3. הוסיפו Firebase Storage
4. הורידו את `GoogleService-Info.plist` (iOS) ו-`google-services.json` (Android)

### שלב 2: הוספת המתכונים
```bash
# טעינו את recipes.json ל-Firestore
firebase firestore:set /recipes recipes.json
```

### שלב 3: Clone ל-IDE
```bash
# iOS
open -a Xcode .

# Android (Android Studio)
open -a "Android Studio" .
```

## 📁 מבנה הפרויקט

```
cocktails-app/
├── ios/              # Swift + SwiftUI
├── android/          # Kotlin + Jetpack Compose
├── recipes.json      # מתכונים (80)
└── README.md         # קובץ זה
```

## 🎨 Design System

- **צבעים עברית**:
  - Primary: `#FF6B35`
  - Secondary: `#004E89`
  - Accent: `#F7931E`

- **Fonts**:
  - Heading: SF Pro Display (iOS), Roboto (Android)
  - Body: -apple-system, Roboto

## 📸 Screenshots

_(יבואו בקרוב)_

## 🔧 Requirements

- iOS 14+ / Swift 5.5+
- Android 8+ / Kotlin 1.8+
- Firebase Account
- Xcode 13+ / Android Studio 2021+

## 📝 License

Open Source - בחופשיות לשימוש

## 👤 Author

- **Ofer Mehulal** - @ClaudeDrop

---

**עדכון אחרון**: 2026-09-12
