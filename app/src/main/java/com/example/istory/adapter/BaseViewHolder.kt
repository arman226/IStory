package com.example.istory.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.istory.db.entity.Story

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(story: Story, clickListener: (Story)->Unit, viewType: Int)
}