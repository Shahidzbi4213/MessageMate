package com.example.messagemate.data.repository

import androidx.compose.runtime.MutableState
import com.example.messagemate.model.UserContact
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.MutableStateFlow


// Created by Shahid Iqbal on 3/9/2023.

interface ContactController {

    val contactList:MutableStateFlow<List<UserContact>>

    suspend fun readContacts()
}