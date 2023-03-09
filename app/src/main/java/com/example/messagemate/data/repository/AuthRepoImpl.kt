package com.example.messagemate.data.repository

import android.net.Uri
import androidx.core.net.toUri
import com.example.messagemate.MainActivity
import com.example.messagemate.data.firebase.Response
import com.example.messagemate.model.User
import com.example.messagemate.utils.Extensions.debug
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AuthRepoImpl(
    private val context: MainActivity,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage,
    private val db: DatabaseReference,
) : AuthRepo {


    private var storeCode: String = ""

    override var phoneSendState: MutableStateFlow<Response<String>> =
        MutableStateFlow(Response.Empty)
        private set

    override var otpState: MutableStateFlow<Response<FirebaseUser>> =
        MutableStateFlow(Response.Empty)
        private set

    override var profileCreationState: MutableStateFlow<Response<String>> =
        MutableStateFlow(Response.Empty)
        private set


    override var currentUserProfile: MutableStateFlow<User?> =
        MutableStateFlow(null)
        private set


    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun createUser(phone: String) {

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                CoroutineScope(Dispatchers.IO).launch {
                    signInCredential(p0)
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                phoneSendState.value = Response.Error(p0.localizedMessage)
            }

            override fun onCodeSent(
                verificationCode: String,
                p1: PhoneAuthProvider.ForceResendingToken,
            ) {
                storeCode = verificationCode
                phoneSendState.value = Response.Success("Code Sent")

                super.onCodeSent(verificationCode, p1)
            }
        }

        phoneSendState.value = Response.Loading
        val options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS).setActivity(context).setCallbacks(callbacks).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override suspend fun verifyOtp(otp: String) {
        otpState.value = Response.Loading
        val credential = PhoneAuthProvider.getCredential(storeCode, otp)
        signInCredential(credential)
    }

    override suspend fun signInCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener(context) { task ->

            otpState.value = if (task.isSuccessful) Response.Success(task.result?.user)
            else Response.Error(task.exception?.localizedMessage)

        }
    }

    override suspend fun createProfile(user: User) {
        if (user.name.isNullOrBlank()) profileCreationState.value = Response.Error("Provide Name")
        else {
            profileCreationState.value = Response.Loading
            if (!user.profileUrl.isNullOrBlank()) {
                val storageRef = storage.reference
                val imageRef = storageRef.child("profiles").child(auth.uid!!)
                imageRef.putFile(user.profileUrl.toUri()).addOnCompleteListener { task ->
                    if (task.isSuccessful) insertToDb(user, imageRef.downloadUrl)
                    else profileCreationState.value =
                        Response.Error(task.exception?.localizedMessage)

                }
            } else insertToDb(user, null)
        }

    }

    private fun insertToDb(user: User, downloadUrl: Task<Uri>?) {
        downloadUrl?.addOnSuccessListener {
            val currentUser = user.copy(
                id = auth.uid, profileUrl = it.toString(),
                phoneNumber = auth.currentUser?.phoneNumber
            )
            dbData(currentUser)
        }?.addOnFailureListener {
            profileCreationState.value = Response.Error(it.localizedMessage)
        } ?: dbData(
            currentUser = user.copy(
                id = auth.uid,
                phoneNumber = auth.currentUser?.phoneNumber
            )
        )
    }

    private fun dbData(currentUser: User) {
        db.child("users").child(currentUser.id!!).setValue(currentUser).addOnSuccessListener {
            profileCreationState.value = Response.Success("Profile Created")
        }.addOnFailureListener { e ->
            profileCreationState.value = Response.Error(e.localizedMessage)
        }
    }

    override suspend fun getLoggedInUserData() {
        val id = auth.uid
        db.child("users").child("$id").get().addOnSuccessListener() {
            currentUserProfile.value = it.getValue(User::class.java)
        }
    }
}