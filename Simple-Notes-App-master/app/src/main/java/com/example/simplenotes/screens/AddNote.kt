package com.example.simplenotes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.simplenotes.R

@Composable
fun AddNote(navController: NavHostController){

    topbar()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp, end = 15.dp, bottom = 70.dp, start = 15.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            var title by remember { mutableStateOf(TextFieldValue("")) }
            var content by remember { mutableStateOf(TextFieldValue("")) }
            Row(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { newText: TextFieldValue -> title = newText},
                    placeholder = { Text(text = "Title") },
                    singleLine = true,
                    keyboardActions= KeyboardActions() ,
                    keyboardOptions= KeyboardOptions()

                )
                Spacer(modifier = Modifier.width(3.dp))
                //add Image or file
                Button(onClick =  { navController.navigate("AddImg")  }) {
                    Image(
                        painter = painterResource(R.drawable.ic_baseline_attach_file_24),
                        contentDescription = "attach img"
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = content,
                onValueChange = { newText -> content = newText},
                placeholder = { Text(text = "Add the note here...") },
                singleLine = false,

                keyboardActions= KeyboardActions() ,
                keyboardOptions= KeyboardOptions(),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(400.dp)
            )
        }
    }


    Menu(navController = navController)
}