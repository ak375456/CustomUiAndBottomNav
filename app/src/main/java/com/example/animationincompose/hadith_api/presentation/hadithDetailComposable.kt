package com.example.animationincompose.hadith_api.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animationincompose.util.Resource
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HadithDetailComposable(hadithId: Int, viewModel: HadithViewModel = hiltViewModel()) {
    val selectedHadith by viewModel.selectedHadith.collectAsState()

    LaunchedEffect(hadithId) {
        viewModel.fetchHadithById(hadithId)  // Fetch Hadith when opened
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (selectedHadith) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.Success -> {
                val hadith = (selectedHadith as Resource.Success).data
                Text(text = "Hadith: ${hadith.hadith}")
                Text(text = "Narrator: ${hadith.narrator}")
                Text(text = "Reference: ${hadith.reference}")
            }
            is Resource.Error -> {
                val errorMessage = (selectedHadith as Resource.Error).error
                Text(text = "Error: $errorMessage")
            }
            else -> Unit
        }
    }
}
