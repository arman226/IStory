package com.example.istory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.istory.constants.TableNames
import com.example.istory.db.entity.Story

@Dao
interface StoryDAO {

    @Insert
    suspend fun insertStory (story: Story):Long

    @Update
    suspend fun updateStory (story: Story):Int

    @Query("UPDATE ${TableNames.STORY_DATA_TABLE} SET active=0")
    suspend fun  archiveAll():Int

    @Query("DELETE FROM ${TableNames.STORY_DATA_TABLE}")
    suspend fun clearArchive():Int

    @Query("SELECT * FROM ${TableNames.STORY_DATA_TABLE}")
    fun getAllActiveStories():LiveData<List<Story>>

    @Query("SELECT * FROM ${TableNames.STORY_DATA_TABLE} WHERE active=0")
    fun getArchive():LiveData<List<Story>>

}