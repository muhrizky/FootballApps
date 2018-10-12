package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.support.v4.find
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.id.search_menu
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.layout.fragment_matches
import id.ac.undip.ce.student.muhammadrizqi.footballapps.adapter.ViewPagerAdapter
import id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.search.MatchesListSearchActivity

class MatchesFragment: Fragment(){
    private var leagueId = "4328"   //EPL

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager(matches_viewpager)

        matches_tabs.setupWithViewPager(matches_viewpager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragment_matches, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = find<Toolbar>(R.id.matches_toolbar)
        toolbar.inflateMenu(R.menu.search)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                search_menu -> {
                    val intent = Intent(context, MatchesListSearchActivity::class.java)
                    startActivity(intent)
                }
            }

            true
        }
    }

    private fun setupViewPager(viewPager: ViewPager){
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFrag(MatchesListFragment.newFragment(2, leagueId), "NEXT")
        adapter.addFrag(MatchesListFragment.newFragment(1, leagueId), "LAST")
        viewPager.adapter = adapter
    }
}