package com.januar.finalgithubuser.model.data

data class DataSource(
    val incomplete_results: Boolean,
    val items: List<DataSourceItem>,
    val total_count: Long

)