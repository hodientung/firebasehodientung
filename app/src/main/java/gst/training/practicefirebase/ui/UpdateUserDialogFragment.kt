package gst.training.practicefirebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import gst.training.practicefirebase.R
import gst.training.practicefirebase.data.User
import gst.training.practicefirebase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update_user_dialog.*


class UpdateUserDialogFragment(private val user: User) : DialogFragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        return inflater.inflate(R.layout.fragment_update_user_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etNameUpdate.setText(user.name)

        userViewModel.result.observe(viewLifecycleOwner, {
            val message = if (it == null) {
                getString(R.string.user_added)
            } else {
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })

        btnAddUserUpdate.setOnClickListener {
            val name = etNameUpdate.text.toString().trim()
            if (name.isEmpty()) {
                etNameUpdate.error = getString(R.string.error_field_required)
                return@setOnClickListener
            }
            user.name = name
            userViewModel.updateUser(user)
        }
    }

    private fun setupViewModel() {
        userViewModel =
            ViewModelProvider(this).get(getViewModel())
    }

    private fun getViewModel() = UserViewModel::class.java
}