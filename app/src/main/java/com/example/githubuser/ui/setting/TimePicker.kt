package com.example.githubuser.ui.setting

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private var dialogTimeListener : DialogTimeListener? = null

    interface DialogTimeListener {
        fun onDialogTimeSet(tag: String? , hourOfDay: Int, minute: Int)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogTimeListener = context as DialogTimeListener
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialogTimeListener != null){
            dialogTimeListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val format24hour = true
        return TimePickerDialog(childFragmentManager as Context, this, hour, minute, format24hour)
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        dialogTimeListener?.onDialogTimeSet(tag,hourOfDay,minute)
    }

}