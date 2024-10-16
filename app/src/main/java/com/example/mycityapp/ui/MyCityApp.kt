package com.example.mycityapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycityapp.R
import com.example.mycityapp.u.PlaceDetailsScreen
import com.example.mycityapp.ui.utils.MyCityContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(currentScreen) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MyCityApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController()
) {
    val contentType = getContentType(windowSize)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Categories.name
    )

    // Create ViewModel
    val viewModel: MyCityViewModel = viewModel()

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen.name,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        when (contentType) {
            MyCityContentType.ListOnly -> {
                NavHost(
                    navController = navController,
                    startDestination = MyCityScreen.Categories.name,
                    modifier = modifier.padding(innerPadding)
                ) {
                    composable(route = MyCityScreen.Categories.name) {
                        CategoriesScreen(
                            modifier = Modifier.fillMaxSize(),
                            categories = viewModel.getPlacesCategories(),
                            onCategoryClick = {
                                viewModel.updateCurrentPlaceCategory(it)
                                navController.navigate(MyCityScreen.Places.name)
                            },
                            selectedCategory = uiState.currentCategory
                        )
                    }
                    composable(route = MyCityScreen.Places.name) {
                        PlacesScreen(
                            modifier = Modifier.fillMaxSize(),
                            places = uiState.places[uiState.currentCategory] ?: emptyList(),
                            onPlaceClick = {
                                viewModel.updateCurrentPlace(it)
                                navController.navigate(MyCityScreen.Details.name)
                            },
                            selectedPlace = uiState.currentSelectedPlace
                        )
                    }
                    composable(route = MyCityScreen.Details.name) {
                        PlaceDetailsScreen(
                            modifier = Modifier.fillMaxSize(),
                            selectedPlace = uiState.currentSelectedPlace,
                            contentPadding = PaddingValues(0.dp),
                        )
                    }
                }
            }

            MyCityContentType.ListAndDetails -> {
                NavHost(
                    navController = navController,
                    startDestination = MyCityScreen.Categories.name,
                    modifier = modifier.padding(innerPadding)
                ) {
                    composable(route = MyCityScreen.Categories.name) {
                        CategoriesScreen(
                            modifier = Modifier
                                .fillMaxSize(),
                            categories = viewModel.getPlacesCategories(),
                            onCategoryClick = {
                                viewModel.updateCurrentPlaceCategory(it)
                                navController.navigate(MyCityScreen.Places.name)
                            },
                            selectedCategory = uiState.currentCategory
                        )
                    }
                    composable(route = MyCityScreen.Places.name) {
                        Row(modifier = modifier.fillMaxSize()) {
                            PlacesScreen(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                places = uiState.places[uiState.currentCategory] ?: emptyList(),
                                onPlaceClick = {
                                    viewModel.updateCurrentPlace(it)
                                },
                                selectedPlace = uiState.currentSelectedPlace
                            )
                            PlaceDetailsScreen(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                selectedPlace = uiState.currentSelectedPlace,
                                contentPadding = PaddingValues(0.dp),
                            )
                        }
                    }
                }
            }

            MyCityContentType.All -> {
                NavHost(
                    navController = navController,
                    startDestination = MyCityScreen.Places.name,
                    modifier = modifier.padding(innerPadding)
                ) {
                    composable(route = MyCityScreen.Places.name) {
                        Row(modifier = modifier.fillMaxSize()) {
                            CategoriesScreen(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                categories = viewModel.getPlacesCategories(),
                                onCategoryClick = {
                                    viewModel.updateCurrentPlaceCategory(it)
                                },
                                selectedCategory = uiState.currentCategory
                            )
                            PlacesScreen(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                places = uiState.places[uiState.currentCategory] ?: emptyList(),
                                onPlaceClick = {
                                    viewModel.updateCurrentPlace(it)
                                },
                                selectedPlace = uiState.currentSelectedPlace
                            )
                            PlaceDetailsScreen(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                selectedPlace = uiState.currentSelectedPlace,
                                contentPadding = PaddingValues(0.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getContentType(windowSize: WindowWidthSizeClass): MyCityContentType {
    return when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            MyCityContentType.ListOnly
        }

        WindowWidthSizeClass.Medium -> {
            MyCityContentType.ListAndDetails
        }

        WindowWidthSizeClass.Expanded -> {
            MyCityContentType.All
        }

        else -> {
            MyCityContentType.ListOnly
        }
    }
}

enum class MyCityScreen {
    Categories,
    Places,
    Details
}

