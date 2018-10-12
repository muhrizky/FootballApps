package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.TheSportDBApi
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.CoroutineContextProvider
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.LeagueResponse
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.EventResponse



class MatchesListPresenter(private val view: MatchesListView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val fixture: Int = 1,
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

    fun getList(id: String?){
        view.showLoading()

        val api = if (fixture == 1) TheSportDBApi.getPrevSchedule(id) else TheSportDBApi.getNextSchedule(id)

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), EventResponse::class.java)
            }

            view.showList(data.await().events)
            view.hideLoading()
        }
    }
}