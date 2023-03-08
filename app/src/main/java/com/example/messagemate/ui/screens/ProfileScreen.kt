package com.example.messagemate.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.messagemate.data.firebase.Response
import com.example.messagemate.model.User
import com.example.messagemate.presentation.viemodels.AuthViewModel
import com.example.messagemate.ui.components.PoweredComponent
import com.example.messagemate.ui.components.ProfileImage
import com.example.messagemate.ui.screens.destinations.MainScreenDestination
import com.example.messagemate.ui.theme.MainBlue
import com.example.messagemate.ui.theme.MainGray
import com.example.messagemate.utils.Extensions.toast
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

// Created by Shahid Iqbal on 3/1/2023.


@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel = koinViewModel(),
) {

    var profilePicUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var name by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var progressState by remember {
        mutableStateOf(false)
    }

    var buttonText = "Setup Profile"
    val userProfile = authViewModel.userProfile.collectAsState().value

    if (userProfile != null) {
        name = userProfile.name!!
        description = userProfile.description ?: ""
        profilePicUri = userProfile.profileUrl?.toUri()
        buttonText = "Update Profile"
    }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                profilePicUri = it
            })


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
            enabled = !progressState,
            onClick = {
                keyboardController?.hide()
                val user = User(
                    id = null,
                    name = name,
                    description = description,
                    profileUrl = profilePicUri?.toString()
                )
                authViewModel.createProfile(user = user)
                //navigator.navigate(ProfileScreenDestination)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MainBlue),
            shape = RectangleShape,
            contentPadding = PaddingValues(15.dp)
        ) {
            Text(text = buttonText)
        }


        Spacer(modifier = Modifier.height(40.dp))
        if (progressState) CircularProgressIndicator()

        Spacer(modifier = Modifier.weight(1f))
        PoweredComponent(modifier = Modifier.align(Alignment.CenterHorizontally))
    }

    authViewModel.profileState.collectAsState().value.apply {
        when (this) {
            Response.Empty -> Unit
            is Response.Error -> {
                progressState = false
                this.error.toast(context)
            }
            Response.Loading -> progressState = true
            is Response.Success -> {
                progressState = false
                navigator.navigate(MainScreenDestination)
            }
        }
    }

}

