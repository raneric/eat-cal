package com.sgg.healthykaly.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sgg.healthykaly.model.RemotePageEntity

@Dao
interface RemotePageDao {

    @Query("SELECT * FROM remotePage")
    fun getRemotePage(): RemotePageEntity

    @Insert
    fun insertOne(remotePage:RemotePageEntity)

    @Query("DELETE FROM remotePage")
    fun deleteRemotePage()
}