package com.og.testapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.og.testapp.R
import com.og.testapp.databinding.FragmentUserCardBinding
import com.og.testapp.model.di.DaggerUserCardComponent
import com.og.testapp.model.di.module.DatabaseModule
import com.og.testapp.model.di.module.UserCardModule
import com.og.testapp.view_model.UserCardViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCardFragment : Fragment() {

    private lateinit var binding: FragmentUserCardBinding
    private lateinit var avatarImageView: ImageView

    @Inject
    lateinit var userCardViewModel: UserCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val userCardComponent =
            DaggerUserCardComponent.builder()
                .userCardModule(UserCardModule(this))
                .databaseModule(DatabaseModule())
                .build()
        userCardComponent.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_card, container, false)
        binding = FragmentUserCardBinding.bind(view)
        binding.viewModel = userCardViewModel
        avatarImageView = binding.avatarImageView
        setObservers()

        return view
    }

    private fun setObservers() {
        userCardViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let { loadAvatar(it.avatar) }
        }
    }

    private fun loadAvatar(link: String) {
        Glide.with(requireContext())
            .load(link)
            .fitCenter()
            .placeholder(CircularProgressDrawable(requireContext()))
            .error(R.drawable.users_menu_item_icon)
            .into(avatarImageView)
    }


}