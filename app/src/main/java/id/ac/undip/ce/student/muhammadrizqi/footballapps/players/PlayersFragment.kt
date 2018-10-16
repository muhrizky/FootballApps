package id.ac.undip.ce.student.muhammadrizqi.footballapps.players

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Player
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R

class PlayersFragment: Fragment(), AnkoComponent<Context>, PlayerView{
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayersPresenter
    private lateinit var adapter: PlayersAdapter
    private lateinit var playerList: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var teamId: String

    companion object {
        fun newFragment(teamId: String): PlayersFragment{
            val fragment = PlayersFragment()
            fragment.teamId = teamId

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayersPresenter(this, request, gson)
        presenter.getPlayerList(teamId)

        adapter = PlayersAdapter(players){
            requireContext().startActivity<PlayerDetailActivity>("PLAYER" to it)
        }

        playerList.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getPlayerList(teamId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        linearLayout {
            orientation = LinearLayout.VERTICAL
            lparams(width = matchParent, height = matchParent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                playerList = recyclerView {
                    id = R.id.rv_matches_list
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showPlayerList(data: List<Player>) {
        hideLoading()
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}