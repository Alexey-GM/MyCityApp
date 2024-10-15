package com.example.mycityapp.ui

import com.example.mycityapp.data.Place
import com.example.mycityapp.data.PlaceCategory

data class MyCityUiState (
    val places: Map<PlaceCategory, List<Place>> = emptyMap(),
    val currentCategory: PlaceCategory = PlaceCategory.Parks,
    val currentSelectedPlace: Place? = null
)