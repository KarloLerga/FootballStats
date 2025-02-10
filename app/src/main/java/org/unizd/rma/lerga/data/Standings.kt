package com.example.footballapp.data

data class StandingsResponse(val response: List<LeagueStandings>)

data class LeagueStandings(
    val league: StandingsLeague
)

data class StandingsLeague(
    val standings: List<List<TeamStanding>>
)

data class TeamStanding(
    val rank: Int,
    val team: TeamInfo,
    val points: Int,
    val goalsDiff: Int,
    val all: MatchStats
)

data class TeamInfo(
    val id: Int,
    val name: String,
    val logo: String
)

data class MatchStats(
    val played: Int,
    val win: Int,
    val draw: Int,
    val lose: Int
)