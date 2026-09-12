package com.ofermehulal.cocktailsapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.ofermehulal.cocktailsapp.ui.screens.BrowseScreenImpl
import com.ofermehulal.cocktailsapp.ui.screens.HomeScreen
import com.ofermehulal.cocktailsapp.ui.screens.SearchScreenImpl

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: @Composable () -> Unit
)

@Composable
fun CocktailsNavigation() {
    val selectedTab = remember { mutableIntStateOf(0) }

    val navigationItems = listOf(
        NavigationItem(
            title = "בעמוד הבית",
            icon = Icons.Filled.Home,
            screen = { HomeScreen() }
        ),
        NavigationItem(
            title = "סינון",
            icon = Icons.Filled.FilterList,
            screen = { BrowseScreenImpl() }
        ),
        NavigationItem(
            title = "חיפוש",
            icon = Icons.Filled.Search,
            screen = { SearchScreenImpl() }
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedTab.intValue == index,
                        onClick = { selectedTab.intValue = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        navigationItems[selectedTab.intValue].screen()
    }
}
