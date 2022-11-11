package com.hybcode.notes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String?,
    var contents: String?,
    @ColumnInfo(name = "release_date")var releaseDate: Date?
)