package com.abraham.searchmoviemvvm.ui.main.repository.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Abraham on 11/09/2018.
 */
object DateFormater {
    fun changeDateFormat(DateFormatInput: String, DateInput: String, DateFormatOutput: String): String {

        try {
            val inputFormat = SimpleDateFormat(DateFormatInput)
            val outputFormat = SimpleDateFormat(DateFormatOutput, Locale.ENGLISH)
            val date: Date
            date = inputFormat.parse(DateInput)

            return outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return ""
    }
}
