package com.example.istory.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.istory.constants.TableNames

@Entity(tableName = TableNames.USER_DATA_TABLE)
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="story_id")
    var id :Int,
    @ColumnInfo(name="last_name")
    var lastName:String,
    @ColumnInfo(name="first_name")
    var firstName:String
)