package com.example.istory.db.factory

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.istory.db.dao.StoryDAO
import com.example.istory.db.entity.Story
import com.example.istory.db.entity.User


@Database(entities = [Story::class, User::class],version = 1)
abstract class IStoryDatabase: RoomDatabase() {

    abstract val storyDAO:StoryDAO

    companion object{
        val DATABASE_NAME="istory_data_database"
        //returns the instance of database
        @Volatile
        private var INSTANCE:IStoryDatabase?=null
        fun getInstance(context: Context):IStoryDatabase{
            synchronized(this){
                var instance= INSTANCE

                if(instance === null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        IStoryDatabase::class.java,
                        DATABASE_NAME
                    )
                        .build()
                }
                return instance
            }
        }
    }


}