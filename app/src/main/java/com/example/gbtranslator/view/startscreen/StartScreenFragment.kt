package com.example.gbtranslator.view.startscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbtranslator.R
import com.example.gbtranslator.app.App
import com.example.gbtranslator.data.AppState
import com.example.gbtranslator.databinding.FragmentStartScreenBinding
import com.example.gbtranslator.utils.isOnline
import com.example.gbtranslator.view.base.BaseFragment
import com.example.gbtranslator.view.startscreen.adapter.StartScreenAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class StartScreenFragment : BaseFragment<AppState, StartScreenInteractor>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentStartScreenBinding
    override lateinit var model: StartScreenViewModel

    private val observer = Observer<AppState> { renderData(it) }

    private val adapter: StartScreenAdapter by lazy {
        StartScreenAdapter(
            onClick = {
                Toast.makeText(requireActivity(), it.text, Toast.LENGTH_SHORT).show()
            },
            mutableListOf()
        )
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as App).dispatchingAndroidInjector.inject(this)
        super.onAttach(context)
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

        model = viewModelFactory.create(StartScreenViewModel::class.java)

        model.subscribe().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })

        initRecycler()

        binding.fabSearch.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    isNetworkAvailable = isOnline(requireActivity().applicationContext)
                    if (isNetworkAvailable) {
                        model.getData(searchWord, isNetworkAvailable)
                    } else {
                        showNoInternetConnectionDialog()
                    }
                }
            })
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
                if (dataModel.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_tittle_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
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
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
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