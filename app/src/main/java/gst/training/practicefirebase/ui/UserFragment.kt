package gst.training.practicefirebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import gst.training.practicefirebase.R
import gst.training.practicefirebase.adapter.UserAdapter
import gst.training.practicefirebase.repository.UserRepository
import gst.training.practicefirebase.viewmodel.UserViewModel
import gst.training.practicefirebase.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        userViewModel.userResponse.observe(viewLifecycleOwner, {
            it.user?.let { users ->
                userAdapter.differ.submitList(users)
            }
            it.exception?.let { exception ->
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupUI() {
        rvUser.layoutManager = LinearLayoutManager(context)
        userAdapter = UserAdapter()
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
            ViewModelProvider(this, ViewModelFactory(getFragmentRepository())).get(getViewModel())
    }

    private fun getFragmentRepository() = UserRepository()

    private fun getViewModel() = UserViewModel::class.java

    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()
    }
}