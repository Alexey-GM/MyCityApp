package com.example.mycityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.MyCityAppTheme
import com.example.mycityapp.ui.MyCityApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCityAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val windowSize = calculateWindowSizeClass(this)
                    MyCityApp(
                        modifier = Modifier.padding(innerPadding),
                        windowSize = windowSize.widthSizeClass
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCityAppPreview() {
    MyCityAppTheme {
        MyCityApp(
            windowSize = WindowWidthSizeClass.Medium
        )
    }
}
