package uz.tillo.umsdealer.data.db.entity


import com.google.gson.annotations.SerializedName

data class MinutesResponse(
        @SerializedName("data")
        val model: List<MinutesModel>,
        @SerializedName("status")
        val status: Boolean
)