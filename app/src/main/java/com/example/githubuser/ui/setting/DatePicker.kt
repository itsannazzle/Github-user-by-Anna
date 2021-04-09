package com.example.githubuser.ui.setting

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.githubuser.R
import java.util.*

class DatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private var dialogDateListener : DialogDateListener? = null

    interface DialogDateListener {
        fun onDialogDateSet(tag: String? , year: Int, dayOfMonth: Int, month: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogDateListener = context as DialogDateListener
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialogDateListener != null){
            dialogDateListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)
        return DatePickerDialog(childFragmentManager as Context, this, year, month, date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dialogDateListener?.onDialogDateSet(tag,year,dayOfMonth,month)
    }

}