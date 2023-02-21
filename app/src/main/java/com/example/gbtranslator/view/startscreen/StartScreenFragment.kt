package com.example.gbtranslator.view.startscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbtranslator.R
import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.databinding.FragmentStartScreenBinding
import com.example.gbtranslator.view.base.BaseFragment
import com.example.gbtranslator.view.startscreen.adapter.StartScreenAdapter

class StartScreenFragment : BaseFragment<AppState>() {//(R.layout.fragment_start_screen) {

    //  private val binding: FragmentStartScreenBinding by viewBinding()
    private lateinit var binding: FragmentStartScreenBinding

    override val model: StartScreenViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(StartScreenViewModel::class.java)
    }

    private val observer = Observer<AppState> { renderData(it) }

    private val adapter: StartScreenAdapter by lazy {
        StartScreenAdapter(
            onClick = {
                Toast.makeText(requireActivity(), it.text, Toast.LENGTH_SHORT).show()
            },
            mutableListOf()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        binding = FragmentStartScreenBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        binding.fabSearch.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model.getData(searchWord, true).observe(requireActivity(), observer)
                }
            })
            searchDialogFragment.show(parentFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    }

    private fun initRecycler() {
        binding.rvStartScreenFragment.apply {
            adapter = this@StartScreenFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter.submitList(dataModel)

                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = android.view.View.VISIBLE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = android.view.View.GONE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.tvError.text = error ?: getString(R.string.undefined_error)
        binding.btnReload.setOnClickListener {
            model.getData("hi", true).observe(this, observer)
        }
    }

    private fun showViewSuccess() {
        binding.layoutSuccess.visibility = android.view.View.VISIBLE
        binding.layoutLoading.visibility = android.view.View.GONE
        binding.layoutError.visibility = android.view.View.GONE
    }

    private fun showViewLoading() {
        binding.layoutSuccess.visibility = android.view.View.GONE
        binding.layoutLoading.visibility = android.view.View.VISIBLE
        binding.layoutError.visibility = android.view.View.GONE
    }

    private fun showViewError() {
        binding.layoutSuccess.visibility = android.view.View.GONE
        binding.layoutLoading.visibility = android.view.View.GONE
        binding.layoutError.visibility = android.view.View.VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

}