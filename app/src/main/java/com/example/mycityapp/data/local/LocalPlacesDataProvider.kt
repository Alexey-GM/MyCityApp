package com.example.mycityapp.data.local

import com.example.mycityapp.R
import com.example.mycityapp.data.Place
import com.example.mycityapp.data.PlaceCategory

object LocalPlacesDataProvider {
    val places = listOf(
        Place(
            title = R.string.mall_ya_title,
            image = R.drawable.ya,
            category = PlaceCategory.Malls,
            description = R.string.mall_ya_description
        ),
        Place(
            title = R.string.mall_laplandia_title,
            image = R.drawable.laplandia,
            category = PlaceCategory.Malls,
            description = R.string.mall_laplandia_description
        ),
        Place(
            title = R.string.mall_prospect_title,
            image = R.drawable.prospect,
            category = PlaceCategory.Malls,
            description = R.string.mall_prospect_description
        ),
        Place(
            title = R.string.mall_oblaka_title,
            image = R.drawable.oblaka,
            category = PlaceCategory.Malls,
            description = R.string.mall_oblaka_description
        ),
        Place(
            title = R.string.mall_promenad3_title,
            image = R.drawable.promenad,
            category = PlaceCategory.Malls,
            description = R.string.mall_promenad3_description
        ),
        Place(
            title = R.string.skatepark_metallka_title,
            image = R.drawable.metallka_sk,
            category = PlaceCategory.Skateparks,
            description = R.string.skatepark_metallka_description
        ),
        Place(
            title = R.string.skatepark_boulevard_title,
            image = R.drawable.boulevard_sk,
            category = PlaceCategory.Skateparks,
            description = R.string.skatepark_boulevard_description
        ),
        Place(
            title = R.string.skatepark_dnk_title,
            image = R.drawable.dnk_sk,
            category = PlaceCategory.Skateparks,
            description = R.string.skatepark_dnk_description
        ),
        Place(
            title = R.string.skatepark_msk_title,
            image = R.drawable.moskovskaya_sk,
            category = PlaceCategory.Skateparks,
            description = R.string.skatepark_msk_description
        ),
        Place(
            title = R.string.restaurant_shshlikoff_title,
            image = R.drawable.shashlykoff,
            category = PlaceCategory.Restaurants,
            description = R.string.restaurant_shshlikoff_description
        ),
        Place(
            title = R.string.restaurant_hofbrau_title,
            image = R.drawable.hofbrau,
            category = PlaceCategory.Restaurants,
            description = R.string.restaurant_hofbrau_description
        ),
        Place(
            title = R.string.restaurant_hochu_puri_title,
            image = R.drawable.hochupuri,
            category = PlaceCategory.Restaurants,
            description = R.string.restaurant_hochu_puri_description
        ),
        Place(
            title = R.string.restaurant_zolotoy_bereg_title,
            image = R.drawable.zolotoybereg,
            category = PlaceCategory.Restaurants,
            description = R.string.restaurant_zolotoy_bereg_description
        ),
        Place(
            title = R.string.park_chudes_title,
            image = R.drawable.chudes,
            category = PlaceCategory.Parks,
            description = R.string.park_chudes_description
        ),
        Place(
            title = R.string.park_moskovskaya_title,
            image = R.drawable.moskovskaya,
            category = PlaceCategory.Parks,
            description = R.string.park_moskovskaya_description
        ),
        Place(
            title = R.string.park_zhukova_title,
            image = R.drawable.zhukova,
            category = PlaceCategory.Parks,
            description = R.string.park_zhukova_description
        ),
        Place(
            title = R.string.park_angelov_title,
            image = R.drawable.angelov,
            category = PlaceCategory.Parks,
            description = R.string.park_angelov_description
        ),
        Place(
            title = R.string.park_komsomolsky_title,
            image = R.drawable.komsomolsky,
            category = PlaceCategory.Parks,
            description = R.string.park_komsomolsky_description
        )
    )

    val defaultPlace = places.filter { it.category == PlaceCategory.Parks }.first()
}