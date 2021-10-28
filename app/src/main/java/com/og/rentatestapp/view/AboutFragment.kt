package com.og.rentatestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.og.rentatestapp.R

private const val TAG = "AboutFragment"

class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    companion object {
        var INSTANCE: AboutFragment? = null
        fun getInstance(): AboutFragment {
            return INSTANCE ?: AboutFragment().also { INSTANCE = it }
        }
    }
}