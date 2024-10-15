package com.example.mycityapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.MyCityAppTheme
import com.example.mycityapp.data.Place
import com.example.mycityapp.data.PlaceCategory
import com.example.mycityapp.data.local.LocalPlacesDataProvider

@Composable
fun MyCityPlacesScreen(
    modifier: Modifier = Modifier,
    places: List<Place>,
    onPlaceClick: (Place) -> Unit,
    selectedPlace: Place
) {
    PlacesItemsList(
        modifier = modifier,
        places = places,
        onPlaceClick = onPlaceClick,
        selectedPlace = selectedPlace
    )
}

@Composable
fun PlacesItemsList(
    modifier: Modifier = Modifier,
    places: List<Place>,
    onPlaceClick: (Place) -> Unit,
    selectedPlace: Place,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(places) { place ->
            PlaceItem(
                title = stringResource(place.title),
                image = painterResource(place.image),
                onClick = { onPlaceClick(place) },
                isSelected = selectedPlace == place
            )
        }
    }
}

@Composable
fun PlaceItem(
    modifier: Modifier = Modifier,
    title: String,
    image: Painter,
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
            .padding(horizontal = 8.dp, vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = modifier
                    .weight(2f)
            )
            Text(
                text = title,
                fontSize = 18.sp,
                modifier = modifier
                    .weight(3f)
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCityPlacesPreview() {
    MyCityAppTheme {
        MyCityPlacesScreen(
            places = LocalPlacesDataProvider.places.filter { it.category == PlaceCategory.Parks },
            onPlaceClick = {},
            selectedPlace = LocalPlacesDataProvider.defaultPlace,
            modifier = Modifier.fillMaxSize()
        )
    }
}