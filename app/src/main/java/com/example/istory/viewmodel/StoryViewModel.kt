package com.example.istory.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.istory.db.entity.Story
import com.example.istory.repository.StoryRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

private const val SAVE ="Save"
private const val UPDATE = "Update"

class StoryViewModel(private val storyRepository: StoryRepository): ViewModel(), Observable {

    val stories = storyRepository.stories

    val archives = storyRepository.archiveStories

    private lateinit var storyToBeUpdatedOrDeleted:Story

    @Bindable
    val inputTitle=MutableLiveData<String>();

    @Bindable
    val inputContent=MutableLiveData<String>();

    @Bindable
    val saveOrUpdateButtonText= MutableLiveData<String>();

    var isUpdate= false

    init {
        saveOrUpdateButtonText.value = SAVE
    }

    fun saveOrUpdate(){
        if(isUpdate){
            storyToBeUpdatedOrDeleted.title= inputTitle.value!!
            storyToBeUpdatedOrDeleted.content= inputContent.value!!
            update(storyToBeUpdatedOrDeleted)
            saveOrUpdateButtonText.value=SAVE
            clearFields()
            isUpdate=false

        }
        else{
        val name =inputTitle.value!!
        //temp
        val content = "just for testing sake"
        val date:Date = Date()
        val active = true;
        insert(Story(0,name,content,date,active))
        //clear the fields
        clearFields()}
    }

    fun clearList(){
        deleteAll()
    }


    fun insert(story: Story):Job=
        viewModelScope.launch {
        storyRepository.insertStory(story)
    }

    fun update (story: Story):Job=
        viewModelScope.launch {
            storyRepository.updateStory(story)
        }

    fun deleteAll ():Job=
        viewModelScope.launch {
            storyRepository.archiveAll()
        }

    fun clearFields(){
        inputTitle.value=""
        inputContent.value=""
    }

    fun initStoryToBeUpdated(story: Story){
        inputTitle.value= story.title
        inputContent.value = story.content
        isUpdate= true
        saveOrUpdateButtonText.value = UPDATE
        storyToBeUpdatedOrDeleted = story
    }




    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}