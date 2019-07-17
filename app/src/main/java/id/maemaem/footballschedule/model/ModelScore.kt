package id.maemaem.footballschedule.model

import com.google.gson.annotations.SerializedName

data class ModelScore (
    @SerializedName("strDate") //ini yg ada di json
    var strDate: String? = null,

    @SerializedName("strHomeTeam") //ini yg ada di json
    var strHomeTeam: String? = null, //homeTeam adalah nama alias json di project ini

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: Int? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: Int? = null,

    @SerializedName("strFilename")
    var strFilename: String? = null
)