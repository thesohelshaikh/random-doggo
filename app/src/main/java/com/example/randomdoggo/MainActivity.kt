package com.example.randomdoggo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomdoggo.generate.GenerateDogsScreen
import com.example.randomdoggo.home.HomeNavEvent
import com.example.randomdoggo.home.HomeScreen
import com.example.randomdoggo.recent.RecentScreen
import com.example.randomdoggo.ui.theme.RandomdoggoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomdoggoTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Route.Home> {
                            HomeScreen(onEvent = {
                                when (it) {
                                    HomeNavEvent.NavigateToGenerate -> {
                                        navController.navigate(Route.Generate)
                                    }

                                    HomeNavEvent.NavigateToGenerated -> {
                                        navController.navigate(Route.Recent)
                                    }
                                }
                            })
                        }

                        composable<Route.Generate> {
                            GenerateDogsScreen()
                        }

                        composable<Route.Recent> {
                            RecentScreen()
                        }

                    }
                }

            }
        }
    }
}