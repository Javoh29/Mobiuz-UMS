package uz.tillo.umsdealer.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.tillo.umsdealer.data.db.MobiuzDao
import uz.tillo.umsdealer.data.db.entity.PacketModel
import uz.tillo.umsdealer.data.network.ApiService
import uz.tillo.umsdealer.data.pravider.UnitProvider

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

    override suspend fun fetchingAllData(): Boolean {
        var isLoaded = true

        val fetchPackets = apiService.getPacketsAsync().await()
        if (fetchPackets.status && fetchPackets.model.isNotEmpty()){
            mobiuzDao.deletePackets()
            fetchPackets.model.forEach {
                mobiuzDao.upsertPackets(it)
            }
        }else isLoaded = false

        val fetchMinutes = apiService.getMinutesAsync().await()
        if (fetchMinutes.status && fetchMinutes.model.isNotEmpty()){
            mobiuzDao.deleteMinutes()
            fetchMinutes.model.forEach {
                mobiuzDao.upsertMinutes(it)
            }
        }else isLoaded = false

        val fetchRate = apiService.getRateAsync().await()
        if (fetchRate.status && fetchRate.model.isNotEmpty()){
            mobiuzDao.deleteRate()
            fetchRate.model.forEach {
                mobiuzDao.upsertRate(it)
            }
        }

        val fetchService = apiService.getServicesAsync().await()
        if (fetchService.status && fetchService.model.isNotEmpty()){
            mobiuzDao.deleteService()
            fetchService.model.forEach {
                mobiuzDao.upsertService(it)
            }
        }
        if (isLoaded){
            unitProvider.saveLoadDate()
        }
        return isLoaded
    }

}