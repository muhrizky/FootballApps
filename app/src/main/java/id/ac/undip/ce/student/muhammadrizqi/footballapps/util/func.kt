package id.ac.undip.ce.student.muhammadrizqi.footballapps.util

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

@SuppressLint("SimpleDateFormat")
fun  strTodate(strDate: String?, pattern: String= "yyyy-MM-dd"): Date{
    val format = SimpleDateFormat(pattern)
    val date = format.parse(strDate)

    return date
}

@SuppressLint("SimpleDateFormat")
fun changeFormatDate(date: Date?): String? = with(date ?: Date()){
    SimpleDateFormat("EEE, dd MMM yyy", Locale.ENGLISH).format(this)
}

@SuppressLint("SimpleDateForamt")
fun toGMTFormat(date: String?, time:String?): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}

fun String.dateTimeToFormat(format: String = "yyyy-MM-dd HH:mm:ss"): Long {

    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
    val date = formatter.parse(this)

    return date.time
}


fun View.visible(){
    visibility = View.VISIBLE
}
fun View.invisible(){
    visibility = View.INVISIBLE
}