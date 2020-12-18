package com.example.istory.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.content.contentValuesOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.istory.R
import com.example.istory.adapter.StoryListAdapter
import com.example.istory.databinding.FragmentListBinding
import com.example.istory.db.entity.Story
import com.example.istory.db.factory.IStoryDatabase
import com.example.istory.repository.StoryRepository
import com.example.istory.ui.SaveOrUpdateStory
import com.example.istory.viewmodel.StoryViewModel
import com.example.istory.viewmodel.StoryViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding:FragmentListBinding
    private lateinit var storyViewModel: StoryViewModel

      override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        val dao= IStoryDatabase.getInstance(container!!.context).storyDAO
        val repository = StoryRepository(dao)
        val factory = StoryViewModelFactory(repository)
        storyViewModel= ViewModelProvider(viewModelStore, factory).get(StoryViewModel::class.java)
        binding.myViewModel = storyViewModel
        binding.lifecycleOwner=this
        initializeStoriesRecyclerView()

          //move to story creating page
        binding.createStoryButton.setOnClickListener{
         moveToSaveOrUpdateStory()
        }

        return binding.root
    }

    //this is a function that observes the list of stories
    private fun displayStoriesList(){
        storyViewModel.stories.observe(viewLifecycleOwner, Observer {
            //bind your custom adapter to recycler view and pass the stories as parameter
            binding.storyRecyclerView.adapter= StoryListAdapter(it, {
                selectedItem-> onStoryClicked(selectedItem)
            });
        })

    }


    private fun initializeStoriesRecyclerView(){
        binding.storyRecyclerView.layoutManager = LinearLayoutManager(context)
        displayStoriesList()

    }

    private fun onStoryClicked(story: Story){
//        Toast.makeText(this.context, story.title, Toast.LENGTH_SHORT).show()
        storyViewModel.initStoryToBeUpdated(story)
        moveToSaveOrUpdateStory()
    }

    private fun moveToSaveOrUpdateStory(){
        var i= Intent(context,SaveOrUpdateStory::class.java)
        startActivity(i)
    }


}