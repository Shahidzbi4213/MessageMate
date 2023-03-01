package com.example.messagemate.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost


// Created by Shahid Iqbal on 2/28/2023.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    Scaffold(modifier = Modifier) {
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            modifier = Modifier
                .padding(it)
        )
    }
}