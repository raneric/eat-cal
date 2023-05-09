package com.sgg.healthykaly.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remotePage")
data class RemotePageEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val nextPage: Int)