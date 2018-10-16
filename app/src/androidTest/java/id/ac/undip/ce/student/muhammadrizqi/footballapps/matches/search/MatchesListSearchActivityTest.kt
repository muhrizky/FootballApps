package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.search

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import android.support.test.rule.ActivityTestRule
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.id.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MatchesListSearchActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MatchesListSearchActivity::class.java)

    @Test
    fun testSearchMatch() {
        Espresso.onView(ViewMatchers.withId(search_src_text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(search_src_text)).perform(ViewActions.typeText("Barc"))
        delay()
        Espresso.onView(ViewMatchers.withId(search_src_text)).perform(ViewActions.typeText("elona"))
        delay(3)

        Espresso.onView(ViewMatchers.withId(R.id.rv_matches_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay(2)

        Espresso.onView(ViewMatchers.withId(R.id.rv_matches_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
        )
        delay(2)

        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Espresso.pressBack()
        delay()

        Espresso.onView(ViewMatchers.withId(R.id.rv_matches_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
        )
        delay(2)
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        delay()
    }

    private fun delay(second: Long = 1) {
        Thread.sleep(1000 * second)
    }
}