package com.example.tppremireappdoriane

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PhotoProfil(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(id = R.drawable.chat),
            contentDescription = "photo de profil",
            Modifier
                .clip(CircleShape)
                .size(250.dp)
        )
        Text(
            text = "Doriane Lami", style = MaterialTheme.typography.headlineLarge
        )
    }

}

@Composable
fun Contacts(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Elève ingénieur ISIS", style = MaterialTheme.typography.headlineSmall)
        Text(text = "doriane.lami@etud.univ-jfc.fr")
        Text(text = "Ceci est mon Linkedin")
    }

}


@Composable
fun Bouton(navController: NavController) {
    Button(onClick = { navController.navigate("Films") }) {
        Text(text = "Demarrer")
    }
}