package com.example.fitpeodemoapp.feature.ui.splash.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fitpeodemoapp.R
import com.example.fitpeodemoapp.feature.ui.home.ui.PhotosActivity
import com.example.fitpeodemoapp.feature.ui.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity:AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)

        viewModel.splashCompleted.observe(this) {
            if (it) {
                navigateToDashboard()
            }
        }
        viewModel.splashRedirect()
    }

    //Navigate to home screen
    fun navigateToDashboard() {
        startActivity(Intent(this, PhotosActivity::class.java))
        finish()
    }
}