package com.example.tppremireappdoriane

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Screen(windowClass: WindowSizeClass, navController: NavController) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PhotoProfil()
                Contacts()
                Spacer(modifier = Modifier.height(20.dp))
                Bouton(navController)
            }

        }

        else -> {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    PhotoProfil()
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Contacts()
                    Spacer(modifier = Modifier.height(20.dp))
                    Bouton(navController)
                }
            }
        }
    }
}
