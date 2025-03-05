package com.example.animationincompose.presentation.bottom_navigation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animationincompose.presentation.bottom_navigation.screens.composables.AlbumComposable
import com.example.animationincompose.presentation.bottom_navigation.screens.composables.HomeComposable
import com.example.animationincompose.presentation.bottom_navigation.screens.composables.ProfileComposable
import com.example.animationincompose.presentation.bottom_navigation.screens.composables.SettingComposable
import com.example.animationincompose.presentation.bottom_navigation.screens.composables.ZapComposable

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = HOME_ROUTE
    ) {
        composable(
            route = Screens.Home.route
        ) {
            HomeComposable()
        }
        composable(
            route = Screens.Setting.route
        ) {
            SettingComposable()
        }
        composable(
            route = Screens.Profile.route
        ) {
            ProfileComposable()
        }
        composable(
            route = Screens.Album.route
        ) {
            AlbumComposable()
        }
        composable(
            route = Screens.Zap.route
        ) {
            ZapComposable()
        }
    }
}