package com.og.testapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.og.testapp.App
import com.og.testapp.R
import com.og.testapp.databinding.FragmentUserListBinding
import com.og.testapp.databinding.UserListItemBinding
import com.og.testapp.model.entity.User
import com.og.testapp.model.util.LoadingStatus
import com.og.testapp.view_model.UserListViewModel
import com.og.testapp.view_model.UserViewModel
import javax.inject.Inject

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private lateinit var userRecyclerView: RecyclerView

    @Inject
    lateinit var userListViewModel: UserListViewModel

    interface Callback {
        fun onUserClick(userId:Int)
    }

    private var callback: Callback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (context?.applicationContext as App).userListComponent.inject(this)
        super.onCreate(savedInstanceState)
        callback = context as Callback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        binding = FragmentUserListBinding.bind(view)
        binding.viewModel = userListViewModel
        userRecyclerView = binding.usersRecyclerView
        setRecyclerView()
        setObservers()

        return view
    }

    private fun setObservers() {
        userListViewModel.userList.observe(viewLifecycleOwner) { list ->
            userRecyclerView.adapter = UserAdapter(list)
        }
        userListViewModel.loadingStatus.observe(viewLifecycleOwner) { loadingStatus ->
            if (loadingStatus.status == LoadingStatus.Status.ERROR) {
                showBadConnectionSnackbar()
            }
        }
    }

    private fun setRecyclerView() {
        val linerLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        userRecyclerView.apply {
            layoutManager = linerLayoutManager
            addItemDecoration(
                DividerItemDecoration(context, linerLayoutManager.orientation)
            )
            setHasFixedSize(false)
            adapter = UserAdapter(listOf())
        }
    }

    private fun showBadConnectionSnackbar() {
        Snackbar.make(
            binding.root,
            getString(R.string.bad_internet_connection),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(getString(R.string.update)) {
                userListViewModel.fetchData()
            }
            .show()
    }


    inner class UserAdapter(private val users: List<User>) :
        RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

        inner class UserViewHolder(private val binding: UserListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {



            init {
                binding.viewModel = UserViewModel()
            }

            fun bind(user: User) {
                binding.apply {
                    viewModel?.user = user
                    executePendingBindings()
                }
                binding.root.setOnClickListener { callback?.onUserClick(user.id) }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            val binding =
                DataBindingUtil
                    .inflate<UserListItemBinding>(
                        layoutInflater,
                        R.layout.user_list_item,
                        parent,
                        false
                    )
            return UserViewHolder(binding)
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            holder.bind(users[position])
        }

        override fun getItemCount(): Int = users.size

    }

}