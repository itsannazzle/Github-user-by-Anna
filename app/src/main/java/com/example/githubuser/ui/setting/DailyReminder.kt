package com.example.githubuser.ui.setting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivityDailyReminderBinding
import java.text.SimpleDateFormat
import java.util.*

class DailyReminder : AppCompatActivity() {
    private lateinit var binding: ActivityDailyReminderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar_setting)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Setting"
        supportFragmentManager.beginTransaction().add(R.id.setting_holder, Setting()).commit()

    }

}