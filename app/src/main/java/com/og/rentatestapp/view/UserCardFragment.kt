package com.og.rentatestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.og.rentatestapp.R
import com.og.rentatestapp.databinding.FragmentUserCardBinding
import com.og.rentatestapp.model.database.UserDbViewModel
import com.og.rentatestapp.view_model.UserViewModel

private const val TAG = "UserCardFragment"
private const val USER_ID = "USER_ID"

class UserCardFragment : Fragment() {

    private lateinit var binding: FragmentUserCardBinding
    private lateinit var userDbViewModel: UserDbViewModel
    private var userId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_card, container, false)
        binding = FragmentUserCardBinding.bind(view)
        userDbViewModel = UserDbViewModel.getInstance()
        binding.viewModel = UserViewModel(requireContext())
        userId = requireArguments().getInt(USER_ID)
        setObservers()

        return view
    }

    private fun setObservers() {
        userDbViewModel.getUserById(userId).observe(viewLifecycleOwner) { user ->
            binding.viewModel?.user = user
            user?.let { loadAvatar() }
        }
    }


    private fun loadAvatar() {
        val circularProgressBar =
            CircularProgressDrawable(requireContext()).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            }
        Glide.with(requireContext())
            .load(binding.viewModel?.avatar)
            .fitCenter()
            .placeholder(circularProgressBar)
            .error(R.drawable.users_menu_item_icon)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.userAvatarImageView)
    }

    companion object {
        fun newInstance(userId: Int): UserCardFragment {
            val args = Bundle().apply {
                putInt(USER_ID, userId)
            }
            return UserCardFragment().apply {
                arguments = args
            }
        }
    }


}