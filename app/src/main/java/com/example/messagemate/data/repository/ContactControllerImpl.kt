package com.example.messagemate.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.provider.ContactsContract
import com.example.messagemate.model.User
import com.example.messagemate.model.UserContact
import com.example.messagemate.utils.Extensions.debug
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow

class ContactControllerImpl(private val context: Application, private val db: DatabaseReference,private val auth: FirebaseAuth) :
    ContactController {

    override val contactList: MutableStateFlow<List<UserContact>> =
        MutableStateFlow(emptyList())

    override val appUser: MutableStateFlow<List<User?>> =
        MutableStateFlow(emptyList())

    @SuppressLint("Range")
    override suspend fun readContacts() {

        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null
        ).use { cr ->

            while (cr?.moveToNext() == true) {
                val name: String =
                    cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

                val phoneNumber: String =
                    cr.getString(cr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))


                contactList.value += UserContact(name, phoneNumber)
            }
        }
    }

    override suspend fun getAllAppUser() {
        db.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    appUser.value += showUser(listOf(it.getValue(User::class.java)))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                databaseError.toException().debug()
            }


        })
    }


    /**
     * Filters the [users] list to only include users whose phone number is in the [contactList],
     * and updates the name of each remaining user with the corresponding name from the [contactList].
     *
     * @param users the list of users to update
     */
    private fun showUser(users: List<User?>): List<User?> {
        // Filter the messageMateUsers list to only include users whose phone number is in the contactList
        val filteredUsers = users.filter {
            contactList.value.map { c -> c.phoneNumber }.contains(it?.phoneNumber)
        }

        // Map the filtered users to new user objects with updated names from the contactList
        return filteredUsers.map { it?.copy(name = contactList.value.find { c -> c.phoneNumber == it.phoneNumber }?.name) }.filter {
            it?.id  != auth.currentUser?.uid
        }
    }

}