package com.example.animationincompose.presentation.bottom_navigation.screens

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"
const val HADITH_ROUTE = "hadith"

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Setting : Screens("retrofit")
    object Room : Screens("room") // Parent
    object RoomMain : Screens("room/main")
    object Album : Screens("album")
    object Zap : Screens("zap")

    object HadithDetail : Screens("room/hadithDetail/{hadithId}") {
        fun createRoute(hadithId: Int) = "room/hadithDetail/$hadithId"
    }
}


