package com.range.mobiuz.data.pravider


interface UnitProvider {

    suspend fun isOnline(): Boolean

    fun saveLoadDate()

    fun getSaveDate(): String

    fun saveLang(lang: Boolean)

    fun getLang(): Boolean
}