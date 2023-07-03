package com.example.fitpeodemoapp.feature.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpeodemoapp.core.utill.Constant
import com.example.fitpeodemoapp.feature.data.model.Photos
import com.example.fitpeodemoapp.feature.data.repository.PhotosRepository
import com.example.fitpeodemoapp.core.utill.NetworkHelper
import com.example.fitpeodemoapp.core.utill.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _photos = MutableLiveData<Resource<List<Photos>>>()
    val photos: LiveData<Resource<List<Photos>>>
        get() = _photos

    init {
        getPhotoListDisplay()
    }

    //This method check network and response
    private fun getPhotoListDisplay() {
        viewModelScope.launch {
            _photos.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                photosRepository.getPhotos().let {
                    if (it.isSuccessful) {
                        _photos.postValue(Resource.success(it.body()))
                    } else _photos.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _photos.postValue(Resource.error(Constant.NO_INTERNET, null))
        }
    }
}
