package id.ac.undip.ce.student.muhammadrizqi.footballapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
        @SerializedName("idPlayer")
        var playerId: String? = null,

        @SerializedName("strPlayer")
        var playerName: String? = null,

        @SerializedName("strDescriptionEN")
        var playerDescription: String? = null,

        @SerializedName("strHeight")
        var playerHeight: String? = null,

        @SerializedName("strWeight")
        var playerWeight: String? = null,

        @SerializedName("strCutout")
        var playerBadge: String? = null,

        @SerializedName("strFanart1")
        var playerImage: String? = null,

        @SerializedName("strPosition")
        var playerPosition: String? = null
): Parcelable