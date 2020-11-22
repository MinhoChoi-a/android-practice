package com.example.jetpacktrial

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacktrial.data.AppDatabase
import com.example.jetpacktrial.data.NoteEntity
import com.example.jetpacktrial.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//class MainViewModel : ViewModel() {

    //to get a reference to database

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    //using livedata Api
    //MutableLiveData: it can be changed at runtime
    //val noteList = MutableLiveData<List<NoteEntity>>()

    //get LiveData as a reference
    val noteList = database?.noteDao()?.getAll()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleNotes = SampleDataProvider.getNotes()
                database?.noteDao()?.insertAll(sampleNotes)
            }
        }
    }


    //-> don't need init
    //executed as the classes initialized
    //init {
//        noteList.value = SampleDataProvider.getNotes()
//    }

}