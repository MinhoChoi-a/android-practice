package com.example.jetpacktrial

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacktrial.data.AppDatabase
import com.example.jetpacktrial.data.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//AndroiViewModel: let get context, easily create instance of the database

class EditorViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val currentNote = MutableLiveData<NoteEntity>()

    fun getNoteById(noteId: Int) {
        //retrieve database form the background thread
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val note =  //if get zero(NEW_NOTE_ID), that means it's a new note
                        if(noteId != NEW_NOTE_ID) {
                            database?.noteDao()?.getNoteById(noteId)
                        } else {
                            NoteEntity()
                        }
                currentNote.postValue(note)
                //postValue => cause working from a background thread
            }
        }
    }

    fun updateNote() {
        currentNote.value?.let {
            it.text = it.text.trim()

            if(it.id == NEW_NOTE_ID && it.text.isEmpty()) {
                return
            }

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    if (it.text.isEmpty()) {
                        database?.noteDao()?.deleteNote(it)
                    } else {
                        database?.noteDao()?.insertNote(it)
                    }
                }
            }
        }

    }
}