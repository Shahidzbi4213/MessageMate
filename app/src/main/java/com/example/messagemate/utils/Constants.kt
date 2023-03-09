package com.example.messagemate.utils

import android.Manifest
import android.os.Build


// Created by Shahid Iqbal on 2/22/2023.

object Constants {

    const val TAG = "Finder"

    val PERMS_ARRAY =
        arrayOf(
            Manifest.permission.READ_CONTACTS,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.POST_NOTIFICATIONS
            else Manifest.permission.INTERNET
        )
}