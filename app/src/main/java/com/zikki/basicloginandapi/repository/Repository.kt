package com.zikki.basicloginandapi.repository

import androidx.lifecycle.MutableLiveData
import com.zikki.basicloginandapi.model.Photo
import com.zikki.basicloginandapi.model.PhotoResponse
import com.zikki.basicloginandapi.data.PhotosApi
import com.zikki.basicloginandapi.utils.extensions.enqueue

/**
 * Repository to retrieve data/error from data source
 */

class Repository {

    private val photosLiveData = MutableLiveData<List<Photo>>()
    private val errorLiveData = MutableLiveData<Boolean>()

    init {
        errorLiveData.value = false
        getPhotosFromSource()
    }

    private fun setPhotos(photos: List<Photo>) {
        photosLiveData.value = photos
    }

    fun photos() = photosLiveData
    fun error() = errorLiveData

    fun getPhotosFromSource() {
        try {
            val retrofit = PhotosApi.invoke()
            val call = retrofit.getPhotos()
            call?.run {
                enqueue {
                    when (this) {
                        is PhotoResponse.Success -> {
                            setPhotos(data)
                        }
                        is PhotoResponse.Error -> {
                            errorLiveData.value = true
                        }
                    }
                }
            }
        } catch (e: Exception) {
            errorLiveData.value = true
        }
    }

    /**
     * create companion object to create instance of Repository
     */

    companion object {
        private var sInstance: Repository? = null
        fun instance(): Repository {
            if (sInstance == null) {
                synchronized(Repository) {
                    sInstance = Repository()
                }
            }
            return sInstance!!
        }
    }

}