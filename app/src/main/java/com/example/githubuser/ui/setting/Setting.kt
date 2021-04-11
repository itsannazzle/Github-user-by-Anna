package com.example.githubuser.ui.setting

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.githubuser.R

class Setting : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var LANG: String
    private lateinit var REMINDER: String
    private lateinit var langPreference: Preference
    private lateinit var switchPreference: SwitchPreference
    private var myReceiver = MyReceiver()
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting_preferences)
        init()
        setSummaries()
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        langPreference.summary = sh.getString(LANG, "Change language")

        switchPreference.isChecked = sh.getBoolean(REMINDER,false)
    }

    private fun init() {
        LANG = resources.getString(R.string.key_language)
        REMINDER = resources.getString(R.string.key_switch)

        langPreference = findPreference<SwitchPreference>(LANG) as Preference
        switchPreference = findPreference<SwitchPreference>(REMINDER) as SwitchPreference

        langPreference.setOnPreferenceClickListener{
            activity?.startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            true
        }


    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if (key == LANG){
             langPreference.summary = sharedPreferences.getString(LANG,"Reminder to push")
        }
        if (key == REMINDER){
            val reminderOn = sharedPreferences.getBoolean(REMINDER,false)
            switchPreference.isChecked = reminderOn
            if (reminderOn){
                context?.let {
                    myReceiver.setDailyReminder(it,MyReceiver.TYPE_REMINDER,"Reminder is on")
                }
            } else {
                context?.let { myReceiver.cancelAlarm(it) }
            }
        }
    }

}