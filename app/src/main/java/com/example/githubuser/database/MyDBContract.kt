package com.example.githubuser.database

import android.net.Uri
import android.provider.BaseColumns

object MyDBContract {
    const val AUTHORITY = "com.example.githubuser"
    const val SCHEME = "content"

    internal class UserDB : BaseColumns{
        companion object {
            const val TABLE_NAME = "favorite_user"
            const val _ID = "user_id"
            const val USERNAME = "username"
            const val USER_PICTURE = "profile_pic"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(TABLE_NAME)
                    .build()
        }
    }
}