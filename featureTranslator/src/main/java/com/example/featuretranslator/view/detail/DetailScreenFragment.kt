package com.example.featuretranslator.view.detail

import android.os.Bundle
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

class DetailScreenFragment : Fragment(R.layout.fragment_detail_screen) {

    private val binding: FragmentDetailScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionbarHomeButtonAsUp()
        startLoadingOrShowError()
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
        if (bundle.meanings?.get(0)?.imageUrl?.isNotEmpty() == true) {
            val imageLink = "https:" + bundle.meanings?.get(0)?.imageUrl
            useCoilToLoadPhoto(binding.descriptionImageview, imageLink)
            stopRefreshAnimationIfNeeded()
        } else {
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        //todo back to main screen
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        imageView.load(imageLink)
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    companion object {

        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"

    }

}