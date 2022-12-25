package com.example.simplenotes

//import androidx.lifecycle.LiveData
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.simplenotes.database.NotesDao
import com.example.simplenotes.database.NotesDatabase
import com.example.simplenotes.model.NoteItem
import com.example.simplenotes.ui.theme.NotesBackGround
import com.example.simplenotes.ui.theme.NotesViewBackGround
import com.example.simplenotes.ui.theme.Purple700
import com.example.simplenotes.ui.theme.SimpleNotesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplenotes.screens.*
import com.example.simplenotes.screens.imageImport.MainScreen
//import com.example.simplenotes.BinImage as BinImage

class MainActivity : ComponentActivity() {


    private lateinit var provideDB: NotesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_notesview)
        provideDB = NotesDatabase.provideDB(applicationContext).NotesDao()
        setContent {
            SimpleNotesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SimpleNotesTheme {
                        AppScaffold(dbHandler = provideDB, this::editNote, this::delete)
                    }
                }
            }
        }

    }

    private fun editNote(noteTitle: String, noteBody: String, noteId: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (noteId == 0) {
                provideDB.insertNote(NoteItem(title = noteTitle, note = noteBody))
            } else {
                provideDB.updateNote(NoteItem(id = noteId, note = noteBody, title = noteTitle))
            }
        }
    }

    private fun delete(noteId: Int) {
        lifecycleScope.launch {
            provideDB.delete(noteId)
        }


    }
}


@Composable
fun NotesList(
    notesList: LiveData<List<NoteItem>>,
    currentNoteTitle: MutableState<String>,
    currentNoteBody: MutableState<String>,
    currentNoteId: MutableState<Int>,
    showDialog: MutableState<Boolean>,
    showDeleteDialog: MutableState<Boolean>
) {
    val notes = notesList.observeAsState()
    LazyRow(
        modifier = Modifier.padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        itemsIndexed(items = notes.value ?: listOf(), itemContent = { index, item ->
            Column(
                modifier = Modifier
                    .background(color = NotesBackGround)
                    .height(120.dp)
                    .width(120.dp)
                    .padding(12.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                currentNoteTitle.value = notes.value?.get(index)?.title ?: ""
                                currentNoteBody.value = notes.value?.get(index)?.note ?: ""
                                currentNoteId.value = notes.value?.get(index)?.id ?: 0
                                showDialog.value = true
                            },
                            onLongPress = {
                                currentNoteId.value = notes.value?.get(index)?.id ?: 0
                                showDeleteDialog.value = true
                            }
                        )
                    }
            ) {
                Text(text = item.title.toString(), fontWeight = FontWeight.Bold)
                Text(text = item.note.toString(), modifier = Modifier.padding(12.dp))

            }

        })
    }
}


@Composable
fun DeleteDialog(
    showDialog: MutableState<Boolean>,
    noteId: MutableState<Int>,
    delete: (Int) -> Unit,
    hasBinDrawableChanged: MutableState<Boolean>
) {
    MaterialTheme {
        Column {
            val openDialog = remember { showDialog }
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    title = { Text(text = "Delete Note") },
                    text = { Text(text = "Do you want to delete this note? ") },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                                delete.invoke(noteId.value)
                                if (!hasBinDrawableChanged.value) {
                                    hasBinDrawableChanged.value = true
                                }
                            }
                        ) {
                            Text("Yes")
                        }
                    }

                )
            }
        }
    }
}


@Composable
fun NoteDialog(
    showDialog: MutableState<Boolean>,
    noteTitle: MutableState<String>? = null,
    noteBody: MutableState<String>? = null,
    noteId: MutableState<Int>? = null,
    onClick: (String, String, Int) -> Unit

) {
    val shouldShow = remember { showDialog }
    if (shouldShow.value) {
        Dialog(onDismissRequest = {}) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Box(contentAlignment = Alignment.Center) {
                    val titleState =
                        remember { mutableStateOf(TextFieldValue(noteTitle?.value ?: "")) }
                    val noteState =
                        remember { mutableStateOf(TextFieldValue(noteBody?.value ?: "")) }
                    val context = LocalContext.current

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                    ) {
                        Text(text = "Note Title")

                        TextField(
                            value = titleState.value,
                            onValueChange = { titleState.value = it },
                            maxLines = 1,
                            singleLine = true
                        )
                        Text(text = "Note Body", modifier = Modifier.padding(top = 12.dp))

                        TextField(
                            value = noteState.value,
                            onValueChange = { noteState.value = it },
                            maxLines = 20,
                            modifier = Modifier
                                .height(500.dp)
                                .padding(bottom = 12.dp)

                        )
                    }

                    Button(
                        onClick = {
                            if (titleState.value.text.isEmpty() || noteState.value.text.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please add note title and note body",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                return@Button
                            }

                            onClick.invoke(
                                titleState.value.text,
                                noteState.value.text,
                                noteId?.value ?: 0
                            )
                            noteTitle?.value = ""
                            noteBody?.value = ""
                            noteId?.value = 0
                            shouldShow.value = false

                        },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}


@Composable
fun AppScaffold(
    dbHandler: NotesDao,
    editNote: (String, String, Int) -> Unit,
    delete: (Int) -> Unit,
) {
    val notes = dbHandler.getAllNotes()
    val shouldShowNoteDialog = remember { mutableStateOf(false) }
    val shouldShowDeleteDialog = remember { mutableStateOf(false) }
    val hasBinDrawableChanged = remember { mutableStateOf(false) }
    val currentNoteTitle = remember { mutableStateOf("") }
    val currentNoteBody = remember { mutableStateOf("") }
    val currentNoteId = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            topbar()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(NotesViewBackGround)
            ) {
                NotesList(
                    notesList = notes,
                    currentNoteTitle = currentNoteTitle,
                    currentNoteBody = currentNoteBody,
                    currentNoteId = currentNoteId,
                    showDialog = shouldShowNoteDialog,
                    showDeleteDialog = shouldShowDeleteDialog
                )

                NoteDialog(showDialog = shouldShowNoteDialog, onClick = editNote)
                DeleteDialog(
                    showDialog = shouldShowDeleteDialog,
                    noteId = currentNoteId,
                    delete = delete,
                    hasBinDrawableChanged = hasBinDrawableChanged
                )

            }
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                shouldShowNoteDialog.value = !shouldShowNoteDialog.value
                currentNoteBody.value = ""
                currentNoteTitle.value = ""
            }) {
                Image(painter = painterResource(R.drawable.add), contentDescription = "add")
            }
        }
    )

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = _navigationItem.Home.route) {
        composable(_navigationItem.Home.route) {
            Home(navController)
        }
        composable(_navigationItem.Profile.route) {
            Profile(navController)
        }

        composable(_navigationItem.AddImg.route) {
            MainScreen(navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SimpleNotesPreview() {
    SimpleNotesTheme {
        Navigation()
    }
}