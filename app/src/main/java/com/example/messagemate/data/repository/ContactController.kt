package com.example.messagemate.data.repository

import com.example.messagemate.model.User
import com.example.messagemate.model.UserContact
import kotlinx.coroutines.flow.MutableStateFlow


// Created by Shahid Iqbal on 3/9/2023.

interface ContactController {

    val contactList: MutableStateFlow<List<UserContact>>
    val appUser: MutableStateFlow<List<User?>>

    suspend fun readContacts()

    suspend fun getAllAppUser()

}