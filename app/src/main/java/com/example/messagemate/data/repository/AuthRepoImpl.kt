package com.example.messagemate.data.repository

import com.example.messagemate.MainActivity
import com.example.messagemate.data.firebase.Response
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class AuthRepoImpl(
    private val context: MainActivity,
    private val auth: FirebaseAuth,
) : AuthRepo {

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            signInCredential(p0)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            signUpState.value = Response.Error(p0.localizedMessage)
        }

        override fun onCodeSent(
            verificationCode: String,
            p1: PhoneAuthProvider.ForceResendingToken,
        ) {
            storeCode = verificationCode
            signUpState.value = Response.Success("Code Send")

            super.onCodeSent(verificationCode, p1)
        }
    }


    private var storeCode: String = ""

    override var signUpState: MutableStateFlow<Response<String>> = MutableStateFlow(Response.Empty)
        private set

    override fun createUser(phone: String) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verifyOtp(otp: String) {
        signUpState.value = Response.Loading
        val credential = PhoneAuthProvider.getCredential(storeCode, otp)
        signInCredential(credential)
    }

    override fun signInCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener(context) { task ->
            signUpState.value = if (task.isSuccessful)
                Response.Success(task.result.user?.phoneNumber ?: "Unknown")
            else
                Response.Error(task.exception?.localizedMessage)

        }
    }
}