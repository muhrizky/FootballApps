package id.ac.undip.ce.student.muhammadrizqi.footballapps.teamsdetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team
import id.ac.undip.ce.student.muhammadrizqi.footballapps.adapter.ViewPagerAdapter
import id.ac.undip.ce.student.muhammadrizqi.footballapps.overview.OverviewFragment
import id.ac.undip.ce.student.muhammadrizqi.footballapps.players.PlayersFragment
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.database
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.drawable.ic_added_to_favorites
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.drawable.ic_add_to_favorites
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity: AppCompatActivity(){
    private lateinit var team: Team
    private val table = id.ac.undip.ce.student.muhammadrizqi.footballapps.db.TeamDB

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar?.hide()

        val toolbar = team_detail_toolbar
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbar.inflateMenu(R.menu.detail_menu)
        menuItem  = toolbar.menu

        toolbar.setNavigationOnClickListener {
            finish()
        }

        toolbar.setOnMenuItemClickListener {
            if(it.itemId.equals(R.id.add_to_favorite)){
                if(isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                return@setOnMenuItemClickListener true
            }else{
                onOptionsItemSelected(it)
            }
        }
//
//        team = intent.getParcelableExtra("TEAM")
        team = intent.getParcelableExtra("teamObject")
        fillData()

        setupViewPager(team_detail_viewpager)
        team_detail_tabs.setupWithViewPager(team_detail_viewpager)

        favoriteState()
        setFavorite()
    }

    private fun fillData(){
        Picasso.get().load(team.teamBadge).into(team_badge)
        team_name.text = team.teamName
        team_year.text = team.teamFormedYear
        team_stadium.text = team.teamStadium
    }

    private fun setupViewPager(viewPager: ViewPager){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(OverviewFragment.newFragment(team), "Overview")
        adapter.addFrag(PlayersFragment.newFragment(team.teamId!!), "Players")
        viewPager.adapter = adapter
    }

    private fun addToFavorite(){
        try{
            database.use {
                insert(table.TABLE_TEAM,
                        table.TEAM_ID to team.teamId,
                        table.TEAM_NAME to team.teamName,
                        table.TEAM_BADGE to team.teamBadge,
                        table.TEAM_STADIUM to team.teamStadium,
                        table.TEAM_YEAR to team.teamFormedYear,
                        table.TEAM_DESC to team.teamDescription)

            }
            team_detail_viewpager.snackbar("Added to favorite").show()
        }catch (e: SQLiteConstraintException){
            team_detail_viewpager.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(table.TABLE_TEAM, "(TEAM_ID = {id})", "id" to team.teamId!!)
            }
        }catch (e: SQLiteConstraintException){
            team_detail_viewpager.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite(){
        if (isFavorite) menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState(){
        database.use {
            val result = select(table.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})", "id" to team.teamId!!)
            val favorite = result.parseList(classParser<id.ac.undip.ce.student.muhammadrizqi.footballapps.db.TeamDB>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}