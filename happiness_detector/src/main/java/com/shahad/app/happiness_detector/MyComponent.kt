package com.shahad.app.happiness_detector

import android.app.Application
import org.koin.core.context.startKoin

class MyComponent: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            this.modules(appModel)
        }
    }

}