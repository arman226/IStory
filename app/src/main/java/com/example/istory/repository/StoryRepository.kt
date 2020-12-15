package com.example.istory.repository

import com.example.istory.db.dao.StoryDAO
import com.example.istory.db.entity.Story

class StoryRepository(private var dao:StoryDAO) {

    val stories= dao.getAllActiveStories()

    val archiveStories = dao.getArchive()

    suspend fun insertStory(story: Story):Long{
        return dao.insertStory(story)
    }

    suspend fun updateStory(story: Story):Int{
        return dao.updateStory(story)
    }

    suspend fun archiveAll(){
        dao.archiveAll()
    }

    suspend fun clearArchive(){
        dao.clearArchive()
    }


}