package com.donghwan.alertdialog.popup

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.donghwan.alertdialog.POPUP_MESSAGE
import com.donghwan.alertdialog.POPUP_TITLE

class OkFragment private constructor() : DialogFragment() {

    private var listener: OkListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OkListener) {
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
                listener?.ok()
            }.create()
    }

    companion object {
        fun newInstance(
            title: String? = null,
            message: String? = null
        ): OkFragment {
            return OkFragment().apply {
                arguments = Bundle().apply {
                    putString(POPUP_TITLE, title)
                    putString(POPUP_MESSAGE, message)
                }
            }
        }
    }
}

interface OkListener {
    fun ok()
}