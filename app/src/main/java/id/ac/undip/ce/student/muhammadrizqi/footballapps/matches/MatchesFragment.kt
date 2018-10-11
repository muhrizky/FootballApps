package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_matches.*

class MatchesFragment: Fragment() {
    private var leagueId = "4328"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager(matches_viewpager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragment_matches, container, false)

        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(fragmentManager!!)
        adapter.addFrag(MatchesListFragment.newFragment(2, leagueId), "NEXT")
        adapter.addFrag(MatchesListFragment.newFragment(1, leagueId), "LAST")
        viewPager.adapter = adapter
    }
}