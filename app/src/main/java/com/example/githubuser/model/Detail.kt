package com.example.githubuser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Detail(
        /*data class untuk menampung data dari json yg berasal dari method getDetailUser*/
        @field:SerializedName("login")
        val username : String,
        @field:SerializedName("avatar_url")
        val avatar : String,
        val name : String,
        val company : String,
        val blog : String,
        val location : String,
        val followers : Int,
        val following : Int,
        val bio : String
) : Parcelable
