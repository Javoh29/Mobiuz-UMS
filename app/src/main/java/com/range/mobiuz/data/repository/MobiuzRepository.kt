package com.range.mobiuz.data.repository

import androidx.lifecycle.LiveData
import com.range.mobiuz.data.db.entity.*

interface MobiuzRepository {

    suspend fun fetchingAllData(): Boolean

    suspend fun getPackets(): LiveData<List<PacketModel>>

    suspend fun getMinutes(): LiveData<List<MinutesModel>>

    suspend fun getRate(): LiveData<List<RateModel>>

    suspend fun getServices(): LiveData<List<ServiceModel>>

    suspend fun getVersionsAsync(): VersionModel?
}