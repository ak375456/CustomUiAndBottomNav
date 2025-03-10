package com.example.animationincompose.presentation.bottom_navigation.screens


import android.transition.Fade
import android.transition.Transition
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
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
        startDestination = HOME_ROUTE,
        enterTransition = {
            return@NavHost slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(700)
            )
        },
        exitTransition = {
            return@NavHost slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
            )
        },

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