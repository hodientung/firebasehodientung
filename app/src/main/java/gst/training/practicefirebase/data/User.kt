package gst.training.practicefirebase.data

import com.google.firebase.database.Exclude
import java.io.Serializable


data class User(
    @get:Exclude
    var id : String? = null, var name: String? = null,
    @get:Exclude
    var isDeleted: Boolean = false
) : Serializable
