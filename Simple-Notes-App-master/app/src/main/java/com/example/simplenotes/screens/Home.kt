package com.example.simplenotes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.simplenotes.database.NotesDao


@Composable
fun Home(navController: NavHostController) {


    topbar()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .wrapContentHeight(Alignment.CenterVertically)
    ) {


    }
    Menu(navController = navController)
}