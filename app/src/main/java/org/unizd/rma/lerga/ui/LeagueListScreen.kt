package com.example.footballapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.NavController
import com.example.footballapp.data.League

@Composable
fun LeagueListScreen(navController: NavController, viewModel: FootballViewModel = viewModel()) {
    val leagues by viewModel.leagues.collectAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pretraži lige") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(leagues.filter { it.league.name.contains(searchQuery, ignoreCase = true) }) { league ->
                LeagueItem(league) {
                    searchQuery = ""
                    navController.navigate("standings/${league.league.id}")
                }
            }
        }
    }
}


@Composable
fun LeagueItem(league: League, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                println("✅ Kliknut je: ${league.league.name} (ID: ${league.league.id})") // OnClick check
                onClick()
            }
            .padding(8.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(league.league.logo),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = league.league.name)
    }
}