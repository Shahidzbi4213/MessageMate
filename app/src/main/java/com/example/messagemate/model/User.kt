package com.example.messagemate.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// Created by Shahid Iqbal on 3/3/2023.
@Parcelize
data class User(

    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val profileUrl: String? = null,
    val phoneNumber: String? = null,
) : Parcelable

