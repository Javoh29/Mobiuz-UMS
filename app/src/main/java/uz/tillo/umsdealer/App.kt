package uz.tillo.umsdealer

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import uz.tillo.umsdealer.data.db.MobiuzDatabase
import uz.tillo.umsdealer.data.network.ApiService
import uz.tillo.umsdealer.data.pravider.UnitProvider
import uz.tillo.umsdealer.data.pravider.UnitProviderImpl
import uz.tillo.umsdealer.data.repository.MobiuzRepository
import uz.tillo.umsdealer.data.repository.MobiuzRepositoryImpl

class App: Application(), KodeinAware {
    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidXModule(this@App))

            bind() from singleton { MobiuzDatabase(instance()) }
            bind() from singleton { instance<MobiuzDatabase>().mobiuzDao() }
            bind() from singleton { ApiService() }
            bind<MobiuzRepository>() with singleton { MobiuzRepositoryImpl(instance(), instance(), instance()) }
            bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        }
}