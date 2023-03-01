package com.example.messagemate.ui.viemodels

import androidx.lifecycle.ViewModel
import com.example.messagemate.data.repository.AuthRepo
import kotlinx.coroutines.flow.asStateFlow


// Created by Shahid Iqbal on 3/1/2023.

class AuthViewModel(private val repo: AuthRepo) : ViewModel() {


    val signUpState = repo.signUpState.asStateFlow()

    fun createAccount(phone: String) = repo.createUser(phone)
    fun verifyCode(otp: String) = repo.verifyOtp(otp)
}