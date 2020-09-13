package com.range.mobiuz.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "minutes_table")
data class MinutesModel(
        @PrimaryKey(autoGenerate = true)
        var idTable: Int = 0,
        @SerializedName("code")
        val code: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: String,
        @SerializedName("type")
        val type: Int
)