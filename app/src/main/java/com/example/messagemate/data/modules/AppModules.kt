package com.example.messagemate.data.modules

import com.example.messagemate.MainActivity
import com.example.messagemate.data.repository.AuthRepo
import com.example.messagemate.data.repository.AuthRepoImpl
import com.example.messagemate.data.repository.ContactController
import com.example.messagemate.data.repository.ContactControllerImpl
import com.example.messagemate.presentation.viemodels.AuthViewModel
import com.example.messagemate.presentation.viemodels.ContactViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module


// Created by Shahid Iqbal on 3/1/2023.

val appModule = module {

    single { Firebase.auth }
    single { Firebase.database.reference }
    single { Firebase.storage }
    factory { MainActivity.getInstance() }
    single<AuthRepo> { AuthRepoImpl(get(), get(), get(), get()) }
    single<ContactController> { ContactControllerImpl(get(), get(), get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { ContactViewModel(get()) }
}
