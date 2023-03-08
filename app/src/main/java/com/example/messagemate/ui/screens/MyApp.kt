package com.example.messagemate.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.messagemate.ui.screens.destinations.LoginScreenDestination
import com.example.messagemate.ui.screens.destinations.MainScreenDestination
import com.example.messagemate.presentation.viemodels.AuthViewModel
import com.example.messagemate.ui.components.TopApp
import com.ramcosta.composedestinations.DestinationsNavHost
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 2/28/2023.

@Composable
fun MyApp(viewModel: AuthViewModel = koinViewModel()) {
    Scaffold(modifier = Modifier) {
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            startRoute =
            if (viewModel.currentUser != null)
                MainScreenDestination
            else
                LoginScreenDestination,
            modifier = Modifier.padding(it)
        )
    }
}