package com.zikki.basicloginandapi.ui.error

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zikki.basicloginandapi.R
import kotlinx.android.synthetic.main.error_page_fragment.*


/**
 * Error Fragment to handle any errors retrieved from the API call
 * could be used to display relevant error message
 *
 * @return fragment with error_page_fragment inflated
 */

class ErrorFragment : Fragment(R.layout.error_page_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setToolbar()
    }

    /**
     * sets toolbar with back option navigating back to login
     */

    private fun setToolbar() {
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.pop_back) }
    }
}