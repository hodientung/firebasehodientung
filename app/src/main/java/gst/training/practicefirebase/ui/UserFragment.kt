package gst.training.practicefirebase.ui

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
import gst.training.practicefirebase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {

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
}