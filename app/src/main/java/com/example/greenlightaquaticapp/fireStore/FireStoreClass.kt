package com.example.greenlightaquaticapp.fireStore

import android.app.Activity
import android.util.Log
import com.example.greenlightaquaticapp.activity.LoginActivity
import com.example.greenlightaquaticapp.activity.RegisterActivity
import com.example.greenlightaquaticapp.constants.Constant.Companion.HEAD_LOG
import com.example.greenlightaquaticapp.constants.DatabaseCollectionName
import com.example.greenlightaquaticapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User) {
        Log.d(HEAD_LOG, "Registration Process")
        mFireStore.collection(DatabaseCollectionName.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge()).addOnCompleteListener {
                Log.d(HEAD_LOG, "Registration Success")
                activity.userRegistrationSuccess()
            }.addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(HEAD_LOG, "error when registration user")
            }
    }

    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        Log.d(HEAD_LOG, "getCurrentUserID ${currentUser?.uid}")
        Log.d(HEAD_LOG, "getUser ${currentUser.toString()}")
        return currentUserId
    }

    fun getCurrentUser(activity: Activity) {
        var userID = ""
        if(getCurrentUserID().isNullOrEmpty()){
            userID = "oijuh4ulwJcCa0cV493X4TKjGXT2"
        }else{
            userID = getCurrentUserID()
        }
        Log.d(HEAD_LOG, "getCurrentUser Process")
        mFireStore.collection(DatabaseCollectionName.USERS)
            .document(userID)
            .get().addOnSuccessListener { document ->
                Log.d(HEAD_LOG, "getCurrentUser Success")
                val user = document.toObject(User::class.java)
                when (activity){
                    is LoginActivity -> {
                        if (user != null) {
                            activity.userLoggedInSuccess(user)
                        }
                    }
                }
                //end
            }.addOnFailureListener { exception ->
                Log.e(HEAD_LOG, "error when getCurrentUser", exception)
            }
    }
}