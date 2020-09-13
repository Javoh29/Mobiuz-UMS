package com.range.mobiuz.data.db.entity


import com.google.gson.annotations.SerializedName

data class VersionModel(
    @SerializedName("app_url")
    val appUrl: String,
    @SerializedName("app_ver")
    val appVer: String,
    @SerializedName("data_ver")
    val dataVer: String
)