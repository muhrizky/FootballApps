package id.ac.undip.ce.student.muhammadrizqi.footballapps.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.id.*
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
            navigation_matches -> {
                openFragment(MatchesFragment())
                return@setOnNavigationItemSelectedListener true
            }
            navigation_teams -> {
                return@setOnNavigationItemSelectedListener true
            }
            navigation_favorites -> {
                return@setOnNavigationItemSelectedListener true
            }
        }
            false
        }

        nav_button.selectedItemId = navigation_matches
    }

    private fun openFragment(fragment: Fragment) {
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                    .commit()
        }
    }


}
