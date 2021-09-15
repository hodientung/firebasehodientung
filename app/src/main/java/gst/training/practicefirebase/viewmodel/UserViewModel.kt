package gst.training.practicefirebase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import gst.training.practicefirebase.data.User
import gst.training.practicefirebase.utils.Constants

class UserViewModel : ViewModel() {

    private val databaseUser = FirebaseDatabase.getInstance().getReference(Constants.NODE_USER)
    private val _user = MutableLiveData<Exception?>()
    val user: LiveData<Exception?>
        get() = _user

    fun addUser(user: User) {
        user.id = databaseUser.push().key
        databaseUser.child(user.id!!).setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _user.value = null
                } else {
                    _user.value = it.exception
                }
            }
    }

}