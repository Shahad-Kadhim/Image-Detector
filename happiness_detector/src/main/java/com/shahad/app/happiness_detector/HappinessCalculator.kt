package com.shahad.app.happiness_detector

import android.graphics.Bitmap
import android.util.Log
import com.google.mlkit.vision.label.ImageLabel

class HappinessCalculator {

    fun analyseImageHappiness(bitmap: Bitmap, onResult: (HappinessLevel)-> Unit){
          ImageAnalyser().detectImage(
              bitmap,
              {
                    onResult(getLevel(it))
              },
              {
                  onResult(HappinessLevel.UNKNOWN)
              }
          )
      }

    private fun getLevel(labels:List<ImageLabel>): HappinessLevel {
        Log.i("TTT",labels.map { it.text }.toString())
        val happy = labels.filter { it.text in happyLabel }
        val sad = labels.filter { it.text in sadLabel }
        return when {
            happy.size < sad.size -> {
                HappinessLevel.SAD
            }
            happy.size > sad.size -> {
                HappinessLevel.HAPPY
            }
            else -> {
                HappinessLevel.NORMAL
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