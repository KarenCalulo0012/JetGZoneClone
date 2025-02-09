package com.kaecals.jetgzoneclone

import android.app.Application
import com.kaecals.jetgzoneclone.di.networkModule
import com.kaecals.jetgzoneclone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule, viewModelModule)
        }
    }
}