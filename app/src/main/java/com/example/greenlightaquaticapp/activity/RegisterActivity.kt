package com.example.greenlightaquaticapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.greenlightaquaticapp.R
import com.example.greenlightaquaticapp.baseView.BaseActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupActionBar()
        initView()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_register_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
        toolbar_register_activity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initView() {
        tv_login.setOnClickListener(this)
        btn_register.setOnClickListener(this)
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(edt_first_name.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }

            TextUtils.isEmpty(edt_last_name.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(edt_email_id.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email_id), true)
                false
            }

            !edt_email_id.text.toString().trim().contains("@gmail.com")
                    || !edt_email_id.text.toString().trim().contains("@gmail.com.vn") -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email_id_type), true)
                false
            }

            TextUtils.isEmpty(edt_password.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            TextUtils.isEmpty(edt_confirm_password.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_agree_terms_and_condition),
                    true
                )
                false
            }

            edt_password.text.toString() != edt_confirm_password.text.toString() -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_confirm_password_not_match),
                    true
                )
                false
            }

            !cb_terms_and_condition.isChecked -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_agree_terms_and_condition),
                    true
                )
                false
            }
            else -> {
                true
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_login -> {
               onBackPressed()
            }
            R.id.btn_register -> {
                if (validateRegisterDetails()) {
                    showProgressDialog(resources.getString(R.string.please_wait))
                    addAccountToFireBase()
                }
            }
        }
    }

    private fun addAccountToFireBase() {
        if (validateRegisterDetails()) {
            val email = edt_email_id.text.toString()
            val password = edt_password.text.toString()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        hideProgressDialog()
                        if (task.isSuccessful) {
                            val firebaseUser = task.result!!.user!!
                            showErrorSnackBar(
                                resources.getString(
                                    R.string.msg_your_register_successfully,
                                    firebaseUser.uid
                                ), false
                            )
                            FirebaseAuth.getInstance().signOut()
                            finish()
                        } else {
                            showErrorSnackBar(
                                task.exception!!.message.toString(), true
                            )
                        }
                    }
                )
        }
    }
}