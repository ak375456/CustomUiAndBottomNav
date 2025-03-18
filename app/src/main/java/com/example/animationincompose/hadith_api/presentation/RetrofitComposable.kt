package com.example.animationincompose.hadith_api.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import com.example.animationincompose.util.Resource

@Composable
fun RetrofitComposable(
    viewModel: HadithViewModel = hiltViewModel(),
) {
    val hadithState by viewModel.hadithState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (hadithState) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }

            is Resource.Success -> {
                val hadith = (hadithState as Resource.Success).data
                Text(text = hadith.hadith)
            }

            is Resource.Error -> {
                val errorMessage = (hadithState as Resource.Error).error
                Text(text = "Error: $errorMessage")
            }

            is Resource.Idle -> {
                Text("Press a button to fetch a random Hadees from Server")
            }
        }
        Button(
            onClick = {
                viewModel.getHadith()
            }
        ) {
            Text(text = "Get Hadith")
        }
    }
}