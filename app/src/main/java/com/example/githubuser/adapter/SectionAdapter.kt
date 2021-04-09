package com.example.githubuser.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.ui.explore.Followers
import com.example.githubuser.ui.explore.Following
import com.example.githubuser.ui.explore.Repository

class SectionAdapter(activity: AppCompatActivity)
    : FragmentStateAdapter(
    activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Repository()
            1 -> fragment = Followers()
            2 -> fragment = Following()
        }
        return fragment as Fragment
    }

}