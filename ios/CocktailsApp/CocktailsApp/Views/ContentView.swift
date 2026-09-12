import SwiftUI

struct ContentView: View {
    @State private var selectedTab: Int = 0
    @StateObject private var firestoreService = FirestoreService()

    var body: some View {
        TabView(selection: $selectedTab) {
            // Home Tab
            HomeView()
                .tabItem {
                    Label("בעמוד הבית", systemImage: "house.fill")
                }
                .tag(0)

            // Browse Tab
            BrowseView(firestoreService: firestoreService)
                .tabItem {
                    Label("סינון", systemImage: "line.3.horizontal.decrease.circle")
                }
                .tag(1)

            // Search Tab
            SearchView(firestoreService: firestoreService)
                .tabItem {
                    Label("חיפוש", systemImage: "magnifyingglass")
                }
                .tag(2)
        }
        .accentColor(Color(red: 1.0, green: 0.42, blue: 0.21)) // Primary color
    }
}

struct HomeView: View {
    var body: some View {
        NavigationStack {
            VStack(spacing: 24) {
                Spacer()

                // Header
                VStack(spacing: 8) {
                    Text("🍸 קוקטיילים")
                        .font(.system(size: 32, weight: .bold))
                        .foregroundColor(Color(red: 1.0, green: 0.42, blue: 0.21))

                    Text("מצא את הקוקטיל המושלם")
                        .font(.headline)
                        .foregroundColor(.primary)
                }

                Spacer()

                // Browse Button
                NavigationLink(destination: BrowseView(firestoreService: FirestoreService())) {
                    HStack {
                        Text("סנן לפי טעם ומשקה")
                            .font(.headline)
                            .foregroundColor(.white)
                    }
                    .frame(maxWidth: .infinity)
                    .frame(height: 56)
                    .background(Color(red: 1.0, green: 0.42, blue: 0.21))
                    .cornerRadius(12)
                }

                // Search Button
                NavigationLink(destination: SearchView(firestoreService: FirestoreService())) {
                    HStack {
                        Text("בחר מרכיבים שיש לך")
                            .font(.headline)
                            .foregroundColor(.white)
                    }
                    .frame(maxWidth: .infinity)
                    .frame(height: 56)
                    .background(Color(red: 0.0, green: 0.31, blue: 0.54))
                    .cornerRadius(12)
                }

                Spacer()

                // Description
                Text("בחר בין שתי דרכים למצוא את הקוקטיל המושלם:")
                    .font(.caption)
                    .foregroundColor(.secondary)
                    .multilineTextAlignment(.center)
            }
            .padding(24)
            .navigationTitle("קוקטיילים")
            .environment(\.layoutDirection, .rightToLeft)
        }
    }
}

#Preview {
    ContentView()
}
