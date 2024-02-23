package com.example.githubcommits.data.repository

import androidx.paging.PagingData
import com.example.githubcommits.domain.response.GithubCommitsRemoteResponseItem
import com.example.githubcommits.domain.wrapper.DataState
import kotlinx.coroutines.flow.Flow

interface GithubApiRepository {

    suspend fun getCommits(owner: String?, repo: String?): Flow<PagingData<GithubCommitsRemoteResponseItem>>
}