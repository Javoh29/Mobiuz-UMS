package uz.tillo.umsdealer.data.repository

import androidx.lifecycle.LiveData
import uz.tillo.umsdealer.data.db.entity.PacketModel

interface MobiuzRepository {

    suspend fun fetchingAllData(): Boolean

    suspend fun getPackets(): LiveData<List<PacketModel>>
}