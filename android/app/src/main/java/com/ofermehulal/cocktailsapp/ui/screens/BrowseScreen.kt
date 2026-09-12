package com.ofermehulal.cocktailsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
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
import com.ofermehulal.cocktailsapp.ui.theme.Primary
import com.ofermehulal.cocktailsapp.ui.theme.Secondary

data class Spirit(val id: String, val name: String)
data class TasteProfile(val id: String, val name: String)

val spirits = listOf(
    Spirit("gin", "גין"),
    Spirit("whiskey", "וויסקי"),
    Spirit("rum", "ראם"),
    Spirit("vodka", "וודקה"),
    Spirit("tequila", "טקילה"),
    Spirit("brandy", "ברנדי"),
    Spirit("pisco", "פיסקו")
)

val tasteProfiles = listOf(
    TasteProfile("sour", "חמוץ"),
    TasteProfile("sweet", "מתוק"),
    TasteProfile("sour_sweet", "חמוץ ומתוק"),
    TasteProfile("fresh", "רענן"),
    TasteProfile("strong", "חזק"),
    TasteProfile("light", "קל"),
    TasteProfile("creamy", "קרמי"),
    TasteProfile("fruity", "פירות")
)

@Composable
fun BrowseScreenImpl() {
    val selectedSpirits = remember { mutableSetOf<String>() }
    val selectedTastes = remember { mutableSetOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Title
        Text(
            text = "סנן לפי טעם ומשקה",
            style = MaterialTheme.typography.headlineMedium,
            color = Primary,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Spirit Filter Section
        Text(
            text = "בחר משקה בסיס",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Spirit Chips
        SpiritFilterChips(
            spirits = spirits,
            selectedSpirits = selectedSpirits,
            onSelectionChange = { spiritId ->
                if (selectedSpirits.contains(spiritId)) {
                    selectedSpirits.remove(spiritId)
                } else {
                    selectedSpirits.add(spiritId)
                }
            }
        )

        Divider(modifier = Modifier.padding(vertical = 20.dp))

        // Taste Filter Section
        Text(
            text = "בחר טעם",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Taste Chips
        TasteFilterChips(
            tastes = tasteProfiles,
            selectedTastes = selectedTastes,
            onSelectionChange = { tasteId ->
                if (selectedTastes.contains(tasteId)) {
                    selectedTastes.remove(tasteId)
                } else {
                    selectedTastes.add(tasteId)
                }
            }
        )

        Divider(modifier = Modifier.padding(vertical = 20.dp))

        // Search Button
        Button(
            onClick = {
                // TODO: Fetch recipes from Firebase based on filters
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary)
        ) {
            Text("חפש קוקטיילים", modifier = Modifier.padding(8.dp))
        }

        // Results Section (Placeholder)
        if (selectedSpirits.isNotEmpty() || selectedTastes.isNotEmpty()) {
            Text(
                text = "תוצאות",
                style = MaterialTheme.typography.titleMedium,
                color = Primary,
                modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
            )

            Text(
                text = "טוען קוקטיילים...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun SpiritFilterChips(
    spirits: List<Spirit>,
    selectedSpirits: Set<String>,
    onSelectionChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        spirits.chunked(2).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { spirit ->
                    FilterChip(
                        selected = selectedSpirits.contains(spirit.id),
                        onClick = { onSelectionChange(spirit.id) },
                        label = { Text(spirit.name) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun TasteFilterChips(
    tastes: List<TasteProfile>,
    selectedTastes: Set<String>,
    onSelectionChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        tastes.chunked(2).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { taste ->
                    FilterChip(
                        selected = selectedTastes.contains(taste.id),
                        onClick = { onSelectionChange(taste.id) },
                        label = { Text(taste.name) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
