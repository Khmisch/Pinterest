package com.example.pinterest.adapter

import android.support.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pinterest.fragment.MessagesMessagesFragment
import com.example.pinterest.fragment.MessagesUpdatesFragment

class ProfileViewPagerAdapter(@NonNull fragmentManager: FragmentManager?, @NonNull lifecycle: Lifecycle?):
    FragmentStateAdapter(fragmentManager!!, lifecycle!!) {
    @NonNull
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            MessagesMessagesFragment()
        } else MessagesUpdatesFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}