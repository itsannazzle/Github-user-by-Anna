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

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var userRepoAdapter: UserRepoAdapter
    private val dashboardViewModel : DashboardViewModel by activityViewModels()
    private val exploreViewModel : ExploreViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)

        showLoading(true)
        userRepoAdapter = UserRepoAdapter()
        userRepoAdapter.notifyDataSetChanged()
        showDetail()
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.getRepo("itsannazzle")

        /*jangan observe data di event listener karna akan terjadi memory leaks, correct me if im wrong*/
        dashboardViewModel.showRepository.observe(viewLifecycleOwner, {
            UserRepository -> if (UserRepository  != null){
                userRepoAdapter.setUserData(UserRepository)

            showLoading(false)
        }
        })

        binding.rvRepouser.adapter = userRepoAdapter
        binding.rvRepouser.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progresBar.visibility = View.VISIBLE
        } else {
            binding.progresBar.visibility = View.INVISIBLE
        }
    }


    private fun showDetail() {

            exploreViewModel.detailUser("itsannazzle")
            exploreViewModel.showDetailUser.observe(requireActivity(), {

                if (it.followers.toString().isNullOrEmpty()){
                    binding.follwers.text = StringBuilder("-")
                } else {
                    binding.follwers.text = it.followers.toString()
                }
                if (it.following.toString().isNullOrEmpty()){
                    binding.following.text = StringBuilder("-")
                } else{
                    binding.following.text = it.following.toString()
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
