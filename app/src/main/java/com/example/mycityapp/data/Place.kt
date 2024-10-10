package com.example.mycityapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    @StringRes
    val title: Int,
    @DrawableRes
    val image: Int,
    val category: PlaceCategory,
    @StringRes
    val description: Int,
)
