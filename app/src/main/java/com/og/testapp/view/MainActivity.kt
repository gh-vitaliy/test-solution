package com.og.testapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.og.testapp.R
import com.og.testapp.databinding.ActivityMainBinding

private const val USER_ID = "UserId"

class MainActivity : AppCompatActivity(), UserListFragment.Callback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationMenu: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bottomNavigationMenu = binding.bottomNavigationMenu
        bottomNavigationMenu.apply {
            setOnItemSelectedListener(getOnItemSelectedListener())
            selectedItemId = R.id.users_menu_item
        }
    }

    private fun getOnItemSelectedListener(): NavigationBarView.OnItemSelectedListener {
        return NavigationBarView.OnItemSelectedListener { menuItem ->
            val fragment: Fragment
            when (menuItem.itemId) {
                R.id.users_menu_item -> {
                    fragment = UserListFragment()
                    replaceFragment(fragment, false)
                }
                R.id.about_menu_item -> {
                    fragment = AboutFragment()
                    replaceFragment(fragment, false)
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, shouldAddToBackStack: Boolean) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .run {
                if (shouldAddToBackStack) addToBackStack(null)
                commit()
            }
    }


    override fun onUserClick(userId: Int) {
        val fragment = UserCardFragment().apply {
            arguments = Bundle().apply { putInt(USER_ID, userId) }
        }
        replaceFragment(fragment, true)
    }


}