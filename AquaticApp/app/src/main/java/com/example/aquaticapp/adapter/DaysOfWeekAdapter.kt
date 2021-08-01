package com.example.aquaticapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aquaticapp.R
import com.example.aquaticapp.homeFragment.HomeFragment
import com.example.aquaticapp.model.DateObject

class DaysOfWeekAdapter(
    var mContext: Context,
    var mList: List<DateObject>,
) : RecyclerView.Adapter<DaysOfWeekAdapter
.DateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DaysOfWeekAdapter
    .DateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DaysOfWeekAdapter.DateViewHolder(
            inflater.inflate(
                R.layout.item_day,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bindData(mContext, mList[position])
    }

    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mDayNormalLayout: LinearLayout = itemView.findViewById(R.id.dayNormal)
        private var mDayNormalNumber: TextView = itemView.findViewById(R.id.tvNumber)
        private var mDayNormalDayOfWeek: TextView = itemView.findViewById(R.id.tvDayInWeek)
        private var mDayNormalMont: TextView = itemView.findViewById(R.id.tvMonth)

        private var mToDayLayout: LinearLayout = itemView.findViewById(R.id.toDayLayout)
        private var mToDayNumber: TextView = itemView.findViewById(R.id.tvNumberToDay)
        private var mToDayDayOfWeek: TextView = itemView.findViewById(R.id.tvDayInWeekToDay)
        private var mToDayMont: TextView = itemView.findViewById(R.id.tvMonthToday)

        private var mChooseDayLayout: LinearLayout = itemView.findViewById(R.id.chooseDayLayout)
        private var mChooseDayNumber: TextView = itemView.findViewById(R.id.tvNumberChooseDay)
        private var mChooseDayDayOfWeek: TextView = itemView.findViewById(R.id.tvDayInWeekChooseDay)
        private var mChooseDayMont: TextView = itemView.findViewById(R.id.tvMonthChooseday)

        fun bindData(
            context: Context,
            item: DateObject,
        ) {
            when {
                item.isToday -> {
                    mToDayLayout.visibility = View.VISIBLE
                    mToDayNumber.text = item.day
                    mToDayDayOfWeek.text = item.dayOfWeek
                    mToDayMont.text = item.month
                }
                item.isChooser -> {
                    mChooseDayLayout.visibility = View.VISIBLE
                    mChooseDayNumber.text = item.day
                    mChooseDayDayOfWeek.text = item.dayOfWeek
                    mChooseDayMont.text = item.month
                }
                else -> {
                    Log.d(HomeFragment.TAG, "bindData $item")
                    mDayNormalLayout.visibility = View.VISIBLE
                    mDayNormalNumber.text = item.day
                    mDayNormalDayOfWeek.text = item.dayOfWeek
                    mDayNormalMont.text = item.month
                }
            }
        }
    }

    override fun getItemCount(): Int = mList.size

}