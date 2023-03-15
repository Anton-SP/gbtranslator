package com.example.featuretranslator.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.featuretranslator.R
import com.example.featuretranslator.data.Word
import com.example.featuretranslator.databinding.FragmentDetailScreenBinding
import com.example.featuretranslator.utils.isOnline
import com.example.featuretranslator.utils.ui.AlertDialogFragment
import com.example.featuretranslator.utils.ui.convertMeaningsToString
import com.example.featuretranslator.utils.ui.navigationData

class DetailScreenFragment: Fragment(R.layout.fragment_detail_screen) {

    private val binding: FragmentDetailScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionbarHomeButtonAsUp()

        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(requireActivity())) {
            setData()
        } else {
            AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                parentFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun setData() {
        val bundle = navigationData as Word
        binding.descriptionHeader.text = bundle.text
        binding.descriptionTextview.text = bundle.meanings?.let { convertMeaningsToString(it) }
        val imageLink = "https:"+ bundle.meanings?.get(0)?.imageUrl
        Log.d("HAPPY",imageLink.toString())
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {

            useCoilToLoadPhoto(binding.descriptionImageview, imageLink)

        }
    }

    private fun setActionbarHomeButtonAsUp() {
       //todo back to main screen
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        imageView.load(imageLink)
        stopRefreshAnimationIfNeeded()
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    companion object {

        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"

        private const val WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        private const val DESCRIPTION_EXTRA = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val URL_EXTRA = "6e4b154d-e01f-4953-a404-639fb3bf7281"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            url: String?
        ): Intent = Intent(context, DetailScreenFragment::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(URL_EXTRA, url)
        }
    }

}