package uz.tillo.umsdealer.data.db.entity


import com.google.gson.annotations.SerializedName
import uz.tillo.umsdealer.data.db.entity.PacketModel

data class PacketsResponse(
        @SerializedName("data")
    val model: List<PacketModel>,
        @SerializedName("status")
    val status: Boolean
)