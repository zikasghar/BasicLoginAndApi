package com.zikki.basicloginandapi.ui.loggedIn

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zikki.basicloginandapi.R
import com.zikki.basicloginandapi.model.Photo
import com.zikki.basicloginandapi.utils.helperfunctions.SoftInputKeyboard
import com.zikki.basicloginandapi.viewmodel.loggedIn.PhotosViewModel
import kotlinx.android.synthetic.main.photos_fragment.*

/**
 * PhotosFragment sets recyclerview with adapter
 *  @return fragment with photos_fragment inflated
 */

class PhotosFragment : Fragment(R.layout.photos_fragment) {
    private lateinit var viewModel: PhotosViewModel
    private val adapter = PhotoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PhotosViewModel::class.java)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
        SoftInputKeyboard.hideKeyboard(requireActivity(), requireView())
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0)
        setObservers()
        setToolbar()
    }

    /**
     * sets the toolbar with back button to return to login
     */

    private fun setToolbar() {
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { findNavController().navigate(R.id.action_photosFragment_to_logBackInFragment) }
    }

    /**
     *  observes photos live data for response from API call, to update adapter
     *  observes for error to navigate to error page(currently one standard page -
     *  can be updated to show more detail via error message)
     */

    private fun setObservers() {
        viewModel.photos().observe(viewLifecycleOwner, Observer {
            if (it !== null) {
                updateAdapter(it)
                progress_bar.visibility = View.INVISIBLE
            } else {
                progress_bar.visibility = View.VISIBLE
            }
        })
        viewModel.error().observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_photosFragment_to_errorFragment)
            }
        })
    }

    /**
     * updates adapter with most current list
     */
    private fun updateAdapter(it: List<Photo>) {
        adapter.submitList(it)
    }
}