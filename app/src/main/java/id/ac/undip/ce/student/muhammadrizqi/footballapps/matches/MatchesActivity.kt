package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import kotlinx.android.synthetic.main.activity_matches.*

class MatchesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)

        setSupportActionBar(matches_toolbar)
    }
    private fun setupviewpager(viewPager: ViewPager){
        val adapter = ViewPagerAdapter(supportFragmentManager)
    }
}
