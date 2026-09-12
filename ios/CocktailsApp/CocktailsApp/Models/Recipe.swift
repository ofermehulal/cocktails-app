import Foundation
import FirebaseFirestoreSwift

struct Recipe: Identifiable, Codable {
    @DocumentID var id: String?
    let name: String
    let description: String
    let baseSpirit: String
    let tastes: [String]
    let difficulty: String
    let cost: String
    let imageUrl: String
    let ingredients: [Ingredient]
    let instructions: [String]
    let garnish: String
    let tips: String

    enum CodingKeys: String, CodingKey {
        case id
        case name
        case description
        case baseSpirit
        case tastes
        case difficulty
        case cost
        case imageUrl
        case ingredients
        case instructions
        case garnish
        case tips
    }
}

struct Ingredient: Identifiable, Codable {
    let id: String
    let name: String
    let amount: String

    enum CodingKeys: String, CodingKey {
        case id
        case name
        case amount
    }
}

struct RecipeFilter {
    var spirit: String?
    var tastes: [String] = []
    var difficulty: String?
    var cost: String?
}

struct IngredientFilter {
    var selectedIngredients: Set<String> = []
}
