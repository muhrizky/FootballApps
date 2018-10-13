package id.ac.undip.ce.student.muhammadrizqi.footballapps.favorites

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.color.colorAccent
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.Team
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.database

class FavoriteTeamsFragment: Fragment(), AnkoComponent<Context>{
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listTeams: RecyclerView
    private lateinit var adapter: FavoriteTeamsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamsAdapter(teams){
            alert("Oke Bro", "Info").show()
        }

        listTeams.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listTeams = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }

    private fun showFavorite(){
        requireContext().database.use {
            swipeRefresh.isRefreshing = true
            val result = select(Team.TABLE_TEAM)
            val team = result.parseList(classParser<Team>())
            teams.clear()
            teams.addAll(team)
            adapter.notifyDataSetChanged()
            swipeRefresh.isRefreshing = false
        }
    }
}