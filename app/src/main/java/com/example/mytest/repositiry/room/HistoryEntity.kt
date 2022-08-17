package com.example.mytest.repositiry.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id") val id: Int,
    @field: ColumnInfo(name = "character") val character: String?,
    @field:ColumnInfo(name = "quote") val quote: String?,
    @field:ColumnInfo(name = "image") val image: String?
)
