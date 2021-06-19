package com.canopas.kotlin_flow_coroutine.data

data class SearchResponse(
    val incomplete_results: Boolean,
    val items: List<Repository>,
    val total_count: Int
)

data class Repository(

    val created_at: String,
    val default_branch: String,
    val description: String,
    val fork: Boolean,
    val forks_count: Int,
    val full_name: String,
    val homepage: String,
    val html_url: String,
    val id: Int,
    val language: String,
    val master_branch: String,
    val name: String,
    val node_id: String,
    val open_issues_count: Int,
    val owner: Owner,
    val `private`: Boolean,
    val pushed_at: String,
    val score: Double,
    val size: Int,
    val stargazers_count: Int,
    val updated_at: String,
    val url: String,
    val watchers_count: Int
)

data class Owner(
    val avatar_url: String,
    val gravatar_id: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val received_events_url: String,
    val type: String,
    val url: String
)