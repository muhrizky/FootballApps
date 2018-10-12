package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.color.colorAccent
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.drawable.ic_add_to_favorites
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.drawable.ic_added_to_favorites
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.ApiRepository
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.database
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Event
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.changeFormatDate
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.strTodate
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class MatchDetailActivity: AppCompatActivity(), MatchDetailView{
    private lateinit var event: Event
    private lateinit var presenter: MatchDetailPresenter
    private val table = id.ac.undip.ce.student.muhammadrizqi.footballapps.db.EventDB

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        event = intent.getParcelableExtra("EVENT")

        val date = strTodate(event.eventDate)
        tv_date.text = changeFormatDate(date)

        home_club.text = event.homeTeam
        home_score.text = event.homeScore

        away_club.text = event.awayTeam
        away_score.text = event.awayScore

        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)
        presenter.getEventDetail(event.eventId, event.homeTeamId, event.awayTeamId)

        swipe_match.onRefresh {
            presenter.getEventDetail(event.eventId, event.homeTeamId, event.awayTeamId)
        }
        swipe_match.setColorSchemeResources(colorAccent,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
    }

    override fun showLoading() {
        swipe_match.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_match.isRefreshing = false
    }

    override fun showDetail(matchDetails: List<Event>, homeTeams: List<Team>, awayTeams: List<Team>) {
        val eventDetail = matchDetails.get(0)
        val homeTeam = homeTeams.get(0)
        val awayTeam = awayTeams.get(0)

        Picasso.get().load(homeTeam.teamBadge).into(home_img)
        home_club.text = eventDetail.homeTeam
        home_score.text = eventDetail.homeScore
        home_formation.text = eventDetail.homeFormation
        home_goals.text = if(eventDetail.homeGoalDetails.isNullOrEmpty()) "-" else eventDetail.homeGoalDetails?.replace(";", ";\n")
        home_shots.text = eventDetail.homeShots ?: "-"
        home_goalkeeper.text = if(eventDetail.homeLineupGoalKeeper.isNullOrEmpty()) "-" else eventDetail.homeLineupGoalKeeper?.replace("; ", ";\n")
        home_defense.text = if(eventDetail.homeLineupDefense.isNullOrEmpty()) "-" else eventDetail.homeLineupDefense?.replace("; ", ";\n")
        home_midfield.text = if(eventDetail.homeLineupMidfield.isNullOrEmpty()) "-" else eventDetail.homeLineupMidfield?.replace("; ", ";\n")
        home_forward.text = if(eventDetail.homeLineupForward.isNullOrEmpty()) "-" else eventDetail.homeLineupForward?.replace("; ", ";\n")
        home_subtitutes.text = if(eventDetail.homeLineupSubstitutes.isNullOrEmpty()) "-" else eventDetail.homeLineupSubstitutes?.replace("; ", ";\n")

        Picasso.get().load(awayTeam.teamBadge).into(away_img)
        away_club.text = eventDetail.awayTeam
        away_score.text = eventDetail.awayScore
        away_formation.text = eventDetail.awayFormation
        away_goals.text = if(eventDetail.awayGoalsDetails.isNullOrEmpty()) "-" else eventDetail.awayGoalsDetails?.replace(";", ";\n")
        away_shots.text = eventDetail.awayShots ?: "-"
        away_goalkeeper.text = if(eventDetail.awayLineupGoalKeeper.isNullOrEmpty()) "-" else eventDetail.awayLineupGoalKeeper?.replace("; ", ";\n")
        away_defense.text = if(eventDetail.awayLineupDefense.isNullOrEmpty()) "-" else eventDetail.awayLineupDefense?.replace("; ", ";\n")
        away_midfield.text = if(eventDetail.awayLineupMidfield.isNullOrEmpty()) "-" else eventDetail.awayLineupMidfield?.replace("; ", ";\n")
        away_forward.text = if(eventDetail.awayLineupForward.isNullOrEmpty()) "-" else eventDetail.awayLineupForward?.replace("; ", ";\n")
        away_subtitutes.text = if(eventDetail.awayLineupSubstitutes.isNullOrEmpty()) "-" else eventDetail.awayLineupSubstitutes?.replace("; ", ";\n")

        hideLoading()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if(isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(table.TABLE_MATCH,
                        table.EVENT_ID to event.eventId,
                        table.EVENT_NAME to event.eventName,
                        table.EVENT_DATE to event.eventDate,
                        table.HOME_TEAM_ID to event.homeTeamId,
                        table.HOME_TEAM_NAME to event.homeTeam,
                        table.HOME_TEAM_SCORE to event.homeScore,
                        table.AWAY_TEAM_ID to event.awayTeamId,
                        table.AWAY_TEAM_NAME to event.awayTeam,
                        table.AWAY_TEAM_SCORE to event.awayScore)
            }
            swipe_match.snackbar("Added to favorite").show()
        }catch (e: SQLiteConstraintException){
            swipe_match.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use{
                delete(table.TABLE_MATCH, "(EVENT_ID = {id})", "id" to event.eventId.orEmpty())
            }
            swipe_match.snackbar( "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipe_match.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite(){
        val icon = if(isFavorite) ic_added_to_favorites else ic_add_to_favorites

        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, icon)
    }

    private fun favoriteState(){
        database.use {
            val result = select(table.TABLE_MATCH)
                    .whereArgs("(EVENT_ID = {id})", "id" to event.eventId.orEmpty())
            val favorite = result.parseList(classParser<id.ac.undip.ce.student.muhammadrizqi.footballapps.db.EventDB
                    >())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}