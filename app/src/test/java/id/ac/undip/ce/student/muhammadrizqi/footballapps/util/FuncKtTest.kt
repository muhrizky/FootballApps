package id.ac.undip.ce.student.muhammadrizqi.footballapps.util

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class FuncKtTest {

    @Test
    fun strTodate() {
        val date = SimpleDateFormat("dd/MM/yyy").parse("22/07/2018")

        assertEquals(date, strTodate("2018-07-22"))
    }

    @Test
    fun changeFormatDate() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", changeFormatDate(date))
    }

    @Test
    fun dateTimeToFormat() {
        val dateTime = "2018-09-15 14:00:00+00:00"
        val dateFormat = 1536994800000
        assertEquals(dateFormat, dateTime.dateTimeToFormat())
    }
}