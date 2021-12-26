package com.shahad.app.happiness_detector

import android.graphics.Bitmap
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

    private fun getLevel(labels:List<ImageLabel>): HappinessLevel =
        when(happinessAverage(labels)) {
            in 0.0..0.49 ->  HappinessLevel.SAD
            in 0.6..1.0 -> HappinessLevel.HAPPY
            else -> HappinessLevel.NORMAL
        }


    private fun happinessAverage(labels: List<ImageLabel>): Double {

        val numberOfHappinessLabel= getNumberOfLabelsMatchInPatten(labels, happyLabel)

        val numberOfSadnessLabel= getNumberOfLabelsMatchInPatten(labels, sadLabel)

        return numberOfHappinessLabel/(numberOfHappinessLabel+numberOfSadnessLabel).toDouble()
    }

    private fun getNumberOfLabelsMatchInPatten(labels: List<ImageLabel>, pattern: String): Int =
        labels.map {
            Regex(pattern).containsMatchIn(it.text)
        }.filter { it }.size

    companion object{
        const val happyLabel = "Comics|Circus|Smile|Laugh|Balloon|Picnic|Clown|Christmas|Dance|Santa claus|Thanksgiving|Vacation|Love|Money|Shikoku|Pet|Pizza|Lipstick|Cool|Duck|Turtle|Dog|Rainbow|Flower|Airplane|Butterfly|Marathon|Cake|Fireworks|Baby|Bride|Joker|Selfie|Dress|Fun|Leisure|River"
        const val sadLabel = "Bullfighting|Junk|Shipwreck|Caving|Jungle|Fire|Cairn terrier|Forest|Militia|Volcano|Rocket|Bangs|Lightning|Army|Storm|Helmet"
    }

}