package com.range.mobiuz.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.range.mobiuz.data.db.entity.*

@Dao
interface MobiuzDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertPackets(model: PacketModel)

    @Query("SELECT * from packets_table")
    fun getPackets(): LiveData<List<PacketModel>>

    @Query("DELETE FROM packets_table")
    fun deletePackets()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertLang(model: LangModel)

    @Query("SELECT * from lang_table")
    fun getLang(): LangModel

    @Query("DELETE FROM lang_table")
    fun deleteLang()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertMinutes(model: MinutesModel)

    @Query("SELECT * from minutes_table")
    fun getMinutes(): LiveData<List<MinutesModel>>

    @Query("DELETE FROM minutes_table")
    fun deleteMinutes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertRate(model: RateModel)

    @Query("SELECT * from rate_table")
    fun getRate(): LiveData<List<RateModel>>

    @Query("DELETE FROM rate_table")
    fun deleteRate()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertService(model: ServiceModel)

    @Query("SELECT * from service_table")
    fun getService(): LiveData<List<ServiceModel>>

    @Query("DELETE FROM service_table")
    fun deleteService()

}