package com.shahad.app.happiness_detector

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.google.mlkit.vision.label.ImageLabel
import org.koin.java.KoinJavaComponent.inject

class HappinessCalc {

    private val imageAnalyser: ImageAnalyser by inject(ImageAnalyser::class.java)

    fun isHappy(image: Drawable , onSuccess: (Level)-> Unit){
          imageAnalyser.detectImage(
              image.toBitmap(),
              {
                    onSuccess(getLevel(it))
              },
              {
                  Log.i("HAPPINESS_DETECTOR","Fail:  ${it.message.toString()}")
              }
          )
      }

    private fun getLevel(labels:List<ImageLabel>): Level {
        Log.i("TTT",labels.map { it.text }.toString())
        val happy = labels.filter { it.text in happyLabel }
        val sad = labels.filter { it.text in sadLabel }
        return when {
            happy.size < sad.size -> {
                Level.SAD
            }
            happy.size > sad.size -> {
                Level.HAPPINESS
            }
            else -> {
                Level.NORMAL
            }
        }
    }

    companion object{

        private val happyLabel = listOf(
            "Comics",
            "Circus","Smile","Laugh","Balloon","Picnic" ,
            "Clown","Christmas","Dance" ,
            "Santa claus","Thanksgiving",
            "Vacation","Love","Money","Shikoku",
            "Pet","Pizza","Lipstick","Cool",
            "Duck","Turtle","Dog","Rainbow",
            "Flower","Airplane","Butterfly",
            "Marathon","Cake","Fireworks","Baby",
            "Bride","Joker","Selfie","Dress","Fun","Smile","Vacation",
            "Leisure","River"
        )

        private val sadLabel = listOf(
            "Bullfighting","Junk","Shipwreck","Caving","Jungle"
            ,"Fire","Cairn terrier","Forest","Militia","Volcano","Rocket",
            "Bangs","Lightning","Army","Storm","Helmet"
        )
    }
}