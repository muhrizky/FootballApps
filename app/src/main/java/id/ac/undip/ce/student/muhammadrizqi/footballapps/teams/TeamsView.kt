package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams

import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.League

interface TeamsView{
    fun showLoading()
    fun hideLoading()
    fun showLeague(data: List<League>)
    fun showTeamList(data: List<Team>)
}