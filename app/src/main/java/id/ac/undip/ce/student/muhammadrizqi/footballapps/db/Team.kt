package id.ac.undip.ce.student.muhammadrizqi.footballapps.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
        val id: Long?,
        val teamId: String?,
        val teamName: String?,
        val teamBadge: String?
): Parcelable {
    companion object {
        const val TABLE_TEAM = "TABLE_TEAM"
        const val ID = "ID_"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_BADGE = "TEAM_BADGE"
    }
}
