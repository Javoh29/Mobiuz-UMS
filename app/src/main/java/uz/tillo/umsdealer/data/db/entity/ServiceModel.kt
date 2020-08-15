package uz.tillo.umsdealer.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "service_table")
data class ServiceModel(
        @PrimaryKey(autoGenerate = true)
        var idTable: Int = 0,
        @SerializedName("code")
        val code: String,
        @SerializedName("def")
        val def: String,
        @SerializedName("def_ru")
        val defRu: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("name_ru")
        val nameRu: String
)