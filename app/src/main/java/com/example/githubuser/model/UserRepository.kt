package com.example.githubuser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepository (
        /*data class untuk menampung data dari json yg berasal dari method getRepositories*/
    @field:SerializedName("stargazers_count")
    val stars : Int,

    val id : Int,
    val name : String,
    val description : String,
    val forks : Int,
    val language : String,
    ) : Parcelable