package com.example.tppremireappdoriane


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun Test(windowClass: WindowSizeClass, navController: NavController) {
    Text(text = "coucou")
    Button(onClick = { navController.navigate("Home") }) {
        Text(text = "Retour")
    }
}


@Composable
fun Films(viewModel: MainViewModel, windowClass: WindowSizeClass, navController: NavController) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) viewModel.getMovies()

    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        items(movies) { movie ->
            Card(
                Modifier.padding(8.dp) .fillMaxSize(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
                    contentDescription = "Affiche du film",
                    Modifier.fillMaxSize()
                )
                Text(movie.original_title, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
                Text(movie.release_date, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            }
        }
    }
}


@Composable
fun FilmsRecherche(viewModel: MainViewModel, windowClass: WindowSizeClass, navController: NavController, motcle : String ) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) viewModel.searchMovies(motcle)

    LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        items(movies) { movie ->
            Card(
                Modifier.padding(8.dp) .fillMaxSize(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
                    contentDescription = "Affiche du film",
                    Modifier.fillMaxSize()
                )
                Text(movie.original_title, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)
                Text(movie.release_date, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            }
        }
    }
}