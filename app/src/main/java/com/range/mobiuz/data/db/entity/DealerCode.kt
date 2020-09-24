package com.range.mobiuz.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dealer_code_table")
data class DealerCode(
        @PrimaryKey(autoGenerate = false)
        val id: Int = 0,
        @SerializedName("code")
        val code: String
)