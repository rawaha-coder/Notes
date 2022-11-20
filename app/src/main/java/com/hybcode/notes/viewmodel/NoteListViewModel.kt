package com.hybcode.notes.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hybcode.notes.data.db.NoteDao
import com.hybcode.notes.data.db.NoteDatabase
import com.hybcode.notes.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteListViewModel(app: Application): ViewModel() {

    var noteList : LiveData<List<Note>>
    private val noteDao: NoteDao

    init {
        noteDao = NoteDatabase.getInstance(app).noteDao()
        noteList = noteDao.getAllNotes()
    }

    fun saveNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.updateNote(note)
        }
    }

    fun deleteNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }

    fun deleteNotesList(list: List<Note>) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteNotes(list)
        }
    }
}