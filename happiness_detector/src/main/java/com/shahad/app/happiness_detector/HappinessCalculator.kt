package com.shahad.app.happiness_detector

import android.graphics.Bitmap

internal interface HappinessCalculator {

    fun analyseImageHappiness(bitmap: Bitmap, onResult: (HappinessLevel)-> Unit)

    fun analyseTextHappiness(text: String, onResult: (HappinessLevel) -> Unit)

}