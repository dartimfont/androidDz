package com.example.kotlindz2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: GiphyViewModel = viewModel(factory = GiphyViewModelFactory(application))
            MainScreen(viewModel = viewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: GiphyViewModel) {
    GifList(viewModel = viewModel)
}