package gst.training.practicefirebase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import gst.training.practicefirebase.data.User
import gst.training.practicefirebase.utils.Constants

class UserViewModel : ViewModel() {

    private val databaseUser = FirebaseDatabase.getInstance().getReference(Constants.NODE_USER)

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    private val _user = MutableLiveData<User>()
    val author: LiveData<User>
        get() = _user

    fun addUser(user: User) {
        user.id = databaseUser.push().key
        databaseUser.child(user.id!!).setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }


    fun fetchUsers() {
        databaseUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val authors = mutableListOf<User>()
                    for (authorSnapshot in snapshot.children) {
                        val user = authorSnapshot.getValue(User::class.java)
                        user?.id = authorSnapshot.key
                        user?.let { authors.add(it) }
                    }
                    _users.value = authors
                }
            }
        })
    }

    fun updateUser(user: User) {
        databaseUser.child(user.id!!).setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

    fun deleteUser(user: User) {
        databaseUser.child(user.id!!).setValue(null)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        databaseUser.removeEventListener(childEventListener)
    }

    private val childEventListener = object : ChildEventListener {
        override fun onCancelled(error: DatabaseError) {}

        override fun onChildMoved(snapshot: DataSnapshot, p1: String?) {}

        override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
            val user = snapshot.getValue(User::class.java)
            user?.id = snapshot.key
            _user.value = user!!
        }

        override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
            val user = snapshot.getValue(User::class.java)
            user?.id = snapshot.key
            _user.value = user!!
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            val user = snapshot.getValue(User::class.java)
            user?.id = snapshot.key
            user?.isDeleted = true
            _user.value = user!!
        }
    }
}