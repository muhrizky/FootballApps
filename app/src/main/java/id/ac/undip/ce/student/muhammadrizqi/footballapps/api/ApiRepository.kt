package id.ac.undip.ce.student.muhammadrizqi.footballapps.api

import java.net.URL

class ApiRepository{
    fun doRequest(url: String) = URL(url).readText()
}