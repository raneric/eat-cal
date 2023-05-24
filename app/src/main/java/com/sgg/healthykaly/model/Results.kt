package com.sgg.healthykaly.model

sealed class SummaryResults() {
    data class Success(val summary: RecipeSummaryModel) : SummaryResults()
    data class Loading(val isLoading: Boolean = true) : SummaryResults()
    data class Error(val message: String?) : SummaryResults()
}