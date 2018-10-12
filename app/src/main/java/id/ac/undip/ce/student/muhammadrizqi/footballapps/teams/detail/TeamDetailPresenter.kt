package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams.detail

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.CoroutineContextProvider
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.TheSportDBApi
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.TeamResponse

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getTeamDetail(teamId: String){
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                        TeamResponse::class.java
                )
            }
            view.showTeamDetail(data.await().teams)
            view.hideLoading()
        }
    }
}