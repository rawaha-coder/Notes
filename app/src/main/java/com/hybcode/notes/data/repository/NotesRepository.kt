package com.hybcode.notes.data.repository

import androidx.lifecycle.LiveData
import com.hybcode.notes.data.model.Note

interface NotesRepository {

    fun getSavedNotes(): LiveData<List<Note>>

    suspend fun saveNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun deleteNotes(notes: List<Note>)

}