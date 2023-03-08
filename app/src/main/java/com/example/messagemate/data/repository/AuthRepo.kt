package com.example.messagemate.data.repository

import com.example.messagemate.data.firebase.Response
import com.example.messagemate.model.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


// Created by Shahid Iqbal on 3/1/2023.

interface AuthRepo {

    val phoneSendState: MutableStateFlow<Response<String>>

    val otpState: MutableStateFlow<Response<FirebaseUser>>

    val profileCreationState: MutableStateFlow<Response<String>>

    val currentUser: FirebaseUser?

    val currentUserProfile: MutableStateFlow<User?>


    suspend fun createUser(phone: String)

    suspend fun verifyOtp(otp: String)

    suspend fun signInCredential(credential: PhoneAuthCredential)

    suspend fun createProfile(user: User)

    suspend fun getLoggedInUserData()

}