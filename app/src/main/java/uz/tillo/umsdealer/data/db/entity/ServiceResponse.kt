package uz.tillo.umsdealer.data.db.entity


import com.google.gson.annotations.SerializedName

data class ServiceResponse(
        @SerializedName("data")
    val model: List<ServiceModel>,
        @SerializedName("status")
    val status: Boolean
)