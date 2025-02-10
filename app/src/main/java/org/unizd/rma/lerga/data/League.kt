package com.example.footballapp.data

data class LeagueResponse(val response: List<League>)

data class League(
    val league: LeagueInfo
)

data class LeagueInfo(
    val id: Int,
    val name: String,
    val logo: String
)