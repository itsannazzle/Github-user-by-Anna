package com.example.githubuser.ui.setting

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver : BroadcastReceiver() {
    companion object{
        const val TYPE_REMINDER = "REMINDER"
        const val EXTRA_MESSAGE = "MESSAGE"
        const val EXTRA_TYPE = "TYPE"
        const val ID_REMINDER = 1
        const val DATE_FORMAT = "EEEE, MMM d, yyyy"
        const val TIME_FORMAT = "HH:mm"


    }
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        TODO("MyReceiver.onReceive() is not implemented")
    }
}