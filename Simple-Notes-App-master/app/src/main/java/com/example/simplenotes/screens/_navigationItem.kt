package com.example.simplenotes.screens

sealed class _navigationItem(val route:String){

    object Home:_navigationItem("home")
    object AddNote: _navigationItem("add")
    object Profile:_navigationItem("profile")
    object AddImg:_navigationItem("AddImg")

}