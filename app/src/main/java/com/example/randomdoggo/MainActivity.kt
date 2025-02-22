package com.example.randomdoggo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomdoggo.generate.GenerateDogsScreen
import com.example.randomdoggo.home.HomeNavEvent
import com.example.randomdoggo.home.HomeScreen
import com.example.randomdoggo.home.RDTopAppBar
import com.example.randomdoggo.recent.RecentScreen
import com.example.randomdoggo.ui.theme.RandomdoggoTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RandomdoggoTheme {
                val navController = rememberNavController()
                val scrollBehavior =
                    TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        RDTopAppBar(navController = navController, scrollBehavior = scrollBehavior)
                    }
                ) { innerPadding ->
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