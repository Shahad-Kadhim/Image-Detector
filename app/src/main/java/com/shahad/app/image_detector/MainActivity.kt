package com.shahad.app.image_detector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.shahad.app.happiness_detector.HappinessCalculator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image= ContextCompat.getDrawable(this, R.drawable.war)?.toBitmap()

        image?.let {
            HappinessCalculator().analyseImageHappiness(it){
                Log.i("TTT1",it.toString())
            }
        }

    }
}