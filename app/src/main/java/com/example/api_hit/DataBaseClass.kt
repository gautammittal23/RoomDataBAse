package com.example.api_hit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TodoData::class),version = 1)
abstract class DataBaseClass:RoomDatabase() {

    abstract fun Dao(): TododataDAO

    companion object {
        fun getInstance(context: Context): DataBaseClass {
          return Room.databaseBuilder(
                    context.getApplicationContext(),
                    DataBaseClass::class.java, "todo.db"
                ).allowMainThreadQueries() .build()
            }

        }
}