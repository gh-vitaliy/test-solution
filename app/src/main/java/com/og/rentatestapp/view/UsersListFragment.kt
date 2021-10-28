package com.og.rentatestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.og.rentatestapp.R
import com.og.rentatestapp.databinding.FragmentUsersListBinding
import com.og.rentatestapp.model.adapters.UserAdapter
import com.og.rentatestapp.model.api.UserRepository
import com.og.rentatestapp.model.database.UserDbViewModel
import com.og.rentatestapp.view_model.UsersListViewModel

private const val TAG = "UsersListFragment"

class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding
    private lateinit var userDbViewModel: UserDbViewModel
    private lateinit var usersRecyclerView: RecyclerView
    private val userRepository = UserRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container, false)
        binding = FragmentUsersListBinding.bind(view)
        userDbViewModel = UserDbViewModel.getInstance()
        binding.viewModel = UsersListViewModel.getInstance()
        usersRecyclerView = binding.usersRecyclerView

        setRecyclerViewParams()

        return view
    }

    private fun setRecyclerViewParams() {
        usersRecyclerView.apply {
            val linearLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
            adapter = UserAdapter()
        }
    }

    override fun onStart() {
        super.onStart()
        userRepository.getUsers()
        setObservers()
    }

    private fun setObservers() {
        userDbViewModel.userList.observe(viewLifecycleOwner, { users ->
            binding.viewModel?.usersList?.value = users
        })
        binding.viewModel?.usersList?.observe(viewLifecycleOwner, { users ->
            users?.let { (usersRecyclerView.adapter as UserAdapter).updateUsersList(users) }
        })
        binding.viewModel?.isApiError?.observe(viewLifecycleOwner, { isError ->
            if (isError) showBadConnectionSnackbar()
        })

    }

    private fun showBadConnectionSnackbar() {
        Snackbar.make(
            requireView(),
            R.string.bad_internet_connection,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.update) {
                userRepository.getUsers()
            }
            .show()
    }


    companion object {
        var INSTANCE: UsersListFragment? = null
        fun getInstance(): UsersListFragment {
            return INSTANCE ?: UsersListFragment().also { INSTANCE = it }
        }
    }
}