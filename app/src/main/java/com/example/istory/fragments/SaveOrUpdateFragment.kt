package com.example.istory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.istory.R
import com.example.istory.databinding.FragmentCalendarBinding
import com.example.istory.databinding.FragmentSaveOrUpdateBinding
import com.example.istory.viewmodel.StoryViewModel


class SaveOrUpdateFragment : Fragment(R.layout.fragment_save_or_update) {
    lateinit var binding: FragmentSaveOrUpdateBinding
    private lateinit var storyViewModel: StoryViewModel
    private lateinit var listFragment: ListFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_save_or_update, container, false);
        storyViewModel= ViewModelProviders.of(requireActivity()).get(StoryViewModel::class.java)
        binding.myViewModel = storyViewModel
        binding.lifecycleOwner=requireActivity()
        storyViewModel.isUpdate.observe(viewLifecycleOwner, Observer{
            if(it){
                binding.updateOrCreateLabel.text = "Update Story"
            }
            else {
                binding.updateOrCreateLabel.text= "Create Story"
            }
        })

        listFragment= ListFragment()

        binding.cancelStoryButton.setOnClickListener{
            storyViewModel.clearFields()
           backToList()
        }

        binding.submitStoryButton.setOnClickListener(){
            storyViewModel.saveOrUpdate()
            backToList()
        }

        return binding.root
    }

    fun backToList(){
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,listFragment)
            commit()
        }
    }




}