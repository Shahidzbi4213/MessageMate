package com.example.messagemate.data.firebase


// Created by Shahid Iqbal on 3/1/2023.


sealed class Response<out T> {

    object Empty : Response<Nothing>()
    object Loading : Response<Nothing>()
    data class Success<out T>(val message: T) : Response<T>()
    data class Error(val error: String?) : Response<Nothing>()

}