package com.example.messagemate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.messagemate.R
import com.example.messagemate.ui.screens.destinations.ProfileScreenDestination
import com.example.messagemate.ui.theme.Green
import com.example.messagemate.ui.theme.MainBlue
import com.example.messagemate.utils.Extensions.debug
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


// Created by Shahid Iqbal on 3/4/2023.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopApp(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {

    var expanded by remember { mutableStateOf(false) }
    var goToProfile by remember {
        mutableStateOf(false)
    }


    TopAppBar(title = {
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MainBlue),
        modifier = modifier,
        actions = {

            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "Localized description",
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
                        clippingEnabled = true
                    ),
                    modifier = Modifier.background(Color.White)
                ) {
                    DropdownMenuItem(text = { Text(text = "Profile") }, onClick = {
                        goToProfile = true
                        expanded = false

                    })
                    Spacer(modifier = Modifier.height(5.dp))
                    DropdownMenuItem(text = { Text(text = "Users") },
                        onClick = { expanded = false })

                    Spacer(modifier = Modifier.height(5.dp))
                    DropdownMenuItem(text = { Text(text = "Logout") },
                        onClick = { expanded = false })

                }
            }
        })

    if (goToProfile) navigator.navigate(ProfileScreenDestination)
}


