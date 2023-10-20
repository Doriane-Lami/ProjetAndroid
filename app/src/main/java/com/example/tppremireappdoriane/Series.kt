package com.example.tppremireappdoriane


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Series(viewModel: MainViewModel, windowClass: WindowSizeClass, navController: NavController) {
    val tvs by viewModel.tvs.collectAsState()

    if (tvs.isEmpty()) viewModel.getSeries()

    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        items(tvs) { tv ->
            Card(
                onClick = { //SerieDetails(tv.id, viewModel, windowClass, navController)
                    navController.navigate("SerieDetails/"+tv.id)
                          },
                Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + tv.poster_path,
                    contentDescription = "Affiche de la série",
                    Modifier.fillMaxSize()
                )
                Text(tv.name, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
                Text(tv.first_air_date, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesRecherche(viewModel: MainViewModel, windowClass: WindowSizeClass, navController: NavController, motcle : String ) {
    val tvs by viewModel.tvs.collectAsState()

    if (tvs.isEmpty()) viewModel.searchSeries(motcle)

    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        items(tvs) { tv ->
            Card(
                Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + tv.poster_path,
                    contentDescription = "Affiche de la série",
                    Modifier.fillMaxSize()
                )
                Text(tv.name, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
                Text(tv.first_air_date, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            }
        }
    }
}


@Composable
fun SerieDetails(tvid: String, viewModel: MainViewModel, windowClass: WindowSizeClass, navController: NavController) {
    val tv by viewModel.tv.collectAsState()

    LaunchedEffect(true){
        viewModel.getTVdetails(tvid)
    }

    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500" + tv.poster_path,
        contentDescription = "Affiche de la série",
        Modifier.fillMaxSize()
    )
    Text(tv.name, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
    Text(tv.first_air_date, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)

    }


