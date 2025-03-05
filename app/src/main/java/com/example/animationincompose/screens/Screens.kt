package com.example.animationincompose.screens

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"

sealed class Screens(val route:String) {
    object Home : Screens("home")
    object Setting : Screens("setting")
    object Profile : Screens("profile")
    object Album : Screens("Album")
    object Zap : Screens("zap")


}