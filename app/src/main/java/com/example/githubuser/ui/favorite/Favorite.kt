package com.example.githubuser.ui.favorite

import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.adapter.SearchResultAdapter
import com.example.githubuser.database.MyDBContract.UserDB.Companion.CONTENT_URI
import com.example.githubuser.databinding.FragmentFavoriteBinding
import com.example.githubuser.helper.MappingHelper
import com.example.githubuser.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class Favorite : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter : SearchResultAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                loadFavotiteUserAsync()
            }
        }

        adapter = SearchResultAdapter()
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(activity)

        activity?.contentResolver?.registerContentObserver(CONTENT_URI, true, myObserver)

        if (savedInstanceState == null) {
            loadFavotiteUserAsync()
        }
        return binding.root
    }
    private fun loadFavotiteUserAsync() {
        GlobalScope.launch(Dispatchers.Main){
            val deferredUser = async(Dispatchers.IO){
                val cursor = activity?.contentResolver?.query(CONTENT_URI,null,null,null,null)
                MappingHelper.mapCursorToArray(cursor)
            }
            val user = deferredUser.await()
            if (user.isNotEmpty()){
                adapter.addUser(user)
            } else {

            }
        }
    }
}