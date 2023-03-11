package com.example.messagemate.utils

import android.Manifest
import android.os.Build


// Created by Shahid Iqbal on 2/22/2023.

object Constants {

    const val TAG = "Finder"

    val PERMS_ARRAY =
        arrayOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
        )
}