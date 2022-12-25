package com.example.simplenotes.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplenotes.model.NoteItem



@Database(entities = [NoteItem::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun NotesDao(): NotesDao

    companion object {
        fun provideDB(
            context: Context,
        ) = Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes"
        ).build()
    }


}