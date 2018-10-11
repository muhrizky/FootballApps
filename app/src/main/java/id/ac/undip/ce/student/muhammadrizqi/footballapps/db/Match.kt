package id.ac.undip.ce.student.muhammadrizqi.footballapps.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
        val id: Long?,
        var eventId: String?,
        var eventName: String?,
        var eventDate: String?,
        var homeTeamId: String?,
        var homeTeam: String?,
        var homeScore: String?,
        var awayTeamId: String?,
        var awayTeam: String?,
        var awayScore: String?): Parcelable {

    companion object {
        const val TABLE_MATCH = "TABLE_MATCH"
        const val ID = "ID_"
        const val EVENT_ID = "EVENT_ID"
        const val EVENT_NAME = "EVENT_NAME"
        const val EVENT_DATE = "EVENT_DATE"
        const val HOME_TEAM_ID = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME = "HOME_TEAM_NAME"
        const val HOME_TEAM_SCORE = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_ID = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME = "AWAY_TEAM_NAME"
        const val AWAY_TEAM_SCORE = "AWAY_TEAM_SCORE"
    }
}