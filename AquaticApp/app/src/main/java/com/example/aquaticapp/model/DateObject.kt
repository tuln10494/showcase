package com.example.aquaticapp.model

class DateObject(mDay: String, mDayOfWeek: String, mMonth: String, mIsToDay: Boolean) {
    var day = mDay
    var dayOfWeek = mDayOfWeek
    var month = mMonth
    var isToday = mIsToDay
    var isChooser = false

    override fun toString(): String {

        return "day : $day" +
                " dayOfWeek : $dayOfWeek" +
                " month : $month" +
                " today : $isToday"
    }
}