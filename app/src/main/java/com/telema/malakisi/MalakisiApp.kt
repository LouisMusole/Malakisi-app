package com.telema.malakisi

import android.app.Application
import com.telema.malakisi.di.localDBModule
import com.telema.malakisi.di.networkModule
import com.telema.malakisi.di.repositoryModule
import com.telema.malakisi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MalakisiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MalakisiApp)
            modules(networkModule, localDBModule, repositoryModule, viewModelModule)
        }
    }
}