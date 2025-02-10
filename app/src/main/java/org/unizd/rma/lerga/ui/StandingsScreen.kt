package com.example.footballapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.footballapp.data.TeamStanding

@Composable
fun StandingsScreen(leagueId: Int, viewModel: StandingsViewModel = viewModel()) {
    println("Otvoren StandingsScreen za ligu ID: $leagueId")

    var selectedSeason by remember { mutableStateOf(2023) }
    val seasons = (2021..2023).toList().reversed()

    LaunchedEffect(selectedSeason) {
        viewModel.fetchStandings(leagueId, selectedSeason)
    }

    val standings by viewModel.standings.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        SeasonDropdown(seasons, selectedSeason) { selectedSeason = it }
        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(standings) { team ->
                TeamItem(team)
            }
        }
    }
}

@Composable
fun SeasonDropdown(seasons: List<Int>, selectedSeason: Int, onSeasonSelected: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { expanded = true }) {
            Text("Sezona: $selectedSeason")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            seasons.forEach { season ->
                DropdownMenuItem(
                    text = { Text(text = season.toString()) },
                    onClick = {
                        onSeasonSelected(season)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun TeamItem(team: TeamStanding) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(team.team.logo),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "${team.rank}. ${team.team.name}")
            Text(text = "Points: ${team.points}, GD: ${team.goalsDiff}")
        }
    }
}
