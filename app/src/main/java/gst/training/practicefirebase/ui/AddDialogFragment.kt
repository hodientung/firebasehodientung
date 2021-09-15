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
import kotlinx.android.synthetic.main.fragment_add_dialog.*


class AddDialogFragment : DialogFragment() {

    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        return inflater.inflate(R.layout.fragment_add_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserverUser()
        setupEventAddUser()
    }

    private fun setupObserverUser() {
        userViewModel.user.observe(viewLifecycleOwner, {
            val message = if (it == null) {
                getString(R.string.user_added)
            } else {
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })
    }

    private fun setupEventAddUser() {
        btnAddUser.setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                etName.error = getString(R.string.error_field_required)
                return@setOnClickListener
            }
            val user = User()
            user.name = name
            userViewModel.addUser(user)
        }
    }

    private fun setupViewModel() {
        userViewModel =
            ViewModelProvider(this).get(getViewModel())
    }

    private fun getViewModel() = UserViewModel::class.java

    companion object {

        @JvmStatic
        fun newInstance() = AddDialogFragment()
    }
}