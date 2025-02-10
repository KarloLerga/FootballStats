package com.example.footballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.footballapp.ui.LeagueListScreen
import com.example.footballapp.ui.StandingsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "leagues") {
                composable("leagues") {
                    LeagueListScreen(navController)
                }
                composable(
                    "standings/{leagueId}",
                    arguments = listOf(navArgument("leagueId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val leagueId = backStackEntry.arguments?.getString("leagueId")?.toIntOrNull() ?: 0
                    println("Navigating to standings with leagueId: $leagueId")
                    StandingsScreen(leagueId)
                }
            }
        }
    }
}