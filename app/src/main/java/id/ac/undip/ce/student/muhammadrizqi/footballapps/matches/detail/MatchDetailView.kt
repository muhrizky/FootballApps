package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.detail
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Event

import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team

interface MatchDetailView{
    fun showLoading()
    fun hideLoading()
    fun showDetail(matchDetails: List<Event>,
                   homeTeams: List<Team>,
                   awayTeams: List<Team>)
}