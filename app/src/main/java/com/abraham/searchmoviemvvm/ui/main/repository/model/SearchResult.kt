package com.abraham.searchmoviemvvm.ui.main.repository.model

data class SearchResult(
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<ResultsItem?>? = null,
	val totalResults: Int? = null
)
