package gst.training.practicefirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import gst.training.practicefirebase.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userResponse = liveData(Dispatchers.IO) {
        emit(userRepository.getResponseFromRealtimeDatabaseWithCoroutine())
    }

}