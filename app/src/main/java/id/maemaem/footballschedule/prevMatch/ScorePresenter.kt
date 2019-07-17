package id.maemaem.footballschedule.prevMatch

import android.util.Log
import android.widget.Toast
import id.maemaem.footballschedule.api.JSONResponseScore
import id.maemaem.footballschedule.api.RequestInterface
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ScorePresenter (private val view: PrevMatch){
    fun loadJSON(idleague: String?) {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient().newBuilder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build())
                .build()
        val request = retrofit.create(RequestInterface::class.java)
        val call = request.getScore(idleague.toString())
        call.enqueue(object : Callback<JSONResponseScore> {
            override fun onResponse(call: Call<JSONResponseScore>, response: Response<JSONResponseScore>) {
                val jsonResponse = response.body()
                view.hideLoading()
                jsonResponse.getEvents()?.let { view.showPrevSchedule(it) }
            }

            override fun onFailure(call: Call<JSONResponseScore>, t: Throwable) {
                Log.d("Error", t.message)
                Toast.makeText(view, "Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show()
            }
        })
    }

}