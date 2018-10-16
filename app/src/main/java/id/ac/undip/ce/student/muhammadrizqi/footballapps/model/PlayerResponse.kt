package id.ac.undip.ce.student.muhammadrizqi.footballapps.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
        @SerializedName("player") val player: List<Player>
)