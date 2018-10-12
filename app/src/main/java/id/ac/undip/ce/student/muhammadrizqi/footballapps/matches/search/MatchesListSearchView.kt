package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.search
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Event

interface MatchesListSearchView{
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<Event>)
}