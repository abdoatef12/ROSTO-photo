package com.rosto.photopro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rosto.photopro.presentation.screens.HomeScreen
import com.rosto.photopro.presentation.screens.EditorScreen
import com.rosto.photopro.presentation.screens.ProjectsScreen
import com.rosto.photopro.ui.theme.RostoPhotoProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RostoPhotoProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController = navController)
                        }
                        composable("editor/{imageUri}") { backStackEntry ->
                            val imageUri = backStackEntry.arguments?.getString("imageUri") ?: ""
                            EditorScreen(imageUri = imageUri, navController = navController)
                        }
                        composable("projects") {
                            ProjectsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
