package com.example.simplenotes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.simplenotes.R


@Composable
fun Profile(navController: NavHostController){

    topbar()

    //content
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp, end = 15.dp, bottom = 70.dp, start = 15.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .background(Color.White)
    ){
        Column(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.person),
                    contentDescription = "profile",
                    Modifier.heightIn(100.dp).width(100.dp)

                )
                Column {
                    Spacer(modifier = Modifier
                        .height(55.dp)
                        .width(10.dp))
                    Text(text = "Name", fontSize = 30.sp)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxHeight()) {
                    PutCards(text = "E-mail           :")
                    Spacer(modifier = Modifier.height(5.dp))
                    PutCards(text = "Contact        :")
                    Spacer(modifier = Modifier.height(5.dp))
                    PutCards(text = "Occupation :")
                    Spacer(modifier = Modifier.height(5.dp))
                    PutCards(text = "Institute       :")
                    Spacer(modifier = Modifier.height(5.dp))
                    PutCards(text = "Grade           :")
                    Spacer(modifier = Modifier.height(5.dp))
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(Modifier.fillMaxHeight()) {
                    Text(text = "xyz@abc.com",fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "XXXXX XXXXX",fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Student ",fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(22.dp))
                    Text(text = "XYZ College",fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(22.dp))
                    Text(text = "S.Y. B.Tech",fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
    Menu(navController = navController)
}


@Composable
fun PutCards(text: String){
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        modifier = Modifier
    ) {
        Text(text = text, modifier = Modifier.padding(10.dp))
    }
}