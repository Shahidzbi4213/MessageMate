package com.example.messagemate

import android.app.Application
import com.example.messagemate.data.modules.appModule
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


// Created by Shahid Iqbal on 2/22/2023.

class MessageMateApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Reference Android context
            androidContext(this@MessageMateApp)
            // Load modules
            modules(appModule)

        }

        //Enable Offline Support
        Firebase.database.setPersistenceEnabled(true)

    }
}