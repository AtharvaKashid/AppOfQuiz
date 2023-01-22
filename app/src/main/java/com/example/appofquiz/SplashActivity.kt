package com.example.appofquiz

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    var progressBar: ProgressBar? = null
    var textView: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        progressBar = findViewById(R.id.progressb)
        textView = findViewById(R.id.textView)
        progressBar!!.max=100

        progressBar!!.setScaleY(3f)


        progressBarAnimation()
    }

    private fun progressBarAnimation() {
        val anim = ProgressBarAnimation(this, progressBar, textView, 0f, 100f)
        anim.setDuration(1800)
        progressBar!!.setAnimation(anim)
    }
}