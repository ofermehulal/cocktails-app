# iOS Setup Guide

## Prerequisites

- macOS 13.0+
- Xcode 15.0+
- CocoaPods 1.12+
- Swift 5.9+

## Step 1: Install Dependencies

```bash
cd ios/CocktailsApp
pod install
```

## Step 2: Firebase Setup

1. Go to [Firebase Console](https://console.firebase.google.com)
2. Create a new project named `cocktails-app`
3. Add an iOS app:
   - Bundle ID: `com.ofermehulal.cocktailsapp`
4. Download `GoogleService-Info.plist`
5. Add it to Xcode project:
   - Drag into `CocktailsApp` folder
   - Ensure "Copy items if needed" is checked
   - Add to targets: `CocktailsApp`

## Step 3: Open in Xcode

```bash
open CocktailsApp.xcworkspace
```

## Step 4: Build & Run

```bash
# Build
xcodebuild -workspace CocktailsApp.xcworkspace -scheme CocktailsApp -configuration Debug

# Or use Xcode UI:
# Product → Run (or Cmd+R)
```

## Project Structure

```
ios/
└── CocktailsApp/
    ├── CocktailsApp/
    │   ├── CocktailsAppApp.swift (Entry point)
    │   ├── Views/
    │   │   ├── ContentView.swift (Tabs)
    │   │   ├── BrowseView.swift (Filter by taste)
    │   │   └── SearchView.swift (Filter by ingredients)
    │   ├── Models/
    │   │   └── Recipe.swift (Data models)
    │   └── Services/
    │       └── FirestoreService.swift (Firestore queries)
    ├── CocktailsApp.xcodeproj
    ├── Podfile
    └── SETUP.md (this file)
```

## Dependencies

### CocoaPods (Podfile)

```ruby
target 'CocktailsApp' do
  pod 'Firebase/Firestore'
  pod 'Firebase/Storage'
  pod 'SDWebImageSwiftUI'
end
```

## Important Notes

1. **GoogleService-Info.plist**: Add to `.gitignore` (already done in root)
2. **Deployment Target**: iOS 14.0+
3. **RTL Support**: Already configured for Hebrew
4. **Theme Colors**:
   - Primary: `#FF6B35` (Orange)
   - Secondary: `#004E89` (Dark Blue)
   - Accent: `#F7931E` (Gold)

## Troubleshooting

### Pod Install Fails
```bash
# Clear cache
rm -rf Pods Podfile.lock
pod repo update
pod install
```

### Firebase Issues
- Ensure `GoogleService-Info.plist` is in correct location
- Check bundle ID matches Firebase project
- Verify Firestore is enabled in Firebase Console

### Xcode Build Fails
- Product → Clean Build Folder (Cmd+Shift+K)
- Close Xcode and re-open workspace
- Check Swift version (should be 5.9+)

## Next Steps

1. Add `GoogleService-Info.plist`
2. Run `pod install`
3. Open `.xcworkspace` in Xcode
4. Build and run on simulator/device
5. Test Firebase queries

## RTL & Hebrew Support

- All strings are in Hebrew
- Layout direction set to RTL in `CocktailsAppApp.swift`
- UI components respect RTL automatically

---

**Last Updated**: 2026-09-12
