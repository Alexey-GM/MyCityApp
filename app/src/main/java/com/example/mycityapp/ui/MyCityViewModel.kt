package com.example.mycityapp.ui

import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.Place
import com.example.mycityapp.data.PlaceCategory
import com.example.mycityapp.data.local.LocalPlacesDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class MyCityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        val places: Map<PlaceCategory, List<Place>> =
            LocalPlacesDataProvider.places.groupBy { it.category }
        _uiState.value =
            MyCityUiState(
                places = places,
                currentSelectedPlace = places[PlaceCategory.Parks]?.get(0)
                    ?: LocalPlacesDataProvider.defaultPlace,
            )
    }

    fun updateCurrentPlaceCategory(placeCategory: PlaceCategory) {
        _uiState.update {
            it.copy(
                currentCategory = placeCategory
            )
        }
    }

    fun updateCurrentPlace(place: Place) {
        _uiState.update {
            it.copy(
                currentSelectedPlace = place
            )
        }
    }

    fun getPlacesCategories(): List<PlaceCategory> {
        return LocalPlacesDataProvider.places.map { it.category }.distinct()
    }
}