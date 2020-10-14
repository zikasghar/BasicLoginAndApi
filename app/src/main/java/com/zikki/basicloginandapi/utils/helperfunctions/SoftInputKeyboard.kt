package com.zikki.basicloginandapi.utils.helperfunctions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity


/**
 * function to hide soft input keyboard
 */

object SoftInputKeyboard {
    fun hideKeyboard(activity: FragmentActivity, view: View) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}