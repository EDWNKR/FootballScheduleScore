package id.maemaem.footballschedule.detailPrevMatch

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

class DetailPrevMatchPresenter (private val view: DetailPrevMatch){

    fun getDetail(filename: String?) {
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
        val call = request.getDetailNextEvents(filename.toString())
        call.enqueue(object : Callback<JSONResponse> {
            override fun onResponse(call: Call<JSONResponse>, response: Response<JSONResponse>) {
                val jsonResponse = response.body()
                view.hideLoading()
                jsonResponse.getEvent()?.let { view.showDetail(it) }
            }

            override fun onFailure(call: Call<JSONResponse>, t: Throwable) {
                Log.d("Error", t.message)
                Toast.makeText(view, "Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun getImageHome(team: String?) {
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
        val call = request.getImageHome(team.toString())
        call.enqueue(object : Callback<JSONResponse> {
            override fun onResponse(call: Call<JSONResponse>, response: Response<JSONResponse>) {
                val jsonResponse = response.body()
                view.hideLoading()
                jsonResponse.getTeams()?.let { view.showImageHome(it) }
            }

            override fun onFailure(call: Call<JSONResponse>, t: Throwable) {
                Log.d("Error", t.message)
                Toast.makeText(view, "Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getImageAway(team: String?) {
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
        val call = request.getImageHome(team.toString())
        call.enqueue(object : Callback<JSONResponse> {
            override fun onResponse(call: Call<JSONResponse>, response: Response<JSONResponse>) {
                val jsonResponse = response.body()
                view.hideLoading()
                jsonResponse.getTeams()?.let { view.showImageAway(it) }
            }

            override fun onFailure(call: Call<JSONResponse>, t: Throwable) {
                Log.d("Error", t.message)
                Toast.makeText(view, "Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show()
            }
        })
    }
}