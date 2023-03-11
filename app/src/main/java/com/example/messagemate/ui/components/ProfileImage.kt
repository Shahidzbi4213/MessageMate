package com.example.messagemate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.messagemate.R


// Created by Shahid Iqbal on 3/1/2023.

@Composable
fun ProfileImage(path: Any?, modifier: Modifier = Modifier, onClick: () -> Unit) {

    AsyncImage(
        model = path,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.avatar),
        error = painterResource(id = R.drawable.avatar),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            },
    )
}