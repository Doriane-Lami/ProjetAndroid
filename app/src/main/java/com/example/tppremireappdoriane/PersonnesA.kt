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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun PersonnesAffiche(
    viewModel: MainViewModel,
    windowClass: WindowSizeClass,
    navController: NavController
) {
    val personnes by viewModel.personnes.collectAsState()

    if (personnes.isEmpty()) viewModel.getPersonnes()

    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                items(personnes) { personne ->
                    Card(
                        Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + personne.profile_path,
                            contentDescription = "Affiche de la série",
                            Modifier.fillMaxSize()
                        )
                        Text(
                            personne.name,
                            style = MaterialTheme.typography.headlineSmall,
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
                items(personnes) { personne ->
                    Card(
                        Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + personne.profile_path,
                            contentDescription = "Affiche de la série",
                            Modifier.fillMaxSize()
                        )
                        Text(
                            personne.name,
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PersonneRecherche(
    viewModel: MainViewModel,
    windowClass: WindowSizeClass,
    navController: NavController,
    motcle: String
) {
    val personnes by viewModel.personnes.collectAsState()

    if (personnes.isEmpty()) viewModel.searchPersonnes(motcle)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(personnes) { personne ->
            Card(
                Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + personne.profile_path,
                    contentDescription = "Affiche de la série",
                    Modifier.fillMaxSize()
                )
                Text(
                    personne.name,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
