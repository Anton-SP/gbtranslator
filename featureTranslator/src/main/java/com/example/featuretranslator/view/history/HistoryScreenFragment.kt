package com.example.featuretranslator.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.featuretranslator.R
import com.example.featuretranslator.data.AppState
import com.example.featuretranslator.data.Word
import com.example.featuretranslator.databinding.FragmentHistoryScreenBinding
import com.example.featuretranslator.view.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryScreenFragment : BaseFragment<AppState, HistoryInteractor>() {

    private lateinit var binding: FragmentHistoryScreenBinding
    override lateinit var model: HistoryViewModel

    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }


    private fun initViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Success -> {
                val list = dataModel.data
                if (list.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_tittle_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {

                    adapter.setData(list)

                }
            }
            is AppState.Loading -> {
                if (dataModel.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarHorizontal.progress = dataModel.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                }
            }
            is AppState.Error -> {
                showAlertDialog(getString(R.string.error_stub), dataModel.error.message)
            }
        }
    }

}