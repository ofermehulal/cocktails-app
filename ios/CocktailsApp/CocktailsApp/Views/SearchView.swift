import SwiftUI

struct SearchView: View {
    @ObservedObject var firestoreService: FirestoreService

    @State private var selectedIngredients: Set<String> = []
    @State private var fullMatches: [Recipe] = []
    @State private var partialMatches: [Recipe] = []

    let ingredientsByCategory: [String: [String]] = [
        "משקאות": ["גין", "וויסקי", "ראם", "וודקה", "טקילה"],
        "מיצים": ["מיץ לימון", "מיץ לימון ירוק", "מיץ תפוז", "מיץ אננס", "מיץ קראנברי"],
        "סירופים": ["סירופ סוכר", "דבש", "טריפל סק", "קמפרי", "ורמוט"],
        "קישוט": ["מנטה", "לימון", "לימון ירוק", "מלח", "ביטרס"]
    ]

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 16) {
                    Text("בחר מרכיבים שיש לך")
                        .font(.headline)

                    Text("בחר את המרכיבים הזמינים וקבל מתכונים שאתה יכול להכין")
                        .font(.caption)
                        .foregroundColor(.secondary)

                    ForEach(ingredientsByCategory.keys.sorted(), id: \.self) { category in
                        VStack(alignment: .leading, spacing: 8) {
                            Text(category)
                                .font(.subheadline)
                                .fontWeight(.semibold)
                                .foregroundColor(Color(red: 1.0, green: 0.42, blue: 0.21))

                            VStack(spacing: 6) {
                                ForEach(ingredientsByCategory[category] ?? [], id: \.self) { ingredient in
                                    HStack {
                                        Text(ingredient)
                                            .font(.body)

                                        Spacer()

                                        Image(systemName: selectedIngredients.contains(ingredient) ? "checkmark.circle.fill" : "circle")
                                            .foregroundColor(selectedIngredients.contains(ingredient) ? Color(red: 1.0, green: 0.42, blue: 0.21) : .gray)
                                    }
                                    .padding(.vertical, 8)
                                    .padding(.horizontal, 12)
                                    .background(selectedIngredients.contains(ingredient) ? Color(red: 1.0, green: 0.42, blue: 0.21).opacity(0.1) : Color(.systemGray6))
                                    .cornerRadius(8)
                                    .onTapGesture {
                                        if selectedIngredients.contains(ingredient) {
                                            selectedIngredients.remove(ingredient)
                                        } else {
                                            selectedIngredients.insert(ingredient)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Divider()
                        .padding(.vertical, 8)

                    Text("בחרת \(selectedIngredients.count) מרכיבים")
                        .font(.caption)
                        .foregroundColor(.secondary)
                        .frame(maxWidth: .infinity, alignment: .center)

                    Button(action: {
                        Task {
                            let (full, partial) = await firestoreService.searchByIngredients(selectedIngredients)
                            fullMatches = full
                            partialMatches = partial
                        }
                    }) {
                        HStack {
                            Text("חפש קוקטיילים")
                                .font(.headline)
                                .foregroundColor(.white)
                        }
                        .frame(maxWidth: .infinity)
                        .frame(height: 56)
                        .background(Color(red: 0.0, green: 0.31, blue: 0.54))
                        .cornerRadius(12)
                    }
                    .disabled(selectedIngredients.isEmpty)

                    // Results
                    if !fullMatches.isEmpty {
                        VStack(alignment: .leading, spacing: 8) {
                            Text("התאמה מלאה")
                                .font(.subheadline)
                                .fontWeight(.semibold)
                                .foregroundColor(Color(red: 1.0, green: 0.42, blue: 0.21))

                            ForEach(fullMatches) { recipe in
                                RecipeRowView(recipe: recipe)
                            }
                        }
                    }

                    if !partialMatches.isEmpty {
                        VStack(alignment: .leading, spacing: 8) {
                            Text("התאמה חלקית")
                                .font(.subheadline)
                                .fontWeight(.semibold)
                                .foregroundColor(Color(red: 0.0, green: 0.31, blue: 0.54))

                            ForEach(partialMatches) { recipe in
                                RecipeRowView(recipe: recipe)
                            }
                        }
                    }
                }
                .padding(16)
            }
            .navigationTitle("בחר מרכיבים")
            .environment(\.layoutDirection, .rightToLeft)
        }
    }
}

struct RecipeRowView: View {
    let recipe: Recipe

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(recipe.name)
                .font(.subheadline)
                .fontWeight(.semibold)

            Text(recipe.description)
                .font(.caption)
                .foregroundColor(.secondary)
                .lineLimit(2)
        }
        .padding(.vertical, 8)
        .padding(.horizontal, 12)
        .background(Color(.systemGray6))
        .cornerRadius(8)
    }
}

#Preview {
    SearchView(firestoreService: FirestoreService())
}
