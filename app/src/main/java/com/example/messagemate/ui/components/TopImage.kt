package com.example.messagemate.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.messagemate.R


// Created by Shahid Iqbal on 2/28/2023.

@Composable
fun TopImage(@DrawableRes imageId:Int) =
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = Modifier
            .padding(top = 20.dp)
            .size(100.dp)
    )
