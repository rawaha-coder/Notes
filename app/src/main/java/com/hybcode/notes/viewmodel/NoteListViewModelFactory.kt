package com.hybcode.notes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NoteListViewModelFactory(private val app: Application)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(
                NoteListViewModel::class.java)) {
            return NoteListViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}