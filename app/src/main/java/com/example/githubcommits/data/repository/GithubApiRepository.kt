package com.example.githubcommits.data.repository

import androidx.paging.PagingData
import com.example.githubcommits.domain.response.GithubCommitsRemoteResponseItem
import com.example.githubcommits.domain.wrapper.DataState
import kotlinx.coroutines.flow.Flow

interface GithubApiRepository {

    suspend fun getCommits(
        owner: String?,
        repo: String?,
        author: String?,
        committer: String?,
        since: String?,
        until: String?
    ): Flow<PagingData<GithubCommitsRemoteResponseItem>>
}