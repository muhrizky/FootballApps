package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.search

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.TheSportDBApi
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.CoroutineContextProvider
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.EventResponse

class MatchesListSearchPresenter(private val view: MatchesListSearchView,
                                 private val apiRepository: ApiRepository,
                                 private val gson: Gson,
                                 private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getList(query: String?){
        view.showLoading()

        val api = TheSportDBApi.getEvents(query)

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), EventResponse::class.java)
            }

            view.showList(data.await().event)
            view.hideLoading()
        }
    }
}