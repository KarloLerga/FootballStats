package com.example.footballapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val API_KEY = "842ffa7fa573d3c51c6f8c4f74bf14a5"
const val BASE_URL = "https://v3.football.api-sports.io/"

interface FootballApi {
    @Headers("x-apisports-key: $API_KEY")
    @GET("leagues")
    suspend fun getLeagues(): LeagueResponse

    @Headers("x-apisports-key: $API_KEY")
    @GET("standings")
    suspend fun getStandings(
        @Query("league") leagueId: Int,
        @Query("season") season: Int = 2023
    ): StandingsResponse

}

object ApiClient {
    val api: FootballApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }
}