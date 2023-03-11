package com.example.messagemate.utils


// Created by Shahid Iqbal on 3/11/2023.

object AppUtils {

    fun generateId(length: Int= 20): String{
        val alphaNumeric = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return alphaNumeric.shuffled().take(length).joinToString("")
    }
}