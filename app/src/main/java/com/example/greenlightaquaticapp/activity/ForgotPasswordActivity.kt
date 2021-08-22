package com.example.greenlightaquaticapp.activity

import android.os.Bundle
import android.text.TextUtils
import com.example.greenlightaquaticapp.R
import com.example.greenlightaquaticapp.baseView.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_register.*

class ForgotPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        initView()
    }

    private fun initView() {
        btn_submit.setOnClickListener {
            sentResetToEmail()
        }
    }

    private fun validateEmailInput(): Boolean {
        if (TextUtils.isEmpty(edt_email_input.text.toString().trim { it <= ' ' })) {
            showErrorSnackBar(resources.getString(R.string.err_msg_enter_email_id), true)
            return false
        } else if (!edt_email_input.text.toString().trim().contains("@gmail.com")
            || !edt_email_input.text.toString().trim().contains("@gmail.com.vn")
        ) {
            showErrorSnackBar(resources.getString(R.string.err_msg_enter_email_id_type), true)
            return false
        }
        return true
    }

    private fun sentResetToEmail() {
        if (validateEmailInput()) {
            showProgressDialog(resources.getString(R.string.please_wait))
            val email = edt_email_input.text.toString()
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener { task ->
                hideProgressDialog()
                if (task.isSuccessful) {
                    showErrorSnackBar(
                        resources.getString(R.string.email_sent_to_reset_password_success),
                        true
                    )
                    onBackPressed()
                } else {
                    showErrorSnackBar(task.exception!!.message.toString(), true)
                }
            }
        }
    }


}