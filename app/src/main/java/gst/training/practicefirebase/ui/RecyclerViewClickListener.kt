package gst.training.practicefirebase.ui

import android.view.View
import gst.training.practicefirebase.data.User

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(view: View, user: User)
}