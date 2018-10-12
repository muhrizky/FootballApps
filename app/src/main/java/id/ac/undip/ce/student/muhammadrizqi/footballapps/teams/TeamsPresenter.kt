package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams

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