package com.ofermehulal.cocktailsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ofermehulal.cocktailsapp.ui.theme.Background
import com.ofermehulal.cocktailsapp.ui.theme.Primary
import com.ofermehulal.cocktailsapp.ui.theme.Secondary

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "🍸 קוקטיילים",
            style = MaterialTheme.typography.displayLarge,
            color = Primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "מצא את הקוקטיל המושלם",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        // Browse by Taste Button
        Button(
            onClick = { /* Navigate to Browse */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .size(height = 56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            )
        ) {
            Text(
                text = "סנן לפי טעם ומשקה",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Search by Ingredients Button
        Button(
            onClick = { /* Navigate to Search */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .size(height = 56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary
            )
        ) {
            Text(
                text = "בחר מרכיבים שיש לך",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Description
        Text(
            text = "בחר בין שתי דרכים למצוא את הקוקטיל המושלם:",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}
