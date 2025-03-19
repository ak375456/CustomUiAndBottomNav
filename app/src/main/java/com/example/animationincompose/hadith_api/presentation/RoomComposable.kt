package com.example.animationincompose.hadith_api.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animationincompose.util.Resource
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animationincompose.hadith_api.domain.model.Hadith
import com.example.animationincompose.presentation.bottom_navigation.screens.Screens

@Composable
fun RoomComposable(
    navController: NavHostController,
    viewModel: HadithViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val nestedNavController = rememberNavController()
        val offlineHadiths by viewModel.offlineHadiths.collectAsState()

        when (offlineHadiths) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }

            is Resource.Success -> {
                val hadiths = (offlineHadiths as Resource.Success).data
                LazyColumn {
                    items(hadiths) { hadith ->
                        HadithItem(
                            hadith = hadith,
                            onClick = {
                                println("Hadith ID: ${hadith.id}")
                                navController.navigate(Screens.HadithDetail.createRoute(hadith.id))
                            }
                        )
                    }
                }
            }

            is Resource.Error -> {
                val errorMessage = (offlineHadiths as Resource.Error).error
                Text(text = "Error: $errorMessage")
            }

            else -> {}
        }

        LaunchedEffect(Unit) {
            viewModel.fetchOfflineHadiths()
        }
    }
}

@Composable
fun HadithItem(hadith: Hadith, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text = "Hadith: ${hadith.hadith}", maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(text = "Narrator: ${hadith.narrator}")
        Text(text = "Reference: ${hadith.reference}")
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp)

    )
}