package com.hybcode.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hybcode.notes.NoteApplication
import com.hybcode.notes.data.repository.NotesRepository

class NoteListViewModelFactory(
    private val repository: NotesRepository,
    private val application: NoteApplication)
    : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(
                NoteListViewModel::class.java)) {

            return NoteListViewModel(repository, application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}