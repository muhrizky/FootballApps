package id.ac.undip.ce.student.muhammadrizqi.footballapps.players

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.CoroutineContextProvider
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.TheSportDBApi
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.PlayerResponse

class PlayersPresenter(private val view: PlayerView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson,
                       private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getPlayerList(id: String){
        view.showLoading()
        val api = TheSportDBApi.getPlayers(id)

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), PlayerResponse::class.java)
            }

            view.showPlayerList(data.await().player)
            view.hideLoading()
        }
    }
}