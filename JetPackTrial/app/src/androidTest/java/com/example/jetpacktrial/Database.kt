package com.example.jetpacktrial

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpacktrial.data.AppDatabase
import com.example.jetpacktrial.data.NoteDao
import com.example.jetpacktrial.data.NoteEntity
import com.example.jetpacktrial.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var dao: NoteDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        //has to be not null
        dao = database.noteDao()!!
    }

    @Test
    fun createNotes() {
        dao.insertAll(SampleDataProvider.getNotes())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getNotes().size)

        // Context of the app under test.
        //val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        //assertEquals("com.example.jetpacktrial", appContext.packageName)
    }

    @Test
    fun createNote() {

        var note = NoteEntity()

        note.text = "minho choi"
        note.date = Date()

        dao.insertNote(note)
        var getNote = dao.getNoteById(1)

        assertEquals(getNote?.date ?:0, 1)

        // Context of the app under test.
        //val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        //assertEquals("com.example.jetpacktrial", appContext.packageName)
    }

    @After
    fun closeDb() {
        database.close()
    }
}