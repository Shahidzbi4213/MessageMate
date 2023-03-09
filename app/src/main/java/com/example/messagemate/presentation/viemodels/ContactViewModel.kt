package com.example.messagemate.presentation.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messagemate.data.repository.ContactController
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/9/2023.

class ContactViewModel(private val controller: ContactController) : ViewModel() {

    init {
        viewModelScope.launch {

            controller.readContacts()
        }
    }
}