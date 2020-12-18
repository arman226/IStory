package com.example.istory.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.istory.R
import com.example.istory.databinding.ActivitySaveOrUpdateStoryBinding
import com.example.istory.db.factory.IStoryDatabase
import com.example.istory.repository.StoryRepository
import com.example.istory.viewmodel.StoryViewModel
import com.example.istory.viewmodel.StoryViewModelFactory
import kotlinx.android.synthetic.main.activity_save_or_update_story.*
import kotlinx.android.synthetic.main.content_main.*

class SaveOrUpdateStory : AppCompatActivity() {
    private lateinit var binding:ActivitySaveOrUpdateStoryBinding
    private lateinit var storyViewModel: StoryViewModel
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_or_update_story)
        setBinding()
        initUI()


        Toast.makeText(this, "isupdated"+ storyViewModel.isUpdate, Toast.LENGTH_SHORT).show()
    }

    private fun initUI(){
        toolbar= findViewById(R.id.save_or_update_toolbar)
        setSupportActionBar(toolbar)
    }

    private fun setBinding(){
        binding= DataBindingUtil.setContentView(this, R.layout.activity_save_or_update_story)
        val dao= IStoryDatabase.getInstance(application).storyDAO
        val repository = StoryRepository(dao)
        val factory = StoryViewModelFactory(repository)
        storyViewModel= ViewModelProvider(this, factory).get(StoryViewModel::class.java)
        binding.myViewModel = storyViewModel
        binding.lifecycleOwner= this
    }

    override fun onBackPressed() {
        super.onBackPressed()
        storyViewModel.isUpdate= false;
    }

}