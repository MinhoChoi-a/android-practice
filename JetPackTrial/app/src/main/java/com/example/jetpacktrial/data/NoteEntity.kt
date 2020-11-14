package com.example.jetpacktrial.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpacktrial.NEW_NOTE_ID
import java.util.*

//data means that class is going to have some properties

//data object

@Entity(tableName = "notes")
data class NoteEntity(
    //SQLLite will generate unique numeric value
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var date: Date,
    var text: String
) {

    constructor(): this(NEW_NOTE_ID, Date(), "")
    constructor(date: Date, text: String): this(NEW_NOTE_ID, date, text)
}