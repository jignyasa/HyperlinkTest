package com.ma.hyperlinktest.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ma.hyperlinktest.db.entity.RegistrationItem
@Dao
interface RegistrationDao{
    @Query("SELECT * FROM registration")
    fun getRegistrationData():LiveData<List<RegistrationItem>>

    @Insert
    fun registerUser(item: RegistrationItem)
}