package com.zikki.basicloginandapi.model


/**
 * PhotoResponse to store data or error accordingly
 */

sealed class PhotoResponse<out T> {

    data class Success<out T>(val data: T) : PhotoResponse<T>()
    data class Error(val error: String) : PhotoResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[error=$error]"
        }
    }
}