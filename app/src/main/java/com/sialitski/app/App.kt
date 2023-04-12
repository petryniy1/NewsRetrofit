package com.sialitski.app

import android.app.Application
import com.sialitski.di.appModule
import com.sialitski.di.dataModule
import com.sialitski.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin{
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                dataModule,
                domainModule,
                appModule
            )
        }
    }
}