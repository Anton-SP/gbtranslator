package com.example.gbtranslator.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gbtranslator.R
import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.databinding.FragmentStartScreenBinding
import com.example.gbtranslator.presenter.Presenter
import com.example.gbtranslator.view.base.View
import com.example.gbtranslator.view.main.adapter.BaseFragment
import com.example.gbtranslator.view.main.adapter.StartScreenAdapter

class StartScreenFragment : BaseFragment<AppState>(){//(R.layout.fragment_start_screen) {

    //  private val binding: FragmentStartScreenBinding by viewBinding()
    private lateinit var binding: FragmentStartScreenBinding

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
                    presenter.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(parentFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    }

    private fun initRecycler() {
       binding.rvStartScreenFragment.apply {
           adapter= this@StartScreenFragment.adapter
           layoutManager = LinearLayoutManager(requireContext())
       }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

    override fun createPresenter(): Presenter<AppState, View> {
        return MainPresenterImpl()
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
                    //  binding.progressBarRound.visibility = android.view.View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = android.view.View.GONE
                    //   binding.progressBarRound.visibility = android.view.View.VISIBLE
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
            presenter.getData("hi", true)
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