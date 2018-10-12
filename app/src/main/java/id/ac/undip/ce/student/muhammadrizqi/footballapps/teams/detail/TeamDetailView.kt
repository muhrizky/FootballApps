package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams.detail
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team
interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}