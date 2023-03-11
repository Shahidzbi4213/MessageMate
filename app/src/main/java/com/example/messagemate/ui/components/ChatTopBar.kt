package com.example.messagemate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.messagemate.R
import com.example.messagemate.model.User
import com.example.messagemate.ui.theme.MainBlue


// Created by Shahid Iqbal on 3/9/2023.

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatTopBar(user: User? = null, onBackClick: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainBlue)
            .padding(top = 5.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onBackClick.invoke()
        }, modifier = Modifier.padding(top = 3.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(5.dp))

        ProfileImage(
            path = user?.profileUrl, modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)

        ) {}

        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = user?.name ?: "No Name",
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Start,
            color = Color.White

        )

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_videocam_24),
                contentDescription = null,
                tint = Color.White
            )
        }

        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                tint = Color.White
            )
        }


        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = Color.White
            )
        }


        Box(modifier = Modifier, contentAlignment = Alignment.TopEnd) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                properties = PopupProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    clippingEnabled = true,
                    usePlatformDefaultWidth = true
                ), modifier = Modifier.background(Color.White)
            )
            {
                DropdownMenuItem(text = { Text(text = "Clear") },
                    onClick = {
                        expanded = false
                    })
            }
        }

        Spacer(modifier = Modifier.width(5.dp))
    }
}


