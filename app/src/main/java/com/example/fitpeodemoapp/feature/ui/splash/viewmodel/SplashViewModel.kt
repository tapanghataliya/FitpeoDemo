package com.example.fitpeodemoapp.feature.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpeodemoapp.core.utill.Constant.Companion.SPLASH_SCREEN_TIMEOUT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _splashCompleted = MutableLiveData<Boolean>()
    val splashCompleted: LiveData<Boolean>
        get() = _splashCompleted

    //This function launch and display splash screen 3 second after redirect on home screen.
    fun splashRedirect() {
        viewModelScope.launch(Dispatchers.Main) {
            _splashCompleted.value = true
            delay(SPLASH_SCREEN_TIMEOUT)
        }
    }
}