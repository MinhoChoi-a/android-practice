package com.example.jetpacktrial.data

import com.example.jetpacktrial.NEW_NOTE_ID
import java.util.*

//data means that class is going to have some properties

//data object
data class NoteEntity(
    var id:Int,
    var date: Date,
    var text: String
) {

    constructor(): this(NEW_NOTE_ID, Date(), "")
    constructor(date: Date, text: String): this(NEW_NOTE_ID, date, text)
}