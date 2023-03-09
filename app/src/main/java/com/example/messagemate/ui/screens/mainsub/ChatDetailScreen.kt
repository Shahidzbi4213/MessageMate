package com.example.messagemate.ui.screens.mainsub

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.messagemate.model.User
import com.example.messagemate.ui.components.ChatTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


// Created by Shahid Iqbal on 3/9/2023.


@Destination
@Composable
fun ChatDetailScreen(user: User, navigator: DestinationsNavigator) {


    Column(modifier = Modifier.fillMaxSize()) {
        ChatTopBar(user = user, onBackClick = { navigator.popBackStack() })
    }
}