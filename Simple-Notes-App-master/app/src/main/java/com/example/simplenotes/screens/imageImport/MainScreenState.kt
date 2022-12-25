package com.example.simplenotes.screens.imageImport

import android.net.Uri

data class MainScreenState(
    val listOfSelectedImages:List<Uri> = emptyList()
)
