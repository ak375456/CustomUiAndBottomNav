package com.example.animationincompose.presentation.bottom_navigation.screens

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.example.animationincompose.hadith_api.presentation.RoomComposable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.animationincompose.hadith_api.presentation.HadithDetailComposable

@Composable
fun RoomNavGraph(parentNavController: NavHostController) {
    val roomNavController = rememberNavController() // Local NavController for Room ✅

    NavHost(
        navController = roomNavController,
        startDestination = Screens.RoomMain.route,
        route = Screens.Room.route // This keeps it inside the Room section
    ) {
        composable(route = Screens.RoomMain.route) {
            RoomComposable(navController = roomNavController) // Pass local navController ✅
        }
        composable(
            route = Screens.HadithDetail.route,
            arguments = listOf(navArgument("hadithId") { type = NavType.IntType })
        ) { backStackEntry ->
            val hadithId = backStackEntry.arguments?.getInt("hadithId") ?: 0
            HadithDetailComposable(hadithId)
        }
    }
}
