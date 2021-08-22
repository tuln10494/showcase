package com.example.greenlightaquaticapp.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.greenlightaquaticapp.R
import com.example.greenlightaquaticapp.baseView.BaseActivity
import com.example.greenlightaquaticapp.constants.SharePrefKey.EMAIL_LOGGED_IN
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        tv_register.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
        initEmail()
    }

    private fun initEmail() {
        edt_email_input.setText(getEmailLoggedInFromSharePref(EMAIL_LOGGED_IN))
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            R.id.btn_login -> {
                loginRegisteredUser()
            }
            R.id.tv_forgot_password -> {
                startActivity(Intent(this, ForgotPasswordActivity::class.java))
            }
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(edt_email_input.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email_id), true)
                false
            }

            !edt_email_input.text.toString().trim().contains("@gmail.com")
                    || !edt_email_input.text.toString().trim().contains("@gmail.com.vn") -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email_id_type), true)
                false
            }

            TextUtils.isEmpty(edt_password_input.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> true
        }
    }

    private fun loginRegisteredUser() {
        if (validateLoginDetails()) {
            showProgressDialog(resources.getString(R.string.please_wait))
            val email = edt_email_input.text.toString()
            val password = edt_password_input.text.toString()

            //login using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    hideProgressDialog()
                    when {
                        task.isSuccessful -> {
                            addEmailLoggedInToSharedPref(EMAIL_LOGGED_IN,email)
                            showErrorSnackBar(resources.getString(R.string.login_success), false)
                            startActivity(Intent(this,HomeActivity::class.java))
                            finish()
                        }
                        task.isCanceled -> {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                        task.isComplete -> {
                            showErrorSnackBar(
                                resources.getString(R.string.already_login_success),
                                false
                            )
                            startActivity(Intent(this,HomeActivity::class.java))
                            finish()
                        }
                    }
                }
        }
    }

}




