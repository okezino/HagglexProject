package com.example.hagglexproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.hagglexproject.databinding.ActivityMainBinding
import com.example.hagglexproject.databinding.ActivitySpalshScreenBinding

class SplashScreen : AppCompatActivity() {

    lateinit var topAnimation : Animation
    lateinit var bottomAnimation : Animation
    lateinit var binding : ActivitySpalshScreenBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpalshScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topAnimation = AnimationUtils.loadAnimation(this,R.animator.topanimation)
        bottomAnimation = AnimationUtils.loadAnimation(this,R.animator.bottomanimation)
        binding.splashScreenIv.animation = topAnimation
        binding.splashScreenTv.animation = bottomAnimation
    }

    override fun onResume() {
        super.onResume()

        Handler(mainLooper).postDelayed(
            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },5000
        )
    }
}