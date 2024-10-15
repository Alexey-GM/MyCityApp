package com.example.mycityapp.ui

import com.example.mycityapp.data.Place
import com.example.mycityapp.data.PlaceCategory
import com.example.mycityapp.data.local.LocalPlacesDataProvider

data class MyCityUiState (
    val places: Map<PlaceCategory, List<Place>> = emptyMap(),
    val currentCategory: PlaceCategory = PlaceCategory.Parks,
    val currentSelectedPlace: Place = LocalPlacesDataProvider.defaultPlace
)