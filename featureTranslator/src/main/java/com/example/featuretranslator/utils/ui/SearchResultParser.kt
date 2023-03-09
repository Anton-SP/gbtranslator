package com.example.featuretranslator.utils.ui

import com.example.featuretranslator.data.AppState
import com.example.featuretranslator.data.Meanings
import com.example.featuretranslator.data.Word

fun parseSearchResults(data: com.example.featuretranslator.data.AppState): com.example.featuretranslator.data.AppState {
    val newSearchResults = arrayListOf<com.example.featuretranslator.data.Word>()
    when (data) {
        is com.example.featuretranslator.data.AppState.Success -> {
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResults)
                }
            }
        }
        else -> {}
    }

    return com.example.featuretranslator.data.AppState.Success(newSearchResults)
}

private fun parseResult(dataModel: com.example.featuretranslator.data.Word, newDataModels: ArrayList<com.example.featuretranslator.data.Word>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<com.example.featuretranslator.data.Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(
                    com.example.featuretranslator.data.Meanings(
                        meaning.translation,
                        meaning.imageUrl
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(com.example.featuretranslator.data.Word(dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<com.example.featuretranslator.data.Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}