package com.shahad.app.happiness_detector

import org.koin.dsl.module

val appModel = module {
    single { ImageAnalyser() }
}
