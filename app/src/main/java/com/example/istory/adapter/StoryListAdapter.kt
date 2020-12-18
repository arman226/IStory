package com.example.istory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.istory.R
import com.example.istory.databinding.StoryListItemBinding
import com.example.istory.db.entity.Story


class StoryListAdapter(private val stories: List<Story>, private val clickListener: (Story) -> Unit): RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding :StoryListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.story_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stories[position], clickListener)
    }
}

class ViewHolder(val binding:StoryListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(story: Story, clickListener: (Story)->Unit){
        binding.storyTitleTextView.text = story.title
        binding.dateTextView.text= story.date.toString()
        binding.listItemLayout.setOnClickListener{
            clickListener(story)
        }
    }
}