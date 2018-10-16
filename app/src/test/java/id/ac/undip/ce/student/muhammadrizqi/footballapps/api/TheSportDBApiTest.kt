package id.ac.undip.ce.student.muhammadrizqi.footballapps.api


import org.junit.Before
import org.junit.Test
import id.ac.undip.ce.student.muhammadrizqi.footballapps.api.TheSportDBApi

import org.junit.Assert.*

class TheSportDBApiTest {
    private lateinit var theSportDbApi: TheSportDBApi

    @Before
    fun setUp() {
        theSportDbApi = TheSportDBApi
    }

    @Test
    fun getPrevSchedule() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        val stringInput = "4328"
        assertEquals(resultLink, theSportDbApi.getPrevSchedule(stringInput))
    }


    @Test
    fun getAllTeams() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        val stringInput = "English%20Premier%20League"
        assertEquals(resultLink, theSportDbApi.getAllTeams(stringInput))
    }

    @Test
    fun getTeams() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=barcelona"
        val stringInput = "barcelona"
        assertEquals(resultLink, theSportDbApi.getTeams(stringInput))
    }


    @Test
    fun getEvents() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=manchester"
        val stringInput = "manchester"
        assertEquals(resultLink, theSportDbApi.getEvents(stringInput))
    }


    @Test
    fun getPlayers() {
        val resultLink = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=133604"
        val stringInput = "133604"
        assertEquals(resultLink, theSportDbApi.getPlayers(stringInput))
    }
}
