package com.example.messagemate.data.repository

import com.example.messagemate.data.firebase.Response
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.MutableStateFlow


// Created by Shahid Iqbal on 3/1/2023.

interface AuthRepo {

    val signUpState: MutableStateFlow<Response<String>>

    fun createUser(phone: String)

    fun verifyOtp(otp:String)

    fun signInCredential(credential: PhoneAuthCredential)
}