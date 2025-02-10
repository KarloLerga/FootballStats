package com.example.footballapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.ApiClient
import com.example.footballapp.data.TeamStanding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StandingsViewModel : ViewModel() {
    private val _standings = MutableStateFlow<List<TeamStanding>>(emptyList())
    val standings = _standings.asStateFlow()

    fun fetchStandings(leagueId: Int, season: Int) {
        viewModelScope.launch {
            try {
                println("Fetching standings for leagueId: $leagueId, season: $season")

                val response = ApiClient.api.getStandings(leagueId, season)
                println("API response: ${response.response}")

                if (response.response.isNotEmpty()) {
                    _standings.value = response.response.first().league.standings.first()
                    println("Fetched standings successfully!")
                } else {
                    println("API response empty for leagueId: $leagueId, season: $season")
                }
            } catch (e: Exception) {
                println("Error fetching standings: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
