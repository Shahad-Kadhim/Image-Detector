package com.shahad.app.image_detector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.shahad.app.happiness_detector.HappinessCalc

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image= ContextCompat.getDrawable(this, R.drawable.war)


        image?.let {
            HappinessCalc().isHappy(it){
                Log.i("TTT1",it.toString())
            }
        }




    }
}