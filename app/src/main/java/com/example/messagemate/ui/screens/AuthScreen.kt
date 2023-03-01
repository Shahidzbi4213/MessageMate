package com.example.messagemate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.messagemate.R
import com.example.messagemate.data.firebase.Response
import com.example.messagemate.data.repository.AuthRepoImpl
import com.example.messagemate.ui.components.PoweredComponent
import com.example.messagemate.ui.components.TopImage
import com.example.messagemate.ui.screens.destinations.OTPScreenDestination
import com.example.messagemate.ui.theme.Green
import com.example.messagemate.ui.theme.Purple80
import com.example.messagemate.ui.viemodels.AuthViewModel
import com.example.messagemate.utils.Extensions.toast
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Destination(start = true)
@Composable
fun LoginScreen(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {


    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val authViewModel: AuthViewModel = koinViewModel()



    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()
    ) {

        TopImage(R.drawable.mobile_auth)

        Text(
            text = "Verify your Phone Number",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 15.dp)
        )
        Text(text = "We will send you code to verify you number", fontSize = 12.sp)

        Card(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .height(225.dp)
                .padding(top = 50.dp),
            elevation = CardDefaults.cardElevation(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Text(
                text = "Phone", fontWeight = FontWeight.Bold, modifier = Modifier.padding(
                    top = 20.dp, start = 10.dp
                ), fontSize = 16.sp
            )
            TextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.length < 11) phoneNumber = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                ),
                prefix = { Text(text = "+92") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp, start = 10.dp, end = 10.dp)
            )


            Button(
                onClick = {
                    keyboardController?.hide()
                    if (phoneNumber.isNotBlank() && phoneNumber.length == 10)
                        authViewModel.createAccount(
                            phoneNumber.let { "+92".plus(it) })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green),
                shape = RectangleShape,
                contentPadding = PaddingValues(14.dp)
            ) {
                Text(text = "Send Code")
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        PoweredComponent(modifier = Modifier.align(Alignment.CenterHorizontally))

        authViewModel.signUpState.collectAsState().value.apply {
            when (this) {
                Response.Empty -> Unit
                is Response.Error -> error.toast(context)
                Response.Loading -> "Loading".toast(context)
                is Response.Success -> {
                    message.toast(context)
                    navigator.navigate(OTPScreenDestination)
                }
            }
        }
    }
}

// Created by Shahid Iqbal on 2/20/2023.
