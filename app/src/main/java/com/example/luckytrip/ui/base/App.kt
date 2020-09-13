package com.example.luckytrip.ui.base

import android.app.Application
import android.content.Context
import com.example.luckytrip.data.network.IApiCaller
import com.example.luckytrip.util.MySharedPreferences
import com.example.luckytrip.util.mSQLite.SQLiteManager

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App = App()

        fun applicationContext(): Context? {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        applicationContext()

        IApiCaller.initWith(this)

        MySharedPreferences.initWith(this)

        SQLiteManager.initWith(this)

    }
}