package uz.tillo.umsdealer.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rate_table")
data class RateModel(
        @PrimaryKey(autoGenerate = true)
        var idTable: Int = 0,
        @SerializedName("code")
        val code: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("minutes")
        val minutes: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("name_ru")
        val nameRu: String,
        @SerializedName("packet")
        val packet: String,
        @SerializedName("price")
        val price: String,
        @SerializedName("sms")
        val sms: String
)