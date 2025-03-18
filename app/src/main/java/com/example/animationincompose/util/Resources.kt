package com.example.animationincompose.util

sealed class Resource<out T> {
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val error:String): Resource<T>()
    object Loading: Resource<Nothing>()
    object Idle: Resource<Nothing>()
}