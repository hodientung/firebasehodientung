package gst.training.practicefirebase.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import gst.training.practicefirebase.data.User
import gst.training.practicefirebase.data.UserResponse
import gst.training.practicefirebase.utils.Constants
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val rootReference: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val userReference: DatabaseReference = rootReference.child(Constants.USER_REFERENCE)
) {

    suspend fun getResponseFromRealtimeDatabaseWithCoroutine(): UserResponse {
        val userResponse = UserResponse()
        try {
            userResponse.user = userReference.get().await().children.map { snapShot ->
                snapShot.getValue(User::class.java)!!
            }

        } catch (exception: Exception) {
            userResponse.exception = exception
        }
        return userResponse
    }

}