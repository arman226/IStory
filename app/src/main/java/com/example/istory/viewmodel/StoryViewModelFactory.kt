package com.example.istory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.istory.repository.StoryRepository
import java.lang.IllegalArgumentException

class StoryViewModelFactory(private val repository: StoryRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StoryViewModel::class.java)){
            return StoryViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown View Model")
    }
}