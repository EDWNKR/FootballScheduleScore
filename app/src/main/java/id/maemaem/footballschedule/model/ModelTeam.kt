package id.maemaem.footballschedule.model

import com.google.gson.annotations.SerializedName

data class ModelTeam (
    @SerializedName("strTeamBadge") //ini yg ada di json
    var strTeamBadge: String? = null,

    @SerializedName("strTeamJersey") //ini yg ada di json
    var strTeamJersey: String? = null //homeTeam adalah nama alias json di project ini

)