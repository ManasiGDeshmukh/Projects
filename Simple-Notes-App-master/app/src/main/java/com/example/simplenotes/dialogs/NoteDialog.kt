package com.example.simplenotes.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.simplenotes.R
import com.example.simplenotes.R.*
import com.example.simplenotes.model.NoteItem
import java.time.Instant
import java.time.format.DateTimeFormatter


class NoteDialog (private val noteListener: AddNoteListener , private val note: NoteItem?): DialogFragment() {

    private var editMode: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragement_container = container
        val rootview = layoutInflater.inflate(layout.dialog, fragement_container, false)

        val button = rootview.findViewById<Button>(R.id.submitbutton)
        val noteText = rootview.findViewById<EditText>(R.id.editTextNote)
        val noteTitle = rootview.findViewById<EditText>(R.id.editTextTitle)

        note?.let {
            editMode = true
            noteTitle.setText(it.title)
            noteText.setText(it.note)
        }

        if (editMode){
            button.text = getString(string.save)
        }

        button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()

            if (noteTitle.text.toString().isEmpty() || noteText.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Please provide a note title and note body", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (editMode && note != null){
                noteListener.editNote(noteText.text.toString(), noteTitle.text.toString(), note.id!!)
                return@setOnClickListener
            }

            val note = NoteItem(
                title = noteTitle.text.toString(),
                note = noteText.text.toString(),
             //   timeStamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()
            )
            noteListener.addNote(note)

        }

        return rootview

    }


}

interface AddNoteListener {
    fun addNote(note: NoteItem)
    fun editNote(noteTitle: String, noteBody: String, noteId: Int)
}