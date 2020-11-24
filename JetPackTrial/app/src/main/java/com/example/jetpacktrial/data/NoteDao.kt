package com.example.jetpacktrial.data

import androidx.lifecycle.LiveData
import androidx.room.*

//Data access object
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    //if the bunch of notes already exist, throw away the new ones
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(notes: List<NoteEntity>)

    //LiveData is kind of observer, so the data is updated automatically
    @Query("SELECT * FROM notes ORDER BY date ASC")
    fun getAll(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity? //it is nullable

    @Query("SELECT COUNT(*) from notes")
    fun getCount(): Int

    @Delete
    fun deleteNotes(selectedNotes: ArrayList<NoteEntity>):Int

    @Query("DELETE FROM notes")
    fun deleteAll()
}