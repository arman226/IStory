package com.example.istory.di.module

import com.example.istory.db.dao.StoryDAO
import com.example.istory.db.factory.IStoryDatabase
import dagger.Module
import dagger.Provides

@Module
class StoryDaoModule {


    @Provides
    fun provideStoryDAO(iStoryDatabase: IStoryDatabase):StoryDAO{
        return iStoryDatabase.storyDAO
    }

}