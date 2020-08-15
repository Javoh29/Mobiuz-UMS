package uz.tillo.umsdealer.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import uz.tillo.umsdealer.data.db.entity.MinutesResponse
import uz.tillo.umsdealer.data.db.entity.PacketsResponse
import uz.tillo.umsdealer.data.db.entity.RateResponse
import uz.tillo.umsdealer.data.db.entity.ServiceResponse

interface ApiService {

    @GET("packets")
    fun getPacketsAsync(): Deferred<PacketsResponse>

    @GET("tariffs")
    fun getRateAsync(): Deferred<RateResponse>

    @GET("minutes")
    fun getMinutesAsync(): Deferred<MinutesResponse>

    @GET("services")
    fun getServicesAsync(): Deferred<ServiceResponse>

    companion object {
        operator fun invoke(): ApiService {
            val logging: HttpLoggingInterceptor =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://umsdealer.000webhostapp.com/api/")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
        }
    }
}