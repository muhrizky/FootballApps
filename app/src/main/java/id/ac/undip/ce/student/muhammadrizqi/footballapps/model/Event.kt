package id.ac.undip.ce.student.muhammadrizqi.footballapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        @SerializedName("strSport")
        var sportName: String? = null,

        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("strEvent")
        var eventName: String? = null,

        @SerializedName("dateEvent")
        var eventDate: String? = null,

        @SerializedName("strTime")
        var eventTime: String? = null,

        @SerializedName("idHomeTeam")
        var homeTeamId: String? = null,

        @SerializedName("idAwayTeam")
        var awayTeamId: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null,

        @SerializedName("intHomeShots")
        var homeShots: String? = null,

        @SerializedName("intAwayShots")
        var awayShots: String? = null,

        @SerializedName("strHomeFormation")
        var homeFormation: String? = null,

        @SerializedName("strAwayFormation")
        var awayFormation: String? = null,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoalsDetails: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeLineupGoalKeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var homeLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var homeLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var homeLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var homeLineupSubstitutes: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayLineupGoalKeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        var awayLineupDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var awayLineupMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        var awayLineupForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var awayLineupSubstitutes: String? = null
) : Parcelable