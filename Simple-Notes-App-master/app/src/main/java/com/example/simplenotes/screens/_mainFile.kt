package com.example.simplenotes.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplenotes.screens.imageImport.MainScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = _navigationItem.Home.route) {
        composable(_navigationItem.Home.route) {
            Home(navController)
        }

        composable(_navigationItem.AddNote.route) {
            AddNote(navController)
        }


        composable(_navigationItem.Profile.route) {
            Profile(navController)
        }

        composable(_navigationItem.AddImg.route) {
            MainScreen(navController)
        }
    }
}