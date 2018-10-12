package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams

interface TeamsView{
    fun showLoading()
    fun hideLoading()
    fun showLeague(data: List<League>)
    fun showTeamList(data: List<Team>)
}