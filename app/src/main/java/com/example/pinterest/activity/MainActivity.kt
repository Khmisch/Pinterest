package com.example.pinterest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.pinterest.R
import com.example.pinterest.fragment.HomeFragment
import com.example.pinterest.fragment.MessagesFragment
import com.example.pinterest.fragment.ProfileFragment
import com.example.pinterest.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fl_Fragment: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val messagesFragment = MessagesFragment()
        val profileFragment = ProfileFragment()
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        fl_Fragment = findViewById(R.id.fl_Fragment)

        replaceFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> replaceFragment(homeFragment)
                R.id.menu_search -> replaceFragment(searchFragment)
                R.id.menu_messages -> replaceFragment(messagesFragment)
                R.id.menu_profile -> replaceFragment(profileFragment)

            }
            true
        }

    }

     fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft = manager.beginTransaction()
            ft.replace(R.id.fl_Fragment, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        else
            super.onBackPressed()
    }

    fun showBottomNavigation(){
        bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation(){
        bottomNavigationView.visibility = View.GONE
    }

}