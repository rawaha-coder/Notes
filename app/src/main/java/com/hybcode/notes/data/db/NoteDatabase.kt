package com.hybcode.notes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hybcode.notes.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
}