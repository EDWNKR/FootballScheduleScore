package id.maemaem.footballschedule.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInterface {

    @GET("/api/v1/json/1/eventspastleague.php")
    fun getScore(@Query("id") idleague: String): Call<JSONResponseScore>

    @GET("/api/v1/json/1/eventsnextleague.php")
    fun getNextEvents(@Query("id") idleague: String): Call<JSONResponse>

    @GET("/api/v1/json/1/searchfilename.php")
    fun getDetailNextEvents(@Query("e") filename: String): Call<JSONResponse>

    @GET("/api/v1/json/1/searchteams.php")
    fun getImageHome(@Query("t") team: String): Call<JSONResponse>
}