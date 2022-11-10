package com.hybcode.notes

import android.app.Application
import androidx.room.Room
import com.hybcode.notes.data.db.NoteDatabase
import com.hybcode.notes.data.repository.NotesRepoRoomImp
import com.hybcode.notes.data.repository.NotesRepository

class NoteApplication: Application() {

    private val DB_NAME = "note_database"

    companion object {
        lateinit var database: NoteDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(this, NoteDatabase::class.java, DB_NAME)
            .build()
    }
}