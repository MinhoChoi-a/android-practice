package com.example.jetpacktrial

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpacktrial.data.NoteEntity
import com.example.jetpacktrial.data.SampleDataProvider

class MainViewModel : ViewModel() {

    //using livedata Api
    //MutableLiveData: it can be changed at runtime
    val noteList = MutableLiveData<List<NoteEntity>>()

    //executed as the classes initialized
    init {
        noteList.value = SampleDataProvider.getNotes()
    }

}