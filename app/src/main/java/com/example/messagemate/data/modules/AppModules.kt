package com.example.messagemate.data.modules

import com.example.messagemate.MainActivity
import com.example.messagemate.data.repository.AuthRepo
import com.example.messagemate.data.repository.AuthRepoImpl
import com.example.messagemate.ui.viemodels.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


// Created by Shahid Iqbal on 3/1/2023.

val appModule = module {

    single { Firebase.auth }
    factory { MainActivity.getInstance() }
    single<AuthRepo> { AuthRepoImpl(get(), get()) }
    viewModel { AuthViewModel(get()) }
}
