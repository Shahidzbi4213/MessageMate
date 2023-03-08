package com.example.messagemate.utils

import androidx.compose.runtime.Composable
import com.example.messagemate.ui.screens.mainsub.Calls
import com.example.messagemate.ui.screens.mainsub.Chats
import com.example.messagemate.ui.screens.mainsub.Status


// Created by Shahid Iqbal on 3/4/2023.

sealed class TabItem(val tabName: String) {

    object Chats : TabItem(tabName = "Chats")
    object Status : TabItem(tabName = "Status")
    object Calls : TabItem(tabName = "Calls")
}