package com.hybcode.notes

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.hybcode.notes.data.db.NoteDao
import com.hybcode.notes.data.db.NoteDatabase
import com.hybcode.notes.data.model.Note
import kotlinx.coroutines.*
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NoteDaoTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var noteDao: NoteDao
    private lateinit var db: NoteDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NoteDatabase::class.java)
           .allowMainThreadQueries()
           .build()
       noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertNote() = runBlocking{

        val previousNumberOfNotes = noteDao.getAllNotes().size

        val note = Note(1, "NoteTitle", "Note contents", "Date")

        noteDao.insertNote(note)

        val newNumberOfNotes = noteDao.getAllNotes().size

        val changeInNotes = newNumberOfNotes - previousNumberOfNotes

        Assert.assertEquals(1, changeInNotes)
    }

    @Test
    fun testGetAllNotes() = runBlocking {
        val note = Note(2, "NoteTitle", "Note contents", "Date")

        noteDao.insertNote(note)
        noteDao.deleteNote(note)

        val job = async(Dispatchers.IO) {
            noteDao.getAllNotes().apply{
                assertThat(this).contains(note)
            }
        }
        job.cancelAndJoin()
    }

    @Test
    fun testDeleteNote() = runBlocking {
        val note = Note(3, "NoteTitle", "Note contents", "Date")

        noteDao.insertNote(note)
        noteDao.deleteNote(note)

        val job = async(Dispatchers.IO) {
            noteDao.getAllNotes().apply{
                assertThat(this).doesNotContain(note)
            }
        }
        job.cancelAndJoin()
    }

    @Test
    fun testUpdateNote() = runBlocking {
        val note = Note(4, "Title", "Contents", "Date")
        noteDao.insertNote(note)

        val updatedNote = Note(id = 4, "updatedTitle", "updatedContents", "updatedDate")

        noteDao.updateNote(updatedNote)

        val result = noteDao.getNote(updatedNote.id)

        assertThat(result.title).isEqualTo(updatedNote.title);

    }
}