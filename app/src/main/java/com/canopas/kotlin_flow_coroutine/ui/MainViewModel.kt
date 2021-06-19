package com.canopas.kotlin_flow_coroutine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.canopas.kotlin_flow_coroutine.data.SearchResponse
import com.canopas.kotlin_flow_coroutine.rest.GithubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class MainViewModel @Inject constructor(val githubService: GithubService) : ViewModel() {


    val query = MutableLiveData<String>()
    val repo = MutableLiveData<SearchResponse>()

    fun fetchData() {
        viewModelScope.launch {
            query.asFlow()
                .debounce(500)
                .filter {
                    it.trim().isEmpty().not()
                }.distinctUntilChanged()
                .flatMapLatest {
                    searchRepo(it)
                }.collect {
                    repo.value = it.body()
                }
        }

    }

    private suspend fun searchRepo(query: String) = flow {
        emit(githubService.searchRepos(query))
    }

}