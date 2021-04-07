package com.example.githubuser.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.adapter.UserRepoAdapter
import com.example.githubuser.databinding.FragmentDashboardBinding
import com.example.githubuser.ui.explore.ExploreViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val exploreViewModel : ExploreViewModel by activityViewModels()
    companion object{
        private var TAB_TITLE = intArrayOf(
                R.string.overview,
                R.string.project,
                R.string.text_package
        )
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        val dashboardSectionAdapter = DashboardSectionAdapter(
                requireActivity().supportFragmentManager,
                lifecycle
        )
        binding.viewpager2.adapter = dashboardSectionAdapter
        TabLayoutMediator(binding.dashboardTab, binding.viewpager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
        binding.dashboardTab.getTabAt(0)?.setIcon(R.drawable.ic_dashboard)
        binding.dashboardTab.getTabAt(1)?.setIcon(R.drawable.ic_github_search)
        binding.dashboardTab.getTabAt(2)?.setIcon(R.drawable.ic_explore)


        showDetail()
        return binding.root
    }


    private fun showDetail() {

            exploreViewModel.detailUser("itsannazzle")
            exploreViewModel.showDetailUser.observe(requireActivity(), {

                if (it.followers.toString().isNullOrEmpty()){
                    binding.follwers.text = StringBuilder("-")
                } else {
                    binding.follwers.text = StringBuilder("${it.followers.toString()} Followers")
                }
                if (it.following.toString().isNullOrEmpty()){
                    binding.following.text = StringBuilder("-")
                } else{
                    binding.following.text = StringBuilder("${it.followers.toString()} Following")
                }
                if(it.avatar.isNullOrEmpty()){
                    context?.applicationContext?.let { it1 ->
                        Glide.with(it1)
                                .load(R.drawable.ic_dashboard)
                                .into(binding.userImager)
                    }
                } else {
                    context?.applicationContext?.let { it1 ->
                        Glide.with(it1)
                                .load(it.avatar)
                                .into(binding.userImager)
                    }
                    }
            })
        }
    }
