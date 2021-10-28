package com.og.rentatestapp.model.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.og.rentatestapp.R
import com.og.rentatestapp.databinding.UserListItemBinding
import com.og.rentatestapp.model.User
import com.og.rentatestapp.view_model.UserViewModel
import com.og.rentatestapp.view_model.UsersListViewModel

class UserAdapter :
    RecyclerView.Adapter<UserAdapter.UsersViewHolder>() {

    private val userListViewModel = UsersListViewModel.getInstance()
    private var usersList = userListViewModel.usersList?.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding =
            DataBindingUtil.inflate<UserListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.user_list_item,
                parent,
                false
            )
        return UsersViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = usersList?.get(position)
        user?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = usersList?.size ?: 0

    fun updateUsersList(usersList: List<User>) {
        this.usersList = usersList
        notifyDataSetChanged()
    }

    inner class UsersViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.viewModel = UserViewModel(binding.root.context)
        }

        fun bind(user: User) {
            binding.apply {
                viewModel?.user = user
                executePendingBindings()
            }
        }

    }
}