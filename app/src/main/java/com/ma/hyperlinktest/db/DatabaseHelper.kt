package com.ma.hyperlinktest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ma.hyperlinktest.db.dao.RegistrationDao
import com.ma.hyperlinktest.db.entity.RegistrationItem

@Database(entities = [RegistrationItem::class],version = 1,exportSchema = false)
abstract class DatabaseHelper :RoomDatabase()
{
    companion object{
        fun getDatabase(context:Context):DatabaseHelper
        {
            var instance:DatabaseHelper?=null
            if(instance==null)
            {
                instance= Room.databaseBuilder(context,DatabaseHelper::class.java,"registration_database").allowMainThreadQueries().build()
            }
            return instance
        }
    }
    abstract fun getRegistrationDao():RegistrationDao
}