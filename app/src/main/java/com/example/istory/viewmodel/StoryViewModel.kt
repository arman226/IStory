package com.example.istory.viewmodel

import android.widget.Toast
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
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

private const val SAVE ="Save"
private const val UPDATE = "Update"

class StoryViewModel

    @Inject
    constructor(private val storyRepository: StoryRepository): ViewModel(), Observable {

    val stories = storyRepository.stories

    val archives = storyRepository.archiveStories

    private lateinit var storyToBeUpdatedOrDeleted:Story

    @Bindable
    val inputTitle=MutableLiveData<String>();

    @Bindable
    val inputContent=MutableLiveData<String>();

    @Bindable
    val saveOrUpdateButtonText= MutableLiveData<String>();

    var isUpdate= MutableLiveData<Boolean>();

    init {
        saveOrUpdateButtonText.value = SAVE
        isUpdate.value = false
    }

    fun saveOrUpdate(){
        if(isUpdate.value!!){
            storyToBeUpdatedOrDeleted.title= inputTitle.value!!
            storyToBeUpdatedOrDeleted.content= inputContent.value!!
            update(storyToBeUpdatedOrDeleted)
            saveOrUpdateButtonText.value=SAVE
            clearFields()
            isUpdate.value=false

        }
        else{
        val name =inputTitle.value!!
        //temp
        val content = inputContent.value!!
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
        isUpdate.value= false
        saveOrUpdateButtonText.value = SAVE

    }

    fun initStoryToBeUpdated(story: Story){
        inputTitle.value= story.title
        inputContent.value = story.content
        isUpdate.value= true
        saveOrUpdateButtonText.value = UPDATE
        storyToBeUpdatedOrDeleted = story
    }




    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}