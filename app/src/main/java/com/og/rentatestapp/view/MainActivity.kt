package com.og.rentatestapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.og.rentatestapp.R
import com.og.rentatestapp.databinding.ActivityMainBinding
import com.og.rentatestapp.model.Callback

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), Callback {

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

    private fun getOnItemSelectedListener(): NavigationBarView.OnItemSelectedListener =
        NavigationBarView.OnItemSelectedListener {
            val fragment: Fragment
            when (it.itemId) {
                R.id.users_menu_item -> {
                    fragment = UsersListFragment.getInstance()
                    replaceFragment(fragment,false)
                }
                R.id.about_menu_item -> {
                    fragment = AboutFragment.getInstance()
                    replaceFragment(fragment,false)
                }
                else -> {
                    fragment = AboutFragment.getInstance()
                    replaceFragment(fragment,false)
                }
            }
            Log.d(TAG, "${fragment.tag} placed")
            true
        }


    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .run {
                if (addToBackStack) addToBackStack(null)
                commit()
            }
    }

    override fun onUserItemClicked(userId: Int) {
        val fragment = UserCardFragment.newInstance(userId)
        replaceFragment(fragment,true)
    }


}