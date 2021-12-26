package com.shahad.app.happiness_detector

import java.lang.Exception
import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import javax.inject.Inject

internal class ImageAnalyser @Inject constructor(){

    private fun getImage(bitmap: Bitmap) =
        InputImage.fromBitmap(bitmap,0)

    private val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

    fun detectImage(
        bitmap: Bitmap,
        onSuccess: (List<ImageLabel>) -> Unit,
        onFail: (Exception) -> Unit
    ) {
        labeler.process(getImage(bitmap))
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFail)
    }

}

