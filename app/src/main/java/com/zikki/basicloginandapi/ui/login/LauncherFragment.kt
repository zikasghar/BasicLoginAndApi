package com.zikki.basicloginandapi.ui.login

import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zikki.basicloginandapi.R

/**
 * Launcher Fragment to Load splashscreen,
 * possibly implement check to see if user is already logged into device
 */
class LauncherFragment : Fragment(R.layout.launcher_fragment) {
    val timer: CountDownTimer = object : CountDownTimer(3000, 1000) {
        override fun onFinish() {
            //possibly use this to see if user is already logged in
            findNavController().navigate(R.id.action_launcherFragment_to_logBackIn)
        }

        override fun onTick(millisUntilFinished: Long) {}
    }.start()
}