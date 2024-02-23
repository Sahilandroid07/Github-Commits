package com.example.githubcommits.presentation.fragments

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubcommits.data.repository.GithubApiRepository
import com.example.githubcommits.domain.response.GithubCommitsRemoteResponseItem
import com.example.githubcommits.domain.wrapper.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubCommitsViewModel @Inject constructor(
    private val repository: GithubApiRepository
) : ViewModel() {

    private val _commitsLiveData = MutableLiveData<PagingData<GithubCommitsRemoteResponseItem>>()
    val commitsLiveData: LiveData<PagingData<GithubCommitsRemoteResponseItem>> = _commitsLiveData

    fun getCommits(owner: String?, repo: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCommits(owner, repo).cachedIn(viewModelScope).collect {
                _commitsLiveData.postValue(it)
            }
        }
    }
}