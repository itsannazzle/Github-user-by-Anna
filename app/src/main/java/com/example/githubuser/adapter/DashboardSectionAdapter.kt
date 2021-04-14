package com.example.githubuser.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.ui.dashboard.Overview
import com.example.githubuser.ui.favorite.NotificationsFragment

class DashboardSectionAdapter(fr: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fr, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Overview()
            1 -> fragment = NotificationsFragment()
            2 -> fragment = NotificationsFragment()
        }
        return fragment as Fragment
    }
}