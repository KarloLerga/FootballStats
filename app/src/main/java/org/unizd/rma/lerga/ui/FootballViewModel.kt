package com.example.footballapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.data.ApiClient
import com.example.footballapp.data.League
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FootballViewModel : ViewModel() {
    private val _leagues = MutableStateFlow<List<League>>(emptyList())
    val leagues = _leagues.asStateFlow()

    init {
        fetchLeagues()
    }

    fun fetchLeagues() {
        viewModelScope.launch {
            try {
                println("Fetching leagues...")
                val response = ApiClient.api.getLeagues()
                _leagues.value = response.response
                println("Leagues fetched successfully!")
            } catch (e: Exception) {
                println("Error fetching leagues: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}