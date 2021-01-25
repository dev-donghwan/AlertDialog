package com.donghwan.alertdialog.popup

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.donghwan.alertdialog.POPUP_MESSAGE
import com.donghwan.alertdialog.POPUP_TITLE

class OkCancelFragment private constructor() : DialogFragment() {
    private var listener: OkCancelListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OkCancelListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement ConfirmationListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString(POPUP_TITLE)
        val message = arguments?.getString(POPUP_MESSAGE)
        return AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("확인") { _, _ ->
                listener?.confirm()
            }
            .setNegativeButton("취소") { _, _ ->
                listener?.cancel()
            }.create()
    }

    companion object {
        fun newInstance(
            title: String? = null,
            message: String? = null
        ): OkCancelFragment {
            return OkCancelFragment().apply {
                arguments = Bundle().apply {
                    putString(POPUP_TITLE, title)
                    putString(POPUP_MESSAGE, message)
                }
            }
        }
    }
}

interface OkCancelListener {
    fun confirm()
    fun cancel()
}