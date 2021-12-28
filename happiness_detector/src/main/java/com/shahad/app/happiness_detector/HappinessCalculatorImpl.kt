package com.shahad.app.happiness_detector

import android.graphics.Bitmap

class HappinessCalculatorImpl(
    private var happyLabels: MutableList<String> = storedHappyLabels ,
    private var sadLabels: MutableList<String> = storeSadLabels
): HappinessCalculator {

    override fun analyseImageHappiness(bitmap: Bitmap, onResult: (HappinessLevel)-> Unit){
          ImageAnalyser().detectImage(
              bitmap,
              {
                  it.map { imageLabel ->
                      imageLabel.text.lowercase()
                  }.run {
                      onResult(getLevel(this))
                  }
              },
              {
                  throw Exception("Fail: ${it.message}")
              }
          )
      }

    override fun analyseTextHappiness(text: String, onResult: (HappinessLevel) -> Unit) {
        text.lowercase()
            .split(" ").apply {
            forEach { it.trim() }
            onResult(getLevel(this))
        }
    }

    private fun getLevel(labels:List<String>): HappinessLevel =
        when(getHappinessAverage(labels)) {
            in MINIMUM_SADNESS..MAXIMUM_SADNESS ->  HappinessLevel.SAD
            in MINIMUM_HAPPINESS..MAXIMUM_HAPPINESS -> HappinessLevel.HAPPY
            else -> HappinessLevel.NORMAL
        }


    private fun getHappinessAverage(labels: List<String>): Double {
        val numberOfHappinessLabel= getNumberOfLabelsInList(labels, happyLabels)
        val numberOfSadnessLabel= getNumberOfLabelsInList(labels, sadLabels)

        return( numberOfHappinessLabel/(numberOfHappinessLabel+numberOfSadnessLabel).toDouble())
    }

    private fun getNumberOfLabelsInList(labels: List<String>, storedLabels: List<String>): Int =
        labels.mapNotNull { label ->
            storedLabels.find { it == label }
        }.size

    fun addHappyLabel(vararg labels: String): HappinessCalculatorImpl {
        happyLabels.addAll(labels)
        return this
    }

    fun addSadLabels(vararg labels: String): HappinessCalculatorImpl {
        sadLabels.addAll(labels.asList())
        return this
    }

    companion object{
        val storedHappyLabels = arrayListOf("comics","circus","smile","laugh","balloon","picnic","clown","christmas","dance","santa claus","thanksgiving","vacation","love","money","shikoku","pet","pizza","lipstick","cool","duck","turtle","dog","rainbow","flower","airplane","butterfly","marathon","cake","fireworks","baby","bride","joker","selfie","dress","fun","leisure","river","blessed","parturition","birth","occasion","joyous","lighthearted","celebration","carnival","party","happy")
        val storeSadLabels = arrayListOf("bullfighting","junk","shipwreck","caving","jungle","fire","cairn terrier","forest","militia","volcano","rocket","bangs","lightning","army","storm","helmet","funeral","sad","awful","burial","dead","depressing","farewell","misery","depression","pain","upset","torture","battle","combat","blood","fire","flood","hospital","weapon","gun","monster","fear","horror","accident","cry","tears","dark")
        const val MINIMUM_SADNESS = 0.0
        const val MAXIMUM_SADNESS = 0.49
        const val MINIMUM_HAPPINESS = 0.6
        const val MAXIMUM_HAPPINESS = 1.0
    }

}