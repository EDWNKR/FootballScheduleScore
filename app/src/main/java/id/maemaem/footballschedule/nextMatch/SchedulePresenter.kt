package id.maemaem.footballschedule.nextMatch

import android.util.Log
import android.widget.Toast
import id.maemaem.footballschedule.NextMatch
import id.maemaem.footballschedule.api.JSONResponse
import id.maemaem.footballschedule.api.RequestInterface
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SchedulePresenter (private val view: NextMatch){

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
        val call = request.getNextEvents(idleague.toString())
        call.enqueue(object : Callback<JSONResponse> {
            override fun onResponse(call: Call<JSONResponse>, response: Response<JSONResponse>) {
                val jsonResponse = response.body()
                view.hideLoading()
                jsonResponse.getEvents()?.let { view.showNextScheduleRetro(it) }
            }

            override fun onFailure(call: Call<JSONResponse>, t: Throwable) {
                Log.d("Error", t.message)
                Toast.makeText(view, "Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show()
            }
        })
    }
}