package com.hybcode.notes.repository

import com.hybcode.notes.data.model.Note

interface NotesRepository {

    fun allNotes(): List<Note>

    fun deleteNote(index: Int)

    fun addNote(note: Note): Int?
}