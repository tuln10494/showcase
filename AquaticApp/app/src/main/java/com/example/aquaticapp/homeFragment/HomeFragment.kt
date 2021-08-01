package com.example.aquaticapp.homeFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aquaticapp.R
import com.example.aquaticapp.adapter.DaysOfWeekAdapter
import com.example.aquaticapp.constants.Constant.Companion.getDayName
import com.example.aquaticapp.constants.Constant.Companion.getMonthName
import com.example.aquaticapp.databinding.FragmentHomeBinding
import com.example.aquaticapp.model.DateObject
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {
    private var mListDate: MutableList<DateObject> = mutableListOf()
    private lateinit var mDaysOfWeekAdapter: DaysOfWeekAdapter
    private lateinit var mBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        mBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_home)
        init(view)
        return view
    }

    companion object {
        const val TAG = "HomeFragment"
    }

    private fun init(view: View) {
        mDaysOfWeekAdapter = DaysOfWeekAdapter(requireContext(), getListDateOfWeek())
        mBinding.rcDay.adapter = mDaysOfWeekAdapter
        mBinding.rcDay.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

    @SuppressLint("SimpleDateFormat")
    private fun getListDateOfWeek(): List<DateObject> {
        mListDate.clear()
        val now = Calendar.getInstance()
        val format = SimpleDateFormat("dd/MM/yyyy")
        val today = format.format(now.time)
        Log.d(TAG,today)
        for (i in 0..6) {
            val date = format.format(now.time)
            Log.d(TAG + "date",date)
            mListDate.add(
                DateObject(
                    date.substring(0, 2),
                    getMonthName(date.substring(3, 5)),
                    getDayName(i),
                    date.equals(today)
                )
            )
            now.add(Calendar.DAY_OF_MONTH, 1)
            Log.d(TAG, mListDate[i].toString())
        }
        return mListDate
    }
}