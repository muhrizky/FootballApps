package id.ac.undip.ce.student.muhammadrizqi.footballapps.favorites

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Event
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.EventDB
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.database
import id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.detail.MatchDetailActivity


class FavoriteMatchesFragment: Fragment(), AnkoComponent<Context> {
    private var matches: MutableList<id.ac.undip.ce.student.muhammadrizqi.footballapps.db.EventDB> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var matchList: RecyclerView
    private lateinit var adapter: FavoriteMatchesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchesAdapter(matches){
            val event = Event(
                    eventId = it.eventId,
                    eventDate = it.eventDate,
                    eventName = it.eventName,
                    homeTeamId = it.homeTeamId,
                    homeTeam = it.homeTeam,
                    homeScore = it.homeScore,
                    awayTeamId = it.awayTeamId,
                    awayTeam = it.awayTeam,
                    awayScore = it.awayScore
            )

            requireContext().startActivity<MatchDetailActivity>("EVENT" to event)
        }
        matchList.adapter = adapter

        showFavorite()
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = createView(AnkoContext.create(requireContext()))

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                matchList = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }

        }
    }

    private fun showFavorite(){
        requireContext().database.use {
            swipeRefresh.isRefreshing = true
            val result = select(EventDB.TABLE_MATCH)
            val match = result.parseList(classParser<EventDB>())
            matches.clear()
            matches.addAll(match)
            adapter.notifyDataSetChanged()
            swipeRefresh.isRefreshing = false
        }
    }
}