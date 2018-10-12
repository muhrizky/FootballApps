package id.ac.undip.ce.student.muhammadrizqi.footballapps.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.layout.fragments_favorites
import kotlinx.android.synthetic.main.fragments_favorites.*
import id.ac.undip.ce.student.muhammadrizqi.footballapps.adapter.ViewPagerAdapter

class FavoritesFragment: Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager(favorites_viewpager)
        favorites_tabs.setupWithViewPager(favorites_viewpager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragments_favorites, container, false)
        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFrag(FavoriteMatchesFragment(), "MATCHES")
        adapter.addFrag(FavoriteTeamsFragment(), "TEAMS")
        viewPager.adapter = adapter
    }
}