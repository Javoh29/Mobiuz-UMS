package com.range.mobiuz.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.range.mobiuz.data.db.MobiuzDao
import com.range.mobiuz.data.db.entity.*
import com.range.mobiuz.data.network.ApiService
import com.range.mobiuz.data.pravider.UnitProvider

class MobiuzRepositoryImpl(
        private val mobiuzDao: MobiuzDao,
        private val unitProvider: UnitProvider,
        private val apiService: ApiService
) : MobiuzRepository {

    override suspend fun getPackets(): LiveData<List<PacketModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext mobiuzDao.getPackets()
        }
    }

    override suspend fun getMinutes(): LiveData<List<MinutesModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext mobiuzDao.getMinutes()
        }
    }

    override suspend fun getRate(): LiveData<List<RateModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext mobiuzDao.getRate()
        }
    }

    override suspend fun getServices(): LiveData<List<ServiceModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext mobiuzDao.getService()
        }
    }

    override suspend fun getVersionsAsync(): VersionModel? {
        val response = apiService.getVersionAsync()
        return if (response.isSuccessful) {
            response.body()
        } else null
    }

    override suspend fun fetchingAllData(): Boolean {
        val response = apiService.getPacketsAsync()
        var isLoaded = true
        if (response.isSuccessful && response.body()!!.isNotEmpty()) {
            mobiuzDao.deletePackets()
            response.body()!!.forEach {
                mobiuzDao.upsertPackets(it)
            }
        } else isLoaded = false

        val response2 = apiService.getMinutesAsync()
        if (response2.isSuccessful && response2.body()!!.isNotEmpty()) {
            mobiuzDao.deleteMinutes()
            response2.body()!!.forEach {
                mobiuzDao.upsertMinutes(it)
            }
        } else isLoaded = false

        val response3 = apiService.getRateAsync()
        if (response3.isSuccessful && response3.body()!!.isNotEmpty()) {
            mobiuzDao.deleteRate()
            response3.body()!!.forEach {
                mobiuzDao.upsertRate(it)
            }
        } else isLoaded = false

        val response4 = apiService.getServicesAsync()
        if (response4.isSuccessful && response4.body()!!.isNotEmpty()) {
            mobiuzDao.deleteService()
            response4.body()!!.forEach {
                mobiuzDao.upsertService(it)
            }
        } else isLoaded = false

        if (isLoaded) {
            unitProvider.saveLoadDate()
        }
        return isLoaded
    }

}