package com.range.mobiuz

import android.app.Application
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import com.range.mobiuz.data.db.MobiuzDatabase
import com.range.mobiuz.data.network.ApiService
import com.range.mobiuz.data.pravider.UnitProvider
import com.range.mobiuz.data.pravider.UnitProviderImpl
import com.range.mobiuz.data.repository.MobiuzRepository
import com.range.mobiuz.data.repository.MobiuzRepositoryImpl

class App: Application(), KodeinAware {
    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidXModule(this@App))

            bind() from singleton { MobiuzDatabase(instance()) }
            bind() from singleton { instance<MobiuzDatabase>().mobiuzDao() }
            bind() from singleton { ApiService() }
            bind<MobiuzRepository>() with singleton { MobiuzRepositoryImpl(instance(), instance(), instance()) }
            bind<UnitProvider>() with singleton { UnitProviderImpl(instance(), instance()) }
        }

    override fun onCreate() {
        super.onCreate()
        val config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build()
        PRDownloader.initialize(applicationContext, config)
    }

    companion object {
        var isLoaded: Boolean = true
    }
}