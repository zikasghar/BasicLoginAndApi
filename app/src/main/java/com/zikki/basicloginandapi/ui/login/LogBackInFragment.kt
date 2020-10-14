package com.zikki.basicloginandapi.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zikki.basicloginandapi.R
import kotlinx.android.synthetic.main.log_back_in_fragment.*


/**
 * fragment to log user back in to Student Beans
 * @return fragment with log_back_in_fragment inflated
 */

class LogBackInFragment : Fragment(R.layout.log_back_in_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    /**
     * onClickListener for "login" button,
     * no current implementation for checking credentials
     */

    private fun setOnClickListeners() {
        login_btn.setOnClickListener {
            if (validForm()) {
                // Checking login credentials would be done here
                findNavController().navigate(R.id.action_logBackIn_to_photosFragment)
            }
        }
    }

    /**
     * check form to ensure all fields populated
     * @return boolean for a valid/invalid form
     */

    private fun validForm(): Boolean {
        var valid = false
        email.run {
            if (text.isNullOrEmpty()) error = R.string.required.toString() else valid = true
            password.run {
                if (text.isNullOrEmpty()) {
                    error = R.string.required.toString()
                    valid = false
                }
            }
            return valid
        }
    }
}