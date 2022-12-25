package com.example.simplenotes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.simplenotes.R


@Composable
fun Menu(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(start = 16.dp, top = 700.dp)
    ) {
        Row() {
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier
                    .padding(end = 16.dp)

            ) {
                Image(painter = painterResource(R.drawable.home), contentDescription = "home")
            }


            Button(onClick = {
                navController.navigate("profile")
            }) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "profile"
                )
            }


        }
    }
}