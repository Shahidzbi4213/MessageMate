package com.example.messagemate.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messagemate.ui.components.PoweredComponent
import com.example.messagemate.ui.components.ProfileImage
import com.example.messagemate.ui.screens.destinations.ProfileScreenDestination
import com.example.messagemate.ui.theme.Green
import com.example.messagemate.ui.theme.Purple80
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

// Created by Shahid Iqbal on 3/1/2023.


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {

    var profilePicUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var name by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                profilePicUri = it
            })

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileImage(
            path = profilePicUri, onClick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }, modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = "Profile Info",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = "Provide your detail for profile screen",
            fontSize = 12.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Name",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 10.dp)
        )
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, start = 10.dp, end = 10.dp)
        )

        Text(
            text = "Description",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp)
                .fillMaxWidth()
                .align(Alignment.Start),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
        TextField(
            value = description,
            onValueChange = {
                description = it
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            minLines = 3,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, start = 10.dp, end = 10.dp)
        )

        Button(
            onClick = {
                //navigator.navigate(ProfileScreenDestination)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            shape = RectangleShape,
            contentPadding = PaddingValues(15.dp)
        ) {
            Text(text = "Setup Profile")
        }

        Spacer(modifier = Modifier.weight(1f))
        PoweredComponent(modifier = Modifier.align(Alignment.CenterHorizontally))

    }
}

