package com.zikki.basicloginandapi.utils.extensions

import com.zikki.basicloginandapi.model.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * extension function to store the result of retrofit enqueue in
 * PhotoResponse (used handle errors in the viewmodel)
 */

inline fun <T> Call<T>.enqueue(crossinline onResult: PhotoResponse<T>.() -> Unit) {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable?) {
            onResult(PhotoResponse.Error("Network error"))
        }

        override fun onResponse(call: Call<T>?, response: Response<T>) {
            onResult(response.result())
        }
    })
}

fun <T> Response<T>.result(): PhotoResponse<T> {
    if (isSuccessful) {
        val body = body()
        return if (body != null) {
            PhotoResponse.Success(body)
        } else {
            PhotoResponse.Error("Empty body")
        }
    }

    return PhotoResponse.Error(errorBody().toString())
}