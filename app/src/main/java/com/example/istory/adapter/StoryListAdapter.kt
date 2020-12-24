package com.example.istory.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.istory.R
import com.example.istory.databinding.StoryListItemBinding
import com.example.istory.databinding.StorylistHeaderBinding
import com.example.istory.db.entity.Story
import java.lang.IllegalArgumentException
private val TYPE_HEADER : Int = 0
private val TYPE_LIST : Int = 1

class StoryListAdapter( private val stories: List<Story>, private val clickListener: (Story) -> Unit, private val onCreateStory:()->Unit): RecyclerView.Adapter<BaseViewHolder<*>>(){


    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }

        return TYPE_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            TYPE_LIST->{

                val binding :StoryListItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.story_list_item, parent, false)
                ItemViewHolder(binding)
            }
            TYPE_HEADER->{
                 val binding :StorylistHeaderBinding =
                 DataBindingUtil.inflate(inflater, R.layout.storylist_header, parent, false)
                 HeaderViewHolder(binding, onCreateStory)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }


    }

    override fun getItemCount(): Int {

           return stories.size+1

    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if(holder.itemViewType== TYPE_LIST)
      holder.bind(stories[position-1], clickListener)
    }

}


class HeaderViewHolder(val binding:StorylistHeaderBinding, val onCreateStory: () -> Unit): BaseViewHolder<View>(binding.root) {

    init {
        binding.createStoryButton.setOnClickListener{
            onCreateStory()
        }
    }
    override fun bind(story: Story, clickListener: (Story) -> Unit) {


    }
}

 class ItemViewHolder(val binding:StoryListItemBinding): BaseViewHolder<View>(binding.root) {

     override fun bind(story: Story, clickListener: (Story) -> Unit) {

         binding.storyTitleTextView.text = story.title
            binding.dateTextView.text= story.date.toString()
            binding.listItemLayout.setOnClickListener{
                clickListener(story)

     }
     }
}

