package com.zikki.basicloginandapi.viewmodel.loggedIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zikki.basicloginandapi.model.Photo
import com.zikki.basicloginandapi.repository.Repository


class PhotosViewModel : ViewModel() {

    private val repository = Repository.instance()
    private val photoList by lazy {
        return@lazy repository.photos()
    }
    private val errorPage by lazy {
        return@lazy repository.error()
    }

    fun photos(): LiveData<List<Photo>> = photoList
    fun error() : LiveData<Boolean> = errorPage

}