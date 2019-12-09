package com.example.api_hit

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface TododataDAO

{

    @Query("SELECT * from tododata")
    fun getAll(): List<TodoData>



    @Insert(onConflict = REPLACE)
    fun insert(todoData: TodoData)



    @Delete
    fun delete(todoData: TodoData)


    @Update
    fun update(todoData: TodoData)



    @Query("DELETE from tododata")
    fun deleteAll()
}