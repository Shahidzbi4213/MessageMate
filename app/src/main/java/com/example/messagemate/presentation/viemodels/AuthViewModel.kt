package com.example.messagemate.presentation.viemodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messagemate.data.repository.AuthRepo
import com.example.messagemate.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/1/2023.

class AuthViewModel(private val repo: AuthRepo) : ViewModel() {


    val phoneSendState = repo.phoneSendState.asStateFlow()
    val otpState = repo.otpState.asStateFlow()
    val profileState = repo.profileCreationState.asStateFlow()
    val currentUser = repo.currentUser
    val userProfile = repo.currentUserProfile.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (currentUser != null)
                repo.getLoggedInUserData()
        }
    }

    fun createAccount(phone: String) =
        viewModelScope.launch(Dispatchers.IO) { repo.createUser(phone) }

    fun verifyCode(otp: String) = viewModelScope.launch(Dispatchers.IO) { repo.verifyOtp(otp) }
    fun createProfile(user: User) =
        viewModelScope.launch(Dispatchers.IO) { repo.createProfile(user) }


}