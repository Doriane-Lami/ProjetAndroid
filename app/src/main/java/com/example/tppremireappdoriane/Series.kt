package com.example.tppremireappdoriane


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Series(viewModel: MainViewModel, windowClass: WindowSizeClass, navController: NavController) {
    val tvs by viewModel.tvs.collectAsState()

    if (tvs.isEmpty()) viewModel.getSeries()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(tvs) { tv ->
            Card(
                onClick = {
                    navController.navigate("SerieDetails/" + tv.id)
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
                Text(
                    tv.name,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    tv.first_air_date,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesRecherche(
    viewModel: MainViewModel,
    windowClass: WindowSizeClass,
    navController: NavController,
    motcle: String
) {
    val tvs by viewModel.tvs.collectAsState()

    if (tvs.isEmpty()) viewModel.searchSeries(motcle)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
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
                Text(
                    tv.name,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    tv.first_air_date,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun SerieDetails(
    tvid: String,
    viewModel: MainViewModel,
    windowClass: WindowSizeClass,
    navController: NavController
) {
    val tv by viewModel.tv.collectAsState()


    LaunchedEffect(true) {
        viewModel.getTVdetails(tvid)
    }

    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Row() {


                Column(
                    modifier = Modifier
                        .verticalScroll(
                            rememberScrollState()
                        )
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w1280" + tv.backdrop_path,
                        contentDescription = "Affiche de la série",
                        Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w342" + tv.poster_path,
                            contentDescription = "Affiche de la série",
                            //Modifier.fillMaxSize()
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                tv.name,
                                style = MaterialTheme.typography.headlineMedium,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                tv.first_air_date,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                            var genres = ""

                            tv.genres.forEach { genres += it.name + ", " }
                            Text(
                                text = genres,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Synopsis",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Left
                    )
                    Text(
                        tv.overview,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                }
            }
            Row() {


                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(tv.credits.cast.take(10)) { cast ->
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
        }
    }
}




