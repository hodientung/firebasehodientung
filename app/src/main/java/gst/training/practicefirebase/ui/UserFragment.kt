package gst.training.practicefirebase.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import gst.training.practicefirebase.R
import gst.training.practicefirebase.adapter.UserAdapter
import gst.training.practicefirebase.data.User
import gst.training.practicefirebase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var userViewModel: UserViewModel
    private val userAdapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        moveToAddDialogFragmentScreen()

        userViewModel.users.observe(viewLifecycleOwner, {
            userAdapter.setUsers(it)
        })
        userViewModel.author.observe(viewLifecycleOwner,{
            userAdapter.addUser(it)
        })
    }

    private fun moveToAddDialogFragmentScreen() {
        btnAdd.setOnClickListener {
            AddDialogFragment.newInstance().show(childFragmentManager, "")
        }
    }

    private fun setupRecyclerView() {
        rvUser.layoutManager = LinearLayoutManager(context)
        rvUser.addItemDecoration(
            DividerItemDecoration(
                rvUser.context,
                (rvUser.layoutManager as LinearLayoutManager).orientation
            )
        )
        userAdapter.listener = this
        rvUser.adapter = userAdapter
    }

    private fun setupViewModel() {
        userViewModel =
            ViewModelProvider(this).get(getViewModel())
    }

    private fun getViewModel() = UserViewModel::class.java

    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()
    }

    override fun onRecyclerViewItemClicked(view: View, user: User) {
        when (view.id) {
            R.id.ivUpdate -> {
                UpdateUserDialogFragment(user).show(childFragmentManager, "")
            }
            R.id.ivDelete -> {
                showDialogDelete(user)
            }
        }
    }

    private fun showDialogDelete(user: User) {
        AlertDialog.Builder(requireContext()).also {
            it.setTitle(getString(R.string.delete_confirmation))
            it.setPositiveButton(getString(R.string.yes)) { _, _ ->
                userViewModel.deleteUser(user)
            }
        }.create().show()
    }

}