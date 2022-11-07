package com.hybcode.notes.presentation

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hybcode.notes.data.Note
import java.io.BufferedReader
import java.io.InputStreamReader

class NoteListViewModel(): ViewModel() {

    companion object {
        private const val FILEPATH = "notes.json"
    }

        fun retrieveNotes(activity : FragmentActivity): MutableList<Note>{
        var noteList = mutableListOf<Note>()
        if (activity.getFileStreamPath(FILEPATH).isFile){
            var reader: BufferedReader? = null
            try {
                val fileInput = activity.openFileInput(FILEPATH)
                reader = BufferedReader(InputStreamReader(fileInput))
                val stringBuilder = StringBuilder()
                for (line in reader.readLine()) stringBuilder.append(line)
                if (stringBuilder.isNotEmpty()){
                    val listType = object : TypeToken<List<Note>>() {}.type
                    noteList = Gson().fromJson(stringBuilder.toString(), listType)
                }
            } catch (e: Exception) {
                reader?.close()
            } finally {
                reader?.close()
            }
        }
        return noteList
    }

    fun deleteNote(index: Int) {

    }

    fun showNote(index: Int) {

    }

}