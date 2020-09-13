package com.range.mobiuz.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lang_table")
data class LangModel(
        @PrimaryKey(autoGenerate = false)
        val id: Int = 0,
        val lang: Boolean
)