package com.example.messagemate.presentation.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messagemate.data.repository.ContactController
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/9/2023.

class ContactViewModel(private val controller: ContactController) : ViewModel() {

    val appUser = controller.appUser


    init {
        viewModelScope.launch {

            controller.readContacts()
            controller.getAllAppUser()
        }
    }
}