package com.range.mobiuz.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.range.mobiuz.data.db.entity.*

interface ApiService {

    @GET("packets.json")
    suspend fun getPacketsAsync(): Response<PacketsResponse>

    @GET("tariffs.json")
    suspend fun getRateAsync(): Response<RateResponse>

    @GET("minutes.json")
    suspend fun getMinutesAsync(): Response<MinutesResponse>

    @GET("services.json")
    suspend fun getServicesAsync(): Response<ServiceResponse>

    @GET("sale.json")
    suspend fun getSaleAsync(): Response<SaleModel>

    @GET("dealerCode.json")
    suspend fun getDealerCode(): Response<DealerCode>

    @GET("banner.json")
    suspend fun getBanners(): Response<BannerResponse>

    companion object {
        operator fun invoke(): ApiService {

            val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://raw.githubusercontent.com/Javoh29/Mobiuz/master/")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
        }
    }
}