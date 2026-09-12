import SwiftUI

struct BrowseView: View {
    @ObservedObject var firestoreService: FirestoreService

    @State private var selectedSpirit: String?
    @State private var selectedTastes: Set<String> = []
    @State private var selectedDifficulty: String?

    let spirits = ["גין", "וויסקי", "ראם", "וודקה", "טקילה", "ברנדי", "פיסקו"]
    let tastes = ["חמוץ", "מתוק", "רענן", "חזק", "קל", "קרמי", "פירות"]
    let difficulties = ["קל", "בינוני", "קשה"]

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 20) {
                    // Spirit Filter
                    VStack(alignment: .leading, spacing: 12) {
                        Text("בחר משקה בסיס")
                            .font(.headline)

                        LazyVGrid(columns: [GridItem(.adaptive(minimum: 100))], spacing: 8) {
                            ForEach(spirits, id: \.self) { spirit in
                                FilterChip(
                                    title: spirit,
                                    isSelected: selectedSpirit == spirit,
                                    action: {
                                        selectedSpirit = selectedSpirit == spirit ? nil : spirit
                                    }
                                )
                            }
                        }
                    }

                    Divider()

                    // Taste Filter
                    VStack(alignment: .leading, spacing: 12) {
                        Text("בחר טעם")
                            .font(.headline)

                        LazyVGrid(columns: [GridItem(.adaptive(minimum: 100))], spacing: 8) {
                            ForEach(tastes, id: \.self) { taste in
                                FilterChip(
                                    title: taste,
                                    isSelected: selectedTastes.contains(taste),
                                    action: {
                                        if selectedTastes.contains(taste) {
                                            selectedTastes.remove(taste)
                                        } else {
                                            selectedTastes.insert(taste)
                                        }
                                    }
                                )
                            }
                        }
                    }

                    Divider()

                    // Difficulty Filter
                    VStack(alignment: .leading, spacing: 12) {
                        Text("בחר קושיות")
                            .font(.headline)

                        LazyVGrid(columns: [GridItem(.adaptive(minimum: 100))], spacing: 8) {
                            ForEach(difficulties, id: \.self) { difficulty in
                                FilterChip(
                                    title: difficulty,
                                    isSelected: selectedDifficulty == difficulty,
                                    action: {
                                        selectedDifficulty = selectedDifficulty == difficulty ? nil : difficulty
                                    }
                                )
                            }
                        }
                    }

                    // Search Button
                    Button(action: {
                        let filter = RecipeFilter(
                            spirit: selectedSpirit,
                            tastes: Array(selectedTastes),
                            difficulty: selectedDifficulty
                        )
                        Task {
                            await firestoreService.searchByFilter(filter)
                        }
                    }) {
                        HStack {
                            Text("חפש קוקטיילים")
                                .font(.headline)
                                .foregroundColor(.white)
                        }
                        .frame(maxWidth: .infinity)
                        .frame(height: 56)
                        .background(Color(red: 1.0, green: 0.42, blue: 0.21))
                        .cornerRadius(12)
                    }
                    .padding(.top, 20)
                }
                .padding(16)
            }
            .navigationTitle("סנן קוקטיילים")
            .environment(\.layoutDirection, .rightToLeft)
        }
    }
}

struct FilterChip: View {
    let title: String
    let isSelected: Bool
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            Text(title)
                .font(.caption)
                .padding(.horizontal, 12)
                .padding(.vertical, 8)
                .background(isSelected ? Color(red: 1.0, green: 0.42, blue: 0.21) : Color(.systemGray5))
                .foregroundColor(isSelected ? .white : .primary)
                .cornerRadius(8)
        }
    }
}

#Preview {
    BrowseView(firestoreService: FirestoreService())
}
