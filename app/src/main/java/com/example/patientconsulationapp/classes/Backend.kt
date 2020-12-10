package com.example.patientconsulationapp.classes

import com.example.patientconsulationapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Backend
{
    // Create a instance of Firebase Firestore
    private val mFireStore = Firebase.firestore.collection("User")

    fun registerUser(user: User){
        GlobalScope.launch(Dispatchers.IO)
        {
            mFireStore.document(getCurrentUserID()).set(user).await()
        }


    }
    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId

    }
}