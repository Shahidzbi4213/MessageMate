package com.example.messagemate.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.messagemate.R


// Created by Shahid Iqbal on 2/28/2023.

@Composable
fun PoweredComponent(modifier: Modifier){
    Text(
        text = "Powered by: ${stringResource(id = R.string.app_name)}",
        modifier = modifier
            .padding(bottom = 5.dp),
        textAlign = TextAlign.Center,
        color = Color.DarkGray,
        fontStyle = FontStyle.Italic
    )
}