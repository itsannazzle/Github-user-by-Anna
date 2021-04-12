package com.example.githubuser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
        /*data class turunan dari search user*/
    @SerializedName("login")
    val username : String ="",

    @SerializedName("avatar_url")
    val avatarUrl : String? ="",

    val id : Int =0

) : Parcelable


