package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.CoroutineContextProvider
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.TheSportDBApi
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.LeagueResponse
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.TeamResponse

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getLeague(){
        view.showLoading()

        val api = TheSportDBApi.getListLeague()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), LeagueResponse::class.java)
            }

            view.showLeague(data.await().countrys)
            view.hideLoading()
        }
    }

    fun getTeamList(query: String?, type: Int = 1){
        view.showLoading()

        val api = if(type == 1) TheSportDBApi.getAllTeams(query) else TheSportDBApi.getTeams(query)

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(api),
                        TeamResponse::class.java
                )
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }
}