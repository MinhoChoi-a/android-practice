package com.example.jetpacktrial.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpacktrial.NEW_NOTE_ID
import kotlinx.android.parcel.Parcelize
import java.util.*

//data means that class is going to have some properties

//data object

//after add the declaration on build.gradle
@Parcelize
@Entity(tableName = "notes")
data class NoteEntity(
    //SQLite will generate unique numeric value
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var date: Date,
    var text: String
) : Parcelable {

    constructor(): this(NEW_NOTE_ID, Date(), "")
    constructor(date: Date, text: String): this(NEW_NOTE_ID, date, text)
}