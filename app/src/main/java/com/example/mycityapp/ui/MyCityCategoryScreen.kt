package com.example.mycityapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.MyCityAppTheme
import com.example.mycityapp.data.PlaceCategory
import com.example.mycityapp.data.local.LocalPlacesDataProvider

@Composable
fun MyCityCategoryScreen(
    modifier: Modifier = Modifier,
    categories: List<PlaceCategory>,
    onCategoryClick: (PlaceCategory) -> Unit,
    selectedCategory: PlaceCategory
) {
    CategoryItemsList(
        modifier = modifier,
        categories = categories,
        onCategoryClick = onCategoryClick,
        selectedCategory = selectedCategory
    )
}

@Composable
fun CategoryItemsList(
    modifier: Modifier = Modifier,
    categories: List<PlaceCategory>,
    onCategoryClick: (PlaceCategory) -> Unit,
    selectedCategory: PlaceCategory,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(categories) { category ->
            CategoryItem(
                title = category.name,
                onClick = { onCategoryClick(category) },
                isSelected = selectedCategory == category
            )
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.tertiaryContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)

    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp)
                .clickable {
                    onClick()
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCityCategoryPreview() {
    MyCityAppTheme {
        MyCityCategoryScreen(
            categories = LocalPlacesDataProvider.places.map { it.category }.distinct(),
            onCategoryClick = {},
            selectedCategory = PlaceCategory.Parks,
            modifier = Modifier.fillMaxSize()
        )
    }
}