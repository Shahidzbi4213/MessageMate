package com.example.messagemate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.messagemate.ui.theme.MainBlue
import com.example.messagemate.ui.theme.MainWhite
import com.example.messagemate.R


// Created by Shahid Iqbal on 3/10/2023.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendBar(modifier: Modifier = Modifier) {

    var messageText by rememberSaveable() {
        mutableStateOf("")
    }


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color.Transparent)

    ) {

        TextField(
            value = messageText,
            onValueChange = { messageText = it },
            placeholder = { Text(text = "Type your message") },
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(100.dp))
                .shadow(30.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MainBlue
            ), trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.baseline_attachment_24
                        ), contentDescription = null
                    )
                }
            }
        )

        // Send button
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(
                    start = 7.dp
                ),
            onClick = { /*sendMessage()*/ },
            containerColor = MainBlue,
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Send,
                contentDescription = "Send",
                tint = Color.White
            )
        }
    }

}

@Preview
@Composable
fun PreviewBar() = SendBar()
