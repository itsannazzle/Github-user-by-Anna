package com.example.githubuser.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.ui.explore.Followers
import com.example.githubuser.ui.explore.Following
import com.example.githubuser.ui.explore.Repository

class DashboardSectionAdapter(fr: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fr, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Overview()
            1 -> fragment = Followers()
            2 -> fragment = Following()
        }
        return fragment as Fragment

    }
}