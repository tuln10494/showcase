package com.example.greenlightaquaticapp.constants

import com.example.greenlightaquaticapp.constants.DayName.nameDaysMap
import com.example.greenlightaquaticapp.constants.DayName.nameMonthsMap

object DayName {
    val nameDaysMap: HashMap<Int, String> = hashMapOf(
        0 to "Mon",
        1 to "Tue",
        2 to "Wed",
        3 to "Thu",
        4 to "Fri",
        5 to "Sat",
        6 to "Sun"
    )
    val nameMonthsMap: HashMap<String, String> = hashMapOf(
        "01" to "Jan",
        "02" to "Feb",
        "03" to "Mar",
        "04" to "Apr",
        "05" to "May",
        "06" to "Jun",
        "07" to "Jul",
        "08" to "Aug",
        "09" to "Sep",
        "10" to "Oct",
        "11" to "Nov",
        "12" to "Dec",
    )
}

object SharePrefKey{
    val EMAIL_LOGGED_IN = "EMAIL_LOGGED_IN"
}

class Constant {
    companion object {
        fun getMonthName(monthNumber: String): String {
            return if (nameMonthsMap[monthNumber].isNullOrBlank()) "" else nameMonthsMap[monthNumber]!!
        }

        fun getDayName(dayNumber: Int): String {
            return if (nameDaysMap[dayNumber].isNullOrBlank()) "" else nameDaysMap[dayNumber]!!

        }

    }
}
