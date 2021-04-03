package com.example.githubuser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchUser (
        /*data class untuk menampung data dari json yg berasal dari method searchUser
        * dimulai dari objek yang didalam nya terdapat array yang di dalam array terdpat objek yg menampung data yg kita butuhkan*/

    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @field:SerializedName("items")
    val items: List<User>
    /*value item pada data json bernilai array maka di store sbg List, kita membutuhkan data yg ada di dalam list tsb
    * sehingga kita buat class lagi*/
    ) : Parcelable
