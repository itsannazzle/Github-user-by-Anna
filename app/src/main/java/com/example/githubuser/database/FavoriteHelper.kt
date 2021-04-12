package com.example.githubuser.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.constraintlayout.widget.Constraints
import com.example.githubuser.database.MyDBContract.UserDB.Companion.TABLE_NAME
import com.example.githubuser.database.MyDBContract.UserDB.Companion.USERNAME
import com.example.githubuser.database.MyDBContract.UserDB.Companion._ID


class FavoriteHelper(context: Context) {
    private lateinit var database: SQLiteDatabase
    private var myDBHelper : MyDBHelper = MyDBHelper(context)
    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: FavoriteHelper? = null

        fun getInstance(context: Context): FavoriteHelper =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: FavoriteHelper(context)
                }
    }

    fun open() {
        database = myDBHelper.writableDatabase
    }

    fun queryAll(): Cursor {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                "$_ID ASC")
    }
    fun queryByUsername(id: String): Cursor {
        return database.query(
                DATABASE_TABLE,
                null,
                "$_ID = ?",
                arrayOf(id),
                null,
                null,
                null,
                null)
    }


    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }
    fun update(id : String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }

    fun check(username: String): Boolean {
        database = myDBHelper.writableDatabase
        val selectUsername = "SELECT * FROM $DATABASE_TABLE WHERE $USERNAME =?"
        val cursor = database.rawQuery(selectUsername, arrayOf(username))
        var check = false
        if (cursor.moveToFirst()) {
            check = true
            var i = 0
            while (cursor.moveToNext()) {
                i++
            }
            Log.d(Constraints.TAG, String.format("%d user found", i))
        }
        cursor.close()
        return check
    }
}