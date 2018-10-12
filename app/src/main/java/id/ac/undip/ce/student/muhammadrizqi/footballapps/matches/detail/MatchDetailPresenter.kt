package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.detail

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.TheSportDBApi
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.CoroutineContextProvider
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.EventResponse
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.TeamResponse

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getEventDetail(eventId: String?, homeTeamId: String?, awayTeamId: String?){
        view.showLoading()

        async(context.main) {
            async(context.main) {
                val matchDetail = bg {
                    gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatchDetail(eventId)), EventResponse::class.java)
                }
                val homeTeam = bg {
                    gson.fromJson(ApiRepository().doRequest(TheSportDBApi.getTeamDetail(homeTeamId)), TeamResponse::class.java)
                }
                val awayTeam = bg {
                    gson.fromJson(ApiRepository().doRequest(TheSportDBApi.getTeamDetail(awayTeamId)), TeamResponse::class.java)
                }

                view.showDetail(matchDetail.await().events, homeTeam.await().teams, awayTeam.await().teams)
                view.hideLoading()
            }
        }
    }

}