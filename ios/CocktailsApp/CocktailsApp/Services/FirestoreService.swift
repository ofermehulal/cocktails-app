import Foundation
import FirebaseFirestore
import FirebaseFirestoreSwift

class FirestoreService: ObservableObject {
    @Published var recipes: [Recipe] = []
    @Published var isLoading = false
    @Published var error: String?

    private let db = Firestore.firestore()

    func getAllRecipes() async {
        DispatchQueue.main.async {
            self.isLoading = true
        }

        do {
            let snapshot = try await db.collection("recipes").getDocuments()
            let recipes = try snapshot.documents.compactMap { try $0.data(as: Recipe.self) }

            DispatchQueue.main.async {
                self.recipes = recipes
                self.isLoading = false
                self.error = nil
            }
        } catch {
            DispatchQueue.main.async {
                self.error = "שגיאה בטעינת מתכונים: \(error.localizedDescription)"
                self.isLoading = false
            }
        }
    }

    func getRecipeById(_ id: String) async -> Recipe? {
        do {
            let snapshot = try await db.collection("recipes").document(id).getDocument()
            return try snapshot.data(as: Recipe.self)
        } catch {
            DispatchQueue.main.async {
                self.error = "שגיאה בטעינת מתכון: \(error.localizedDescription)"
            }
            return nil
        }
    }

    func searchBySpirit(_ spirit: String) async {
        DispatchQueue.main.async {
            self.isLoading = true
        }

        do {
            let snapshot = try await db.collection("recipes")
                .whereField("baseSpirit", isEqualTo: spirit)
                .getDocuments()

            let recipes = try snapshot.documents.compactMap { try $0.data(as: Recipe.self) }

            DispatchQueue.main.async {
                self.recipes = recipes
                self.isLoading = false
                self.error = nil
            }
        } catch {
            DispatchQueue.main.async {
                self.error = "שגיאה בחיפוש: \(error.localizedDescription)"
                self.isLoading = false
            }
        }
    }

    func searchByFilter(_ filter: RecipeFilter) async {
        DispatchQueue.main.async {
            self.isLoading = true
        }

        do {
            var query: Query = db.collection("recipes")

            if let spirit = filter.spirit {
                query = query.whereField("baseSpirit", isEqualTo: spirit)
            }

            let snapshot = try await query.getDocuments()
            var recipes = try snapshot.documents.compactMap { try $0.data(as: Recipe.self) }

            // Apply additional filters in-memory
            if !filter.tastes.isEmpty {
                recipes = recipes.filter { recipe in
                    filter.tastes.contains { taste in
                        recipe.tastes.contains(taste)
                    }
                }
            }

            if let difficulty = filter.difficulty {
                recipes = recipes.filter { $0.difficulty == difficulty }
            }

            if let cost = filter.cost {
                recipes = recipes.filter { $0.cost == cost }
            }

            DispatchQueue.main.async {
                self.recipes = recipes
                self.isLoading = false
                self.error = nil
            }
        } catch {
            DispatchQueue.main.async {
                self.error = "שגיאה בחיפוש: \(error.localizedDescription)"
                self.isLoading = false
            }
        }
    }

    func searchByIngredients(_ selectedIngredients: Set<String>) async -> (fullMatches: [Recipe], partialMatches: [Recipe]) {
        DispatchQueue.main.async {
            self.isLoading = true
        }

        do {
            let snapshot = try await db.collection("recipes").getDocuments()
            let allRecipes = try snapshot.documents.compactMap { try $0.data(as: Recipe.self) }

            var fullMatches: [Recipe] = []
            var partialMatches: [Recipe] = []

            for recipe in allRecipes {
                let recipeIngredientIds = Set(recipe.ingredients.map { $0.id })
                let matchCount = selectedIngredients.intersection(recipeIngredientIds).count
                let matchPercentage = recipeIngredientIds.isEmpty ? 0 : CGFloat(matchCount) / CGFloat(recipeIngredientIds.count) * 100

                if matchCount == recipeIngredientIds.count {
                    fullMatches.append(recipe)
                } else if matchPercentage >= 80 {
                    partialMatches.append(recipe)
                }
            }

            DispatchQueue.main.async {
                self.isLoading = false
                self.error = nil
            }

            return (fullMatches, partialMatches)
        } catch {
            DispatchQueue.main.async {
                self.error = "שגיאה בחיפוש: \(error.localizedDescription)"
                self.isLoading = false
            }
            return ([], [])
        }
    }
}
