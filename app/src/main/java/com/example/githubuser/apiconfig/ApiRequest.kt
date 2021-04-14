package com.example.githubuser.apiconfig

import com.example.githubuser.model.Detail
import com.example.githubuser.model.SearchUser
import com.example.githubuser.model.User
import com.example.githubuser.model.UserRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {

/*    @GET("users")
    @Headers("Authorization: token 98988805a0011805189ea1bbfe87283301efcaf8")
    fun getUsers() : Call<ArrayList<User>>*/
    /*arraylist unutk json array*/

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUser(@Query("q")
                   username : String) : Call<SearchUser>
    /*call aja dari json object, gapake arraylist, only generic karena data di json nya object(diawali dengan { )*/

    @GET("users")
    @Headers("Authorization: token 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")
    fun popuralUser() : Call<List<User>>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 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")
    fun getRepos(@Path("username")
                 username: String) : Call<ArrayList<UserRepository>>
    /*pake arraylist karena data di json nya adalah array, yg di awali dengan [*/

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(@Path("username")
                 username: String) : Call<Detail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(@Path("username")
                      username: String) : Call<List<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path("username")
                     username: String) : Call<List<User>>

}