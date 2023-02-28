package com.example.messagemate.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


// Created by Shahid Iqbal on 2/22/2023.

object Extensions {


        fun Any?.debug() = Log.d(Constants.TAG, "$this")

        fun String?.toast(context: Context) =
            Toast.makeText(context, "$this", Toast.LENGTH_SHORT).show()

}