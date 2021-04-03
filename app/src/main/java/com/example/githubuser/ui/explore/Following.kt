package com.example.githubuser.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.adapter.SearchResultAdapter
import com.example.githubuser.databinding.FragmentFollowingBinding
import com.example.githubuser.model.User


class Following : Fragment() {
    private lateinit var binding : FragmentFollowingBinding
    private val exploreViewModel: ExploreViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFollowingBinding.inflate(inflater, container, false)

        showFollowing()
        showLoading(true)
        return binding.root
    }
    private fun showFollowing() {
        val searchResultAdapter = SearchResultAdapter()
        searchResultAdapter.notifyDataSetChanged()
        exploreViewModel.showFollowing.observe(viewLifecycleOwner){
            User -> if (User != null) {
            searchResultAdapter.addUser(User as ArrayList<User>)
            showLoading(false)
        }
        }

        val selectedUser = activity?.intent?.getParcelableExtra<User>(DetailActivity.EXTRA_DATA)
        if (selectedUser != null) {

            exploreViewModel.detailUserFollowing(selectedUser.username)

            binding.rvFollowing.adapter = searchResultAdapter
            binding.rvFollowing.layoutManager = LinearLayoutManager(activity)
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