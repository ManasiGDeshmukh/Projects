package com.example.simplenotes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplenotes.R

@Composable
fun topbar(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(bottom = 5.dp)
    ){
        //logo
        Image(
            painter = painterResource(R.drawable.ic_baseline_note_24),
            contentDescription = "logo",
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))

        //title
        Text(
            text = "Notes Keeper",
            fontSize = 40.sp
        )
//        Spacer(modifier = Modifier.width(5.dp))
    }
}