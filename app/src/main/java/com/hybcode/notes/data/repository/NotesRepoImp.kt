package com.hybcode.notes.data.repository

import androidx.lifecycle.LiveData
import com.hybcode.notes.NoteApplication
import com.hybcode.notes.data.db.NoteDao
import com.hybcode.notes.data.model.Note

class NotesRepoRoomImp: NotesRepository {

    private val noteDao: NoteDao by lazy {
        NoteApplication.database.noteDao()
    }
    private val allNotes by lazy {
        noteDao.getAllNotes()
    }

    override fun getSavedNotes(): LiveData<List<Note>> = allNotes

    override suspend fun saveNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun deleteNotes(notes: List<Note>) {
        noteDao.deleteNotes(notes)
    }

}