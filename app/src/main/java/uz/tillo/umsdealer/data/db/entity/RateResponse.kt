package uz.tillo.umsdealer.data.db.entity


import com.google.gson.annotations.SerializedName

data class RateResponse(
        @SerializedName("data")
    val model: List<RateModel>,
        @SerializedName("status")
    val status: Boolean
)