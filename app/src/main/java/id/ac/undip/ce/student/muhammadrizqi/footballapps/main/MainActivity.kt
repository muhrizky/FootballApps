package id.ac.undip.ce.student.muhammadrizqi.footballapps.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.id.*
import id.ac.undip.ce.student.muhammadrizqi.footballapps.teams.TeamsFragment
import id.ac.undip.ce.student.muhammadrizqi.footballapps.favorites.FavoritesFragment
import id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.MatchesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        setContentView(R.layout.activity_main)

        nav_button.setOnNavigationItemSelectedListener {
            item -> when(item.itemId){
            navigation_mathes -> {
                supportActionBar?.hide()
                openFragment(MatchesFragment())
                return@setOnNavigationItemSelectedListener true
            }
            navigation_tams -> {
                supportActionBar?.show()
                openFragment(TeamsFragment())
                return@setOnNavigationItemSelectedListener true
            }
            navigation_favorite -> {
                supportActionBar?.hide()
                openFragment(FavoritesFragment())
                return@setOnNavigationItemSelectedListener true
            }
        }
            false
        }

        nav_button.selectedItemId = navigation_mathes
    }

    private fun openFragment(fragment: FavoritesFragment){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
        }
    }
}
