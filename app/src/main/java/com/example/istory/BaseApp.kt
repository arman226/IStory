package com.example.istory

import android.app.Application
import android.widget.Toast
import com.example.istory.db.factory.IStoryDatabase
import com.example.istory.repository.StoryRepository
import com.example.istory.viewmodel.StoryViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApp:Application() {

    lateinit var factory: StoryViewModelFactory
    override fun onCreate() {
        super.onCreate()
        val dao= IStoryDatabase.getInstance(this).storyDAO
        val repository = StoryRepository(dao)
        factory = StoryViewModelFactory(repository)}

    fun getVMFactory():StoryViewModelFactory{
        return factory
    }


}