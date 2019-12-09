package com.example.api_hit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "tododata")
 class TodoData(
    @PrimaryKey(autoGenerate = true) var id:Int?=null,

    @ColumnInfo(name = "Title") var title: String="",

    @ColumnInfo(name = "Description") var description: String=""

    ):Serializable

