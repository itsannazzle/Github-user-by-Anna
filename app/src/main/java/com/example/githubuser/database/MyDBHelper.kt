package com.example.githubuser.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object{
        val DATABASE_VERSION = 2
        val DATABASE_NAME = "githubUSER"
        val SQL_CREATE_ENTRIES = "CREATE TABLE ${MyDBContract.UserDB.TABLE_NAME}" +
                "(${MyDBContract.UserDB._ID} INTEGER PRIMARY," +
                "${MyDBContract.UserDB.USERNAME} TEXT NOT NULL," +
                "${MyDBContract.UserDB.USER_PICTURE} TEXT)"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${MyDBContract.UserDB.TABLE_NAME}")
        onCreate(db)
    }
}