package com.example.featuretranslator.view.translator


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.featuretranslator.R
import com.example.featuretranslator.data.AppState
import com.example.featuretranslator.databinding.FragmentTranslatorScreenBinding
import com.example.featuretranslator.utils.isOnline
import com.example.featuretranslator.utils.ui.navigate
import com.example.featuretranslator.utils.ui.viewById
import com.example.featuretranslator.view.base.BaseFragment
import com.example.featuretranslator.view.translator.adapter.TranslatorScreenAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel


class TranslatorScreenFragment : BaseFragment<AppState, TranslatorScreenInteractor>() {

    private val translatorRecyclerView by viewById<RecyclerView>(R.id.rv_start_screen_fragment)
    private val translatorFAB by viewById<FloatingActionButton>(R.id.fab_search)
    /*
    Оставил binding хотя с делегатом можно было бы и без него.
    Пример с делегатом view наглядный, но вводить для работы с ним на каждый элемент
    свою переменную я считаю избыточным/ binidng в этом плане делает то что должен, избавляет нас от этого
     */
    private lateinit var binding: FragmentTranslatorScreenBinding
    override lateinit var model: TranslatorScreenViewModel

    private val adapter: TranslatorScreenAdapter by lazy {
        TranslatorScreenAdapter(
            onClick = {
                navigate(
                    actionId = R.id.action_translatorScreenFragment_to_detailScreenFragment,
                    data = it
                )
            },
            mutableListOf()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        binding = FragmentTranslatorScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecycler()

        translatorFAB.setOnClickListener {
            val translatorDialogFragment = TranslatorDialogFragment.newInstance()
            translatorDialogFragment.setOnSearchClickListener(object :
                TranslatorDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    isNetworkAvailable = isOnline(requireActivity().applicationContext)
                    if (isNetworkAvailable) {
                        model.getData(searchWord, isNetworkAvailable)
                    } else {
                        showNoInternetConnectionDialog()
                    }
                }
            })
            translatorDialogFragment.show(parentFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    private fun initViewModel() {
        if (translatorRecyclerView.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: TranslatorScreenViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(viewLifecycleOwner, { renderData(it) })
    }


    private fun initRecycler() {
        translatorRecyclerView.apply {
            adapter = this@TranslatorScreenFragment.adapter
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