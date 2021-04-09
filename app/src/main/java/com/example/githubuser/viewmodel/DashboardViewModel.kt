package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.apiconfig.RetrofitInstance
import com.example.githubuser.model.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    private val TAG = DashboardViewModel::class.java.simpleName

/*userrepo bernilai arraylist karena di apiconfignya merupakan arraylist*/
    private var listUserRepo : MutableLiveData<ArrayList<UserRepository>> = MutableLiveData()
    /*dari mutable live data diparsing menjadi live data untuk mencegah perubahan data di view*/
    val showRepository : LiveData<ArrayList<UserRepository>> = listUserRepo

    fun getRepo(username : String){
        RetrofitInstance.getRetrofit().getRepos(username).enqueue(object : Callback<ArrayList<UserRepository>>{
            override fun onResponse(call: Call<ArrayList<UserRepository>>, response: Response<ArrayList<UserRepository>>) {
                listUserRepo.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<UserRepository>>, t: Throwable) {
               Log.d(TAG,"Request time over")
            }

        })
    }


}