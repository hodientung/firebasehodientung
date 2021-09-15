package gst.training.practicefirebase.data

import com.google.firebase.database.Exclude


data class User(
    @get:Exclude
    var id : String? = null, var name: String? = null
)
