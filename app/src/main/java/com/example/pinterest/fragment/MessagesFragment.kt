package com.example.pinterest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.pinterest.R
import com.example.pinterest.adapter.MessagesViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MessagesFragment : Fragment() {
    private var viewPagerAdapter: MessagesViewPagerAdapter? = null
    private var viewPager2: ViewPager2? = null
    private var tabLayout: TabLayout? = null
    private lateinit var ic_sort: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_message, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        viewPager2 = view.findViewById(R.id.viewpager2)
        tabLayout = view.findViewById(R.id.tabLayout)
        ic_sort = view.findViewById(R.id.ic_sort)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Updates"));
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Messages"));
        // Set the adapter
        val fragmentManager: androidx.fragment.app.FragmentManager = requireActivity().getSupportFragmentManager()
        viewPagerAdapter = MessagesViewPagerAdapter(fragmentManager, lifecycle)
        viewPager2!!.setAdapter(viewPagerAdapter)

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2!!.setCurrentItem(tab.position)
                if(tab.position == 0){
                    ic_sort.visibility = View.VISIBLE
                }
                if(tab.position == 1){
                    ic_sort.visibility = View.INVISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager2!!.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout!!.selectTab(tabLayout!!.getTabAt(position))
            }
        })


    }
}