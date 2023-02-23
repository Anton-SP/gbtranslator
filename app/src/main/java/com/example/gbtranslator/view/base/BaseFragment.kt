package com.example.gbtranslator.view.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gbtranslator.R
import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.utils.isOnline
import com.example.gbtranslator.utils.ui.AlertDialogFragment
import com.example.gbtranslator.viewmodel.BaseViewModel
import com.example.gbtranslator.viewmodel.Interactor

abstract class BaseFragment<T : AppState, I : Interactor<T>> : Fragment() {

    abstract val model: BaseViewModel<T>

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNetworkAvailable = isOnline(requireActivity().applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(requireActivity().applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(getParentFragmentManager(), DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return getParentFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun renderData(dataModel: T)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}