package com.hybcode.notes.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hybcode.notes.NoteApplication
import com.hybcode.notes.data.model.Note
import com.hybcode.notes.data.repository.NotesRepoRoomImp
import com.hybcode.notes.data.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteListViewModel(private val repository: NotesRepository,
                        application: NoteApplication):
    AndroidViewModel(application) {

    private val _noteList = MutableLiveData<List<Note>>()
    val noteList: LiveData<List<Note>> get() = _noteList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _noteList.value = repository.getSavedNotes().value
        }
    }

    fun saveNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun clearNotes(notes: List<Note>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNotes(notes)
        }
    }

}