package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.League
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R

class TeamsFragment: Fragment(), AnkoComponent<Context>, TeamsView{
    private var teams: MutableList<Team> = mutableListOf()
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
        presenter.getLeague()

        adapter = TeamsAdapter(teams){
            //            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
            alert("Oke", "Berhasil Bro!").show()

        }
        listTeam.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return createView(AnkoContext.create(ctx))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_view, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
//        searchView.setIconifiedByDefault(false)
//        searchView.isIconified = false
//        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if(text?.length!! > 2) {
                    presenter.getTeamList(text, 2)
                }
                return false
            }

        })
    }

    override fun createView(ui: AnkoContext<Context>)= with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = R.id.spinner

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                        leagueName = spinner.selectedItem.toString()
                        presenter.getTeamList(leagueName)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }

                }
            }

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.list_team
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
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

        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, leagues)
        spinner.adapter = spinnerAdapter

        spinner.setSelection(spinnerAdapter.getPosition(League("4328", "English Premier League")))
    }

    override fun showTeamList(data: List<Team>) {
        hideLoading()
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}