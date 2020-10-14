package com.zikki.basicloginandapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  Parcelized data class to hold object of List returned from url
 */
@Parcelize
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable
