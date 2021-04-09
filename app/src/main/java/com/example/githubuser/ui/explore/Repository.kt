package com.example.githubuser.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.adapter.UserRepoAdapter
import com.example.githubuser.databinding.FragmentRepositoryBinding
import com.example.githubuser.model.User
import com.example.githubuser.viewmodel.DashboardViewModel


class Repository : Fragment() {
    private lateinit var binding: FragmentRepositoryBinding
    private val dashboardViewModel : DashboardViewModel by activityViewModels()
    private lateinit var userRepoAdapter : UserRepoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        userRepoAdapter = UserRepoAdapter()
        userRepoAdapter.notifyDataSetChanged()
        showLoading(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedUser = activity?.intent?.getParcelableExtra<User>(DetailActivity.EXTRA_DATA)
        dashboardViewModel.showRepository.observe(viewLifecycleOwner) {
          UserRepository -> if (UserRepository != null){
              userRepoAdapter.setUserData(UserRepository)
            showLoading(false)
        }
        }
        if (selectedUser != null){
            dashboardViewModel.getRepo(selectedUser.username)

            binding.rvRepouser.adapter = userRepoAdapter
            binding.rvRepouser.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        }
    }
    private fun showLoading(state: Boolean) {
        if (state){
            binding.progresBar.visibility = View.VISIBLE
        } else {
            binding.progresBar.visibility = View.INVISIBLE
        }
    }
}