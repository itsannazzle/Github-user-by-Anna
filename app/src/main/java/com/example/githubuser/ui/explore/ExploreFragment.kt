package com.example.githubuser.ui.explore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.adapter.SearchResultAdapter
import com.example.githubuser.databinding.FragmentExploreBinding
import com.example.githubuser.model.User
import com.example.githubuser.viewmodel.ExploreViewModel

class ExploreFragment : Fragment() {
    private val exploreViewModel: ExploreViewModel by activityViewModels()
    private lateinit var binding: FragmentExploreBinding
    private lateinit var searchResultAdapter: SearchResultAdapter
    private val TAG = ExploreFragment::class.java.simpleName
    companion object {
        const val EXTRA_ID = "ID"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        searchResultAdapter = SearchResultAdapter()
        searchResultAdapter.notifyDataSetChanged()
        binding.searchResult.adapter = searchResultAdapter
        binding.searchResult.layoutManager = LinearLayoutManager(activity)

        showLoading(true)
        showPopularUser()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchIcon.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchIcon.clearFocus()
                if (query != null) {
                    exploreViewModel.loadUser(query)
                }
                Log.d(TAG,"search successful")
                showLoading(true)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                showSearchResult()
                return false
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progresBar.visibility = View.VISIBLE
        } else {
            binding.progresBar.visibility = View.INVISIBLE
        }
    }

    private fun showPopularUser(){
        exploreViewModel.showPopularUser.observe(viewLifecycleOwner){
            User -> if ( User != null) {
            searchResultAdapter.addUser(User as ArrayList<User>)
            searchResultAdapter.setOnItemCallback(object : SearchResultAdapter.OnItemCallback {
                override fun onItemClicked(user: User) {
                    val intent = Intent(activity,DetailActivity::class.java)
                    intent.putExtra(EXTRA_ID,user)
                    startActivity(intent)
                }
            })
            showLoading(false)
        }
        }
        exploreViewModel.popolarUser()
        binding.textView7.setText("Popular user")
        binding.searchIcon.setOnClickListener {
            binding.searchIcon.isIconified = false

        }
    }

    private fun showSearchResult(){
        exploreViewModel.getListUser.observe(viewLifecycleOwner){
            User -> if (User != null) {
            searchResultAdapter.addUser(User as ArrayList<User>)
            searchResultAdapter.setOnItemCallback(object : SearchResultAdapter.OnItemCallback {
                override fun onItemClicked(user: User) {
                    val intent = Intent(activity,DetailActivity::class.java)
                    intent.putExtra(EXTRA_ID,user)
                    startActivity(intent)
                }
            })
            showLoading(false)
            binding.textView7.setText("Search result")
            binding.textView7.visibility = View.VISIBLE
        }
        }
    }

}