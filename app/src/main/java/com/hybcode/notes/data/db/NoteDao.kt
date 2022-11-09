package com.hybcode.notes.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hybcode.notes.data.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY release_date")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNote(noteId: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Delete
    suspend fun deleteNotes(notes: List<Note>)
}