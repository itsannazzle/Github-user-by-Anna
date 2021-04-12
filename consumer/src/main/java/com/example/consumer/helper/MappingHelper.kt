package com.example.githubuser.helper

import android.database.Cursor
import com.example.consumer.helper.MyDBContract.UserDB.Companion.USERNAME
import com.example.consumer.helper.MyDBContract.UserDB.Companion.USER_PICTURE
import com.example.consumer.helper.MyDBContract.UserDB.Companion._ID
import com.example.githubuser.model.User

object MappingHelper {
    fun mapCursorToArray(userCursor: Cursor?) : ArrayList<User>{
        val userDetail = ArrayList<User>()
        userCursor?.apply {
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(_ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val profile_pic = getString(getColumnIndexOrThrow(USER_PICTURE))
                userDetail.add(User(username,profile_pic,id))
            }
        }
        return userDetail
    }
  
}