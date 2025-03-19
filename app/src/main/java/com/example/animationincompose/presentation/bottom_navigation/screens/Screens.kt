package com.example.animationincompose.presentation.bottom_navigation.screens

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"

sealed class Screens(val route:String) {
    object Home : Screens("home")
    object Setting : Screens("retrofit")
    object Room : Screens("root")
    object Album : Screens("Album")
    object Zap : Screens("zap")
}