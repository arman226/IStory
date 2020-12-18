package com.example.istory.di.module

import com.example.istory.repository.StoryRepository
import dagger.Binds
import dagger.Module

@Module
abstract class StoryRepositoryModule {

    @Binds
    abstract fun providesStoryRepository(repository: StoryRepository):StoryRepository

}