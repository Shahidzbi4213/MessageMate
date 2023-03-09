package com.example.messagemate.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.provider.ContactsContract
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.messagemate.model.UserContact
import com.example.messagemate.utils.Extensions.debug
import kotlinx.coroutines.flow.MutableStateFlow

class ContactControllerImpl(private val context: Application) : ContactController {

    override val contactList: MutableStateFlow<List<UserContact>> =
        MutableStateFlow(emptyList())

    @SuppressLint("Range")
    override suspend fun readContacts() {

        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null
        ).use { cr ->

            while (cr?.moveToNext() == true) {
                val phoneNumber: String =
                    cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                val name: String =
                    cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

                contactList.value += UserContact(name, phoneNumber)
            }
        }
    }
}