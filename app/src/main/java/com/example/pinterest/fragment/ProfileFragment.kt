package com.example.pinterest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.pinterest.R
import com.example.pinterest.adapter.MessagesViewPagerAdapter
import com.example.pinterest.adapter.ProfileViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class ProfileFragment : Fragment() {
    private var viewPagerAdapter: ProfileViewPagerAdapter? = null
    private var viewPager2: ViewPager2? = null
    private var tabLayout: TabLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        viewPager2 = view.findViewById(R.id.viewpager2)
        tabLayout = view.findViewById(R.id.tabLayout)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Create"));
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Saved"));

        val fragmentManager: androidx.fragment.app.FragmentManager = requireActivity().supportFragmentManager
        viewPagerAdapter = ProfileViewPagerAdapter(fragmentManager, lifecycle)
        viewPager2!!.setAdapter(viewPagerAdapter)

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2!!.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager2!!.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout!!.selectTab(tabLayout!!.getTabAt(position))
            }
        })
    }
}