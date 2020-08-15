package uz.tillo.umsdealer.data.pravider

interface UnitProvider {

    suspend fun isOnline(): Boolean

    fun saveLoadDate()

    fun getSaveDate(): String

}