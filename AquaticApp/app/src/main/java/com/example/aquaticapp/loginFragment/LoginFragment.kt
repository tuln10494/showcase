package com.example.aquaticapp.loginFragment

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.aquaticapp.R
import com.example.aquaticapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(),View.OnClickListener {
    private lateinit var mBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_login)
        init(view)
        return view
    }

    private fun init(view: View?) {
        mBinding.edtPassword.setOnKeyListener(onKeyChangeListener)
        mBinding.btnNext.setOnClickListener(this)
    }

    private var onKeyChangeListener = View.OnKeyListener { _, _, _ ->
        mBinding.txtInputPassword.error = null
        false
    }

    override fun onClick(view: View?) {
      when(view?.id){
          R.id.btn_next->{
              mBinding.txtInputPassword.error = "error password"
          }
      }
    }

}