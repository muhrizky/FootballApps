package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Event
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.League

interface MatchesListView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<Event>)
    fun showLeague(data: List<League>)
}