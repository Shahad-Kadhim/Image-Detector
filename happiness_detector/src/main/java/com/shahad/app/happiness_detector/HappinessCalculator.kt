package com.shahad.app.happiness_detector

import android.graphics.Bitmap
import com.google.mlkit.vision.label.ImageLabel
import java.util.*

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
            Regex(pattern).containsMatchIn(it.text.lowercase(Locale.getDefault()))
        }.filter { it }.size

    companion object{
        const val happyLabel = "comics|circus|smile|laugh|balloon|picnic|clown|christmas|dance|santa claus|thanksgiving|vacation|love|money|shikoku|pet|pizza|lipstick|cool|duck|turtle|dog|rainbow|flower|airplane|butterfly|marathon|cake|fireworks|baby|bride|joker|selfie|dress|fun|leisure|river|blessed|parturition|birth|occasion|joyous|lighthearted|celebration|carnival|party"
        const val sadLabel = "bullfighting|junk|shipwreck|caving|jungle|fire|cairn terrier|forest|militia|volcano|rocket|bangs|lightning|army|storm|helmet|funeral|sad|awful|burial|dead|depressing|farewell|misery|depression|pain|upset|torture|battle|combat|blood|fire|flood|hospital|weapon|gun|monster|fear|horror|accident|cry|tears|dark"
    }

}