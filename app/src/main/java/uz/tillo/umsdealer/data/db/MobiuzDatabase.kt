package uz.tillo.umsdealer.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.tillo.umsdealer.data.db.entity.MinutesModel
import uz.tillo.umsdealer.data.db.entity.PacketModel
import uz.tillo.umsdealer.data.db.entity.RateModel
import uz.tillo.umsdealer.data.db.entity.ServiceModel


@Database(
        entities = [PacketModel::class, MinutesModel::class, RateModel::class, ServiceModel::class],
        version = 1
)
abstract class MobiuzDatabase : RoomDatabase() {

    abstract fun mobiuzDao(): MobiuzDao

    companion object {
        @Volatile
        private var instance: MobiuzDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        MobiuzDatabase::class.java, "mobiuz.db")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}