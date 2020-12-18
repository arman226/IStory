package com.example.istory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.istory.R
import com.example.istory.adapter.StoryListAdapter
import com.example.istory.databinding.FragmentListBinding
import com.example.istory.db.entity.Story
import com.example.istory.viewmodel.StoryViewModel


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding:FragmentListBinding
    private lateinit var storyViewModel: StoryViewModel
    private lateinit var saveOrUpdateFragment: SaveOrUpdateFragment

      override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        storyViewModel= ViewModelProviders.of(requireActivity()).get(StoryViewModel::class.java)
        binding.myViewModel = storyViewModel
        binding.lifecycleOwner=requireActivity()
        initializeStoriesRecyclerView()

          //move to story creating page
        binding.createStoryButton.setOnClickListener{
         moveToSaveOrUpdateStory()
        }

          saveOrUpdateFragment= SaveOrUpdateFragment()

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
        storyViewModel.initStoryToBeUpdated(story)
        moveToSaveOrUpdateStory()
    }

    private fun moveToSaveOrUpdateStory(){
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,saveOrUpdateFragment)
            commit()
        }
    }


}