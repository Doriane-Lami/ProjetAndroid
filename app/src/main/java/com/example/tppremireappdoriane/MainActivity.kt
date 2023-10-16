package com.example.tppremireappdoriane

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
//import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.SearchBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        setContent {

            var searchVisible by remember {
                mutableStateOf(false)
            }

            var searchText by remember {
                mutableStateOf("")
            }

            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()

            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()


            Scaffold(
                bottomBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    var currentDestination = navBackStackEntry?.destination
                    if (currentDestination != null) {
                        if (currentDestination.route != "Home") {
                            BottomNavigation {
                                BottomNavigationItem(
                                    icon = {
                                        Image(
                                            painterResource(id = R.drawable.baseline_movie_24),
                                            contentDescription = "logo films",
                                        )
                                    },
                                    label = { Text("Films") },
                                    selected = false,
                                    onClick = {
                                        navController.navigate("Films")
                                    })
                                BottomNavigationItem(
                                    icon = {
                                        Image(
                                            painterResource(id = R.drawable.baseline_tv_24),
                                            contentDescription = "logo séries",
                                        )
                                    },
                                    label = { Text("Series") },
                                    selected = false,
                                    onClick = {
                                        navController.navigate("Series")
                                    })
                                BottomNavigationItem(
                                    icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                                    label = { Text("Personnes") },
                                    selected = false,
                                    onClick = {
                                        navController.navigate("Personnes")
                                    })
                            }
                        }
                    }
                },   //} du if pour ne pas afficher dans Home
                topBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    var currentDestination = navBackStackEntry?.destination
                    if (currentDestination != null) {
                        if (currentDestination.route != "Home") {
                            if (!searchVisible) {
                                TopAppBar(
                                    title = { Text("Super App'") },
                                    actions = {
                                        IconButton(onClick = { searchVisible = true }) {
                                            Icon(
                                                Icons.Filled.Search,
                                                contentDescription = "recherche"
                                            )
                                        }
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = { }) {
                                            Icon(
                                                imageVector = Icons.Filled.ArrowBack,
                                                contentDescription = "Localized description"
                                            )
                                        }
                                    },
                                )
                            } else {
                                DockedSearchBar(
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .padding(horizontal = 4.dp)
                                        .height(50.dp)
                                        .fillMaxSize(),
                                    query = searchText,
                                    onQueryChange = { searchText = it },
                                    onSearch = {
                                        //active = false

                                        //Attention il faut distinguer les pages de films/series/personnes

                                        //navController.navigate("RechercheFilm")



                                    },
                                    active = true,
                                    onActiveChange = { },
                                    placeholder = { Text("Recherche") },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Default.Search,
                                            contentDescription = null
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = { searchVisible = false }) {
                                            Icon(
                                                Icons.Default.Close,
                                                contentDescription = null
                                            )
                                        }
                                    },
                                ) {
                                }
                            }
                        }
                    }
                },
            )


            { innerPadding ->
                NavHost(navController, startDestination = "Home", Modifier.padding(innerPadding)) {
                    composable("Home") { Screen(windowSizeClass, navController) }
                    composable("Films") { Films(viewModel, windowSizeClass, navController) }
                    composable("Series") { Series(viewModel, windowSizeClass, navController) }
                    composable("Personnes") { PersonnesAffiche(viewModel, windowSizeClass, navController) }
                    composable("RechercheFilm") {
                        FilmsRecherche(
                            viewModel,
                            windowSizeClass,
                            navController,
                            searchText
                        )
                    }
                    composable("SerieDetails/{tvid}", arguments = listOf(navArgument("tvid"){type = NavType.IntType})) {
                        SerieDetails(
                            tvid,
                            viewModel,
                            windowSizeClass,
                            navController
                        )
                    }
                }
            }
        }
    }
}



