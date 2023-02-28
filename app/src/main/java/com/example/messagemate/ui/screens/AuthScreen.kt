package com.example.messagemate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.messagemate.R
import com.example.messagemate.ui.components.PoweredComponent
import com.example.messagemate.ui.components.TopImage
import com.example.messagemate.ui.screens.destinations.OTPScreenDestination
import com.example.messagemate.ui.theme.Green
import com.example.messagemate.ui.theme.Purple80
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


// Created by Shahid Iqbal on 2/20/2023.

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Destination(start = true)
@Composable
fun LoginScreen(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {


    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()) {

        TopImage(R.drawable.mobile_auth)

        Text(
            text = "Verify your Phone Number",
            fontWeight = FontWeight.Bold, fontSize = 22.sp,
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
                    top = 20.dp,
                    start = 10.dp
                ), fontSize = 16.sp
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.length < 11)
                        phoneNumber = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                prefix = { Text(text = "+92") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Purple80),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp, start = 10.dp, end = 10.dp)
            )


            Button(
                onClick = {
                    if (phoneNumber.isNotBlank())
                        navigator.navigate(
                            OTPScreenDestination
                        )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green),
                shape = RectangleShape, contentPadding = PaddingValues(14.dp)
            ) {
                Text(text = "Send Code")
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        PoweredComponent(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}
