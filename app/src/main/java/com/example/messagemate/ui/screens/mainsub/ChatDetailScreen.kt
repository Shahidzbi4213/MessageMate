package com.example.messagemate.ui.screens.mainsub

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.messagemate.model.User
import com.example.messagemate.ui.components.ChatTopBar
import com.example.messagemate.ui.components.SendBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.messagemate.R


// Created by Shahid Iqbal on 3/9/2023.


@Destination
@Composable
fun ChatDetailScreen(user: User, navigator: DestinationsNavigator) {


    Box(modifier = Modifier) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ChatTopBar(user = user, onBackClick = { navigator.popBackStack() })
            LazyColumn(modifier = Modifier.weight(1f)) { items(10) {} }
            SendBar(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}