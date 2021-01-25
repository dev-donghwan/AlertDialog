package com.donghwan.alertdialog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.donghwan.alertdialog.databinding.ActivityMainBinding
import com.donghwan.alertdialog.popup.OkCancelFragment
import com.donghwan.alertdialog.popup.OkCancelListener
import com.donghwan.alertdialog.popup.OkFragment
import com.donghwan.alertdialog.popup.OkListener

class MainActivity :
    AppCompatActivity(),
    OkListener,
    OkCancelListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnOkDialog.setOnClickListener {
            createOkPopUp(
                title = "PopUp Title",
                message = "PopUp Message"
            )
        }

        binding.btnOkCancelDialog.setOnClickListener {
            createOkCancelPopUp(
                title = "PopUp Title",
                message = "PopUp Message"
            )
        }
    }

    private fun createOkPopUp(
        title: String,
        message: String
    ) {
        OkFragment.newInstance(title, message).apply {
            isCancelable = false
        }.show(supportFragmentManager, "")
    }

    private fun createOkCancelPopUp(
        title: String,
        message: String
    ) {
        OkCancelFragment.newInstance(title, message).apply {
            isCancelable = false
        }.show(supportFragmentManager, "")
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun ok() {
        makeToast("OkPopUp - Ok Click")
    }

    override fun confirm() {
        makeToast("OkCancelPopUp - Ok Click")
    }

    override fun cancel() {
        makeToast("OkCancelPopUp - Cancel Click")
    }
}