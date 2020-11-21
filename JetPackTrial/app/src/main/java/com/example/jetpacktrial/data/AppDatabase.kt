package com.example.jetpacktrial.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//extend RoomDatabase class

@Database(entities=[NoteEntity::class],version=1, exportSchema = false) //which entity is a part of the data structure, exportSchema=false -> don't bother generating the documentation
@TypeConverters(DateConverter::class)

abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao?

    companion object {
        private var INSTANCE: AppDatabase?= null

        fun getInstance(context: Context):AppDatabase? {

            if(INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                    "plainnotes.db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }

}