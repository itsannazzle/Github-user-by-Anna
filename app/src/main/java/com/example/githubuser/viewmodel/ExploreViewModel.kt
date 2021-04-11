package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.apiconfig.RetrofitInstance
import com.example.githubuser.model.Detail
import com.example.githubuser.model.SearchUser
import com.example.githubuser.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel : ViewModel() {
    private val TAG = ExploreViewModel::class.java.simpleName
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    var isFavorite = false
    val text: LiveData<String> = _text

    private val listUser : MutableLiveData<List<User>> = MutableLiveData()
    val getListUser: LiveData<List<User>> =listUser
    private val detailUser : MutableLiveData<Detail> = MutableLiveData()
    val showDetailUser : LiveData<Detail> = detailUser
    private val userFollowers : MutableLiveData<List<User>> = MutableLiveData()
    val showFollowers : LiveData<List<User>> = userFollowers
    private val userFollowing : MutableLiveData<List<User>> = MutableLiveData()
    val showFollowing : LiveData<List<User>> = userFollowing
    private val popular : MutableLiveData<List<User>> = MutableLiveData()
    val showPopularUser : LiveData<List<User>> = popular


    fun loadUser(query : String){
        /*memanggil callback search user karena data user ada di dalam class tersebut*/
        RetrofitInstance.getRetrofit().searchUser(query).enqueue(object : Callback<SearchUser>{
            override fun onResponse(call: Call<SearchUser>, search: Response<SearchUser>) {
                /*ambil data dari search dan parsing ke item untuk ambil data user*/
                val data = search.body()?.items as ArrayList<User>
                listUser.postValue(data)
            }

            override fun onFailure(call: Call<SearchUser>, t: Throwable) {
                Log.d(TAG, "Request time over")
            }

        })
    }

    fun detailUser(username : String){
        RetrofitInstance.getRetrofit().getDetailUser(username).enqueue(object : Callback<Detail>{
            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                detailUser.postValue(response.body())

            }

            override fun onFailure(call: Call<Detail>, t: Throwable) {
                Log.d(TAG, "Request time over")
            }

        })
    }

    fun detailUserFollowers(username: String){
        RetrofitInstance.getRetrofit().getFollowers(username).enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                userFollowers.postValue(response.body() as ArrayList<User>)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d(TAG, "Request time over")
            }

        })
    }

    fun detailUserFollowing(username: String){
        RetrofitInstance.getRetrofit().getFollowing(username).enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                userFollowing.postValue(response.body() as ArrayList<User>)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d(TAG, "Request time over")
            }

        })
    }

    fun popolarUser(){
        RetrofitInstance.getRetrofit().popuralUser().enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                popular.postValue(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}