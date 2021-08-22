package com.example.greenlightaquaticapp.baseView

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.example.greenlightaquaticapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {

    private lateinit var mDialog: Dialog

    @SuppressLint("ShowToast")
    fun showErrorSnackBar(message: String, errorMassage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        if (errorMassage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.snack_bar_error
                )
            )

        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.snack_bar_success
                )
            )

        }
        snackBar.show()
    }

    fun showProgressDialog(text: String) {
        mDialog = Dialog(this)
        mDialog.setContentView(R.layout.dialog_progress)
        mDialog.tv_progress_text.text = text
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()
    }

    fun hideProgressDialog() {
        mDialog.dismiss()
    }

    fun addEmailLoggedInToSharedPref(key: String, value: String) {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getEmailLoggedInFromSharePref(key: String): String {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
            ?: return resources.getString(R.string.owner_name)
        return sharedPref.getString(key, resources.getString(R.string.owner_name)).toString()

    }

    fun showToast(context: Context,message: String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
}