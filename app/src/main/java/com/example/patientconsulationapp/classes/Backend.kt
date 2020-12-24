package com.example.patientconsulationapp.classes

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.patientconsulationapp.AppointmentFragment
import com.example.patientconsulationapp.model.User
import com.example.patientconsulationapp.ui.FinalFragment
import com.example.patientconsulationapp.ui.ProfileFragment
import com.example.patientconsulationapp.utils.Constants
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

    fun registerUser(user: User) {
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

    fun loadUserData(activity: Fragment) {

        // Here we pass the collection name from which we wants the data.
        mFireStore
            // The document id to get the Fields of user.
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.e(activity.javaClass.simpleName, document.toString())

                // Here we have received the document snapshot which is converted into the User Data model object.
                val loggedInUser = document.toObject(User::class.java)!!

                // Here call a function of base activity for transferring the result to it.
                when (activity) {
                    is AppointmentFragment -> {
                        activity.setUserDataInUI(loggedInUser)
                    }
                    is FinalFragment -> {
                        activity.setUserDataInUI(loggedInUser)
                    }

                }
            }
            .addOnFailureListener { e ->
                // Here call a function of base activity for transferring the result to it.
                when (activity) {
                    is AppointmentFragment -> {

                    }

                }
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting loggedIn user details",
                    e
                )
            }
    }

    fun updateUserProfileData(activity: ProfileFragment, userHashMap: HashMap<String, Any>) {
        mFireStore
            .document(getCurrentUserID()) // Document ID
            .update(userHashMap) // A hashmap of fields which are to be updated.
            .addOnSuccessListener {
                // Profile data is updated successfully.
                Log.e(activity.javaClass.simpleName, "Profile Data updated successfully!")

                //Toast.makeText(activity, "Profile updated successfully!", Toast.LENGTH_SHORT).show()

                // Notify the success result.
                activity.profileUpdateSuccess()
            }
            .addOnFailureListener { e ->

                Log.e(
                    activity.javaClass.simpleName,
                    "Error while creating a board.",
                    e
                )
            }
    }
}