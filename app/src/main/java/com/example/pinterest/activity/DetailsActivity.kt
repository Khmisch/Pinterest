package com.example.pinterest.activity

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.pinterest.R
import com.example.pinterest.adapter.DetailsPagerAdapter
import com.example.pinterest.fragment.DetailsFragment
import com.example.pinterest.model.Photo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setTransparentStatusBar()
        initViews()
    }

    private fun initViews() {
        val vpDetails = findViewById<ViewPager>(R.id.vp_details)
        refreshAdapter(vpDetails, getList(), getPosition())
    }

    private fun refreshAdapter(viewPager: ViewPager, photoList: ArrayList<Photo>, position: Int) {
        val adapter = DetailsPagerAdapter(supportFragmentManager)
        for (photoItem in photoList) {
            adapter.addFragment(DetailsFragment(photoItem))
        }
        viewPager.adapter = adapter
        viewPager.currentItem = position
    }

    private fun getList(): ArrayList<Photo> {
        val json = intent.getStringExtra("photoList")
        val type: Type = object : TypeToken<ArrayList<Photo>>() {}.type
        return Gson().fromJson(json, type)
    }

    private fun getPosition(): Int {
        return intent.getIntExtra("position", 0)
    }
        fun setTransparentStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}