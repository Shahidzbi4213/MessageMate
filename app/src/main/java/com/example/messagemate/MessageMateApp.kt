package com.example.messagemate

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


// Created by Shahid Iqbal on 2/22/2023.

class MessageMateApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MessageMateApp)
            // Load modules
           // modules(KoinModules.ext)

        }

    }
}