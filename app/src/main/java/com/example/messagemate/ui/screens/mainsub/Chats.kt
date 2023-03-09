package com.example.messagemate.ui.screens.mainsub

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.messagemate.model.User
import com.example.messagemate.ui.components.UserChat


// Created by Shahid Iqbal on 3/4/2023.

@Composable
fun Chats(onClick: (user: User) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(count = 10) {
                Surface(modifier = Modifier.clickable {
                    onClick(User(name = "Bingo"))
                }) {
                    UserChat()
                    Divider()
                }

            }
        }

    }
}