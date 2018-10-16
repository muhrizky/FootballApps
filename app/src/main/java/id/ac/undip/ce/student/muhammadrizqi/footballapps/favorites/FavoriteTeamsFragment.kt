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
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.color.colorAccent
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.TeamDB
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.database
import id.ac.undip.ce.student.muhammadrizqi.footballapps.teamsdetail.TeamDetailActivity

class FavoriteTeamsFragment: Fragment(), AnkoComponent<Context>{
    private var teamDB: MutableList<TeamDB> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listTeams: RecyclerView
    private lateinit var adapter: FavoriteTeamsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamsAdapter(teamDB){
            val team = Team(
                    it.teamId,
                    it.teamName,
                    it.teamBadge,
                    it.teamStadium,
                    it.teamFormedYear,
                    it.teamDescription
            )

            requireContext().startActivity<TeamDetailActivity>("teamObject" to team)
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
            val result = select(TeamDB.TABLE_TEAM)
            val team = result.parseList(classParser<TeamDB>())
            teamDB.clear()
            teamDB.addAll(team)
            adapter.notifyDataSetChanged()
            swipeRefresh.isRefreshing = false
        }
    }
}