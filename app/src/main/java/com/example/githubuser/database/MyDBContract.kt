package com.example.githubuser.database

import android.provider.BaseColumns

class MyDBContract {
    class UserDB : BaseColumns{
        companion object {
            val TABLE_NAME = "favorite_user"
            val ID = "user_id"
            val USERNAME = "username"
            val NAME = "name"
            val USER_PICTURE = "profile_pic"
            val BIO = "bio"
        }
    }
}