package com.example.messagemate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messagemate.R
import com.example.messagemate.data.firebase.Response
import com.example.messagemate.ui.components.PoweredComponent
import com.example.messagemate.ui.components.TopImage
import com.example.messagemate.ui.screens.destinations.OTPScreenDestination
import com.example.messagemate.ui.screens.destinations.ProfileScreenDestination
import com.example.messagemate.ui.theme.Green
import com.example.messagemate.ui.viemodels.AuthViewModel
import com.example.messagemate.utils.Extensions.toast
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 2/28/2023.

@Composable
@Destination
fun OTPScreen(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {

    var otpText by remember {
        mutableStateOf("")
    }

    val authViewModel: AuthViewModel = koinViewModel()
    val context = LocalContext.current



    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopImage(R.drawable.otp_verify)

        Text(
            text = "OTP Verification",
            fontWeight = FontWeight.Bold, fontSize = 22.sp,
            modifier = Modifier.padding(top = 15.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .height(220.dp)
                .padding(top = 50.dp)
                .align(Alignment.CenterHorizontally),
            elevation = CardDefaults.cardElevation(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    text = "Provide 6 digits OTP",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )

                BasicTextField(
                    modifier = modifier.padding(top = 20.dp),
                    value = otpText,
                    onValueChange = {
                        otpText = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    decorationBox = {
                        Row(horizontalArrangement = Arrangement.Center) {
                            repeat(6) { index ->
                                CharView(
                                    index = index,
                                    text = otpText
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
                )

                Button(
                    onClick = {
                        if (otpText.isNotBlank() && otpText.length == 6)
                            authViewModel.verifyCode(otpText)

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, start = 10.dp, end = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                    shape = RectangleShape, contentPadding = PaddingValues(14.dp)
                ) {
                    Text(text = "Continue")
                }
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
                    //message.toast(context)
                  //  navigator.navigate(ProfileScreenDestination)
                }
            }
        }

    }
}

@Composable
private fun CharView(
    index: Int,
    text: String,
) {
    val char = when {
        index >= text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(40.dp)
            .background(Color.LightGray)
            .drawBehind {
                drawLine(
                    color = Green,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }
            .padding(10.dp),
        text = char,
        textAlign = TextAlign.Center
    )

}