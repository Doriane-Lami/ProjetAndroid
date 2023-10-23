package com.example.tppremireappdoriane


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Films(viewModel: MainViewModel, windowClass: WindowSizeClass, navController: NavController) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) viewModel.getMovies()

    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                items(movies) { movie ->
                    Card(
                        onClick = {
                            navController.navigate("FilmDetails/" + movie.id)
                        },
                        Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
                            contentDescription = "Affiche du film",
                            Modifier.fillMaxSize()
                        )
                        Text(
                            movie.original_title,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            movie.release_date,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                items(movies) { movie ->
                    Card(
                        onClick = {
                            navController.navigate("FilmDetails/" + movie.id)
                        },
                        Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
                            contentDescription = "Affiche du film",
                            Modifier.fillMaxSize()
                        )
                        Text(
                            movie.original_title,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            movie.release_date,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsRecherche(
    viewModel: MainViewModel,
    windowClass: WindowSizeClass,
    navController: NavController,
    motcle: String
) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) viewModel.searchMovies(motcle)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(movies) { movie ->
            Card(
                onClick = {
                    navController.navigate("FilmDetails/" + movie.id)
                },
                Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
                    contentDescription = "Affiche du film",
                    Modifier.fillMaxSize()
                )
                Text(
                    movie.original_title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    movie.release_date,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun FilmDetails(
    tvid: String,
    viewModel: MainViewModel,
    windowClass: WindowSizeClass,
    navController: NavController
) {
    val film by viewModel.film.collectAsState()


    LaunchedEffect(true) {
        viewModel.getFilmdetails(tvid)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w1280" + film.backdrop_path,
                contentDescription = "Affiche de la série",
                Modifier.fillMaxWidth()
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w342" + film.poster_path,
                    contentDescription = "Affiche de la série",
                    Modifier.padding(5.dp)
                    //Modifier.fillMaxSize()
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(3.dp)
                ) {
                    Text(
                        film.title,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        film.release_date,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    var genres = ""

                    film.genres.forEach { genres += it.name + ", " }
                    Text(
                        text = genres,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = " Synopsis",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                film.overview,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 6.dp)
            )
        }
        items(film.credits.cast) { cast ->
            Card(
                Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + cast.profile_path,
                    contentDescription = "Affiche de la série",
                    Modifier.fillMaxSize()
                )
                Text(
                    text = cast.name,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
