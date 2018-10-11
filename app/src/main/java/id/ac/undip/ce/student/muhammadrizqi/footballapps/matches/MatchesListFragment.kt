package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import android.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.*
import com.google.gson.Gson
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Event
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.League
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.MatchesListPresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchesListFragment: Fragment(), AnkoComponent<Context>, MatchesListView {
    private val events: MutableList<Event> = mutableListOf()
    private val league: MutableList<League> = mutableListOf()
    private lateinit var presenter: MatchesListPresenter
    private lateinit var matchesList: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: MatchesListAdapter
    private lateinit var spinner: Spinner
    private var fixture = 1
    private var leagueId= "4328"

    companion object {
        fun newFragment(fixture: Int, leagueId: String): MatchesListFragment{
            val fragment = MatchesListFragment()
            fragment.fixture = fixture
            fragment.leagueId = leagueId

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchesListPresenter(this, request, gson, fixture)
        presenter.getLeague()

        adapter = MatchesListAdapter(events){
            Toast.makeText(context, "Oke Bro!", Toast.LENGTH_SHORT).show()
        }

        matchesList.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getList(leagueId)
        }

        presenter.getList(leagueId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(context!!))
    }

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        linearLayout {
            orientation = LinearLayout.VERTICAL
            lparams(width = matchParent, height = matchParent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = R.id.sp_league

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val league = spinner.selectedItem as League

                        leagueId = league.leagueId.orEmpty()
                        if(leagueId.isNotEmpty()){
                            presenter.getList(leagueId)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }
            }.lparams(width = matchParent, height = wrapContent)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                matchesList = recyclerView {
                    id = R.id.rv_matches_list
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
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

    override fun showLeague(data: List<League>) {
        hideLoading()
        leagues.clear()
        leagues.addAll(data)

        val spinnerAdapter = ArrayAdapter(context, R.layout.simple_spinner_dropdown_item, leagues)
        spinner.adapter = spinnerAdapter
    }

    override fun showList(data: List<Event>) {
        hideLoading()
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}