package com.example.githubuser.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.adapter.SearchResultAdapter
import com.example.githubuser.databinding.FragmentFollowersBinding
import com.example.githubuser.model.User
import com.example.githubuser.viewmodel.ExploreViewModel


class Followers : Fragment() {
    private lateinit var binding: FragmentFollowersBinding
    private val exploreViewModel: ExploreViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        showFollowers()
        showLoading(true)
        return binding.root
    }

    private fun showFollowers() {
        val searchResultAdapter = SearchResultAdapter()
        searchResultAdapter.notifyDataSetChanged()
        exploreViewModel.showFollowers.observe(viewLifecycleOwner){
            User -> if (User != null) {
            searchResultAdapter.addUser(User as ArrayList<User>)

            showLoading(false)
        }
        }

        val selectedUser = activity?.intent?.getParcelableExtra<User>(ExploreFragment.EXTRA_ID)
        if (selectedUser != null) {
            exploreViewModel.detailUserFollowers(selectedUser.username)

            binding.rvFollowers.adapter = searchResultAdapter
            binding.rvFollowers.layoutManager = LinearLayoutManager(activity)
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