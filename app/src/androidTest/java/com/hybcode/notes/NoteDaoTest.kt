package com.hybcode.notes

import android.content.Context
import android.util.Log
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
import java.util.*

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
        try {
            db = Room.inMemoryDatabaseBuilder(
                context, NoteDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }catch (e: Exception){
            Log.i(this.javaClass.simpleName, e.message ?: "")
        }

       noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertNote() = runBlocking{

        val previousNumberOfNotes = noteDao.getAllNotes().value?.size

        val note = Note(1, "NoteTitle", "Note contents", Date())

        noteDao.insertNote(note)

        val newNumberOfNotes = noteDao.getAllNotes().value?.size

        val changeInNotes = previousNumberOfNotes?.let { newNumberOfNotes?.minus(it) }

        Assert.assertEquals(1, changeInNotes)
    }

    @Test
    fun testGetAllNotes() = runBlocking {
        val note = Note(2, "NoteTitle", "Note contents", Date())

        noteDao.insertNote(note)
        noteDao.deleteNote(note)

        val job = async(Dispatchers.IO) {
            noteDao.getAllNotes()?.let{
                assertThat(it.value).contains(note)
            }
        }
        job.cancelAndJoin()
    }

    @Test
    fun testDeleteNote() = runBlocking {
        val note = Note(3, "NoteTitle", "Note contents", Date())

        noteDao.insertNote(note)
        noteDao.deleteNote(note)

        val job = async(Dispatchers.IO) {
            noteDao.getAllNotes()?.let{
                assertThat(it.value).doesNotContain(note)
            }
        }
        job.cancelAndJoin()
    }

    @Test
    fun testUpdateNote() = runBlocking {
        val note = Note(4, "Title", "Contents", Date())
        noteDao.insertNote(note)

        val updatedNote = Note(id = 4, "updatedTitle", "updatedContents", Date())

        noteDao.updateNote(updatedNote)

        val result = noteDao.getAllNotes().value?.get(updatedNote.id)

        assertThat(result?.title).isEqualTo(updatedNote.title);

    }
}