package com.ofermehulal.cocktailsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableSetOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ofermehulal.cocktailsapp.ui.theme.Background
import com.ofermehulal.cocktailsapp.ui.theme.Secondary

data class Ingredient(val id: String, val name: String, val category: String)

val ingredients = listOf(
    // Spirits
    Ingredient("gin", "גין", "משקאות"),
    Ingredient("whiskey", "וויסקי", "משקאות"),
    Ingredient("rum", "ראם", "משקאות"),
    Ingredient("vodka", "וודקה", "משקאות"),
    Ingredient("tequila", "טקילה", "משקאות"),

    // Juices
    Ingredient("lime-juice", "מיץ לימון", "מיצים"),
    Ingredient("lemon-juice", "מיץ לימון ירוק", "מיצים"),
    Ingredient("orange-juice", "מיץ תפוז", "מיצים"),
    Ingredient("pineapple-juice", "מיץ אננס", "מיצים"),
    Ingredient("cranberry-juice", "מיץ קראנברי", "מיצים"),

    // Syrups & Modifiers
    Ingredient("sugar-syrup", "סירופ סוכר", "סירופים"),
    Ingredient("honey", "דבש", "סירופים"),
    Ingredient("triple-sec", "טריפל סק", "ליקרים"),
    Ingredient("campari", "קמפרי", "ליקרים"),
    Ingredient("vermouth", "ורמוט", "ליקרים"),

    // Garnishes
    Ingredient("mint", "מנטה", "קישוט"),
    Ingredient("lime", "לימון", "קישוט"),
    Ingredient("lemon", "לימון ירוק", "קישוט"),
    Ingredient("salt", "מלח", "קישוט"),
    Ingredient("bitters", "ביטרס", "קישוט")
)

@Composable
fun SearchScreenImpl() {
    val selectedIngredients = remember { mutableSetOf<String>() }
    val groupedIngredients = ingredients.groupBy { it.category }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "בחר מרכיבים שיש לך",
            style = MaterialTheme.typography.headlineMedium,
            color = Secondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "בחר את המרכיבים הזמינים וקבל מתכונים שאתה יכול להכין",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Ingredients List
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            groupedIngredients.forEach { (category, categoryIngredients) ->
                // Category Header
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium,
                    color = Secondary,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 12.dp)
                        .fillMaxWidth()
                )

                // Ingredients in category
                categoryIngredients.forEach { ingredient ->
                    IngredientCheckbox(
                        ingredient = ingredient,
                        isSelected = selectedIngredients.contains(ingredient.id),
                        onSelectionChange = { selected ->
                            if (selected) {
                                selectedIngredients.add(ingredient.id)
                            } else {
                                selectedIngredients.remove(ingredient.id)
                            }
                        }
                    )
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Selected Count
        Text(
            text = "בחרת ${selectedIngredients.size} מרכיבים",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        // Search Button
        Button(
            onClick = {
                // TODO: Search recipes by selected ingredients
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Secondary),
            enabled = selectedIngredients.isNotEmpty()
        ) {
            Text("חפש קוקטיילים", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun IngredientCheckbox(
    ingredient: Ingredient,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Secondary.copy(alpha = 0.1f)
            else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = ingredient.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )

            Checkbox(
                checked = isSelected,
                onCheckedChange = onSelectionChange
            )
        }
    }
}
