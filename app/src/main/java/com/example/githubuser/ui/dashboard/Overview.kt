package com.example.githubuser.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.adapter.UserRepoAdapter
import com.example.githubuser.databinding.FragmentOverviewBinding

class Overview : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private lateinit var userRepoAdapter: UserRepoAdapter
    private val dashboardViewModel : DashboardViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        showLoading(true)
        userRepoAdapter = UserRepoAdapter()
        userRepoAdapter.notifyDataSetChanged()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

}