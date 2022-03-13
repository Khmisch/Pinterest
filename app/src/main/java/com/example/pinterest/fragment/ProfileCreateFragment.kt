package com.example.pinterest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest.R

class ProfileCreateFragment : Fragment() {

    lateinit var recyclerView:RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile_create, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {

    }
}