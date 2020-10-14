package com.zikki.basicloginandapi.data

import com.zikki.basicloginandapi.model.Photo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * interface to retrieve photos from provided url
 */
interface PhotosApi {

    @GET("photos/")
    fun getPhotos(): Call<List<Photo>>?

    /**
     * companion object to create Retrofit builder
     */
    companion object {
        operator fun invoke(): PhotosApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
                .create(PhotosApi::class.java)
        }
    }
}