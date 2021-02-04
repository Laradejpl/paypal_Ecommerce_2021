package com.example.onlinestorekotlin

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        val typeface: Typeface = Typeface.createFromAsset(assets,"Tabby - Display.ttf")
        tv_title_splash.typeface = typeface

        Handler().postDelayed(

            {

                //lance le mainActivity
                startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
                finish()

            },3000
        )

    }
}